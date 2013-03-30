package me.delocaz.cmdx.commands;

import me.delocaz.cmdx.api.CXCommand;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CXWeather extends CXCommand {
	@Override
	public boolean executeCommand(CommandSender cs, String[] args) {
		World w = null;
		if (args.length > 0) {
			w = Bukkit.getWorld(args[args.length-1]);
		}
		if (w == null) {
			if (cs instanceof Player) {
				w = ((Player) cs).getWorld();
			} else {
				cs.sendMessage(getAPI().getLanguageManager().getLang("consoleSpecifyWorld"));
				return false;
			}
		}
		boolean toSet = !w.hasStorm();
		if (args.length > 0) {
			switch (args[0]) {
			case "on":
			case "storm":
			case "rain":
			case "true":
				toSet = true;
				break;
			case "off":
			case "clear":
			case "sun":
			case "false":
				toSet = false;
				break;
			}
		}
		w.setStorm(toSet);
		cs.sendMessage(getAPI().getLanguageManager().getLang("weatherSet", toSet ? "on" : "off"));
		return true;
	}
}
