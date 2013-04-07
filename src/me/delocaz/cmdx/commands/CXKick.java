package me.delocaz.cmdx.commands;

import me.delocaz.cmdx.api.CXCommand;
import me.delocaz.cmdx.util.CXUtils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CXKick extends CXCommand {
	@Override
	public boolean executeCommand(CommandSender cs, String[] args) {
		if (args.length == 0) {
			cs.sendMessage(getAPI().getLanguageManager().getLang("notEnoughArguments"));
			return false;
		} else if (args.length == 1) {
			if (CXUtils.getPlayer(args[0]) != null) {
				Player p = CXUtils.getPlayer(args[0]);
				p.kickPlayer(getAPI().getLanguageManager().getLang("defaultKick"));
				CXUtils.broadcast(getAPI().getLanguageManager().getLang("kickBroadcastNoReason", cs.getName(), p.getName()));
			} else {
				cs.sendMessage(getAPI().getLanguageManager().getLang("playerNotFound", args[0]));
	            return false;
			}
		} else {
			if (CXUtils.getPlayer(args[0]) != null) {
				Player p = CXUtils.getPlayer(args[0]);
				p.kickPlayer(getAPI().getLanguageManager().getLang("kickMessage", CXUtils.assemble(args, 1)));
				CXUtils.broadcast(getAPI().getLanguageManager().getLang("kickBroadcast", cs.getName(), p.getName(), CXUtils.assemble(args, 1)));
			} else {
				cs.sendMessage(getAPI().getLanguageManager().getLang("playerNotFound", args[0]));
	            return false;
			}
		}
		return true;
	}
}
