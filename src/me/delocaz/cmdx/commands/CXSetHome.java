package me.delocaz.cmdx.commands;

import me.delocaz.cmdx.api.CXCommand;
import me.delocaz.cmdx.util.CXUtils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CXSetHome extends CXCommand {
	@Override
	public boolean executeCommand(CommandSender cs, String[] args) {
		Player p = (Player) cs;
		getAPI().getPlayerDataManager().putData(p, "home", CXUtils.locationToString(p.getLocation()));
		cs.sendMessage(getAPI().getLanguageManager().getLang("homeSet"));
		return true;
	}
}
