package net.wfoas.minecraft.reseditor.textandiconlist;

import javax.swing.ImageIcon;

import net.wfoas.minecraft.reseditor.notescr.IInfo;

public class DisplayableEntry {
	private final String title;
	private final String imagePath;
	private ImageIcon image;

	public DisplayableEntry(IInfo ii) {
		this("<html><body>" + ii.getName() + "<br>von " + ii.getAuthor()
				+ System.lineSeparator() + "</body></html>", ii.getIcon());
	}

	public DisplayableEntry(String title, String imagePath) {
		this.title = title;
		this.imagePath = imagePath;
	}

	public DisplayableEntry(String title, ImageIcon imgicon) {
		this.image = imgicon;
		this.title = title;
		this.imagePath = null;
	}

	public String getTitle() {
		return this.title;
	}

	public ImageIcon getImage() {
		if (this.image == null) {
			this.image = new ImageIcon(this.imagePath);
		}
		return this.image;
	}

	public String toString() {
		return this.title;
	}
}