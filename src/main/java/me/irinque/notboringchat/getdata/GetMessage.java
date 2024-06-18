package me.irinque.notboringchat.getdata;

import me.irinque.notboringchat.Main;

public class GetMessage
{
    public static Main plugin = Main.getInstance();

    public GetMessage() {}
    public static String getMsg(String path)
    {
        String message = plugin.getConfig().getString("message." + path);
        String text = "[NBC] " + message;
        return text;
    }
}
