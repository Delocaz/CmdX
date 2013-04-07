package me.delocaz.cmdx.commands;

import me.delocaz.cmdx.api.CXCommand;
import me.delocaz.cmdx.util.CXUtils;

import org.apache.commons.lang.StringUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class CXSlap extends CXCommand {
	@Override
	public boolean executeCommand(CommandSender cs, String[] args) {
		int power = 3;
		Player target = null;
		if (args.length == 0) {
			if (cs instanceof Player) {
				target = (Player) cs;
			} else {
				cs.sendMessage(getAPI().getLanguageManager().getLang("consoleSpecifyPlayer"));
				return false;
			}
		} else if (args.length == 1) {
			if (StringUtils.isNumeric(args[0])) {
				power = Integer.parseInt(args[0]);
				if (cs instanceof Player) {
					target = (Player) cs;
				} else {
					cs.sendMessage(getAPI().getLanguageManager().getLang("consoleSpecifyPlayer"));
					return false;
				}
			} else if (CXUtils.getPlayer(args[0]) != null) {
				target = CXUtils.getPlayer(args[0]);
			} else {
				cs.sendMessage(getAPI().getLanguageManager().getLang("playerNotFound", args[0]));
	            return false;
			}
		} else {
			if (StringUtils.isNumeric(args[1])) {
				power = Integer.parseInt(args[1]);
			} else {
				cs.sendMessage(getAPI().getLanguageManager().getLang("notANumber", args[1]));
				return false;
			}
			if (CXUtils.getPlayer(args[0]) != null) {
				target = CXUtils.getPlayer(args[0]);
			} else {
				cs.sendMessage(getAPI().getLanguageManager().getLang("playerNotFound", args[0]));
	            return false;
			}
		}
		Vector v = target.getLocation().getDirection();
		v.setY(0.2);
		v.add(Vector.getRandom().multiply(0.5));
		v.multiply(power);
		target.setVelocity(v);
		cs.sendMessage(getAPI().getLanguageManager().getLang("successSlapped", target.getName(), power+""));
		return true;
	}
}
