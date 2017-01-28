package net.slimefununiverse.modos.discord.commands;

import java.util.Date;

import org.bukkit.Location;

import net.dv8tion.jda.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import net.slimefununiverse.modos.ModOS;
import net.slimefununiverse.modos.api.objects.User;
import net.slimefununiverse.modos.database.UserCache;
import net.slimefununiverse.modos.discord.DiscordBot;

public class DiscordSeenPlayer extends ListenerAdapter{

	DiscordBot bot;
	
	public DiscordSeenPlayer(DiscordBot bot){
		this.bot = bot;
		bot.getJDA().addEventListener(this);
	}
	
	@Override
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent e) {
		if(bot.isAllowedCmdByMP(e.getAuthor())){
			String cmd = e.getMessage().getContent().split(" ")[0];
			String[] args = e.getMessage().getContent().substring(cmd.length() + 1).split(" ");
			if(cmd.equalsIgnoreCase("seen")){
				if(args.length == 1){
					User target = UserCache.instance().get(args[0]);
					if(target != null){
						long now = new Date().getTime();
						Location loc;
						if(target.isOnline()){
							loc = target.getPlayer().getLocation();
							bot.sendPM(e.getAuthor(), ":eye: **" + target.getName() + "** est connecté depuis **" + ModOS.getFormattedTimeString((now/1000) - (target.getLastConnection()/1000)) + "**\n"
									+ ":eye: Dernière position : **" + loc.getWorld().getName() + " " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + "**");
						}
						else{
							loc = target.getDisconnectLocation();
							bot.sendPM(e.getAuthor(), ":eye: **" + target.getName() + "** est déconnecté depuis **" + ModOS.getFormattedTimeString((now/1000) - (target.getLastDisconnection()/1000)) + "**\n"
									+ ":eye: Dernière position : **" + loc.getWorld().getName() + " " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + "**");
						}
					}
					else bot.sendPM(e.getAuthor(), "Joueur spécifié inconnu");
				}
				else bot.sendPM(e.getAuthor(), "Syntaxe: **seen <joueur>**");
			}
		}
	}

}
