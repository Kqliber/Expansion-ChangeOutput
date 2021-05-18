package me.kaliber.expansions.changeoutput

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.OfflinePlayer
import org.bukkit.ChatColor

class ChangeOutputExpansion : PlaceholderExpansion()
{

    private val separator = Regex("(?<!\\\\)_")

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
        return "1.1"
    }

    override fun onRequest(player: OfflinePlayer?, params: String): String?
    {
        val (opts, input, checkAgainst, outputIfTrue, outputIfFalse) = PlaceholderAPI.setBracketPlaceholders(player, params)
            .split(separator)
            .map { it.replace("\\_", "_") }
            .takeIf { it.size >= 5 } ?: return null

        val options = opts.lowercase().split(",")
        if (options.size > 3) return null

        val inputNum = input.toDoubleOrNull() ?: 0.0
        val checkNum = checkAgainst.toDoubleOrNull() ?: 0.0

        if (options.size == 1)
        {
            return when (options[0].lowercase())
            {
                "ignorecase" -> if (input.equals(checkAgainst, true)) outputIfTrue else outputIfFalse
                "ignorecolor" -> if (ChatColor.stripColor(input) == checkAgainst) outputIfTrue else outputIfFalse
                "contains" -> if (checkAgainst in input) outputIfTrue else outputIfFalse

                ">" -> if (inputNum > checkNum) outputIfTrue else outputIfFalse
                ">=" -> if (inputNum >= checkNum) outputIfTrue else outputIfFalse
                "<" -> if (inputNum < checkNum) outputIfTrue else outputIfFalse
                "<=" -> if (inputNum <= checkNum) outputIfTrue else outputIfFalse

                else -> if (input == checkAgainst) outputIfTrue else outputIfFalse
            }
        }

        var newInput = input
        var newCheckAgainst = checkAgainst

        if (options.contains("ignorecase"))
        {
            newInput = newInput.lowercase()
            newCheckAgainst = newCheckAgainst.lowercase()
        }

        if (options.contains("ignorecolor"))
        {
            newInput = ChatColor.stripColor(newInput) ?: ""
            newCheckAgainst = ChatColor.stripColor(newCheckAgainst) ?: ""
        }

        if (options.contains("contains"))
        {
            return if (newCheckAgainst in newInput) outputIfTrue else outputIfFalse
        }

        return if (newInput == newCheckAgainst) outputIfTrue else outputIfFalse
    }
}
