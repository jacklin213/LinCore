package me.jacklin213.lincore.utils;

import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

/**
 * DataStorage class, Contains basic variables and methods to set them. To add extra variables create a local class and extend DataStorage.
 * Make sure to use the {@link #setVariables() setVariables} methods afterwards to store them in this class.
 * @author jacklin213
 *
 */
public class DataStorage {

	private HashMap<String, String> variables = new HashMap<String, String>();
	private PluginDescriptionFile pdfFile;
	private Plugin plugin;

	// PluginDescription stuff
	private String author = "jacklin213";
	private String description;
	private String pluginName;
	private String version;

	// Colors and format stuff
	private String chatPluginName;

	// Messages
	private String invalidCommand;
	private String invalidNumber = ChatColor.RED + "Invalid number";
	private String invalidPlayer = ChatColor.RED + "Invalid or Offline Player";
	private String playerOnly = "This is a player only Command!";
	private String permMessage = ChatColor.RED	+ "You do not have the permissions to use this command!";

	// Constructor
	/**
	 * DataStorage constructor for LinPlugin series. Runs the {@link #setVariables() setVariables}  method.
	 * @param instance Plugin instance
	 * @param pdfFile PluginDescriptionFile of the plugin
	 */
	public DataStorage(Plugin instance, PluginDescriptionFile pdfFile) {
		this.plugin = instance;
		this.pdfFile = pdfFile;
		this.setVariables();
	}
	
	// Methods
	/**
	 * Accesses the private HashMap: variables and gets the variable value. 
	 * @param variable Key/Variable name
	 * @return Value/Variable value
	 */
	public String getVaribale(String variable) {
		if (variables.containsKey(variable)) {
			return variables.get(variable);
		}
		return null;
	}
	
	/**
	 * Adds a variable to the local HashMap.
	 * @param key Variable name
	 * @param value Variable value
	 */
	public void addVariable(String key, String value){
		this.variables.put(key, value);
	}
	
	/**
	 * Removes variable from the local HashMap.
	 * @param key Variable name
	 */
	public void removeVariable(String key){
		this.variables.remove(key);
	}
	
	/**
	 * Checks to see if the local HashMap contains a variable.
	 * @param key Varible name
	 * @return true If HashMap contains key
	 */
	public boolean contains(String key){
		if (this.variables.containsKey(key)){
			return true;
		}
		return false;
	}

	// Setters
	/**
	 * Sets the author variable.
	 * @param author String grabbed from the PluginDescriptionFile
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	
	/**
	 * Sets the chatPluginName with color and formatting.
	 * @param chatPluginName Formatted pluginName
	 */
	public void setChatPluginName(String chatPluginName) {
		this.chatPluginName = chatPluginName;
	}
	
	/**
	 * Sets the description.
	 * @param description String grabbed from the PluginDescriptionFile
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Sets the plain pluginName, stripped of color/formatting.
	 * @param pluginName String grabbed from the PluginDescriptionFile
	 */
	public void setPluginName(String pluginName) {
		this.pluginName = pluginName;
	}
	
	/**
	 * Sets the version.
	 * @param version String grabbed from the PluginDescriptionFile
	 */
	public void setVersion(String version) {
		this.version = version;
	}
	
	/**
	 * Sets the invalidCommand message.
	 * @param invalidCommand Formatted invalidCommand message
	 */
	public void setInvalidCommand(String invalidCommand) {
		this.invalidCommand = invalidCommand;
	}
	
	/**
	 * Sets the invalidNumber message.
	 * @param invalidNumber Formatted invalidNumber message
	 */
	public void setInvalidNumber(String invalidNumber) {
		this.invalidNumber = invalidNumber;
	}
	
	/**
	 * Sets the invalidPlayer message
	 * @param invalidPlayer Formatted invalidPlayer message
	 */
	public void setInvalidPlayer(String invalidPlayer) {
		this.invalidPlayer = invalidPlayer;
	}
 
	/**
	 * Sets the playerOnly message.
	 * @param playerOnly Formatted playerOnly message
	 */
	public void setPlayerOnly(String playerOnly) {
		this.playerOnly = playerOnly;
	}
	
	/**
	 * Sets the PluginDescriptionFile(Does not need to be used, {@link #DataStorage(Plugin, PluginDescriptionFile) DataStorage} constructor already does this).
	 */
	public void setPluginDescriptionFile(){
		this.pdfFile = plugin.getDescription();
	}
	
	/**
	 * Sets the permMessage.
	 * @param permMessage Formatted permMessage
	 */
	public void setPermMessage(String permMessage) {
		this.permMessage = permMessage;
	}
	
	/**
	 * Set the Name, Version, Description Variables from the {@link PluginDescriptionFile}
	 */
	public void setPDFVariables(){
		this.setDescription(pdfFile.getDescription());
		this.setPluginName(pdfFile.getName());
		this.setVersion(pdfFile.getVersion());
	}
	
	/**
	 * SetVariables method - Runs the {@link #setPDFVariables() setPDFVariables} method, Clears the local variable HashMap and stores all the new variables inside
	 */
	public void setVariables() {
		setPDFVariables();
		
		// Does this update the hashmap on second call?
		variables.clear();
		// PluginDescriptionFile variables
		variables.put("author", author);
		variables.put("chatPluginName", chatPluginName);
		variables.put("description", description);
		variables.put("pluginName", pluginName);
		variables.put("version", version);

		// Messages variables
		variables.put("invalidCommand", invalidCommand);
		variables.put("invalidNumber", invalidNumber);
		variables.put("invalidPlayer", invalidPlayer);
		variables.put("playerOnly", playerOnly);
		variables.put("permMessage", permMessage);
	}

}
