package net.wfoas.minecraft.reseditor.textandiconlist;

import java.awt.Component;
import java.awt.Rectangle;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

class TextAndIconListCellRenderer extends JLabel implements
		ListCellRenderer<TextAndIcon> {
	private static final long serialVersionUID = 1636642633364883773L;
	private static final Border NO_FOCUS_BORDER = new EmptyBorder(1, 1, 1, 1);
	private Border insideBorder;

	public TextAndIconListCellRenderer() {
		this(0, 0, 0, 0);
	}

	public TextAndIconListCellRenderer(int padding) {
		this(padding, padding, padding, padding);
	}

	public TextAndIconListCellRenderer(int topPadding, int rightPadding,
			int bottomPadding, int leftPadding) {
		this.insideBorder = BorderFactory.createEmptyBorder(topPadding,
				leftPadding, bottomPadding, rightPadding);
		this.setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(
			JList<? extends TextAndIcon> list, TextAndIcon value, int index,
			boolean isSelected, boolean cellHasFocus) {
		if (!(value instanceof TextAndIcon)) {
			try {
				throw new IOException("Object is not instanceof TextAndIcon");
			} catch (IOException var14) {
				var14.printStackTrace();
			} finally {
				System.exit(-1);
			}
			return null;
		} else {
			TextAndIcon tai = (TextAndIcon) value;
			this.setText(tai.getText());
			this.setIcon(tai.getIcon());
			if (isSelected) {
				this.setBackground(list.getSelectionBackground());
				this.setForeground(list.getSelectionForeground());
			} else {
				this.setBackground(list.getBackground());
				this.setForeground(list.getForeground());
			}
			Border outsideBorder;
			if (cellHasFocus) {
				outsideBorder = UIManager
						.getBorder("List.focusCellHighlightBorder");
			} else {
				outsideBorder = NO_FOCUS_BORDER;
			}

			this.setBorder(BorderFactory.createCompoundBorder(outsideBorder,
					this.insideBorder));
			this.setComponentOrientation(list.getComponentOrientation());
			this.setEnabled(list.isEnabled());
			this.setFont(list.getFont());
			return this;
		}
	}

	public void validate() {
	}

	public void invalidate() {
	}

	public void repaint() {
	}

	public void revalidate() {
	}

	public void repaint(long tm, int x, int y, int width, int height) {
	}

	public void repaint(Rectangle r) {
	}

}
