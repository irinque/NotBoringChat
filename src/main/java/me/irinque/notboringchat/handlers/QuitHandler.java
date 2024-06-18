package me.irinque.notboringchat.handlers;

import me.irinque.notboringchat.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.Objects;

public class QuitHandler implements Listener
{
    static Main plugin = Main.getInstance();

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event)
    {
        if (Objects.equals(plugin.getConfig().getString("custom-quit.toggle"), "true"))
        {
            Player player = event.getPlayer();
            String sign_join = plugin.getConfig().getString("custom-quit.sign");
            String sign_color = plugin.getConfig().getString("custom-quit.sign-color");
            event.setQuitMessage(sign_color + sign_join + player.getDisplayName());
        }

    }
}
