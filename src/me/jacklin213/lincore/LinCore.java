package me.jacklin213.lincore;

import java.util.logging.Logger;

import me.jacklin213.chatalert.ChatAlert;
import me.jacklin213.linchat.LinChat;
import me.jacklin213.lincore.utils.DataStorage;
import me.jacklin213.lincore.utils.Updater;
import me.jacklin213.lincore.utils.Updater.UpdateResult;
import me.jacklin213.lincore.utils.Updater.UpdateType;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LinCore extends JavaPlugin{
	
	public static LinCore LinCore;
	public Logger log;
	public Updater updater;
	public LinCoreConfigHandler configHandler = new LinCoreConfigHandler(this);
	
	
	private LinChat LinChat;
	private ChatAlert ChatAlert;
	
	@Override
	public void onEnable() {
		this.setLogger();
		configHandler.setup(this);
		linkPlugins();
		Boolean updateCheck = Boolean.valueOf(getConfig().getBoolean("UpdateCheck"));
		Boolean autoUpdate = Boolean.valueOf(getConfig().getBoolean("AutoUpdate"));
		
		// Once DBO accepts project get ID
		/*this.updateCheck(updateCheck, autoUpdate, null);*/
		log.info(String.format("Version %s by jacklin213 has been Enabled!", getDescription().getVersion()));
	}
	
	@Override
	public void onDisable() {
		log.info(String.format("Disabled Version %s", getDescription().getVersion()));
	}
	
	private void setLogger(){
		this.log = getLogger();
	}
	
	private void linkPlugin(String plugin){
		PluginManager pm = getServer().getPluginManager();
		if (plugin.equals("LinChat")){
			LinChat = (me.jacklin213.linchat.LinChat) pm.getPlugin(plugin);
		}
		if (plugin.equals("ChatAlert")){
			ChatAlert = (me.jacklin213.chatalert.ChatAlert) pm.getPlugin(plugin);
		}
	}
	
	private void linkPlugins(){
		linkPlugin("LinChat");
		linkPlugin("ChatAlert");
	}
	
	public boolean isPluginNull(Plugin plugin){
		if (plugin !=null){
			return true;
		}
		return false;
	}
	
	private void updateCheck(boolean updateCheck, boolean autoUpdate, int ID){
		if(updateCheck && (autoUpdate == false)){
			updater = new Updater(this, ID, this.getFile(), UpdateType.NO_DOWNLOAD, true);
			if (updater.getResult() == UpdateResult.UPDATE_AVAILABLE) {
			    log.info("New version available! " + updater.getLatestName());
			}
			if (updater.getResult() == UpdateResult.NO_UPDATE){
				log.info(String.format("You are running the latest version of %s", getDescription().getName()));
			}
		}
		if(autoUpdate && (updateCheck == false)){
			updater = new Updater(this, ID, this.getFile(), UpdateType.NO_VERSION_CHECK, true);
		} 
		if(autoUpdate && updateCheck){
			updater = new Updater(this, ID, this.getFile(), UpdateType.DEFAULT, true);
			if (updater.getResult() == UpdateResult.UPDATE_AVAILABLE) {
			    log.info("New version available! " + updater.getLatestName());
			}
			if (updater.getResult() == UpdateResult.NO_UPDATE){
				log.info(String.format("You are running the latest version of %s", getDescription().getName()));
			}
		}
	}
	
	//Does not work, do not use. (Left here for further reminder)
	/*public void updateCheck(Plugin plugin, String pluginName, File file, boolean updateCheck, boolean autoUpdate, int ID){
		if (!(plugin == null)){
			Logger log = plugin.getLogger();
			if(updateCheck && (autoUpdate == false)){
				updater = new Updater(this, ID, file, UpdateType.NO_DOWNLOAD, true);
				if (updater.getResult() == UpdateResult.UPDATE_AVAILABLE) {
				    log.info("New version available! " + updater.getLatestName());
				}
				if (updater.getResult() == UpdateResult.NO_UPDATE){
					log.info(String.format("You are running the latest version of %s", plugin.getDescription().getName()));
				}
			}
			if(autoUpdate && (updateCheck == false)){
				updater = new Updater(this, ID, this.getFile(), UpdateType.NO_VERSION_CHECK, true);
			} 
			if(autoUpdate && updateCheck){
				updater = new Updater(this, ID, file, UpdateType.DEFAULT, true);
				if (updater.getResult() == UpdateResult.UPDATE_AVAILABLE) {
				    log.info("New version available! " + updater.getLatestName());
				}
				if (updater.getResult() == UpdateResult.NO_UPDATE){
					log.info(String.format("You are running the latest version of %s", plugin.getDescription().getName()));
				}
			}
		} else {
			log.severe("Cannot find the plugin: " + pluginName);
		}
	}*/
		
}
