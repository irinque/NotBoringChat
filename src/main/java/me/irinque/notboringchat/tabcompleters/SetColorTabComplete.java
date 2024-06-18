package me.irinque.notboringchat.tabcompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class SetColorTabComplete implements TabCompleter
{
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String alias, String[] args) {
        if (args.length == 2)
        {
            return List.of("DEFAULT", "DARK_RED", "RED", "GOLD", "YELLOW", "DARK_GREEN", "GREEN", "AQUA", "DARK_AQUA", "DARK_BLUE", "BLUE", "LIGHT_PURPLE", "DARK_PURPLE", "GRAY", "DARK_GRAY", "BLACK");
        }
        //if (args.length == 3)
        //{return List.of("DEFAULT", "DARK_RED", "RED", "GOLD", "YELLOW", "DARK_GREEN", "GREEN", "AQUA", "DARK_AQUA", "DARK_BLUE", "BLUE", "LIGHT_PURPLE", "DARK_PURPLE", "GRAY", "DARK_GRAY", "BLACK");}
        if (args.length > 2)
        {
            return List.of();
        }
        return null;
    }
}