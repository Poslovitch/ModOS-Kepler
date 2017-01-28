package net.slimefununiverse.modos.commands;

import java.util.Date;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.slimefununiverse.modos.ModOS;
import net.slimefununiverse.modos.api.objects.MessageBuilder;
import net.slimefununiverse.modos.api.objects.User;
import net.slimefununiverse.modos.api.objects.sanction.TagInfo;
import net.slimefununiverse.modos.api.objects.sanction.TagInfo.TagPriority;
import net.slimefununiverse.modos.api.objects.sanction.TagInfo.TagType;
import net.slimefununiverse.modos.api.utils.MessageSender;
import net.slimefununiverse.modos.api.utils.TextHelper;
import net.slimefununiverse.modos.database.SanctionManager;
import net.slimefununiverse.modos.database.UserCache;

public class ReportPlayer implements CommandExecutor{

	ModOS main;

	public ReportPlayer(ModOS plugin){
		main = plugin;
		main.getCommand("report").setExecutor(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		if(sender.hasPermission("modos.commands.report")){
			if(args.length >= 2){
				User target = UserCache.instance().get(args[0]);
				if(target != null){
					if(sender.getName() != target.getName()){
						if(target.isOnline()){
							String raison = "";
							for(int i = 1; i < args.length; i++) raison += " " + args[i];
							raison = raison.substring(1);
							
							// If Staff is Online > send them an alert 
							if(ModOS.isStaffOnline()){
								MessageSender.broadcastToStaff("report", new MessageBuilder("REPORT").color(ChatColor.AQUA).then(": ").color(ChatColor.GRAY).then(sender.getName()).color(ChatColor.GOLD)
										.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new MessageBuilder("Reports Formulés: ").color(ChatColor.WHITE).then((sender instanceof Player) ? String.valueOf(UserCache.instance().get(sender.getName()).getFormulatedReports()) : "Inconnu").color(ChatColor.AQUA).bc()))
										.then(" a report ").color(ChatColor.GRAY).then(target.getName()).color(ChatColor.RED).bold(true)
										.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new MessageBuilder("Clique pour te téléporter sur ce joueur").color(ChatColor.WHITE).bc()))
										.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tp " + target.getName()))
										.then(" pour: ").color(ChatColor.GRAY).bold(false).then(raison).color(ChatColor.RED).bc());
							}
							
							// Put a Tag on the reported player and broadcast on Discord.
							SanctionManager.tag(target, new TagInfo(TagType.AUTOMATIC, TagPriority.CRITICAL, "Report '" + raison + "' par " + sender.getName() , "ModOS", new Date().getTime()));
							main.getBot().broadcast(":exclamation: **REPORT** | *" + target.getName() + "* pour \"*" + raison + "*\" ; par *" + sender.getName() + "*");
						}
						else sender.sendMessage(TextHelper.errorCmdExecution("Le joueur n'est pas en ligne. Effectuez votre report sur le Discord ou sur notre forum."));
					}
					else sender.sendMessage(TextHelper.errorCmdExecution("Vous ne pouvez pas vous report vous-même."));
				}
				else sender.sendMessage(TextHelper.errorCmdExecution("Le joueur spécifié est inconnu."));
			}
			else sender.sendMessage(TextHelper.syntaxCmd("/report <joueur> <raison>"));
		}
		else sender.sendMessage(TextHelper.errorCmdExecution("Vous n'avez pas la permission."));
		return true;
	}
}
