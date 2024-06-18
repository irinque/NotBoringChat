package me.irinque.notboringchat.loaders;

import me.irinque.notboringchat.Main;
import me.irinque.notboringchat.commands.*;
import me.irinque.notboringchat.tabcompleters.*;

public class CommandsLoader implements Runnable
{
    static Main plugin = Main.getInstance();

    public void run()
    {
        plugin.getServer().getPluginCommand("message").setExecutor(new Message());
        plugin.getServer().getPluginCommand("message").setTabCompleter(new MessageTabComplete());
        plugin.getServer().getPluginCommand("setprefix").setExecutor(new SetPrefix());
        plugin.getServer().getPluginCommand("setprefix").setTabCompleter(new SetPrefixTabComplete());
        plugin.getServer().getPluginCommand("setcolor").setExecutor(new SetColor());
        plugin.getServer().getPluginCommand("setcolor").setTabCompleter(new SetColorTabComplete());
        plugin.getServer().getPluginCommand("removeprefix").setExecutor(new RemovePrefix());
        plugin.getServer().getPluginCommand("removeprefix").setTabCompleter(new RemovePrefixTabComplete());
        plugin.getServer().getPluginCommand("reload-nbc").setExecutor(new Reload());
        plugin.getServer().getPluginCommand("reload-nbc").setTabCompleter(new ReloadTabComplete());
    }
}