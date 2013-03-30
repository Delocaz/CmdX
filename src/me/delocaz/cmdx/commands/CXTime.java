package me.delocaz.cmdx.commands;

import me.delocaz.cmdx.api.CXCommand;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CXTime extends CXCommand {
	@Override
	public boolean executeCommand(CommandSender cs, String[] args) {
		if (args.length == 0) {
			cs.sendMessage(getAPI().getLanguageManager().getLang("notEnoughArguments"));
			return false;
		}
		int time = 2000;
		switch (args[0]) {
		case "night":
			time = 14000;
			break;
		case "noon":
			time = 6000;
			break;
		case "midnight":
			time = 18000;
			break;
		}
		if (StringUtils.isNumeric(args[0])) {
			time = Integer.parseInt(args[0]);
		}
		if (cs instanceof Player) {
			for (World w : Bukkit.getWorlds()) {
				w.setTime(time);
			}
		} else {
			Player p = (Player) cs;
			p.getWorld().setTime(time);
		}
		cs.sendMessage(getAPI().getLanguageManager().getLang("weatherSet", time+""));
		return true;
	}
}
