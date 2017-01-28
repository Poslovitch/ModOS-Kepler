package net.slimefununiverse.modos.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.slimefununiverse.modos.ModOS;
import net.slimefununiverse.modos.api.utils.TextHelper;

public class WorldTeleport implements CommandExecutor{

	ModOS main;

	public WorldTeleport(ModOS plugin) {
		main = plugin;
		main.getCommand("wtp").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(sender instanceof Player){
			if(sender.hasPermission("modos.commands.wtp")){
				if(args.length == 4){
					if(Bukkit.getWorld(args[0]) != null){
						if(isNumber(args[1]) && isNumber(args[2]) && isNumber(args[3])){
							Player p = (Player) sender;
							p.teleport(new Location(Bukkit.getWorld(args[0]), Double.valueOf(args[1]), Double.valueOf(args[2]), Double.valueOf(args[3])));
						}
						else sender.sendMessage(TextHelper.errorCmdExecution("Coordonnées invalides (caractères non-numériques)."));
					}
					else sender.sendMessage(TextHelper.errorCmdExecution("Le monde spécifié est inconnu."));
				}
				else sender.sendMessage(TextHelper.syntaxCmd("/wtp <world> <x> <y> <z>"));
			}
			else sender.sendMessage(TextHelper.errorCmdExecution("Vous n'avez pas la permission."));
		}
		else sender.sendMessage(TextHelper.errorCmdExecution("Seul un joueur peut utiliser cette commande."));
		
		return true;
	}
	
	private boolean isNumber(String s) {
	    try {
	        Integer.parseInt(s);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}
