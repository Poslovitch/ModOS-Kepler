package net.slimefununiverse.modos.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import net.slimefununiverse.modos.ModOS;
import net.slimefununiverse.modos.api.utils.MessageSender;
import net.slimefununiverse.modos.api.utils.TextHelper;

public class BroadcastMessage implements CommandExecutor{

	ModOS main;
	
	public BroadcastMessage(ModOS plugin){
		main = plugin;
		main.getCommand("broadcast").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(sender.hasPermission("modos.commands.broadcast")){
			if(args.length >= 1){
				String msg = "";
				for(int i = 0; i < args.length; i++) msg += " " + args[i];
				msg = msg.substring(1);
				msg = ChatColor.translateAlternateColorCodes('&', msg);
				
				MessageSender.broadcastToAll(ChatColor.GOLD + "" + ChatColor.BOLD + "ANNONCE" + ChatColor.WHITE + " | " + ChatColor.AQUA + sender.getName() + ": " + ChatColor.WHITE + msg);
				main.getBot().broadcast(":loudspeaker: **Broadcast** | **" + sender.getName() + "** : " + msg);
			}
			else sender.sendMessage(TextHelper.syntaxCmd("/bc <message>"));
		}
		else sender.sendMessage(TextHelper.errorCmdExecution("Vous n'avez pas la permission."));
		return true;
	}
}
