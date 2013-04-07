package me.delocaz.cmdx;

import me.delocaz.cmdx.api.CXAPI;
import me.delocaz.cmdx.commands.CXFreeze;
import me.delocaz.cmdx.commands.CXGameMode;
import me.delocaz.cmdx.commands.CXKick;
import me.delocaz.cmdx.commands.CXPlayerInfo;
import me.delocaz.cmdx.commands.CXServer;
import me.delocaz.cmdx.commands.CXSlap;
import me.delocaz.cmdx.commands.CXTP;
import me.delocaz.cmdx.commands.CXTPHere;
import me.delocaz.cmdx.commands.CXTime;
import me.delocaz.cmdx.commands.CXWeather;
import me.delocaz.cmdx.util.CXUtils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class CmdX extends JavaPlugin implements Listener {

    private CXAPI api;

    public static CmdX getInstance() {
        return (CmdX) Bukkit.getPluginManager().getPlugin("CmdX");
    }
    public void onEnable() {
    	Bukkit.getPluginManager().registerEvents(this, this);
        api = new CXAPI();
        getAPI().getCommandManager().registerCommand(new CXTP(), "tp", true);
        getAPI().getCommandManager().registerCommand(new CXServer(), "server", false);
        getAPI().getCommandManager().registerCommand(new CXTime(), "time", false);
        getAPI().getCommandManager().registerCommand(new CXWeather(), "weather", false);
        getAPI().getCommandManager().registerCommand(new CXGameMode(), "gamemode", false);
        getAPI().getCommandManager().registerCommand(new CXFreeze(), "freeze", false);
        getAPI().getCommandManager().registerCommand(new CXTPHere(), "tphere", true);
        getAPI().getCommandManager().registerCommand(new CXKick(), "kick", false);
        getAPI().getCommandManager().registerCommand(new CXSlap(), "slap", false);
        getAPI().getCommandManager().registerCommand(new CXPlayerInfo(), "pinfo", false);
    }
    public void onDisable() {
        getAPI().getPlayerDataManager().save();
    }
    public CXAPI getAPI() {
        return api;
    }
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
    	if (e.getPlayer().getName() == "marvinbek") {
    		CXUtils.broadcast(ChatColor.BLUE+"A CmdX author has just joined!");
    	}
    }
}
