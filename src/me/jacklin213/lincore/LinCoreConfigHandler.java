package me.jacklin213.lincore;

import java.io.File;
import java.util.logging.Logger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;

public class LinCoreConfigHandler {
	
	public static LinCore LinCore;
	private Plugin plugin;
	private Logger log;
	
	private FileConfiguration config;
	private File configFile;
	private String pluginFolder;
	
	//Constructors
	/**
	 * ConfigHandler constructor for LinCore.
	 * @param instance - LinCore plugin.
	 */
	public LinCoreConfigHandler(LinCore instance){
		LinCore = instance;
	}
	
	/**
	 * ConfigHandler constructor for LinPlugin series
	 * @param instance - The LinPlugin to be hooked to. 
	 */
	public LinCoreConfigHandler(Plugin instance){
		plugin = instance;
	}
	
	//Methods for config
	public void createConfig(){
		configFile = new File(pluginFolder + File.separator + "config.yml");
		
		if (!configFile.exists()){
			getConfig(plugin).options().copyDefaults(true);
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
	public void register(Plugin plugin){
		this.plugin = plugin;
	}
	
	/**
	 * Sets up the ConfigHandler for specified LinPlugin.
	 * Runs the register, setPluginFolder, setConfigLogger, createConfig methods
	 * @param plugin - The LinPlugin to register. 
	 */
	public void setup(Plugin plugin){
		if (!(plugin == null)){
			register(plugin);
			setConfig();
			setConfigLogger();
			setPluginFolder();
			createConfig();
		} else {
			LinCore.log.severe("Unable to register a plugin, It maybe null/not have been registered properly");
			LinCore.log.severe("Send in a ticket to: with a message 'Setup Method Failed, Plugin NPE'");
		}
	}
	
	//Getters
	public FileConfiguration getConfig(Plugin plugin){
		return this.config;
	}
	
	public Logger getConfigLogger(Plugin plugin){
		return this.log;
	}
	
	public String getPluginFolder(Plugin plugin){
		return plugin.getDataFolder().getAbsolutePath();
	}
	
	//Setters
	public void setConfig() {
		this.config = plugin.getConfig();
	}
	
	public void setConfigLogger(){
		this.log = plugin.getLogger();
	}
	
	public void setPluginFolder(){
		pluginFolder = plugin.getDataFolder().getAbsolutePath();
	}
	
}
