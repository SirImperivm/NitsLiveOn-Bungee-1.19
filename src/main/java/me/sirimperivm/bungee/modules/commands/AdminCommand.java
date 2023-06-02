package me.sirimperivm.bungee.modules.commands;

import me.sirimperivm.bungee.Main;
import me.sirimperivm.bungee.assets.managers.ConfigManager;
import me.sirimperivm.bungee.assets.other.General;
import me.sirimperivm.bungee.assets.utils.Colors;
import me.sirimperivm.bungee.assets.utils.Errors;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

@SuppressWarnings("all")
public class AdminCommand extends Command {
    private Main plugin;
    public AdminCommand(Main plugin) {
        super("liveon");
        this.plugin = plugin;
    }

    private static ConfigManager conf = Main.getConf();

    private static String Prefix(String id) {
        return General.Prefix(id);
    }

    private void getUsage(CommandSender s) {
        for (String usage : conf.getCfg().getStringList("help.adminCommand")) {
            s.sendMessage(new TextComponent(Colors.text(usage)));
        }
    }

    @Override
    public void execute(CommandSender s, String[] a) {
        if (Errors.noPerm(s, conf.getCfg().getString("permissions.adminCommands.main"))) {
            return;
        } else {
            if (a.length != 1) {
                getUsage(s);
            } else {
                if (a[0].equalsIgnoreCase("reload")) {
                    if (Errors.noPerm(s, conf.getCfg().getString("permissions.adminCommands.reload"))) {
                        return;
                    } else {
                        conf.loadAll();
                        s.sendMessage(new TextComponent(ConfigManager.getTrans("messages.success.plugin.reloaded")
                                .replace("%p", Prefix("success"))));
                    }
                } else {
                    getUsage(s);
                }
            }
        }
    }
}
