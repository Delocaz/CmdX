package me.delocaz.cmdx.commands;

import me.delocaz.cmdx.api.CXCommand;
import me.delocaz.cmdx.util.CXUtils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

public class CXFreeze extends CXCommand {
	@Override
	public boolean executeCommand(CommandSender cs, String[] args) {
		Player ptarget = null ;
		boolean target;
		if (args.length == 0) {
			if (!CXUtils.isPlayer(cs)) {
				cs.sendMessage(getAPI().getLanguageManager().getLang("consoleSpecifyPlayer"));
				return false;
			} else {
				ptarget = (Player) cs;
			}
		} else {
			if (CXUtils.getPlayer(args[0]) == null) {
				cs.sendMessage(getAPI().getLanguageManager().getLang("playerNotFound", args[0]));
				return false;
			} else {
				ptarget = CXUtils.getPlayer(args[0]);
			}
		}
		Object otarget = getAPI().getPlayerDataManager().getData((Player) cs, "isFrozen");
		if (otarget == null) {
			target = false;
		} else {
			target = (boolean) otarget;
		}
		target = !target;
		getAPI().getPlayerDataManager().putData(ptarget, "isFrozen", target);
		if (target) {
			cs.sendMessage(getAPI().getLanguageManager().getLang("youHaveFrozen", ptarget.getDisplayName()));
		} else {
			cs.sendMessage(getAPI().getLanguageManager().getLang("youHaveUnfrozen", ptarget.getDisplayName()));
		}
		return true;
	}
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		Boolean isFrozen = (Boolean) getAPI().getPlayerDataManager().getData(e.getPlayer(), "isFrozen");
		if (isFrozen != null && isFrozen) {
			e.setCancelled(true);
			e.getPlayer().teleport(e.getFrom());
			e.getPlayer().sendMessage(getAPI().getLanguageManager().getLang("youAreFrozen"));
		}
	}
}
