package me.irinque.notboringchat.commands;

import me.irinque.notboringchat.Main;
import me.irinque.notboringchat.getdata.GetMessage;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

public class SetColor implements CommandExecutor
{
    static Main plugin = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
    {
        Player player = (Player) sender;
        Player target = plugin.getServer().getPlayer(args[0]);
        String color = args[1];

        if (player.hasPermission("setcolor"))
        {
            Dictionary colormap = new Hashtable();
            colormap.put("DEFAULT", "§f");
            colormap.put("DARK_RED", "§4");
            colormap.put("RED", "§c");
            colormap.put("GOLD", "§6");
            colormap.put("YELLOW", "§e");
            colormap.put("DARK_GREEN", "§2");
            colormap.put("GREEN", "§a");
            colormap.put("AQUA", "§b");
            colormap.put("DARK_AQUA", "§3");
            colormap.put("BLUE", "§9");
            colormap.put("DARK_BLUE", "§1");
            colormap.put("LIGHT_PURPLE", "§d");
            colormap.put("DARK_PURPLE", "§5");
            colormap.put("GRAY", "§7");
            colormap.put("DARK_GRAY", "§8");
            colormap.put("BLACK", "§0");

            if (args.length == 2)
            {plugin.get_config_players().set("player-data." + target.getUniqueId().toString() + ".prefix-color", colormap.get(color));}

            //if (args.length == 3)
            //{main.getplayersConfig().set("player-data." + target.getUniqueId().toString() + ".prefix-color", colormap.get(color) + "," + colormap.get(args[2]));}

            try {plugin.get_config_players().save(plugin.get_file_players());} catch (IOException e) {throw new RuntimeException(e);}

            String newprefix = plugin.get_config_players().get("player-data." + target.getUniqueId().toString() + ".prefix").toString();
            String prefixcolor = plugin.get_config_players().get("player-data." + target.getUniqueId().toString() + ".prefix-color").toString();
            target.setDisplayName(prefixcolor + newprefix + target.getName());
            target.setPlayerListName(target.getDisplayName());
            player.sendMessage(ChatColor.GREEN + GetMessage.getMsg("ColorSet"));
        }
        else
        {
            player.sendMessage(ChatColor.RED + GetMessage.getMsg("NoPermission"));
        }
        return true;
    }
}