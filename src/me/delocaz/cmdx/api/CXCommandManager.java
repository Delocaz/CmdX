package me.delocaz.cmdx.api;

import org.bukkit.Bukkit;

import me.delocaz.cmdx.CmdX;

public class CXCommandManager {

    /**
     * Registers a CXCommand
     *
     * @param cmd The command in question
     * @param cmdname The name of the command
     * @param playeronly Whether the command is for players only
     */
    public void registerCommand(CXCommand cmd, String cmdname, boolean playeronly) {
        cmd.setCommandName(cmdname);
        cmd.setPlayerOnly(playeronly);
        try {
            CmdX.getInstance().getCommand("cx_" + cmd.getCommandName()).setExecutor(cmd);
        } catch (NoClassDefFoundError e) {
        }
        Bukkit.getPluginManager().registerEvents(cmd, CmdX.getInstance());
    }
}
