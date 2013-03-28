package me.delocaz.cmdx.commands;

import me.delocaz.cmdx.CmdX;
import me.delocaz.cmdx.api.CXCommand;

import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;

public class CXServer extends CXCommand {
	@Override
	public boolean executeCommand(CommandSender cs, String[] args) {
		if (args.length == 0) {
			cs.sendMessage(getAPI().getLanguageManager().getLang("notEnoughArguments"));
			return false;
		}
		switch (args[0]) {
		case "stop":
			int delay = 10;
			if (args.length > 1) {
				if (StringUtils.isNumeric(args[1])) {
					delay = Integer.parseInt(args[1]);
				}
			}
			Bukkit.broadcastMessage(getAPI().getLanguageManager().getLang("serverStop", delay+"", cs.getName()));
			Bukkit.getScheduler().scheduleSyncDelayedTask(CmdX.getInstance(), new Runnable() {
				public void run() {
					Bukkit.shutdown();
				}
			}, delay*20L);
			break;
		}
		return true;
	}
}
