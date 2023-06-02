package me.sirimperivm.bungee.assets.other;

import me.sirimperivm.bungee.assets.managers.ConfigManager;

@SuppressWarnings("all")
public class General {

    public static String Prefix(String id) {
        return ConfigManager.getTrans("messages.prefixes." + id);
    }
}
