package me.delocaz.cmdx.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Maps;

public class CXItemManager {
	
	HashMap<String, ItemStack> items;

    public CXItemManager() {
    	items = Maps.newHashMap();
    	init();
    }
    private void init() {
    	BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/items.csv")));
    	String line;
    	try {
			while ((line = br.readLine()) != null) {
				if (line.split(",").length == 3) {
					String[] lines = line.split(",");
					if (StringUtils.isNumeric(lines[1]) && StringUtils.isNumeric(lines[2])) {
						int id = Integer.parseInt(lines[1]);
						int data = Integer.parseInt(lines[2]);
						ItemStack item = new ItemStack(id);
						item.setDurability((short) data);
						item.setAmount(1);
						items.put(lines[0], item);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    /**
     * Parses a string into an ItemStack, uses items.csv list of item names
     * @param query The item name to parse
     * @return An ItemStack of the item name passed
     */
    public ItemStack parse(String query) {
    	ItemStack i = null;
    	if (items.containsKey(query.split(":")[0])) {
    		i = items.get(query);
    	}
    	if (StringUtils.isNumeric(query.split(":")[0])) {
    		i = new ItemStack(Integer.parseInt(query.split(":")[0]));
    	}
    	if (query.split(":").length > 1) {
    		if (StringUtils.isNumeric(query.split(":")[1])) {
    			if (i != null) {
    				i.setDurability((short) Integer.parseInt(query.split(":")[1]));
    			}
    		}
    	}
    	return i;
    }
}