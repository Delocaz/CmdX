package me.delocaz.cmdx.commands;

import me.delocaz.cmdx.api.CXCommand;
import me.delocaz.cmdx.api.CXUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CXTP extends CXCommand {
    @Override
    public boolean executeCommand(CommandSender cs, String[] args) {
        if (args.length == 0) {
            return false;
        }
        Player pp = CXUtils.getPlayer(args[0]);
        if (pp == null) {
            cs.sendMessage(getAPI().getLanguageManager().getLang("playerNotFound", args[0]));
            return false;
        }
        Player p = (Player) cs;
        p.teleport(pp);
        cs.sendMessage(getAPI().getLanguageManager().getLang("tpSuccess", pp.getDisplayName()));
        return true;
    }
}
