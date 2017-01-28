package net.slimefununiverse.modos.api.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.chat.BaseComponent;

public class MessageSender {
	
	//TODO alert id for staff settings
	public static void broadcastToStaff(String alert, String msg){
		for(Player p : Bukkit.getOnlinePlayers()){
			if(p.hasPermission("modos.staff")) p.sendMessage(msg);
		}
	}
	
	public static void broadcastToStaff(String alert, BaseComponent[] msg){
		for(Player p : Bukkit.getOnlinePlayers()){
			if(p.hasPermission("modos.staff")) p.sendMessage(msg);
		}
	}
	
	public static void broadcastToAll(String message){
		for(Player p : Bukkit.getOnlinePlayers()){
			p.sendMessage(message);
		}
	}
	
	public static void broadcastToAll(BaseComponent[] message){
		for(Player p : Bukkit.getOnlinePlayers()){
			p.sendMessage(message);
		}
	}
	
	public static void debug(String msg){
		for(Player p : Bukkit.getOnlinePlayers()){
			if(p.hasPermission("modos.superadmin")) p.sendMessage(msg);
		}
	}
}
