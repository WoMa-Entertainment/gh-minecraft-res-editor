package net.wfoas.minecraft.reseditor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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

public class DialogDeleteBlock extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblDeleteBlock;

	public DefaultComboBoxModel<String> addAll(List<String> slist, DefaultComboBoxModel<String> sl) {
		for (String s : slist) {
			sl.addElement(s);
		}
		return sl;
	}

	public DialogDeleteBlock(ResEditorWindow resedit) {
		setTitle("Delete Block");
		setResizable(false);
		setBounds(100, 100, 450, 190);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			lblDeleteBlock = new JLabel("Delete Block");
			lblDeleteBlock.setHorizontalAlignment(SwingConstants.CENTER);
			lblDeleteBlock.setFont(new Font("Tahoma", Font.BOLD, 16));
		}
		JLabel lblBlock = new JLabel("BlockID:");
		JComboBox comboBox = new JComboBox();
		DefaultComboBoxModel<String> box = new DefaultComboBoxModel<>();
		addAll(resedit.readModels(), box);
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
						MinecraftResEditor.deleteModel((String) comboBox.getSelectedItem());
						resedit.redesign();
						DialogDeleteBlock.this.setVisible(false);
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
						DialogDeleteBlock.this.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
