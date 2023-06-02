package me.sirimperivm.bungee.modules.commands;

import me.sirimperivm.bungee.Main;
import me.sirimperivm.bungee.assets.managers.ConfigManager;
import me.sirimperivm.bungee.assets.other.General;
import me.sirimperivm.bungee.assets.utils.Colors;
import me.sirimperivm.bungee.assets.utils.Errors;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

@SuppressWarnings("all")
public class LiveCommand extends Command {

    private Main plugin;
    public LiveCommand(Main plugin) {
        super("live");
        this.plugin = plugin;
    }

    private static ConfigManager conf = Main.getConf();

    private static String Prefix(String id) {
        return General.Prefix(id);
    }

    private void getUsage(CommandSender s) {
        for (String usage : conf.getCfg().getStringList("help.liveCommand")) {
            s.sendMessage(new TextComponent(Colors.text(usage)));
        }
    }

    @Override
    public void execute(CommandSender s, String[] a) {
        if (Errors.noPerm(s, conf.getCfg().getString("permissions.userCommands.live.main"))) {
            return;
        } else {
            if (Errors.noConsole(s)) {
                return;
            } else {
                ProxiedPlayer p = (ProxiedPlayer) s;
                if (a.length != 1) {
                    getUsage(p);
                } else {
                    if (a[0].equalsIgnoreCase("on")) {
                        String liveSuffix = conf.getCfg().getString("settings.liveSuffix");
                        String suffixCommand = "lpb user %username% permission set \"suffix.1000. &5&lLIVE ON\""
                                .replace("%username%", p.getName())
                                .replace("%suffix%", liveSuffix)
                                ;
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), suffixCommand);
                        p.sendMessage(new TextComponent(ConfigManager.getTrans("messages.success.live.on")
                                .replace("%p", Prefix("success"))
                                .replace("%suffix%", liveSuffix)));
                    } else if (a[0].equalsIgnoreCase("off")) {
                        String suffixCommand = "lpb user %username% meta removesuffix 1000"
                                .replace("%username%", p.getName())
                                ;
                        ProxyServer.getInstance().getPluginManager().dispatchCommand(ProxyServer.getInstance().getConsole(), suffixCommand);
                        p.sendMessage(new TextComponent(ConfigManager.getTrans("messages.success.live.off")
                                .replace("%p", Prefix("success"))));
                    } else {
                        getUsage(p);
                    }
                }
            }
        }
    }
}
