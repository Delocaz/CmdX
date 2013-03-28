package me.delocaz.cmdx;

import me.delocaz.cmdx.api.CXAPI;
import me.delocaz.cmdx.commands.CXServer;
import me.delocaz.cmdx.commands.CXTP;
import me.delocaz.cmdx.commands.CXTime;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class CmdX extends JavaPlugin {

    private CXAPI api;

    public static CmdX getInstance() {
        return (CmdX) Bukkit.getPluginManager().getPlugin("CmdX");
    }
    public void onEnable() {
        api = new CXAPI();
        getAPI().getCommandManager().registerCommand(new CXTP(), "tp", true);
        getAPI().getCommandManager().registerCommand(new CXServer(), "server", false);
        getAPI().getCommandManager().registerCommand(new CXTime(), "time", false);
    }
    public void onDisable() {
        getAPI().getPlayerDataManager().save();
    }
    public CXAPI getAPI() {
        return api;
    }
}
