package me.kaliber.expansions.changeoutput

import me.clip.placeholderapi.expansion.PlaceholderExpansion
import me.clip.placeholderapi.PlaceholderAPI
import org.bukkit.OfflinePlayer
import org.bukkit.ChatColor

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
        return "1.0"
    }

    override fun onRequest(player: OfflinePlayer?, params: String): String?
    {
        val (option, input, checkAgainst, outputIfTrue, outputIfFalse) = params.split(',')
            .map { PlaceholderAPI.setBracketPlaceholders(player, it) }
            .takeIf { it.size >= 5 } ?: return null

        val inputNum = input.toDoubleOrNull() ?: 0.0
        val checkNum = checkAgainst.toDoubleOrNull() ?: 0.0

        return when (option.lowercase())
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

}
