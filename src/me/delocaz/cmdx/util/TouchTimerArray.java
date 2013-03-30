package me.delocaz.cmdx.util;

import java.util.HashMap;

import com.google.common.collect.Maps;

public class TouchTimerArray {
	HashMap<String, TouchTimer> map;
	public TouchTimerArray() {
		map = Maps.newHashMap();
	}
	public TouchTimer getTimer(String s) {
		if (!map.containsKey(s)) {
			map.put(s, new TouchTimer());
		}
		return map.get(s);
	}
}
