package net.slimefununiverse.modos.api.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.slimefununiverse.modos.api.objects.sanction.Ban;
import net.slimefununiverse.modos.api.objects.sanction.Comment;
import net.slimefununiverse.modos.api.objects.sanction.Kick;
import net.slimefununiverse.modos.api.objects.sanction.Mute;
import net.slimefununiverse.modos.api.objects.sanction.Report;
import net.slimefununiverse.modos.api.objects.sanction.TagInfo;
import net.slimefununiverse.modos.api.objects.sanction.Warn;

public class User{
	
	private UUID uuid;
	private String name, ip;
	private List<String> pseudoHistory;
	private long lastConnect, lastDisconnect;
	private Location disconnectLocation;
	private boolean isOnline, isFrozen, isSpec;
	private Location lastSpecLoc;
	private GameMode beforeSpecGameMode;
	private TagInfo activeTag;
	private List<TagInfo> tags;
	private Ban activeBan;
	private List<Ban> bans;
	private Mute activeMute;
	private List<Mute> mutes;
	private List<Kick> kicks;
	private List<Warn> warns;
	private int formulatedReports;
	private List<Report> reports;
	private List<Comment> comments;
	private int censured;
	
	public User(UUID uuid, String name, List<String> pseudoHistory, String ip, long lastConnect, long lastDisconnect, Location loc){
		this.uuid = uuid;
		this.name = name;
		this.pseudoHistory = pseudoHistory;
		this.ip = ip;
		this.lastConnect = lastConnect;
		this.lastDisconnect = lastDisconnect;
		this.disconnectLocation = loc;
		this.isOnline = true;
		this.isFrozen = false;
		this.isSpec = false;
		this.lastSpecLoc = loc;
		this.beforeSpecGameMode = GameMode.SURVIVAL;
		this.activeTag = null;
		this.tags = new ArrayList<TagInfo>();
		this.activeBan = null;
		this.bans = new ArrayList<Ban>();
		this.activeMute = null;
		this.mutes = new ArrayList<Mute>();
		this.kicks = new ArrayList<Kick>();
		this.warns = new ArrayList<Warn>();
		this.formulatedReports = 0;
		this.reports = new ArrayList<Report>();
		this.comments = new ArrayList<Comment>();
		this.censured = 0;
	}
	
	public UUID getUUID(){return this.uuid;}
	public String getName(){return this.name;}
	public List<String> getPseudoHistory(){return this.pseudoHistory;}
	public String getIP(){return this.ip;}
	public long getLastConnection(){return this.lastConnect;}
	public long getLastDisconnection(){return this.lastDisconnect;}
	public Location getDisconnectLocation(){return this.disconnectLocation;}
	public boolean isOnline(){return this.isOnline;}
	public boolean isFrozen(){return this.isFrozen;}
	public boolean isBanned(){return this.activeBan != null;}
	public boolean isMute(){return this.activeMute != null;}
	public boolean isTagged(){return this.activeTag != null;}
	public boolean isSpec(){return this.isSpec;}
	public Location getSpecLocation(){return this.lastSpecLoc;}
	public GameMode getSpecGamemode(){return this.beforeSpecGameMode;}
	public TagInfo getActiveTag(){return this.activeTag;}
	public List<TagInfo> getTags(){return this.tags;}
	public Ban getActiveBan(){return this.activeBan;}
	public List<Ban> getBans(){return this.bans;}
	public Mute getActiveMute(){return this.activeMute;}
	public List<Mute> getMutes(){return this.mutes;}
	public List<Kick> getKicks(){return this.kicks;}
	public List<Warn> getWarns(){return this.warns;}
	public int getFormulatedReports(){return this.formulatedReports;}
	public List<Report> getReports(){return this.reports;}
	public List<Comment> getComments(){return this.comments;}
	public int getCensuredAmount(){return this.censured;}
	
	// Dont call this method if player isnt online
	public Player getPlayer(){return Bukkit.getPlayer(this.getUUID());}
	
	public void setUUID(UUID uuid){this.uuid = uuid;}
	public void setName(String s){this.name = s;}
	public void setPseudoHistory(List<String> list){this.pseudoHistory = list;}
	public void setIP(String ip){this.ip = ip;}
	public void setLastConnection(long l){this.lastConnect = l;}
	public void setLastDisconnection(long l){this.lastDisconnect = l;}
	public void setDisconnectLocation(Location l){this.disconnectLocation = l;}
	public void setOnline(boolean b){this.isOnline = b;}
	public void setFrozen(boolean b){this.isFrozen = b;}
	public void setSpec(boolean b, Location loc, GameMode gamemode){
		this.isSpec = b;
		this.lastSpecLoc = loc;
		this.beforeSpecGameMode = gamemode;
	}
	public void setActiveTag(TagInfo tag){this.activeTag = tag;}
	public void setTags(List<TagInfo> list){this.tags = list;}
	public void setActiveBan(Ban ban){this.activeBan = ban;}
	public void setBans(List<Ban> list){this.bans = list;}
	public void setActiveMute(Mute mute){this.activeMute = mute;}
	public void setMutes(List<Mute> list){this.mutes = list;}
	public void setKicks(List<Kick> list){this.kicks = list;}
	public void setWarns(List<Warn> list){this.warns = list;}
	public void setFormulatedReports(int i){this.formulatedReports = i;}
	public void setReports(List<Report> list){this.reports = list;}
	public void setComments(List<Comment> list){this.comments = list;}
	public void setCensuredAmount(int i){this.censured = i;}
}
