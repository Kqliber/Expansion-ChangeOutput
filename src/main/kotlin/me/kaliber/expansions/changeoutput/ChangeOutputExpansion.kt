package me.kaliber.expansions.changeoutput

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.OfflinePlayer
import org.bukkit.ChatColor

const val INPUT_MATCHER = "_input:"
const val MATCHER_MATCHER = "_matcher:"
const val IFMATCH_MATCHER = "_ifmatch:"
const val ELSE_MATCHER = "_else:"

class ChangeOutputExpansion : PlaceholderExpansion()
{

    override fun getIdentifier(): String
    {
        return "changeoutput"
    }

    override fun getAuthor(): String
    {
        return "Kaliber"
    }

    override fun getVersion(): String
    {
        return "1.2"
    }

    override fun onRequest(player: OfflinePlayer?, params: String): String?
    {
        if (!params.contains(INPUT_MATCHER) ||
            !params.contains(MATCHER_MATCHER) ||
            !params.contains(IFMATCH_MATCHER) ||
            !params.contains(ELSE_MATCHER))
        {
            return null
        }

        val parsed = PlaceholderAPI.setBracketPlaceholders(player, params)

        val options = parsed.substringBefore(INPUT_MATCHER).lowercase().split(',')
        if (options.size > 3) return null

        val input = parsed.substringAfter(INPUT_MATCHER).substringBefore(MATCHER_MATCHER)
        val matcher = parsed.substringAfter(MATCHER_MATCHER).substringBefore(IFMATCH_MATCHER)
        val outputIfTrue = parsed.substringAfter(IFMATCH_MATCHER).substringBefore(ELSE_MATCHER)
        val outputIfFalse = parsed.substringAfter(ELSE_MATCHER)

        val inputNum = input.toDoubleOrNull() ?: 0.0
        val checkNum = matcher.toDoubleOrNull() ?: 0.0

        if (options.size == 1)
        {
            return when (options[0].lowercase())
            {
                "ignorecase" -> if (input.equals(matcher, true)) outputIfTrue else outputIfFalse
                "ignorecolor" -> if (ChatColor.stripColor(input) == matcher) outputIfTrue else outputIfFalse
                "contains" -> if (matcher in input) outputIfTrue else outputIfFalse

                ">" -> if (inputNum > checkNum) outputIfTrue else outputIfFalse
                ">=" -> if (inputNum >= checkNum) outputIfTrue else outputIfFalse
                "<" -> if (inputNum < checkNum) outputIfTrue else outputIfFalse
                "<=" -> if (inputNum <= checkNum) outputIfTrue else outputIfFalse

                else -> if (input == matcher) outputIfTrue else outputIfFalse
            }
        }

        var newInput = input
        var newMatcher = matcher

        if (options.contains("ignorecase"))
        {
            newInput = newInput.lowercase()
            newMatcher = newMatcher.lowercase()
        }

        if (options.contains("ignorecolor"))
        {
            newInput = ChatColor.stripColor(newInput) ?: ""
            newMatcher = ChatColor.stripColor(newMatcher) ?: ""
        }

        if (options.contains("contains"))
        {
            return if (newMatcher in newInput) outputIfTrue else outputIfFalse
        }

        return if (newInput == newMatcher) outputIfTrue else outputIfFalse
    }
}
