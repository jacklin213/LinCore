package me.jacklin213.lincore;

import java.io.File;
import java.util.logging.Logger;

import me.jacklin213.lincore.utils.DataStorage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

/**
 * LocalConfigHandler is a class which handles basic configuration. If addition methods need to be added make a local class which extends to this class.
 * @author jacklin213
 *
 */
public class LocalConfigHandler {
	
	public static LinCore LinCore;
	
	private Plugin plugin;
	private Logger log;
	private DataStorage dataStorage;
	
	private FileConfiguration config;
	private File configFile;
	private PluginDescriptionFile pdfFile;
	private String pluginFolder;
	
	//Constructors
	/**
	 * LocalConfigHandler constructor for LinPlugin series. Runs the {@link #setup() setup}  method 
	 * @param instance The LinPlugin to be hooked to. 
	 * @param logger Plugin logger (getLogger() from main class)
	 * @param dataStorage DataStorage instance to set and retrieve data from 
	 */
	public LocalConfigHandler(Plugin instance, Logger logger, DataStorage dataStorage){
		plugin = instance;
		this.log = logger;
		this.dataStorage = dataStorage;
		this.setup();
	}
	
	//Methods for config
	/**
	 * Creates a config which is copied from the default config.yml, will cause and NPE if there is no default config
	 */
	public void createConfig(){
		configFile = new File(pluginFolder + File.separator + "config.yml");
		
		if (!configFile.exists()){
			getConfig().options().copyDefaults(true);
			plugin.saveDefaultConfig();
			log.info(String.format("[%s] Cannot find config.yml, Generating now....", plugin.getDescription().getName()));
			log.info(String.format("[%s] Config generated !", plugin.getDescription().getName()));
		}
	}
	
	/**
	 * Reloads the config and logs message to console
	 */
	public void reloadConfig(){
		plugin.reloadConfig();
		setConfig();
		log.info("Config reloaded");
	}
	// Other methods

	/**
	 * Sets up the ConfigHandler for specified LinPlugin from the {@link #LocalConfigHandler(Plugin, Logger, DataStorage) LocalConfigHandler} constructor 
	 * Runs the register, setPluginFolder, setConfigLogger, createConfig methods. 
	 */
	public void setup(){
		if (!(plugin == null)){
			setConfig();
			setConfigLogger();
			setPluginDescriptionFile();
			setPluginFolder();
			createConfig();
		} else {
			LinCore.log.severe("Unable to register a plugin, It maybe null/not have been registered properly");
			LinCore.log.severe("Send in a ticket to: with a message 'Setup Method Failed, Plugin NPE'");
		}
	}
	
	//Getters
	/**
	 * Gets the config
	 * @return FileConfiguration config
	 */
	public FileConfiguration getConfig(){
		return this.config;
	}
	
	/**
	 * Returns the plugin logger.
	 * @return Logger log
	 */
	public Logger getConfigLogger(){
		return this.log;
	}
	
	/**
	 * Gets the absolute path of the plugin datafolder.
	 * @return String dataFolder
	 */
	public String getPluginFolder(){
		return plugin.getDataFolder().getAbsolutePath();
	}
	
	//Setters
	/**
	 * Sets the config from the plugin class.
	 */
	public void setConfig() {
		this.config = plugin.getConfig();
	}
	
	/**
	 * Sets the logger form the plugin class.
	 */
	public void setConfigLogger(){
		this.log = plugin.getLogger();
	}
	
	/**
	 * Sets the instance of the DataStorage class for the ConfigHandler class.
	 * @param instance DataStorage instance (Should be declared in main class)
	 */
	public void setDataStorageClass(DataStorage instance){
		this.dataStorage = instance;
	}
	
	/**
	 * Sets the PluginDesciptionFile.
	 */
	public void setPluginDescriptionFile(){
		this.pdfFile = plugin.getDescription();
	}
	
	/**
	 * Sets the pluginFolder.
	 */
	public void setPluginFolder(){
		pluginFolder = plugin.getDataFolder().getAbsolutePath();
	}
	
	/**
	 * Assigns the Strings to their specified variables in the {@link me.jacklin213.lincore.utils.DataStorage DataStorage} class
	 */
	public void assignVariableStrings(){
		//PDFFile Stuff
		dataStorage.setAuthor(pdfFile.getName());
		dataStorage.setDescription(pdfFile.getDescription());
		dataStorage.setPluginName(pdfFile.getName());
		dataStorage.setVersion(pdfFile.getVersion());
		
	}
}
