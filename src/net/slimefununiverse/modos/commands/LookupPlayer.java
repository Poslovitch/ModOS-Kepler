package net.slimefununiverse.modos.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.slimefununiverse.modos.ModOS;

public class LookupPlayer implements CommandExecutor{

	ModOS main;
	
	public LookupPlayer(ModOS plugin){
		main = plugin;
		main.getCommand("lookup").setExecutor(this);
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		
		return true;
	}
}
