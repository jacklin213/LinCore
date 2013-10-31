package me.jacklin213.lincore.utils;

import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class MSG {
	
	private Plugin plugin;
	
	private DataStorage dataStorage;
	private Logger log;
	
	public MSG(Plugin instance, Logger logger, DataStorage dataStorage) {
		this.plugin = instance;
		this.log = instance.getLogger();
		this.dataStorage = dataStorage;
	}
	
	/**
	 * Sets the instance of the DataStorage class for the MSG class
	 * @param instance - DataStorage instance (Should be declared in main class)
	 */
	public void setDataStorageClass(DataStorage instance){
		this.dataStorage = instance;
	}
	
	public void basicInfo(CommandSender sender){
		sender.sendMessage(dataStorage.getVaribale("chatPluginName") + "Made by " + ChatColor.GOLD + dataStorage.getVaribale("author")); 
	}
	
	public void info(CommandSender sender){
		sender.sendMessage(ChatColor.GOLD + " ============ " + dataStorage.getVaribale("chatPluginName") + ChatColor.GOLD + "============ ");
		sender.sendMessage(ChatColor.GOLD + "Plugin name: " + ChatColor.AQUA + dataStorage.getVaribale("pluginName"));
		sender.sendMessage(ChatColor.GOLD + "Version: " + ChatColor.AQUA + dataStorage.getVaribale("version"));
		sender.sendMessage(ChatColor.GOLD + "Author: " + ChatColor.AQUA  + "by " + dataStorage.getVaribale("author"));
		sender.sendMessage(ChatColor.GOLD + "Description: " + ChatColor.AQUA + dataStorage.getVaribale("description"));
	}
	
	public void commandReply(String target, String message){
		Player reciever = plugin.getServer().getPlayer(target);
		if (reciever != null){
			reciever.sendMessage(dataStorage.getVaribale("chatPluginName") + message);
		}
	}
	
	public void commandConsoleReply(String message){
		log.info(message);
	}
}
