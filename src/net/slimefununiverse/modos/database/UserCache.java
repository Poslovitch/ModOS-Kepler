package net.slimefununiverse.modos.database;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;

import net.slimefununiverse.modos.api.objects.User;

public class UserCache {
	
	private static Map<UUID, User> users = new HashMap<UUID, User>();
	private static UserCache instance;
	
	public UserCache(){
		instance = this;
	}
	
	public static UserCache instance(){return instance;}
	
	public Collection<User> getAll(){
		return users.values();
	}

	public User get(UUID uuid){
		return users.get(uuid);
	}
	
	@SuppressWarnings("deprecation")
	public User get(String name){
		return users.get(Bukkit.getOfflinePlayer(name).getUniqueId());
	}

	public boolean contains(UUID uuid){
		return users.containsKey(uuid);
	}

	public void add(User user){
		users.put(user.getUUID(), user);
	}

	public void remove(UUID uuid){
		users.remove(uuid);
	}
}
