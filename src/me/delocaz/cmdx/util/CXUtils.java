package me.delocaz.cmdx.util;

import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
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
	/**
	 * Matches a player from a string, also works with offline players
	 * @param p The string to get the player from
	 * @return The player
	 */
	public static OfflinePlayer getOfflinePlayer(String p) {
		return Bukkit.getOfflinePlayer(p);
	}
	/**
	 * Gets all the keys from a Map with a specified value (Courtesy of Vitalii Fedorenko of StackOverflow)
	 * @param map The map to check
	 * @param value The value to check for
	 * @return A Set of keys which have the corresponding value
	 */
	public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
		Set<T> keys = new HashSet<T>();
		for (Entry<T, E> entry : map.entrySet()) {
			if (value.equals(entry.getValue())) {
				keys.add(entry.getKey());
			}
		}
		return keys;
	}
	/**
	 * Gets a gamemode from a string
	 * @param q The string to parse
	 * @return The gamemode which corresponds to the string
	 */
	public static GameMode parseGameMode(String q) {
		switch (q) {
		case "0":
		case "survival":
			return GameMode.SURVIVAL;
		case "1":
		case "creative":
			return GameMode.CREATIVE;
		case "2":
		case "adventure":
			return GameMode.ADVENTURE;
		}
		return null;
	}
	/**
	 * Inverts a gamemode
	 * @param gm The gamemode to invert
	 * @return The opposite of the specified gamemode
	 */
	public static GameMode invertGameMode(GameMode gm) {
		if (gm == GameMode.CREATIVE) {
			return GameMode.SURVIVAL;
		} else {
			return GameMode.CREATIVE;
		}
	}
	/**
	 * Broadcasts a message to the server, including [CX]
	 * @param s The message to broadcast
	 */
	public static void broadcast(String s) {
		Bukkit.broadcastMessage(ChatColor.BLUE+"["+ChatColor.GOLD+"CX"+ChatColor.BLUE+"] "+s);
	}
	/**
	 * Assembles a string array into a string
	 * @param args The array to assemble
	 * @param start The 
	 * @return
	 */
	public static String assemble(String[] args, int start) {
		String s = "";
		for (;start<args.length;start++) {
			s = s + args[start] + " ";
		}
		return s.substring(0, s.length()-2);
	}
	/**
	 * Parses a String into a Location
	 * @param location The string to parse
	 * @return The location
	 */
	public static Location parseLocation(String location) {
        String[] coords = location.split(",");
        if (coords.length < 4){
            throw new IllegalArgumentException("Unable to parse Location: " + location);
        }
        World world = Bukkit.getWorld(coords[0]);
        if (world == null){
            throw new IllegalArgumentException("Unable to find world: "+ coords[0]);
        }
        double x = Double.parseDouble(coords[1]);
        double y = Double.parseDouble(coords[2]);
        double z = Double.parseDouble(coords[3]);
        return new Location(world, x, y, z);
       
    }
	/**
	 * Turns a location into a string
	 * @param location The location to turn into a string
	 * @return The string
	 */
    public static String locationToString(Location location) {
        StringBuilder s = new StringBuilder();
        s.append(location.getWorld().getName());
        s.append(",");
        s.append(location.getBlockX());
        s.append(",");
        s.append(location.getBlockY());
        s.append(",");
        s.append(location.getBlockZ());
        return s.toString();
    }
}
