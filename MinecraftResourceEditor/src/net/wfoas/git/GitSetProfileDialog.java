package net.wfoas.git;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;

import net.wfoas.minecraft.reseditor.MinecraftResEditor;
import net.wfoas.minecraft.reseditor.ResEditorWindow;

import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class GitSetProfileDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	public GitSetProfileDialog() {
		setTitle("Change Global Git Profile");
		setResizable(false);
		setBounds(100, 100, 450, 278);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		JLabel lblChangeGlobalGit = new JLabel("Change Global Git Profile");
		lblChangeGlobalGit.setHorizontalAlignment(SwingConstants.CENTER);
		lblChangeGlobalGit.setFont(new Font("Tahoma", Font.PLAIN, 17));
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(
				new TitledBorder(null, "Use other profile...", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JRadioButton rdbtnOtherProfile = new JRadioButton("Other Profile");
		rdbtnOtherProfile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!rdbtnOtherProfile.isSelected()) {
					getUNTextField().setEditable(false);
					getMATextField_1().setEditable(false);
				} else {
					getUNTextField().setEditable(true);
					getMATextField_1().setEditable(true);
				}
			}
		});
		buttonGroup.add(rdbtnOtherProfile);

		JRadioButton rdbtnGithubProfile = new JRadioButton("GitHub Profile");
		rdbtnGithubProfile.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!rdbtnOtherProfile.isSelected()) {
					getUNTextField().setEditable(false);
					getMATextField_1().setEditable(false);
				} else {
					getUNTextField().setEditable(true);
					getMATextField_1().setEditable(true);
				}
			}
		});
		buttonGroup.add(rdbtnGithubProfile);
		rdbtnGithubProfile.setSelected(true);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(rdbtnOtherProfile)
										.addContainerGap())
								.addGroup(
										gl_contentPanel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_contentPanel.createSequentialGroup()
														.addComponent(rdbtnGithubProfile).addContainerGap())
												.addGroup(Alignment.TRAILING, gl_contentPanel.createSequentialGroup()
														.addComponent(lblChangeGlobalGit, GroupLayout.DEFAULT_SIZE, 414,
																Short.MAX_VALUE)
														.addContainerGap()))
								.addGroup(Alignment.TRAILING,
										gl_contentPanel.createSequentialGroup()
												.addComponent(panel_1, GroupLayout.DEFAULT_SIZE, 424, Short.MAX_VALUE)
												.addContainerGap()))));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addComponent(lblChangeGlobalGit)
						.addGap(18).addComponent(rdbtnGithubProfile).addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(rdbtnOtherProfile).addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE)
						.addGap(85)));
		JLabel lblUser = new JLabel("Username: ");
		JLabel lblEmail = new JLabel("Email:");

		textField = new JTextField();
		textField.setEditable(false);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1.setHorizontalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_1.createSequentialGroup().addGap(34)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addComponent(lblUser)
								.addComponent(lblEmail))
						.addGap(18)
						.addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE).addComponent(
										textField, GroupLayout.DEFAULT_SIZE, 295, Short.MAX_VALUE))
						.addContainerGap()));
		gl_panel_1.setVerticalGroup(gl_panel_1.createParallelGroup(Alignment.LEADING).addGroup(gl_panel_1
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(lblUser).addComponent(
						textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE).addComponent(lblEmail).addComponent(
						textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(23, Short.MAX_VALUE)));
		panel_1.setLayout(gl_panel_1);
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if (rdbtnGithubProfile.isSelected()) {
							updateGlobalGitProfile(MinecraftResEditor.getMail(), MinecraftResEditor.getName());
						} else {
							updateGlobalGitProfile(getMATextField_1().getText(), getUNTextField().getText());
						}
						GitSetProfileDialog.this.dispose();
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
						GitSetProfileDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public void updateGlobalGitProfile(String mail, String name) {
		MinecraftResEditor.rese.git.getRepository().getConfig().setString("user", null, "name", mail);
		MinecraftResEditor.rese.git.getRepository().getConfig().setString("user", null, "email", mail);
	}

	public JTextField getUNTextField() {
		return textField;
	}

	public JTextField getMATextField_1() {
		return textField_1;
	}
}