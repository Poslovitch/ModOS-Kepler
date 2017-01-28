package net.slimefununiverse.modos.api.utils;

import org.bukkit.ChatColor;

public class TextHelper {

	private static String modos = ChatColor.DARK_AQUA + "" + ChatColor.BOLD + "ModOS" + ChatColor.GRAY + " > ";
	private static String imodos = ChatColor.RED + "" + ChatColor.BOLD + "ModOS" + ChatColor.DARK_RED + " > ";
	
	public static String errorCmdExecution(String error){return modos + ChatColor.RED + error;}
	public static String syntaxCmd(String syntax){return modos + ChatColor.GOLD + syntax;}
	
	public static String modos(String message){return modos + ChatColor.RESET + message;}
	public static String debug(String message){return imodos + ChatColor.DARK_GRAY + message;}
}
