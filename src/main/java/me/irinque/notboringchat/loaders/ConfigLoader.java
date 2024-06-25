package me.irinque.notboringchat.loaders;

import me.irinque.notboringchat.Main;
import org.bukkit.Bukkit;

import java.io.File;

public class ConfigLoader implements Runnable
{
    File configFile = new File("plugins/NotBoringChat/config.yml");
    public File get_file_config() {return configFile;}

    static Main main = Main.getInstance();

    public void run()
    {
        if (!get_file_config().exists() || (main.getConfig().getString("custom-join.toggle") == null) || (main.getConfig().getString("local-chat.radius") == null) || (main.getConfig().getString("global-chat.world-prefix") == null) || (main.getConfig().getString("personal-messages.sign") == null) || (main.getConfig().getString("custom-death.toggle") == null) || (main.getConfig().getString("message.EmptyMessage") == null) || (main.getConfig().getString("message.Reload") == null) || (main.getConfig().getString("message.EmptyTarget") == null))
        {
            main.getConfig().set("custom-join.toggle", true);
            main.getConfig().set("custom-join.sign", "[+] ");
            main.getConfig().set("custom-join.sign-color", "§2");
            main.getConfig().set("custom-quit.toggle", true);
            main.getConfig().set("custom-quit.sign", "[-] ");
            main.getConfig().set("custom-quit.sign-color", "§4");
            
            main.getConfig().set("local-chat.radius", 50);
            main.getConfig().set("local-chat.message-color", "§7");

            main.getConfig().set("global-chat.world-prefix", true);
            main.getConfig().set("global-chat.symbol", "■");
            main.getConfig().set("global-chat.overworld-color", "§a");
            main.getConfig().set("global-chat.nether-color", "§c");
            main.getConfig().set("global-chat.end-color", "§9");

            main.getConfig().set("personal-messages.sign", "[✉] ");
            main.getConfig().set("personal-messages.color", "§b");
            main.getConfig().set("personal-messages.notification.toggle", true);

            main.getConfig().set("custom-death.toggle", true);
            main.getConfig().set("custom-death.sign", "[\uD83D\uDC80] ");
            main.getConfig().set("custom-death.color", "§3");

            main.getConfig().set("message.NoPermission", "You do not have permission to use this command.");
            main.getConfig().set("message.NoListeners", "Nobody hear you, type ! before text to send message to global chat.");
            main.getConfig().set("message.PrefixSet", "Prefix successfully set.");
            main.getConfig().set("message.PrefixRemoved", "Prefix successfully removed.");
            main.getConfig().set("message.ColorSet", "Color successfully set.");
            main.getConfig().set("message.Reload", "Plugin config successfully reloaded.");
            main.getConfig().set("message.EmptyMessage", "You can't send an empty message.");
            main.getConfig().set("message.EmptyTarget", "Select the player to whom the message will be sent.");
            main.saveConfig();
        }
    }
}
