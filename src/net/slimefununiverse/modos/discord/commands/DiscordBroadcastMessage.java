package net.slimefununiverse.modos.discord.commands;

import fr.mrsheepsheep.universeauthenticator.UniverseAuthenticator;
import net.dv8tion.jda.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import net.md_5.bungee.api.ChatColor;
import net.slimefununiverse.modos.api.objects.User;
import net.slimefununiverse.modos.api.utils.MessageSender;
import net.slimefununiverse.modos.database.UserCache;
import net.slimefununiverse.modos.discord.DiscordBot;

public class DiscordBroadcastMessage extends ListenerAdapter{

	DiscordBot bot;
	
	public DiscordBroadcastMessage(DiscordBot bot){
		this.bot = bot;
		bot.getJDA().addEventListener(this);
	}

	@Override
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent e) {
		if(bot.isAllowedCmdByMP(e.getAuthor())){
			String cmd = e.getMessage().getContent().split(" ")[0];
			String[] args = e.getMessage().getContent().substring(cmd.length() + 1).split(" ");
			if(cmd.equalsIgnoreCase("broadcast") || cmd.equalsIgnoreCase("bc")){
				if(args.length >= 1){
					String msg = "";
					for(int i = 0; i < args.length; i++) msg += " " + args[i];
					msg = msg.substring(1);
					msg = ChatColor.translateAlternateColorCodes('&', msg);
					User sender = UserCache.instance().get(UniverseAuthenticator.getUUID(e.getAuthor().getId()));
					
					MessageSender.broadcastToAll(ChatColor.GOLD + "" + ChatColor.BOLD + "ANNONCE" + ChatColor.WHITE + " | " + ChatColor.AQUA + sender.getName() + ": " + ChatColor.WHITE + msg);
					bot.broadcast(":loudspeaker: **Broadcast** | **" + sender.getName() + "** : " + msg);
					bot.sendPM(e.getAuthor(), ":loudspeaker: **Broadcast** | " + msg);
				}
				else bot.sendPM(e.getAuthor(), "Syntaxe: **bc <message>**");
			}
		}
	}
}
