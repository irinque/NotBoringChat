package me.irinque.notboringchat.handlers;


import me.irinque.notboringchat.Main;
import me.irinque.notboringchat.getdata.GetMessage;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;


public class MessageHandler implements Listener
{
    static Main plugin = Main.getInstance();

    @EventHandler
    public void onPlayerMessage(AsyncPlayerChatEvent event)
    {
        String message = event.getMessage();
        Player player = event.getPlayer();
        String playerprefix = plugin.get_config_players().getString("player-data." + player.getUniqueId().toString() + ".prefix");
        event.setCancelled(true);
        if (!Character.toString(message.charAt(0)).equals("/"))
        {
            if (!Character.toString(message.charAt(0)).equals("!"))
            {

                int Listeners = 0;
                int radius_localchat = (int) plugin.getConfig().get("local-chat.radius");
                String color_localchat = plugin.getConfig().getString("local-chat.message-color");
                for (Player target : player.getWorld().getPlayers())
                {
                    if (target.getLocation().distance(player.getLocation()) <= radius_localchat & !target.getUniqueId().equals(player.getUniqueId()))
                    {
                        Listeners++;
                        target.sendMessage(color_localchat + " " + player.getDisplayName() + color_localchat + " >> " + message);
                    }

                }
                if (Listeners == 0)
                {
                    player.sendMessage(ChatColor.DARK_RED + GetMessage.getMsg("NoListeners"));
                }
                if (Listeners > 0)
                {
                    player.sendMessage(color_localchat + " " + player.getDisplayName() + color_localchat + " >> " + message);
                }
            }
            if (Character.toString(message.charAt(0)).equals("!"))
            {
                if (plugin.getConfig().get("global-chat.world-prefix").toString().equals("true"))
                {
                    String world_prefix_symbol = plugin.getConfig().getString("global-chat.symbol");
                    if (player.getWorld().toString().contains("_nether")) {
                        String world_prefix_color = plugin.getConfig().getString("global-chat.nether-color");
                        TextComponent World = new TextComponent(world_prefix_color + world_prefix_symbol);
                        TextComponent PlayerComponent = new TextComponent(ChatColor.WHITE + " " + player.getDisplayName());
                        PlayerComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tell " + player.getName() + " "));
                        TextComponent Message = new TextComponent(world_prefix_color + " >> " + ChatColor.WHITE + message.substring(1));
                        plugin.getServer().spigot().broadcast(World, PlayerComponent, Message);
                    }
                    if (player.getWorld().toString().contains("_the_end")) {
                        String world_prefix_color = plugin.getConfig().getString("global-chat.end-color");
                        TextComponent World = new TextComponent(world_prefix_color + world_prefix_symbol);
                        TextComponent PlayerComponent = new TextComponent(ChatColor.WHITE + " " + player.getDisplayName());
                        PlayerComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tell " + player.getName() + " "));
                        TextComponent Message = new TextComponent(world_prefix_color + " >> " + ChatColor.WHITE + message.substring(1));
                        plugin.getServer().spigot().broadcast(World, PlayerComponent, Message);
                    }
                    if (!player.getWorld().toString().contains("_the_end") & !player.getWorld().toString().contains("_nether")) {
                        String world_prefix_color = plugin.getConfig().getString("global-chat.overworld-color");
                        TextComponent World = new TextComponent(world_prefix_color + world_prefix_symbol);
                        TextComponent PlayerComponent = new TextComponent(ChatColor.WHITE + " " + player.getDisplayName());
                        PlayerComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tell " + player.getName() + " "));
                        TextComponent Message = new TextComponent(world_prefix_color + " >> " + ChatColor.WHITE + message.substring(1));
                        plugin.getServer().spigot().broadcast(World, PlayerComponent, Message);
                    }
                }
                if (plugin.getConfig().get("global-chat.world-prefix").toString().equals("false"))
                {
                    TextComponent PlayerComponent = new TextComponent(player.getDisplayName());
                    PlayerComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tell " + player.getName() + " "));
                    TextComponent Message = new TextComponent(ChatColor.WHITE + " >> " + ChatColor.WHITE + message.substring(1));
                    plugin.getServer().spigot().broadcast(PlayerComponent, Message);
                }
            }

        }
    }
}


