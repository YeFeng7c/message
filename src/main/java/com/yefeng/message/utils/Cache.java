package com.yefeng.message.utils;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {
    private static final ConcurrentHashMap<String, List<String>> MAP = new ConcurrentHashMap();

    public Cache() {
    }

    public static synchronized void put(String key, List<String> data) {
        remove(key);
        MAP.put(key, data);
    }

    public static List<String> get(String key) {
        return (List)MAP.get(key);
    }

    public static synchronized void remove(String key) {
        MAP.remove(key);
    }
}
