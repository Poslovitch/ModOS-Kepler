package net.slimefununiverse.modos.discord;

import fr.mrsheepsheep.discorduniverse.DiscordUniverse;
import net.dv8tion.jda.JDA;
import net.dv8tion.jda.entities.User;
import net.slimefununiverse.modos.ModOS;
import net.slimefununiverse.modos.discord.commands.DiscordBroadcastMessage;
import net.slimefununiverse.modos.discord.commands.DiscordHelp;
import net.slimefununiverse.modos.discord.commands.DiscordSeenPlayer;

public class DiscordBot {
	
	ModOS main;
	JDA jda;
	String defaultChannel;
	
	public DiscordBot(ModOS plugin, String defaultChannel){
		main = plugin;
		this.defaultChannel = defaultChannel;
		jda = DiscordUniverse.getBot("Slimefun Universe");
	}
	
	public void loadCommands(){
		new DiscordHelp(this);
		new DiscordSeenPlayer(this);
		new DiscordBroadcastMessage(this);
	}
	
	public JDA getJDA(){
		return jda;
	}
	
	public String getDefaultChannel(){
		return defaultChannel;
	}
	
	public void broadcast(String channel, String message){
		DiscordUniverse.broadcast(jda, channel, message);
	}
	
	public void broadcast(String message){
		DiscordUniverse.broadcast(jda, defaultChannel, message);
	}
	
	public void broadcastHelp(){
		DiscordUniverse.broadcast(jda, defaultChannel, ":warning: Commande inconnue. Utilisez **help** pour connaître la liste des commandes.");
	}
	
	public void sendPM(User user, String message){
		user.getPrivateChannel().sendMessage(message);
	}
	
	public boolean isAllowedCmdByMP(User user){
		return (!user.isBot() /*&& UniverseAuthenticator.hasPermission(user, "modos.staff")*/);
	}
}
