package me.irinque.notboringchat.commands;

import me.irinque.notboringchat.Main;
import me.irinque.notboringchat.getdata.GetMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Reload implements CommandExecutor
{
    static Main plugin = Main.getInstance();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
    {
        Player player = (Player) sender;
        if (player.hasPermission("reload-nbc"))
        {
            plugin.reloadConfig();
            player.sendMessage(ChatColor.GREEN + GetMessage.getMsg("Reload"));
        }
        else
        {
            player.sendMessage(ChatColor.RED + GetMessage.getMsg("NoPermission"));

        }
        return true;
    }
}
