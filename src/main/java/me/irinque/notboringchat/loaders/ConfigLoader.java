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
        if (!get_file_config().exists() || (main.getConfig().getString("custom-join.toggle") == null) || (main.getConfig().getString("local-chat.radius") == null) || (main.getConfig().getString("global-chat.world-prefix") == null) || (main.getConfig().getString("personal-messages.sign") == null) || (main.getConfig().getString("custom-death.toggle") == null) || (main.getConfig().getString("message.EmptyMessage") == null) || (main.getConfig().getString("message.Reload") == null) || (main.getConfig().getString("message.EmptyTarget") == null) || (main.getConfig().getString("message.SelectTarget") == null) || (main.getConfig().getString("message.SelectColor") == null) || (main.getConfig().getString("message.WritePrefix") == null) || (main.getConfig().getString("message.PlayerMuted") == null) || (main.getConfig().getString("message.YouMuted") == null))
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

            main.getConfig().set("message.NoPermission", "You do not have permission to use this command."); //v1.0.1
            main.getConfig().set("message.NoListeners", "Nobody hear you, type ! before text to send message to global chat.");
            main.getConfig().set("message.PrefixSet", "Prefix successfully set.");
            main.getConfig().set("message.PrefixRemoved", "Prefix successfully removed.");
            main.getConfig().set("message.ColorSet", "Color successfully set.");
            main.getConfig().set("message.Reload", "Plugin config successfully reloaded."); //v1.0.2
            main.getConfig().set("message.EmptyMessage", "You can't send an empty message.");
            main.getConfig().set("message.EmptyTarget", "There is no player with this nickname on the server!");
            main.getConfig().set("message.SelectTarget", "You need to select a player in the command argument."); //v1.0.3
            main.getConfig().set("message.SelectColor", "You need to select color.");
            main.getConfig().set("message.WritePrefix", "You need to write player prefix.");
            main.getConfig().set("message.PlayerMuted", "The player is muted."); // v1.0.4
            main.getConfig().set("message.PlayerUnmuted", "The player is unmuted.");
            main.getConfig().set("message.YouMuted", "You are muted by the server administrator!");
            main.saveConfig();
        }
    }
}
