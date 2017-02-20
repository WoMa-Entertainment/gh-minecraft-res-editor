package net.wfoas.minecraft.reseditor.notescr;

import javax.swing.ImageIcon;

public interface IInfo {

	public static enum IInfoType {
		NOTE, CRAFTING_RECIPE;
	}

	ImageIcon getIcon();

	String getAuthor();

	String getName();

	IInfoType getInfoType();
}
