package me.delocaz.cmdx.api;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CXUtils {
    /**
     * Checks if a CommandSender is an instance of a player
     * @param cs The CommandSender in question
     * @return true if CommandSender is a player
     */
    public static boolean isPlayer(CommandSender cs) {
        return cs instanceof Player;
    }
    /**
     * Replaces color codes with actual colors
     * @param str The string to colorize
     * @return The colorized string
     */
    public static String colorize(String str) {
        return ChatColor.translateAlternateColorCodes('&', str);
    }
    /**
     * Matches a player from a string
     * @param p The string to get the player from
     * @return The player
     */
    public static Player getPlayer(String p) {
        return Bukkit.getPlayer(p);
    }
}
