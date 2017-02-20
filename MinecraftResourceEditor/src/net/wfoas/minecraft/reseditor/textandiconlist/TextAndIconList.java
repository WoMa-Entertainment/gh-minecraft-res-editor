package net.wfoas.minecraft.reseditor.textandiconlist;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class TextAndIconList {
	public static volatile boolean POD_REQ = false;

	public static JList<TextAndIcon> createJListWithTextAndIcon(
			DisplayableEntry[] de) {
		DefaultListModel<TextAndIcon> listModel = new DefaultListModel<TextAndIcon>();
		DisplayableEntry[] var6 = de;
		int var5 = de.length;

		for (int renderer = 0; renderer < var5; ++renderer) {
			DisplayableEntry jtail = var6[renderer];
			if (jtail != null) {
				listModel.addElement(new TextAndIcon(jtail.getTitle(), jtail
						.getImage()));
			}
		}

		JList<TextAndIcon> var8 = new JList<TextAndIcon>(listModel);
		TextAndIconListCellRenderer var9 = new TextAndIconListCellRenderer(2);
		var8.setCellRenderer(var9);
		return var8;
	}

	public static void add(JList<TextAndIcon> jlist, DisplayableEntry de) {
		DefaultListModel<TextAndIcon> model = (DefaultListModel<TextAndIcon>) jlist
				.getModel();
		model.addElement(new TextAndIcon(de.getTitle(), de.getImage()));
		jlist.setModel(model);
		jlist.updateUI();
	}
}
