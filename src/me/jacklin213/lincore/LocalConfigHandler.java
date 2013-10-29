package me.jacklin213.lincore;

import java.io.File;
import java.util.logging.Logger;

import me.jacklin213.lincore.utils.DataStorage;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;

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
	 * ConfigHandler constructor for LinPlugin series
	 * @param instance - The LinPlugin to be hooked to. 
	 */
	public LocalConfigHandler(Plugin instance, Logger logger, DataStorage dataStorage){
		plugin = instance;
		this.log = logger;
		this.dataStorage = dataStorage;
	}
	
	//Methods for config
	public void createConfig(){
		configFile = new File(pluginFolder + File.separator + "config.yml");
		
		if (!configFile.exists()){
			getConfig().options().copyDefaults(true);
			plugin.saveDefaultConfig();
			log.info(String.format("[%s] Cannot find config.yml, Generating now....", plugin.getDescription().getName()));
			log.info(String.format("[%s] Config generated !", plugin.getDescription().getName()));
		}
	}
	
	public void reloadConfig(){
		plugin.reloadConfig();
		setConfig();
		log.info("Config reloaded");
	}
	// Other methods

	/**
	 * Sets up the ConfigHandler for specified LinPlugin.
	 * Runs the register, setPluginFolder, setConfigLogger, createConfig methods
	 * @param plugin - The LinPlugin to register. 
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
	public FileConfiguration getConfig(){
		return this.config;
	}
	
	public Logger getConfigLogger(){
		return this.log;
	}
	
	public String getPluginFolder(){
		return plugin.getDataFolder().getAbsolutePath();
	}
	
	//Setters
	public void setConfig() {
		this.config = plugin.getConfig();
	}
	
	public void setConfigLogger(){
		this.log = plugin.getLogger();
	}
	
	/**
	 * Sets the instance of the DataStorage class for the ConfigHandler class
	 * @param instance - DataStorage instance (Should be declared in main class)
	 */
	public void setDataStorageClass(DataStorage instance){
		this.dataStorage = instance;
	}
	
	public void setPluginDescriptionFile(){
		this.pdfFile = plugin.getDescription();
	}
	
	public void setPluginFolder(){
		pluginFolder = plugin.getDataFolder().getAbsolutePath();
	}

	public void assignVariableStrings(){
		//PDFFile Stuff
		dataStorage.setAuthor(pdfFile.getName());
		dataStorage.setDescription(pdfFile.getDescription());
		dataStorage.setPluginName(pdfFile.getName());
		dataStorage.setVersion(pdfFile.getVersion());
		
	}
}
