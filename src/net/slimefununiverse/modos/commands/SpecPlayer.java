package net.slimefununiverse.modos.commands;

import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.slimefununiverse.modos.ModOS;
import net.slimefununiverse.modos.api.objects.User;
import net.slimefununiverse.modos.api.utils.TextHelper;
import net.slimefununiverse.modos.database.UserCache;

/**
 * @author Cleymax
 */
public class SpecPlayer implements CommandExecutor {

	ModOS main;

	public SpecPlayer(ModOS plugin) {
		main = plugin;
		main.getCommand("spectate").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			Player p = (Player) sender;
			if (p.hasPermission("modos.commands.spectate")) {
				User user = UserCache.instance().get(p.getUniqueId());
				if(user.isSpec()){
					p.teleport(user.getSpecLocation());
					p.setGameMode(user.getSpecGamemode());
					user.setSpec(false, p.getLocation(), user.getSpecGamemode());
					p.sendMessage(TextHelper.modos(ChatColor.GREEN + "Tu n'es plus en spectateur !"));
				} else {
					user.setSpec(true, p.getLocation(), p.getGameMode());
					p.setGameMode(GameMode.SPECTATOR);
					p.sendMessage(TextHelper.modos(ChatColor.GREEN + "Tu es maintenant en spectateur !"));
				}
			}
			else p.sendMessage(TextHelper.errorCmdExecution("Vous n'avez pas la permission."));
		} 
		else sender.sendMessage(TextHelper.errorCmdExecution("Seul les joueurs peuvent faire cette commande !!"));
		return true;
	}
}
