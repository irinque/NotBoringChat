package me.irinque.notboringchat;

import me.irinque.notboringchat.handlers.JoinHandler;
import me.irinque.notboringchat.handlers.PlayerDeathHandler;
import me.irinque.notboringchat.handlers.QuitHandler;
import me.irinque.notboringchat.handlers.MessageHandler;
import me.irinque.notboringchat.loaders.*;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class Main extends JavaPlugin
{
    File file_players = new File("plugins/NotBoringChat/players.yml");
    FileConfiguration config_players = YamlConfiguration.loadConfiguration(file_players);
    public FileConfiguration get_config_players() {return config_players;}
    public File get_file_players() {return file_players;}
    File plugin_directory = new File("plugins/NotBoringChat");
    public File get_plugin_directory() {return plugin_directory;}

    public static Main instance;
    public static Main getInstance() {
        return instance;
    }


    @Override
    public void onEnable()
    {
        if (instance == null) {instance = this;}

        if (!get_plugin_directory().exists())
        {get_plugin_directory().mkdirs();}

        if (!get_file_players().exists())
        {
            try {get_file_players().createNewFile();}
            catch (IOException e) {throw new RuntimeException(e);}
        }

        getServer().getPluginManager().registerEvents(new MessageHandler(), this);
        getServer().getPluginManager().registerEvents(new JoinHandler(), this);
        getServer().getPluginManager().registerEvents(new QuitHandler(), this);
        getServer().getPluginManager().registerEvents(new PlayerDeathHandler(), this);

        Bukkit.getScheduler().runTaskAsynchronously(this, new ConfigLoader());
        Bukkit.getScheduler().runTaskAsynchronously(this, new CommandsLoader());
        Bukkit.getScheduler().runTaskAsynchronously(this, new PlayersLoader());
        getServer().getLogger().info("§a[NotBoringChat] Plugin is ready!");
    }

    @Override
    public void onDisable()
    {
        if (instance != null) {instance = null;}
        getServer().getLogger().info("§4[NotBoringChat] Plugin was shutdown!");
    }
}
