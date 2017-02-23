package net.wfoas.woma.slgh.ncr;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import net.wfoas.minecraft.reseditor.MinecraftResEditor;

import java.awt.Toolkit;

public class NewCRDialog extends JDialog {

	private static final long serialVersionUID = 6511622682612440595L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	public NewCRDialog() {
		setResizable(false);
		setTitle("Neues Rezept erstellen...");
		setBounds(100, 100, 450, 240);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblNeuenOrdnerErstellen = new JLabel("Neues Rezept erstellen...");
			lblNeuenOrdnerErstellen.setBounds(134, 10, 165, 19);
			lblNeuenOrdnerErstellen.setFont(new Font("Tahoma", Font.PLAIN, 15));
			contentPanel.add(lblNeuenOrdnerErstellen);
		}

		textField = new JTextField();
		textField.setBounds(134, 78, 244, 20);
		contentPanel.add(textField);
		textField.setColumns(10);

		JLabel lblOrdnername = new JLabel("Rezeptname:");
		lblOrdnername.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblOrdnername.setBounds(36, 79, 87, 14);
		contentPanel.add(lblOrdnername);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (textField.getText() == null) {
							JOptionPane.showMessageDialog(null, "Es muss eine Name eingegeben werden.(NULL)", "Fehler",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						if (textField.getText().equalsIgnoreCase("")) {
							JOptionPane.showMessageDialog(null, "Es muss eine Name eingegeben werden.(EMPT)", "Fehler",
									JOptionPane.ERROR_MESSAGE);
							return;
						}
						SLGH.createCR(MinecraftResEditor.getName(), textField.getText());
						NewCRDialog.this.dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Abbrechen");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						NewCRDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}