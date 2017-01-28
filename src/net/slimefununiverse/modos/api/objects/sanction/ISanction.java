package net.slimefununiverse.modos.api.objects.sanction;

public class ISanction {
	
	private String reason, punisher;
	private long when;
	
	public ISanction(String reason, String punisher, long when){
		this.reason = reason;
		this.punisher = punisher;
		this.when = when;
	}
	
	public String getReason(){return this.reason;}
	public String getPunisher(){return this.punisher;}
	public long getWhen(){return this.when;}
}
