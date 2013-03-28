package me.delocaz.cmdx.api;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class CXLang {

    private FileConfiguration yml;

    public CXLang() {
        yml = new YamlConfiguration();
        yml.addDefaults(YamlConfiguration.loadConfiguration(getClass().getResourceAsStream("/lang.yml")));
        yml.options().copyDefaults(true);
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
        }
        return CXUtils.colorize(str);
    }
}
