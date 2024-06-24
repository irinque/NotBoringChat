package me.irinque.notboringchat.handlers;

import me.irinque.notboringchat.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class PlayerDeathHandler implements Listener
{
    static Main plugin = Main.getInstance();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event)
    {
        if(plugin.getConfig().getString("custom-death.toggle").equals("true"))
        {
            String death_sign = plugin.getConfig().getString("custom-death.sign");
            String death_color = plugin.getConfig().getString("custom-death.color");
            String message = event.getDeathMessage().toString();
            event.setDeathMessage(death_color + death_sign + message);
        }
    }
}
