package net.slimefununiverse.modos.listeners;

import java.util.ArrayList;
import java.util.Date;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import net.slimefununiverse.modos.ModOS;
import net.slimefununiverse.modos.api.objects.User;
import net.slimefununiverse.modos.api.utils.MessageSender;
import net.slimefununiverse.modos.api.utils.TextHelper;
import net.slimefununiverse.modos.database.UserCache;

public class ConnectionListener implements Listener{
	
	ModOS main;
	
	public ConnectionListener(ModOS plugin){
		main = plugin;
		main.getServer().getPluginManager().registerEvents(this, main);
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e){
		Player p = e.getPlayer();
		
		//Register in UserCache
		if(!UserCache.instance().contains(p.getUniqueId())){
			//TODO Get IP + get pseudo list
			String address = p.getAddress().toString().replaceAll("/", "").split(":")[0];
			UserCache.instance().add(new User(p.getUniqueId(), p.getName(), new ArrayList<String>(), address, new Date().getTime(), new Date().getTime() + 1, p.getLocation()));
			MessageSender.debug(TextHelper.debug("NewUser: " + p.getName()));
			
		} else {
			User user = UserCache.instance().get(p.getUniqueId());
			//TODO Changement de pseudo
			user.setOnline(true);
			user.setLastConnection(new Date().getTime());
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e){
		Player p = e.getPlayer();
		
		User user = UserCache.instance().get(p.getUniqueId());
		user.setOnline(false);
		user.setLastDisconnection(new Date().getTime());
		user.setDisconnectLocation(p.getLocation());
	}
}
