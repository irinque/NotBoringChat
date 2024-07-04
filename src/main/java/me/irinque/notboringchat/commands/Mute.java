package me.irinque.notboringchat.commands;

import me.irinque.notboringchat.Main;
import me.irinque.notboringchat.getdata.GetMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class Mute implements CommandExecutor
{
    static Main plugin = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
    {
        Player player = (Player) sender;
        if (player.hasPermission("notboringchat.mute"))
        {
            if (args.length > 0)
            {
                Player target = plugin.getServer().getPlayer(args[0]);
                if (target != null)
                {
                    String uuid = target.getUniqueId().toString();
                    String is_muted = plugin.get_config_players().getString("player-data." + uuid + ".mute");
                    if (is_muted.equals("false")) {
                        plugin.get_config_players().set("player-data." + uuid + ".mute", true);
                        player.sendMessage(ChatColor.YELLOW + GetMessage.getMsg("PlayerMuted"));
                    }
                    if (is_muted.equals("true")) {
                        plugin.get_config_players().set("player-data." + uuid + ".mute", false);
                        player.sendMessage(ChatColor.GREEN + GetMessage.getMsg("PlayerUnmuted"));
                    }
                    try
                    {
                        plugin.get_config_players().save(plugin.get_file_players());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                } else
                {
                    player.sendMessage(ChatColor.RED + GetMessage.getMsg("EmptyTarget"));
                }
            }
            if (args.length == 0)
            {
                player.sendMessage(ChatColor.RED + GetMessage.getMsg("SelectTarget"));
            }
        }
        else
        {
            player.sendMessage(ChatColor.RED + GetMessage.getMsg("NoPermission"));
        }
        return true;
    }
}
