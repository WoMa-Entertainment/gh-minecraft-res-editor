package net.wfoas.minecraft.reseditor.notescr;

import javax.swing.ImageIcon;

import net.wfoas.minecraft.reseditor.MinecraftResEditor;

public class InfoCraftingRecipe implements IInfo {

	protected String author, name;

	public InfoCraftingRecipe(String author, String name) {
		this.author = author;
		this.name = name;
	}

	@Override
	public ImageIcon getIcon() {
		return MinecraftResEditor.CR;
	}

	@Override
	public String getAuthor() {
		return author;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public IInfoType getInfoType() {
		return IInfoType.CRAFTING_RECIPE;
	}
}