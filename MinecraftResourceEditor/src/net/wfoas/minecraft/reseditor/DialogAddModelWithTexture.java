package net.wfoas.minecraft.reseditor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogAddModelWithTexture extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox comboBox;
	protected ResEditorWindow resedit;
	protected JButton okButton;

	public DialogAddModelWithTexture(ResEditorWindow resedit) {
		setResizable(false);
		this.resedit = resedit;
		setTitle("AddModelDialog - GameHelper");
		setBounds(100, 100, 450, 284);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		JLabel lblAddBkcm = new JLabel("Add Block with model and texture");
		lblAddBkcm.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddBkcm.setFont(new Font("Tahoma", Font.BOLD, 16));

		JLabel lblModid = new JLabel("ModID:");

		textField = new JTextField();
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
						&& (!textField_2.getText().equals("")) && textField.getText() != null
						&& textField_1.getText() != null && textField_2.getText() != null
						&& (!textField.getText().isEmpty()) && (!textField_1.getText().isEmpty())
						&& (!textField_2.getText().isEmpty())) {
					okButton.setEnabled(true);
				} else {
					okButton.setEnabled(false);
				}
			}
		};
		textField.getDocument().addDocumentListener(dc);
		textField.setColumns(10);

		JLabel lblBlockid = new JLabel("BlockID:");

		textField_1 = new JTextField();
		textField_1.getDocument().addDocumentListener(dc);
		textField_1.setColumns(10);

		JLabel lblModel = new JLabel("Model:");

		DefaultComboBoxModel<String> box = new DefaultComboBoxModel<>();
		addAll(resedit.readAvailModels(), box);
		comboBox = new JComboBox();
		comboBox.setModel(box);

		JLabel lblTexture = new JLabel("Texture:");

		JButton btnSearch = new JButton("Search...");
		FileFilter ff = new FileNameExtensionFilter("Minecraft-Texture", "png");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileFilter(ff);
				jfc.showOpenDialog(null);
				File f = jfc.getSelectedFile();
				if (f == null)
					return;
				if (f.isDirectory())
					return;
				textField_2.setText(f.getAbsolutePath());
			}
		});

		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.getDocument().addDocumentListener(dc);
		textField_2.setColumns(10);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addGroup(gl_contentPanel
						.createParallelGroup(Alignment.LEADING)
						.addComponent(lblAddBkcm, GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
						.addGroup(gl_contentPanel.createSequentialGroup()
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lblModid).addComponent(lblBlockid).addComponent(lblModel)
										.addComponent(lblTexture))
								.addGap(73)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(Alignment.TRAILING,
												gl_contentPanel.createSequentialGroup()
														.addComponent(textField_2, GroupLayout.DEFAULT_SIZE, 204,
																Short.MAX_VALUE)
														.addGap(18).addComponent(btnSearch))
										.addComponent(textField_1, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
										.addComponent(textField, GroupLayout.DEFAULT_SIZE, 299, Short.MAX_VALUE)
										.addComponent(comboBox, 0, 299, Short.MAX_VALUE))))
						.addContainerGap()));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING).addGroup(gl_contentPanel
				.createSequentialGroup().addContainerGap().addComponent(lblAddBkcm).addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblModid).addComponent(
						textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblBlockid).addComponent(
						textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblModel).addComponent(
						comboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblTexture)
						.addComponent(btnSearch).addComponent(textField_2, GroupLayout.PREFERRED_SIZE,
								GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(182, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setEnabled(false);
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// DO something
						MinecraftResEditor.addModel(getMIDTextField().getText(), getBIDTextField_1().getText(),
								(String) (getMDLComboBox().getSelectedItem()), getTEXPATHTextField_2().getText());
						resedit.redesign();
						DialogAddModelWithTexture.this.setVisible(false);
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
						DialogAddModelWithTexture.this.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public DefaultComboBoxModel<String> addAll(List<String> slist, DefaultComboBoxModel<String> sl) {
		for (String s : slist) {
			sl.addElement(s);
		}
		return sl;
	}

	public JTextField getMIDTextField() {
		return textField;
	}

	public JTextField getBIDTextField_1() {
		return textField_1;
	}

	public JComboBox getMDLComboBox() {
		return comboBox;
	}

	public JTextField getTEXPATHTextField_2() {
		return textField_2;
	}
}
