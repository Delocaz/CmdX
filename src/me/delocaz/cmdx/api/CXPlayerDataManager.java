package me.delocaz.cmdx.api;

import com.google.common.collect.Maps;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.delocaz.cmdx.CmdX;
import org.bukkit.OfflinePlayer;

public class CXPlayerDataManager {

    private HashMap<String, HashMap<String, Serializable>> data = Maps.newHashMap();

    public CXPlayerDataManager() {
        load();
    }
    /**
     * Puts data into a player's data file
     * @param p The player in question
     * @param path The name of the data
     * @param pdata The data itself
     */
    public void putData(OfflinePlayer p, String path, Serializable pdata) {
        if (!data.containsKey(p.getName())) {
            data.put(p.getName(), new HashMap<String, Serializable>());
        }
        data.get(p.getName()).put(path, pdata);
        save(p.getName());
    }
    /**
     * Gets data from a player's data file
     * @param p The player in question
     * @param path The name of the data to get
     * @return The data
     */
    public Serializable getData(OfflinePlayer p, String path) {
    	HashMap<String, Serializable> playermap = data.get(p.getName());
        return playermap != null ? playermap.get(path) : null;
    }
    /**
     * Saves all player data files to disk
     */
    public void save() {
        for (String s : data.keySet()) {
            save(s);
        }
    }
    /**
     * Saves a player's data file to disk
     * @param name The player to save data
     */ 
    public void save(String name) {
        if (data.containsKey(name)) {
            ObjectOutputStream oos = null;
            try {
                String path = CmdX.getInstance().getDataFolder().getAbsolutePath() + "/playerdata/" + name + ".dat";
                oos = new ObjectOutputStream(new FileOutputStream(path));
                oos.writeObject(data.get(name));
                oos.flush();
                oos.close();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(CXPlayerDataManager.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(CXPlayerDataManager.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    oos.close();
                } catch (IOException ex) {
                    Logger.getLogger(CXPlayerDataManager.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    /**
     * Loads all data from disk
     */
    public void load() {
        File f = new File(CmdX.getInstance().getDataFolder().getAbsolutePath() + "/playerdata/");
        f.mkdirs();
        for (File ff : f.listFiles()) {
            if (ff.getName().endsWith(".dat")) {
                load(ff.getName().substring(0, ff.getName().length()-4));
            }
        }
    }
    /**
     * Loads a specific player's data from disk into memory
     * @param name The player
     */
    @SuppressWarnings("unchecked")
	public void load(String name) {
        ObjectInputStream ois = null;
        try {
            String path = CmdX.getInstance().getDataFolder().getAbsolutePath() + "/playerdata/" + name + ".dat";
            ois = new ObjectInputStream(new FileInputStream(path));
            data.put(name, (HashMap<String, Serializable>) ois.readObject());
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CXPlayerDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CXPlayerDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CXPlayerDataManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                ois.close();
            } catch (IOException ex) {
                Logger.getLogger(CXPlayerDataManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
