package me.irinque.notboringchat.handlers;

import me.irinque.notboringchat.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.IOException;
import java.util.Objects;

public class JoinHandler implements Listener
{
    static Main plugin = Main.getInstance();

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        String UUID = player.getUniqueId().toString();
        String is_user = String.valueOf(plugin.get_config_players().get("player-data." + UUID));

        if (is_user.equals("null") || plugin.get_config_players().getString("player-data." + UUID + ".mute") == null)
        {
            plugin.get_config_players().set("player-data." + UUID + ".nickname", player.getName());
            plugin.get_config_players().set("player-data." + UUID + ".prefix", "");
            plugin.get_config_players().set("player-data." + UUID + ".prefix-color", "§f");
            plugin.get_config_players().set("player-data." + UUID + ".mute", false);
            try {
                plugin.get_config_players().save(plugin.get_file_players());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        else
        {
            String prefix = plugin.get_config_players().get("player-data." + UUID + ".prefix").toString();
            String prefixcolor = plugin.get_config_players().get("player-data." + UUID + ".prefix-color").toString();
            player.setDisplayName(prefixcolor + prefix + player.getName());
            player.setPlayerListName(player.getDisplayName());
        }

        if (Objects.equals(plugin.getConfig().getString("custom-join.toggle"), "true"))
        {
            String sign_join = plugin.getConfig().getString("custom-join.sign");
            String sign_color = plugin.getConfig().getString("custom-join.sign-color");
            event.setJoinMessage(sign_color + sign_join + player.getDisplayName());
        }

    }
}