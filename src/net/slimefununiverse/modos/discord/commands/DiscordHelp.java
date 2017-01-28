package net.slimefununiverse.modos.discord.commands;

import net.dv8tion.jda.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.hooks.ListenerAdapter;
import net.slimefununiverse.modos.discord.DiscordBot;

public class DiscordHelp extends ListenerAdapter{

	DiscordBot bot;
	
	public DiscordHelp(DiscordBot bot){
		this.bot = bot;
		bot.getJDA().addEventListener(this);
	}
	
	@Override
	public void onPrivateMessageReceived(PrivateMessageReceivedEvent e) {
		if(bot.isAllowedCmdByMP(e.getAuthor())){
			if(e.getMessage().getContent().equalsIgnoreCase("help")){
				bot.sendPM(e.getAuthor(), "**Liste des commandes - ModOS KEPLER**"
						+ "\n\n"
						+ "**help**: affiche la liste des commandes\n"
						+ "**server**: affiche les informations relatives au serveur (*nombre de connectés, TPS...*)\n"
						+ "**dailyreport**: vous envoie le rapport journalier\n"
						+ "**broadcast <message>**: broadcast un message sur le serveur\n"
						+ "**seen <joueur>**: affiche la dernière connexion/déconnexion du joueur spécifié\n"
						+ "**lookup <joueur>**: affiche les informations concernant le joueur spécifié (*sanctions, UUID, historique de pseudo...*)\n"
						+ "**ban <joueur> <raison>**\n"
						+ "**tempban <joueur> <durée> <raison>**\n"
						+ "**mute <joueur> <raison>**\n"
						+ "**tempmute <joueur> <raison>**\n"
						+ "**kick <joueur> <raison>**\n"
						+ "**warn <joueur> <raison>**\n"
						+ "**comment <joueur> <raison>**\n"
						+ "\n"
						+ ":gear: Commandes de débug. Seul un Maître expérimenté sait s'en servir... :gear:\n"
						+ "**checkup**: Allô docteur? ModOS va-t-y bien?\n"
						+ "**ssmdp <target> <old> <new>**: changer le mdp StaffSecurity\n"
						+ "**forcedailyreport**: forcer l'envoi du dailyreport dans le channel\n"
						+ "**reload**: reload la config de ModOS");
			}
		}
	}
}
