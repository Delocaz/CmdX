package me.delocaz.cmdx.api;

import java.io.File;
import java.io.IOException;

import me.delocaz.cmdx.CmdX;
import me.delocaz.cmdx.util.CXUtils;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CXLanguageManager {

    private FileConfiguration yml;

    public CXLanguageManager() {
    	File f = new File(CmdX.getInstance().getDataFolder(), "lang.yml");
    	if (!f.exists()) {
    		try {
				f.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
        yml = new YamlConfiguration();
        try {
			yml.load(f);
		} catch (IOException
				| InvalidConfigurationException e1) {
			e1.printStackTrace();
		}
        yml.addDefaults(YamlConfiguration.loadConfiguration(getClass().getResourceAsStream("/lang.yml")));
        yml.options().copyDefaults(true);
        try {
			yml.save(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * Gets an entry from the language file
     * @param name The name of the entry
     * @param args Any arguments to replace %argNs in the file
     * @return
     */
    public String getLang(String name, String... args) {
        String str = yml.getString(name);
        int i = 0;
        for (String arg : args) {
            str = str.replace("%arg" + i, arg);
            i++;
        }
        return CXUtils.colorize(str);
    }
}