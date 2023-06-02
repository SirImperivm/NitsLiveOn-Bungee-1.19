package me.sirimperivm.bungee.assets.utils;

import me.sirimperivm.bungee.assets.managers.ConfigManager;
import me.sirimperivm.bungee.assets.other.General;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

@SuppressWarnings("all")
public class Errors {

    private static String Prefix() {
        return General.Prefix("fail");
    }

    public static boolean noPerm(CommandSender s, String p) {
        if (s.hasPermission(p))
            return false;
        s.sendMessage(new TextComponent(ConfigManager.getTrans("messages.errors.no-perm")
                .replace("%p", Prefix())));
        return true;
    }

    public static boolean noConsole(CommandSender s) {
        if (s instanceof ProxiedPlayer)
            return false;
        s.sendMessage(new TextComponent(ConfigManager.getTrans("messages.errors.no-console")
                .replace("%p", Prefix())));
        return true;
    }
}
