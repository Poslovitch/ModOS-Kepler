package net.slimefununiverse.modos.api.objects.sanction;

public class TagInfo extends ISanction{

	private long expiration;
	private TagType type;
	private TagPriority priority;
	
	public TagInfo(TagType type, TagPriority priority, String reason, String whoHasTag, long when){
		super(reason, whoHasTag, when);
		switch(type){
		default: break;
		case MANUAL:
			this.expiration = when + 7889238000L; break;
		case AUTOMATIC:
			this.expiration = when + 604800000L; break;
		}
		this.type = type;
		this.priority = priority;
	}
	
	public long getExpiration(){
		return expiration;
	}
	
	public TagType getType(){return type;}
	
	public enum TagType{
		MANUAL,
		AUTOMATIC;
	}
	
	public TagPriority getPriority(){return priority;}
	
	public enum TagPriority{
		CRITICAL(1),
		HIGH(2),
		NORMAL(3),
		LOW(4);
		
		private int value;
		
		TagPriority(int value){
			this.value = value;
		}
		
		public int getValue(){return value;}
	}
}
