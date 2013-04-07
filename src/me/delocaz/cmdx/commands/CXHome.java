package me.delocaz.cmdx.commands;

import me.delocaz.cmdx.api.CXCommand;
import me.delocaz.cmdx.util.CXUtils;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CXHome extends CXCommand {
	@Override
	public boolean executeCommand(CommandSender cs, String[] args) {
		Player p = (Player) cs;
		String s = (String) getAPI().getPlayerDataManager().getData(p, "home");
		if (s != null) {
			Location l = null;
			try {
				l = CXUtils.parseLocation(s);
				p.teleport(l);
				cs.sendMessage(getAPI().getLanguageManager().getLang("homeTp"));
			} catch (IllegalArgumentException e) {
				cs.sendMessage(getAPI().getLanguageManager().getLang("noHome"));
				return false;
			}
		} else {
			cs.sendMessage(getAPI().getLanguageManager().getLang("noHome"));
			return false;
		}
		return true;
	}
}