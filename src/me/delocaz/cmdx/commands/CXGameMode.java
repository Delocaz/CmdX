package me.delocaz.cmdx.commands;

import me.delocaz.cmdx.api.CXCommand;
import me.delocaz.cmdx.util.CXUtils;

import org.bukkit.GameMode;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CXGameMode extends CXCommand {
	@Override
	public boolean executeCommand(CommandSender cs, String[] args) {
		GameMode target = null;
		Player ptarget = null;
		if (args.length == 0) {
			if (cs instanceof Player) {
				Player p = (Player) cs;
				target = CXUtils.invertGameMode(p.getGameMode());
				ptarget = p;
			} else {
				cs.sendMessage(getAPI().getLanguageManager().getLang("consoleSpecifyPlayer"));
				return false;
			}
		} else if (args.length == 1) {
			target = CXUtils.parseGameMode(args[0]);
			ptarget = CXUtils.getPlayer(args[0]);
			if (ptarget == null) {
				if (!CXUtils.isPlayer(cs)) {
					cs.sendMessage(getAPI().getLanguageManager().getLang("playerNotFound", args[0]));
					return false;
				} else {
					ptarget = (Player) cs;
				}
			} else if (target == null) {
				target = CXUtils.invertGameMode(ptarget.getGameMode());
			}
		} else if (args.length >= 2) {
			target = CXUtils.parseGameMode(args[1]);
			ptarget = CXUtils.getPlayer(args[0]);
			if (target == null) {
				cs.sendMessage(getAPI().getLanguageManager().getLang("invalidGameMode", args[0]));
				return false;
			}
			if (ptarget == null) {
				cs.sendMessage(getAPI().getLanguageManager().getLang("playerNotFound", args[1]));
				return false;
			}
		}
		ptarget.setGameMode(target);
		cs.sendMessage(getAPI().getLanguageManager().getLang("gameModeSet", ptarget.getDisplayName(), target.toString().toLowerCase()));
		return true;
	}
}
