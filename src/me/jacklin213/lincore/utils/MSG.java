package me.jacklin213.lincore.utils;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

/**
 * MSG class - Short for Messaging handles all the player messaging. To add methods create a local class and extend MSG.
 * @author jack
 *
 */
public class MSG {
	
	private Plugin plugin;
	
	private DataStorage dataStorage;
	private Logger log;
	
	/**
	 * MSG constructor for LinPlugin series.
	 * @param instance Plugin instance
	 * @param logger Plugin logger
	 * @param dataStorage DataStorage instance from the Plugin
	 */
	public MSG(Plugin instance, Logger logger, DataStorage dataStorage) {
		this.plugin = instance;
		this.log = instance.getLogger();
		this.dataStorage = dataStorage;
	}
	
	/**
	 * Sets the instance of the DataStorage class for the MSG class.
	 * @param instance DataStorage instance (Should be declared in main class)
	 */
	public void setDataStorageClass(DataStorage instance){
		this.dataStorage = instance;
	}
	
	/**
	 * Sends the sender a "Made by jacklin213" message.
	 * @param sender
	 */
	public void basicInfo(CommandSender sender){
		sender.sendMessage(dataStorage.getVaribale("chatPluginName") + "Made by " + ChatColor.GOLD + dataStorage.getVaribale("author")); 
	}
	
	/**
	 * Sends the sender the Plugin name, Version, Author, Description.
	 * @param sender
	 */
	public void info(CommandSender sender){
		sender.sendMessage(ChatColor.GOLD + " ============ " + dataStorage.getVaribale("chatPluginName") + ChatColor.GOLD + "============ ");
		sender.sendMessage(ChatColor.GOLD + "Plugin name: " + ChatColor.AQUA + dataStorage.getVaribale("pluginName"));
		sender.sendMessage(ChatColor.GOLD + "Version: " + ChatColor.AQUA + dataStorage.getVaribale("version"));
		sender.sendMessage(ChatColor.GOLD + "Author: " + ChatColor.AQUA  + "by " + dataStorage.getVaribale("author"));
		sender.sendMessage(ChatColor.GOLD + "Description: " + ChatColor.AQUA + dataStorage.getVaribale("description"));
	}
	
	/**
	 * commandReply is used instead of {@link Player#sendMessage(String) Player.sendMessage()} method. This applies the Formatted pluginName with colors before the message.
	 * @param target The player that recieves the message
	 * @param message The message that is to be sent
	 */
	public void commandReply(String target, String message){
		Player reciever = plugin.getServer().getPlayer(target);
		if (reciever != null){
			reciever.sendMessage(dataStorage.getVaribale("chatPluginName") + message);
		}
	}
	
	/**
	 * commandConsoleReply is used instead of {@link #commandConsoleReply(String) commandReply()} because the console is not a Player
	 * @param message
	 */
	public void commandConsoleReply(String message){
		log.info(message);
	}
}
