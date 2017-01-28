package net.slimefununiverse.modos.commands;

import java.util.Date;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.slimefununiverse.modos.ModOS;
import net.slimefununiverse.modos.api.objects.MessageBuilder;
import net.slimefununiverse.modos.api.objects.User;
import net.slimefununiverse.modos.api.utils.TextHelper;
import net.slimefununiverse.modos.database.UserCache;

public class SeenPlayer implements CommandExecutor{
	
	ModOS main;
	
	public SeenPlayer(ModOS plugin) {
		main = plugin;
		main.getCommand("seen").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(sender.hasPermission("modos.commands.seen")){
			if(args.length == 1){
				User target = UserCache.instance().get(args[0]);
				if(target != null){
					long now = new Date().getTime();
					Location loc;
					if(target.isOnline()){
						loc = target.getPlayer().getLocation();
						sender.sendMessage(TextHelper.modos(ChatColor.GOLD + target.getName() + ChatColor.AQUA + " est " + ChatColor.GREEN + "connecté" + ChatColor.AQUA + " depuis " + ChatColor.LIGHT_PURPLE + ModOS.getFormattedTimeString((now/1000) - (target.getLastConnection()/1000))));
					}
					else{
						loc = target.getDisconnectLocation();
						sender.sendMessage(TextHelper.modos(ChatColor.GOLD + target.getName() + ChatColor.AQUA + " est " + ChatColor.RED + "déconnecté" + ChatColor.AQUA + " depuis " + ChatColor.LIGHT_PURPLE + ModOS.getFormattedTimeString((now/1000) - (target.getLastDisconnection()/1000))));
					}
					sender.sendMessage(new MessageBuilder("Position: ").color(ChatColor.WHITE)
							.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/wtp " + loc.getWorld().getName() + " " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ()))
							.then(loc.getWorld().getName() + " " + loc.getBlockX() + " " + loc.getBlockY() + " " + loc.getBlockZ() + " ").color(ChatColor.AQUA).then("(Clique pour te téléporter)").color(ChatColor.GRAY).bc());
				}
				else sender.sendMessage(TextHelper.errorCmdExecution("Le joueur spécifié est inconnu."));
			}
			else sender.sendMessage(TextHelper.syntaxCmd("/seen <player>"));
		}
		else sender.sendMessage(TextHelper.errorCmdExecution("Vous n'avez pas la permission."));
		
		return true;
	}
}
