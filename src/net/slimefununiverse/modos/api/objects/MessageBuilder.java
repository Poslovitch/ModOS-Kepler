package net.slimefununiverse.modos.api.objects;

import java.util.ArrayList;
import java.util.List;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder.FormatRetention;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

public class MessageBuilder {

	private TextComponent current;
	private final List<BaseComponent> parts = new ArrayList<BaseComponent>();

	public MessageBuilder(MessageBuilder original){
		current = new TextComponent(original.current);
		for(BaseComponent baseComponent : original.parts)
		{ parts.add(baseComponent.duplicate()); }
	}

	public MessageBuilder(String text){
		current = new TextComponent(text);
	}

	public MessageBuilder then(String text){
		return then(text, FormatRetention.ALL);
	}

	public MessageBuilder then(String text, FormatRetention retention){
		parts.add(current);

		current = new TextComponent(current);
		current.setText(text);
		retain(retention);

		return this;
	}

	public MessageBuilder color(ChatColor color){
		current.setColor(color);
		return this;
	}

	public MessageBuilder bold(boolean bold){
		current.setBold(bold);
		return this;
	}

	public MessageBuilder italic(boolean italic){
		current.setItalic(italic);
		return this;
	}

	public MessageBuilder underlined(boolean underlined){
		current.setUnderlined(underlined);
		return this;
	}

	public MessageBuilder strikethrough(boolean strikethrough){
		current.setStrikethrough(strikethrough);
		return this;
	}

	public MessageBuilder obfuscated(boolean obfuscated){
		current.setObfuscated(obfuscated);
		return this;
	}

	public MessageBuilder insertion(String insertion){
		current.setInsertion(insertion);
		return this;
	}

	public MessageBuilder event(ClickEvent clickEvent){
		current.setClickEvent(clickEvent);
		return this;
	}

	public MessageBuilder event(HoverEvent hoverEvent){
		current.setHoverEvent(hoverEvent);
		return this;
	}

	public MessageBuilder reset(){
		return retain(FormatRetention.NONE);
	}

	public MessageBuilder retain(FormatRetention retention){
		BaseComponent previous = current;

		switch (retention){
		case NONE:
			current = new TextComponent(current.getText());
			break;
		case ALL:
			// No changes are required
			break;
		case EVENTS:
			current = new TextComponent(current.getText());
			current.setInsertion(previous.getInsertion());
			current.setClickEvent(previous.getClickEvent());
			current.setHoverEvent(previous.getHoverEvent());
			break;
		case FORMATTING:
			current.setClickEvent(null);
			current.setHoverEvent(null);
			break;
		}
		return this;
	}

	public BaseComponent[] bc(){
		parts.add(current);
		return parts.toArray(new BaseComponent[parts.size()]);
	}
}
