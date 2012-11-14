package edu.unca.szhang.GameSpout;

import java.text.MessageFormat;

import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.getspout.spout.player.SpoutCraftPlayer;
import org.getspout.spoutapi.SpoutManager;
import org.getspout.spoutapi.sound.SoundManager;

/*
 * This is a sample event listener
 */
public class GameSpoutListener implements Listener {
    private final GameSpout plugin;

    /*
     * This listener needs to know about the plugin which it came from
     */
    public GameSpoutListener(GameSpout plugin) {
        // Register the listener
        plugin.getServer().getPluginManager().registerEvents(this, plugin);
        
        this.plugin = plugin;
    }

    /*
     * Send the sample message to all players that join
     */
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {        
        event.getPlayer().sendMessage(this.plugin.getConfig().getString("sample.message"));
        
    	plugin.musicplayed.put(event.getPlayer(), false);        
    }
    
    @EventHandler
    public void music (PlayerMoveEvent event) {
    	if (plugin.musicplayed.get(event.getPlayer()) == false) {
    		Player player = event.getPlayer();
    		SpoutCraftPlayer sp = (SpoutCraftPlayer) player;
		
    		SoundManager sound = SpoutManager.getSoundManager();
    		sound.playCustomMusic(plugin, sp, "http://www.culdceptcentral.com/soundtracks/cps2/cd1/03.mp3", true);
    		
        	plugin.musicplayed.put(player, true);            		
    	}
    }
    
    /*
     * Another example of a event handler. This one will give you the name of
     * the entity you interact with, if it is a Creature it will give you the
     * creature Id.
     */
    @EventHandler
    public void onPlayerInteract(PlayerInteractEntityEvent event) {
        final EntityType entityType = event.getRightClicked().getType();

        event.getPlayer().sendMessage(MessageFormat.format(
                "You interacted with a {0} it has an id of {1}",
                entityType.getName(),
                entityType.getTypeId()));
    }    
}
