package net.wfoas.woma.slgh.ncr;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.wfoas.minecraft.reseditor.MinecraftResEditor;
import net.wfoas.minecraft.reseditor.ResEditorWindow;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JComboBox;

public class DeleteNote extends JDialog {

	private static final long serialVersionUID = -2104080711841229799L;
	private final JPanel contentPanel = new JPanel();
	private JLabel lblDeleteBlock;

	public DefaultComboBoxModel<String> addAll(List<String> slist, DefaultComboBoxModel<String> sl) {
		for (String s : slist) {
			sl.addElement(s);
		}
		return sl;
	}

	public DeleteNote(ResEditorWindow resedit) {
		setTitle("Delete Note");
		setResizable(false);
		setBounds(100, 100, 450, 190);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblDeleteBlock = new JLabel("Delete Note");
			lblDeleteBlock.setHorizontalAlignment(SwingConstants.CENTER);
			lblDeleteBlock.setFont(new Font("Tahoma", Font.BOLD, 16));
		}
		JLabel lblBlock = new JLabel("Note:");
		JComboBox<String> comboBox = new JComboBox<String>();
		DefaultComboBoxModel<String> box = new DefaultComboBoxModel<>();
		addAll(SLGH.getNotes(), box);
		comboBox.setModel(box);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDeleteBlock, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 414,
										Short.MAX_VALUE)
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblBlock).addGap(139)
										.addComponent(comboBox, 0, 236, Short.MAX_VALUE)))
						.addContainerGap()));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addGap(5).addComponent(lblDeleteBlock).addGap(28)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblBlock).addComponent(
						comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(162, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						SLGH.deleteNote((String) comboBox.getSelectedItem());
						resedit.redesign();
						DeleteNote.this.setVisible(false);
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DeleteNote.this.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}