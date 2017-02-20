package net.wfoas.minecraft.reseditor.textandiconlist;

import javax.swing.Icon;

public class TextAndIcon {
	private String text;
	private Icon icon;

	public TextAndIcon(String text, Icon icon) {
		this.text = text;
		this.icon = icon;
	}

	public String getText() {
		return this.text;
	}

	public Icon getIcon() {
		return this.icon;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setIcon(Icon icon) {
		this.icon = icon;
	}
}
