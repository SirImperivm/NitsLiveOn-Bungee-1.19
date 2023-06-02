package me.sirimperivm.bungee.assets.managers;

import me.sirimperivm.bungee.Main;
import me.sirimperivm.bungee.assets.utils.Colors;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.util.logging.Logger;

@SuppressWarnings("all")
public class ConfigManager {

    private static Main plugin = Main.getPlugin();
    private static Logger log = Logger.getLogger("NitsLiveOn");
    private File f;
    private Configuration cfg;

    public ConfigManager() {
        try {
            f = new File(plugin.getDataFolder(), "settings.yml");
            if (!plugin.getDataFolder().exists()) {
                plugin.getDataFolder().mkdir();
            }
            if (!f.exists()) {
                Files.copy(plugin.getResourceAsStream("settings.yml"), f.toPath(), new CopyOption[0]);
            }
            loadConfig();
        } catch (IOException e) {
            log.severe("Impossibile creare il file di settings.");
        }
    }

    public void saveConfig() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(cfg, f);
        } catch (IOException e) {
            log.severe("Impossibile salvare il file di settings.");
            e.printStackTrace();
        }
    }

    public void loadConfig() {
        try {
            cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(f);
        } catch (IOException e) {
            log.severe("Impossibile caricare il file di settings.");
            e.printStackTrace();
        }
    }

    public void saveAll() {
        saveConfig();
    }

    public void loadAll() {
        loadConfig();
    }

    public Configuration getCfg() {
        return cfg;
    }

    public static String getTrans(String value) {
        return Colors.text(Main.getConf().getCfg().getString(value));
    }
}
