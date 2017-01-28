package net.slimefununiverse.modos;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import net.slimefununiverse.modos.commands.BroadcastMessage;
import net.slimefununiverse.modos.commands.LookupPlayer;
import net.slimefununiverse.modos.commands.ReportPlayer;
import net.slimefununiverse.modos.commands.SeenPlayer;
import net.slimefununiverse.modos.commands.SpecPlayer;
import net.slimefununiverse.modos.commands.WorldTeleport;
import net.slimefununiverse.modos.database.UserCache;
import net.slimefununiverse.modos.database.config.ConfigLoader;
import net.slimefununiverse.modos.discord.DiscordBot;
import net.slimefununiverse.modos.listeners.ConnectionListener;

public class ModOS extends JavaPlugin{
	
	DiscordBot bot;
	ConfigLoader config;
	
	@Override
	public void onEnable(){
		
		// Load Config
		config = new ConfigLoader(this);
		config.load();
		
		//Load UserCache
		new UserCache();
		
		//Listeners
		new ConnectionListener(this);
		
		// Commands
		new SeenPlayer(this);
		new LookupPlayer(this);
		new ReportPlayer(this);
		new WorldTeleport(this);
		new BroadcastMessage(this);
		new SpecPlayer(this);
		
		// Discord
		bot = new DiscordBot(this, "modos-kepler");
		bot.loadCommands();
		bot.broadcast(":satellite_orbital: **ModOS Kepler actif** - I'm watching you! :white_check_mark:");
		
	}
	
	@Override
	public void onDisable(){
		
		bot.broadcast(":satellite_orbital: **ModOS Kepler inactif** - Soyez sages! :x:");
	}
	
	public DiscordBot getBot(){
		return bot;
	}
	
	public ConfigLoader getConfiguration(){
		return config;
	}
	
	public static boolean isStaffOnline(){
		for(Player p : Bukkit.getOnlinePlayers()){
			if(p.hasPermission("modos.staff")) return true;
		}
		return false;
	}
	
	public static String getFormattedTimeString(long timeInSeconds){
		String timeStr = new String();
		long sec_term = 1;
		long min_term = 60 * sec_term;
		long hour_term = 60 * min_term;
		long day_term = 24 * hour_term;
		long month_term = 30 * day_term;
		long year_term = 12 * month_term + 5;
		long result = Math.abs(timeInSeconds);
		
		int year = (int) (result / year_term);
		result = result % year_term;
		int month = (int) (result / month_term);
		result = result % month_term;
		int day = (int) (result / day_term);
		result = result % day_term;
		int hour = (int) (result / hour_term);
		result = result % hour_term;
		int min = (int) (result / min_term);
		result = result % min_term;
		int sec = (int) (result / sec_term);

		if(timeInSeconds < 0) timeStr = "-";
		
		if(year > 0) timeStr += year + "a ";
		if(month > 0) timeStr += month + "m ";
		if(day > 0) timeStr += day + "j ";
		if(hour > 0) timeStr += hour + "h ";
		if(min > 0) timeStr += min + "m ";
		if(sec > 0) timeStr += sec + "s";

		return timeStr;
	}
	
	
}
