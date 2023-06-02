package me.sirimperivm.bungee;

import me.sirimperivm.bungee.assets.managers.ConfigManager;
import me.sirimperivm.bungee.modules.commands.AdminCommand;
import me.sirimperivm.bungee.modules.commands.LiveCommand;
import net.md_5.bungee.api.plugin.Plugin;

@SuppressWarnings("all")
public final class Main extends Plugin {

    private static Main plugin;
    private static ConfigManager conf;

    void startUp() {
        plugin = this;
        conf = new ConfigManager();
        conf.loadAll();
    }

    void endUp() {
        conf.saveAll();
    }

    @Override
    public void onEnable() {
        startUp();
        getProxy().getPluginManager().registerCommand(this, new LiveCommand(this));
        getProxy().getPluginManager().registerCommand(this, new AdminCommand(this));
    }

    @Override
    public void onDisable() {
        endUp();
    }

    public static Main getPlugin() {
        return plugin;
    }

    public static ConfigManager getConf() {
        return conf;
    }
}
