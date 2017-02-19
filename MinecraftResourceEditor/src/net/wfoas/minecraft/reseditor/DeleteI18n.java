package net.wfoas.minecraft.reseditor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DeleteI18n extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JButton okButton;

	public DeleteI18n(String language) {
		setTitle("Delete I18N Entry");
		setResizable(false);
		setBounds(100, 100, 450, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblDeleteIn = new JLabel("Delete I18n-Entry");
		lblDeleteIn.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeleteIn.setFont(new Font("Tahoma", Font.BOLD, 16));
		JLabel lblKey = new JLabel("Key:");
		textField = new JTextField();
		textField.setColumns(10);
		JLabel lblLanguage = new JLabel("Language:");
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		if (language != null) {
			textField_1.setEditable(false);
			textField_1.setText(language);
		}
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(lblDeleteIn, GroupLayout.PREFERRED_SIZE, 414, GroupLayout.PREFERRED_SIZE)
								.addGroup(Alignment.TRAILING,
										gl_contentPanel.createSequentialGroup()
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
														.addComponent(lblKey).addComponent(lblLanguage))
												.addPreferredGap(ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
												.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
														.addComponent(textField_1).addComponent(textField,
																GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE))))
						.addContainerGap()));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addContainerGap()
				.addComponent(lblDeleteIn, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE).addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblKey).addComponent(
						textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblLanguage)
						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE))
				.addContainerGap(128, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MinecraftResEditor.rese.deleteI18n(getKEYTextField().getText(), getLANGTextField_1().getText());
						MinecraftResEditor.rese.redesign();
						DeleteI18n.this.setVisible(false);
					}
				});
				okButton.setEnabled(false);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			DocumentListener dc = new DocumentListener() {
				public void changedUpdate(DocumentEvent e) {
					changed();
				}

				public void removeUpdate(DocumentEvent e) {
					changed();
				}

				public void insertUpdate(DocumentEvent e) {
					changed();
				}

				public void changed() {
					if ((!textField.getText().equals("")) && (!textField_1.getText().equals(""))
							&& textField.getText() != null && textField_1.getText() != null
							&& (!textField.getText().isEmpty()) && (!textField_1.getText().isEmpty())) {
						okButton.setEnabled(true);
					} else {
						okButton.setEnabled(false);
					}
				}
			};
			textField.getDocument().addDocumentListener(dc);
			textField_1.getDocument().addDocumentListener(dc);
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						DeleteI18n.this.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JButton getOkButton() {
		return okButton;
	}

	public JTextField getKEYTextField() {
		return textField;
	}

	public JTextField getLANGTextField_1() {
		return textField_1;
	}
}