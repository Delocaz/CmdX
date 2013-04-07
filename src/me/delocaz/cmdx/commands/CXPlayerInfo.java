package me.delocaz.cmdx.commands;

import java.util.Date;

import me.delocaz.cmdx.CmdX;
import me.delocaz.cmdx.api.CXCommand;
import me.delocaz.cmdx.util.CXUtils;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CXPlayerInfo extends CXCommand {
	public void init() {
		Bukkit.getScheduler().runTaskTimer(CmdX.getInstance(), new Runnable() {
			public void run() {
				for (Player p : Bukkit.getOnlinePlayers()) {
					collect(p);
				}
			}
			public void collect(Player p) {
				getAPI().getPlayerDataManager().putData(p, "lastSeen", System.currentTimeMillis());
				getAPI().getPlayerDataManager().putData(p, "lastSeenAt", CXUtils.locationToString(p.getLocation()));
				getAPI().getPlayerDataManager().putData(p, "ip", (String) (p.getAddress().getAddress().getCanonicalHostName()+" / "+p.getAddress().getAddress().getHostAddress()));
			}
		}, 1L, 10L);
	}
	public boolean executeCommand(CommandSender cs, String[] args) {
		OfflinePlayer target = null;
		if (args.length == 0) {
			if (cs instanceof Player) {
				target = (Player) cs;
			} else {
				cs.sendMessage(getAPI().getLanguageManager().getLang("consoleSpecifyPlayer"));
				return false;
			}
		} else {
			if (CXUtils.getPlayer(args[0]) != null) {
				target = CXUtils.getPlayer(args[0]);
			} else if (CXUtils.getOfflinePlayer(args[0]) != null) {
				cs.sendMessage(getAPI().getLanguageManager().getLang("playerNotFound", args[0]));
				return false;
			}
		}
		Object lastseen = getAPI().getPlayerDataManager().getData(target, "lastSeen");
		Object ip = getAPI().getPlayerDataManager().getData(target, "ip");
		Object lastseenat = getAPI().getPlayerDataManager().getData(target, "lastSeenAt");
		cs.sendMessage(getAPI().getLanguageManager().getLang("pInfoFor", target.getName()));
		if (target.isOnline()) {
			cs.sendMessage(getAPI().getLanguageManager().getLang("pInfoNick", ((Player) target).getDisplayName()));
		}
		if (lastseen != null) {
			cs.sendMessage(getAPI().getLanguageManager().getLang("pInfoLastSeen", new Date((Long) lastseen).toString()));
		}
		if (ip != null) {
			cs.sendMessage(getAPI().getLanguageManager().getLang("pInfoIp", (String) ip));
		}
		if (lastseenat != null) {
			cs.sendMessage(getAPI().getLanguageManager().getLang("pInfoLastSeenAt", (String) lastseenat));
		}
		cs.sendMessage(getAPI().getLanguageManager().getLang(target.isOp() ? "pInfoOpYes" : "pInfoOpNo"));
		if (target.isOnline()) {
			Player p = (Player) target;
			cs.sendMessage(getAPI().getLanguageManager().getLang("pInfoGamemode", p.getGameMode().toString().toLowerCase()));
			Object isFrozen = getAPI().getPlayerDataManager().getData(target, "isFrozen");
			if (isFrozen == null) {
				isFrozen = Boolean.FALSE;
			}
			cs.sendMessage(getAPI().getLanguageManager().getLang((Boolean) isFrozen ? "pInfoFrozenYes" : "pInfoFrozenNo"));
		}
		return true;
	}
}
