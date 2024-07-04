package me.irinque.notboringchat.commands;
import me.irinque.notboringchat.Main;

import me.irinque.notboringchat.getdata.GetMessage;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class Message implements CommandExecutor
{
    static Main plugin = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args)
    {
        Player player = (Player) sender;
        StringBuilder sb = new StringBuilder();
        if (args.length > 0)
        {
            Player target = plugin.getServer().getPlayer(args[0]);
            if (target != null)
            {
                List<String> messagelist = Arrays.asList(args);
                if (messagelist.size() > 1)
                {
                    for (int i = 1; i < messagelist.size(); i++) {
                        sb.append(messagelist.get(i));
                        if (i < messagelist.size() - 1) {
                            sb.append(" ");
                        }
                    }
                    String message = sb.toString();

                    String sign_personal_message = plugin.getConfig().getString("personal-messages.sign");
                    String color_personal_message = plugin.getConfig().getString("personal-messages.color");
                    String notification_personal_message = plugin.getConfig().getString("personal-messages.notification.toggle");

                    TextComponent PlayerComponent = new TextComponent(color_personal_message + sign_personal_message + player.getDisplayName());
                    PlayerComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tell " + player.getName() + " "));
                    TextComponent PlayerMessage = new TextComponent(color_personal_message + " -> " + target.getDisplayName() + color_personal_message + " >> " + message);
                    player.spigot().sendMessage(PlayerComponent, PlayerMessage);

                    TextComponent TargetComponent = new TextComponent(color_personal_message + sign_personal_message + player.getDisplayName() + color_personal_message);
                    TargetComponent.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/tell " + player.getName() + " "));
                    TextComponent TargetMessage = new TextComponent(color_personal_message + " >> " + message);
                    target.spigot().sendMessage(TargetComponent, TargetMessage);
                    if (notification_personal_message.toString().equals("true")) {
                        target.playSound(target.getLocation(), Sound.BLOCK_NOTE_BLOCK_BELL, 1, 1);
                    }
                }
                else
                {
                    player.sendMessage(ChatColor.RED + GetMessage.getMsg("EmptyMessage"));
                }

            }
            else
            {
                player.sendMessage(ChatColor.RED + GetMessage.getMsg("EmptyTarget"));
            }
        }
        if (args.length == 0)
        {
            player.sendMessage(ChatColor.RED + GetMessage.getMsg("SelectTarget"));
        }
        return true;
    }
}
