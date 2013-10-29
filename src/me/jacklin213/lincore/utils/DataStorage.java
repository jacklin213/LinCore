package me.jacklin213.lincore.utils;

import java.util.HashMap;

import org.bukkit.ChatColor;

public class DataStorage {
	
	private HashMap<String, String> variables = new HashMap<String, String>();
	
	//PluginDescription stuff
	private String author = "jacklin213";
	private String description;
	private String pluginName;
	private String version;
	
	//Colors and format stuff
	private String chatPluginName;
	
	//Messages
	private String invalidCommand;
	private String invalidNumber = ChatColor.RED + "Invalid number";
	private String invalidPlayer = ChatColor.RED + "Invalid or Offline Player";
	private String playerOnly = "This is a player only Command!";
	private String permMessage = ChatColor.RED + "You do not have the permissions to use this command!";
	
	//Methods
	
	public String getVaribale(String variable){
		if (variables.containsKey(variable)){
			return variables.get(variable);
		}
		return null;
	}
	//Setters
	public void setAuthor(String author) {
		this.author = author;
	}
	public void setChatPluginName(String chatPluginName) {
		this.chatPluginName = chatPluginName;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public void setInvalidCommand(String invalidCommand) {
		this.invalidCommand = invalidCommand;
	}
	public void setInvalidNumber(String invalidNumber) {
		this.invalidNumber = invalidNumber;
	}
	public void setInvalidPlayer(String invalidPlayer) {
		this.invalidPlayer = invalidPlayer;
	}
	public void setPlayerOnly(String playerOnly) {
		this.playerOnly = playerOnly;
	}
	public void setPermMessage(String permMessage) {
		this.permMessage = permMessage;
	}
	public void setVariables(){
		//PluginDescriptionFile variables
		variables.put("author", author);
		variables.put("chatPluginName", chatPluginName);
		variables.put("description", description);
		variables.put("pluginName", pluginName);
		variables.put("version", version);
		
		//Messages variables
		variables.put("invalidCommand", invalidCommand);
		variables.put("invalidNumber", invalidNumber);
		variables.put("invalidPlayer", invalidPlayer);
		variables.put("playerOnly", playerOnly);
		variables.put("permMessage", permMessage);
	}
  	
}
