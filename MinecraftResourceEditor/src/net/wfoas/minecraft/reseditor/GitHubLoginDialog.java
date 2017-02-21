package net.wfoas.minecraft.reseditor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.client.GitHubRequest;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.event.ActionEvent;
import javax.swing.JCheckBox;

public class GitHubLoginDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	private JCheckBox chckbxStayLoggedInt;

	public GitHubLoginDialog() {
		setTitle("MinecraftResEditor - GitHub - Login");
		setResizable(false);
		setBounds(100, 100, 450, 260);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblNewLabel = new JLabel("GitHub - Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblUsername = new JLabel("Username:");

		textField = new JTextField();
		textField.setColumns(10);

		JLabel lblEmail = new JLabel("Email:");

		textField_1 = new JTextField();
		textField_1.setColumns(10);

		JLabel lblPassword = new JLabel("Password:");

		passwordField = new JPasswordField();

		chckbxStayLoggedInt = new JCheckBox("Remember me");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblUsername).addComponent(lblEmail).addComponent(lblPassword))
								.addGap(112).addGroup(
										gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 250,
														Short.MAX_VALUE)
												.addComponent(textField, GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE)
												.addComponent(passwordField, GroupLayout.DEFAULT_SIZE, 250,
														Short.MAX_VALUE)
												.addComponent(chckbxStayLoggedInt))))
						.addContainerGap()));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addComponent(lblNewLabel).addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblUsername).addComponent(textField, GroupLayout.PREFERRED_SIZE,
										GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblEmail)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18)
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblPassword)
								.addComponent(passwordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
										GroupLayout.PREFERRED_SIZE))
						.addGap(18).addComponent(chckbxStayLoggedInt).addContainerGap(21, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// GitHubClient client = GitHubClient
						// .createClient("https://github.com/wfoasm-woma-net/gamehelper-mc-189")
						// .setCredentials(getUSERTextField().getText(),
						// getPasswordField().getText());
						// try {
						// client.get(new GitHubRequest().setUri("/user"));
						// } catch (IOException e1) {
						// e1.printStackTrace();
						// }

						//
						if (getChckbxStayLoggedInt().isSelected()) {
							MinecraftResEditor.saveProperties(getUSERTextField().getText(),
									getEMAILTextField_1().getText(), getPasswordField().getText());
						}
						MinecraftResEditor.startRegular();
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
						System.exit(0);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public JTextField getUSERTextField() {
		return textField;
	}

	public JTextField getEMAILTextField_1() {
		return textField_1;
	}

	public JPasswordField getPasswordField() {
		return passwordField;
	}

	public JCheckBox getChckbxStayLoggedInt() {
		return chckbxStayLoggedInt;
	}
}