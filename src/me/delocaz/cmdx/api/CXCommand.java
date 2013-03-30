package me.delocaz.cmdx.api;

import me.delocaz.cmdx.CmdX;
import me.delocaz.cmdx.util.CXUtils;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Listener;

public abstract class CXCommand implements CommandExecutor, Listener {

    private String cmdname;
    private boolean playeronly;

    public CXCommand() {
        init();
    }

    @Override()
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        if (!CXUtils.isPlayer(cs)) {
            if (playeronly) {
                cs.sendMessage(getAPI().getLanguageManager().getLang("noConsole"));
            }
        }
        boolean b = executeCommand(cs, strings);
        if (!b) {
            cs.sendMessage(getAPI().getLanguageManager().getLang("usage", cmnd.getUsage()));
        }
        return true;
    }

    public abstract boolean executeCommand(CommandSender cs, String[] args);

    /**
     * This method is executed on object creation
     */
    public void init() {
    }

    /**
     * Gets this command's name
     *
     * @return This command's name
     */
    public String getCommandName() {
        return cmdname;
    }

    /**
     * Sets this command's name
     *
     * @param cmdname The command name to set
     */
    public void setCommandName(String cmdname) {
        this.cmdname = cmdname;
    }

    /**
     * Sets if this command is for players only
     *
     * @param playeronly true if this command is players only
     */
    public void setPlayerOnly(boolean playeronly) {
        this.playeronly = playeronly;
    }

    /**
     * Gets if this command is for players only
     *
     * @return true if this command is players only
     */
    public boolean getPlayerOnly() {
        return playeronly;
    }

    /**
     * Gets CX's API
     *
     * @return CX's API
     */
    public CXAPI getAPI() {
        return CmdX.getInstance().getAPI();
    }
}
