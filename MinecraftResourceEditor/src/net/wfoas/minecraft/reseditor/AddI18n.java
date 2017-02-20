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

public class AddI18n extends JDialog {

	private static final long serialVersionUID = -867534977469427004L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JButton okButton;

	public AddI18n(String language) {
		setResizable(false);
		setTitle("New I18N Entry");
		setBounds(100, 100, 450, 260);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblAddInentry = new JLabel("Add I18n-Entry");
		lblAddInentry.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddInentry.setFont(new Font("Tahoma", Font.BOLD, 16));
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
				if ((!textField.getText().equals(""))
						&& (!textField_1.getText().equals(""))
						&& (!textField_2.getText().equals(""))
						&& textField.getText() != null
						&& textField_1.getText() != null
						&& textField_2.getText() != null
						&& (!textField.getText().isEmpty())
						&& (!textField_1.getText().isEmpty())
						&& (!textField_2.getText().isEmpty())) {
					okButton.setEnabled(true);
				} else {
					okButton.setEnabled(false);
				}
			}
		};
		JLabel lblKey = new JLabel("Key:");
		textField = new JTextField();
		textField.setColumns(10);
		textField.getDocument().addDocumentListener(dc);
		JLabel lblValue = new JLabel("Value:");
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.getDocument().addDocumentListener(dc);
		JLabel lblLanguage = new JLabel("Language:");
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		if (language != null) {
			textField_2.setEditable(false);
			textField_2.setText(language);
		}
		textField_2.getDocument().addDocumentListener(dc);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_contentPanel
																		.createParallelGroup(
																				Alignment.LEADING,
																				false)
																		.addGroup(
																				gl_contentPanel
																						.createSequentialGroup()
																						.addComponent(
																								lblAddInentry,
																								GroupLayout.PREFERRED_SIZE,
																								414,
																								GroupLayout.PREFERRED_SIZE)
																						.addPreferredGap(
																								ComponentPlacement.RELATED))
																		.addGroup(
																				Alignment.TRAILING,
																				gl_contentPanel
																						.createSequentialGroup()
																						.addGroup(
																								gl_contentPanel
																										.createParallelGroup(
																												Alignment.LEADING)
																										.addComponent(
																												lblKey)
																										.addComponent(
																												lblValue))
																						.addPreferredGap(
																								ComponentPlacement.RELATED,
																								68,
																								Short.MAX_VALUE)
																						.addGroup(
																								gl_contentPanel
																										.createParallelGroup(
																												Alignment.LEADING,
																												false)
																										.addComponent(
																												textField_1)
																										.addComponent(
																												textField,
																												GroupLayout.DEFAULT_SIZE,
																												305,
																												Short.MAX_VALUE)
																										.addComponent(
																												textField_2))
																						.addGap(21)))
														.addComponent(
																lblLanguage))
										.addContainerGap(10, Short.MAX_VALUE)));
		gl_contentPanel
				.setVerticalGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_contentPanel
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(lblAddInentry,
												GroupLayout.PREFERRED_SIZE, 20,
												GroupLayout.PREFERRED_SIZE)
										.addGap(33)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblKey)
														.addComponent(
																textField,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(lblValue)
														.addComponent(
																textField_1,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addGap(18)
										.addGroup(
												gl_contentPanel
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(
																lblLanguage)
														.addComponent(
																textField_2,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE))
										.addContainerGap(65, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MinecraftResEditor.rese.addI18n(getKEYTextField()
								.getText(), getLANGTextField_2().getText(),
								getVALUETextField_1().getText());
						MinecraftResEditor.rese.redesign();
						AddI18n.this.setVisible(false);
					}
				});
				okButton.setEnabled(false);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						AddI18n.this.setVisible(false);
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

	public JTextField getVALUETextField_1() {
		return textField_1;
	}

	public JTextField getLANGTextField_2() {
		return textField_2;
	}
}
