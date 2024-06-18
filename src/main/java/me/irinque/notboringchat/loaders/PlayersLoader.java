package me.irinque.notboringchat.loaders;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class PlayersLoader implements Runnable
{
    File file_players = new File("plugins/NotBoringChat/players.yml");
    FileConfiguration config_players = YamlConfiguration.loadConfiguration(file_players);
    public FileConfiguration get_config_players() {return config_players;}
    public File get_file_players() {return file_players;}

    public void run()
    {
        try
        {
            get_config_players().set("player-data.uuid.nickname", "UserName");
            get_config_players().set("player-data.uuid.prefix", "UserPrefix");
            get_config_players().set("player-data.uuid.prefix-color", "Color");
            get_config_players().save(get_file_players());
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

    }
}