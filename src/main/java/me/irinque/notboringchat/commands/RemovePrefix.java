package me.irinque.notboringchat.commands;

import me.irinque.notboringchat.Main;
import me.irinque.notboringchat.getdata.GetMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

public class RemovePrefix implements CommandExecutor
{
    static Main plugin = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
    {
        Player player = (Player) sender;
        Player target = plugin.getServer().getPlayer(args[0]);
        if (player.hasPermission("notboringchat.removeprefix"))
        {
            plugin.get_config_players().set("player-data." + target.getUniqueId().toString() + ".prefix", "");
            try {
                plugin.get_config_players().save(plugin.get_file_players());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String newprefix = plugin.get_config_players().get("player-data." + target.getUniqueId().toString() + ".prefix").toString();
            String prefixcolor = plugin.get_config_players().get("player-data." + target.getUniqueId().toString() + ".prefix-color").toString();
            target.setDisplayName(prefixcolor + newprefix + target.getName());
            target.setPlayerListName(target.getDisplayName());
            player.sendMessage(ChatColor.GREEN + GetMessage.getMsg("PrefixRemoved"));
        }
        else
        {
            player.sendMessage(ChatColor.RED + GetMessage.getMsg("NoPermission"));
        }
        return true;
    }
}