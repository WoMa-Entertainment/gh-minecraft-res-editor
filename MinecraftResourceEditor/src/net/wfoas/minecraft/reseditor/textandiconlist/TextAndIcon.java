package net.wfoas.minecraft.reseditor.textandiconlist;

import javax.swing.Icon;

import net.wfoas.minecraft.reseditor.notescr.IInfo;

public class TextAndIcon {
	private String text;
	private IInfo id_name;
	private Icon icon;

	public TextAndIcon(String text, Icon icon, IInfo id_name) {
		this.text = text;
		this.icon = icon;
		this.id_name = id_name;
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

	public IInfo getid_name() {
		return id_name;
	}
}