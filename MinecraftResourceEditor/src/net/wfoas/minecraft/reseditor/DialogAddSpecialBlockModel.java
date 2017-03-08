package net.wfoas.minecraft.reseditor;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

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

import net.wfoas.minecraft.reseditor.TextureCollector.TexMode;

import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class DialogAddSpecialBlockModel extends JDialog {

	private static final long serialVersionUID = -7653180994513561554L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox<String> comboBox;
	protected ResEditorWindow resedit;
	protected JButton okButton;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnSingle;
	private JRadioButton rdbtnSides;
	private JRadioButton rdbtnTexturemodeAll;
	private JButton button_4;
	private JButton button;
	private JButton button_1;
	private JButton button_2;
	private JButton btnSearch;
	private JButton button_3;

	public boolean mayNotBeEmpty(JTextField textField) {
		return !textField.getText().equals("") && textField.getText() != null && (!textField.getText().isEmpty());
	}

	public void changed() {
		if ((!textField.getText().equals("")) && (!textField_1.getText().equals(""))
				&& (!textField_2.getText().equals("")) && (!textField_3.getText().equals(""))
				&& (!textField_3.getText().equals("")) && textField.getText() != null && textField_1.getText() != null
				&& textField_2.getText() != null && textField_3.getText() != null && textField_4.getText() != null
				&& (!textField.getText().isEmpty()) && (!textField_1.getText().isEmpty())
				&& (!textField_2.getText().isEmpty()) && (!textField_3.getText().isEmpty())
				&& (!textField_4.getText().isEmpty())) {
			if (rdbtnTexturemodeAll.isSelected()) {
				okButton.setEnabled(true);
			} else if (rdbtnSides.isSelected()) {
				if (mayNotBeEmpty(textField_5) && mayNotBeEmpty(textField_7) && mayNotBeEmpty(textField_9)) {
					okButton.setEnabled(true);
				} else {
					okButton.setEnabled(false);
				}
			} else if (rdbtnSingle.isSelected()) {
				if (mayNotBeEmpty(textField_5) && mayNotBeEmpty(textField_7) && mayNotBeEmpty(textField_9)
						&& mayNotBeEmpty(textField_6) && mayNotBeEmpty(textField_8)) {
					okButton.setEnabled(true);
				} else {
					okButton.setEnabled(false);
				}
			}
		} else {
			okButton.setEnabled(false);
		}
	}

	public void boxInit() {
		String name = comboBox.getSelectedItem().toString();
		Properties p = new Properties();
		try {
			FileInputStream fis = new FileInputStream(
					new File(MinecraftResEditor.rese.repository, "minecraft-res-editor/special/" + name + ".res-desc"));
			p.load(fis);
			fis.close();
		} catch (IOException eww) {
			eww.printStackTrace();
			return;
		}
		TexMode s = TexMode.valueOf(p.getProperty("TextureMode").toUpperCase());
		if (s == TexMode.ALL) {
			rdbtnTexturemodeAll.setSelected(true);
			rdbtnSingle.setSelected(false);
			rdbtnSides.setSelected(false);
			//
			button.setEnabled(false);
			button_1.setEnabled(false);
			button_2.setEnabled(false);
			button_3.setEnabled(false);
			button_4.setEnabled(false);
			changed();
		} else if (s == TexMode.SIDES) {
			rdbtnTexturemodeAll.setSelected(false);
			rdbtnSingle.setSelected(false);
			rdbtnSides.setSelected(true);
			//
			button.setEnabled(true);
			button_1.setEnabled(false);
			button_2.setEnabled(true);
			button_3.setEnabled(false);
			button_4.setEnabled(true);
			changed();
		} else if (s == TexMode.SINGLE) {
			rdbtnTexturemodeAll.setSelected(false);
			rdbtnSingle.setSelected(true);
			rdbtnSides.setSelected(false);
			//
			button.setEnabled(true);
			button_1.setEnabled(true);
			button_2.setEnabled(true);
			button_3.setEnabled(true);
			button_4.setEnabled(true);
			changed();
		}

	}

	public DialogAddSpecialBlockModel(ResEditorWindow resedit) {
		setResizable(false);
		this.resedit = resedit;
		setTitle("AddModelDialog - GameHelper");
		setBounds(100, 100, 450, 603);
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

		};
		textField.getDocument().addDocumentListener(dc);
		textField.setColumns(10);

		JLabel lblBlockid = new JLabel("BlockID:");

		textField_1 = new JTextField();
		textField_1.getDocument().addDocumentListener(dc);
		textField_1.setColumns(10);

		JLabel lblModel = new JLabel("SpecialModel:");

		DefaultComboBoxModel<String> box = new DefaultComboBoxModel<>();
		addAll(resedit.readAvailSpecialModels(), box);
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = comboBox.getSelectedItem().toString();
				Properties p = new Properties();
				try {
					FileInputStream fis = new FileInputStream(new File(MinecraftResEditor.rese.repository,
							"minecraft-res-editor/special/" + name + ".res-desc"));
					p.load(fis);
					fis.close();
				} catch (IOException eww) {
					eww.printStackTrace();
					return;
				}
				TexMode s = TexMode.valueOf(p.getProperty("TextureMode").toUpperCase());
				if (s == TexMode.ALL) {
					rdbtnTexturemodeAll.setSelected(true);
					rdbtnSingle.setSelected(false);
					rdbtnSides.setSelected(false);
					//
					button.setEnabled(false);
					button_1.setEnabled(false);
					button_2.setEnabled(false);
					button_3.setEnabled(false);
					button_4.setEnabled(false);
					changed();
				} else if (s == TexMode.SIDES) {
					rdbtnTexturemodeAll.setSelected(false);
					rdbtnSingle.setSelected(false);
					rdbtnSides.setSelected(true);
					//
					button.setEnabled(true);
					button_1.setEnabled(false);
					button_2.setEnabled(true);
					button_3.setEnabled(false);
					button_4.setEnabled(true);
					changed();
				} else if (s == TexMode.SINGLE) {
					rdbtnTexturemodeAll.setSelected(false);
					rdbtnSingle.setSelected(true);
					rdbtnSides.setSelected(false);
					//
					button.setEnabled(true);
					button_1.setEnabled(true);
					button_2.setEnabled(true);
					button_3.setEnabled(true);
					button_4.setEnabled(true);
					changed();
				}
			}
		});
		comboBox.setModel(box);
		JLabel lblTexture = new JLabel("Texture[up/normal/all]:");

		btnSearch = new JButton("Search...");
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

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "I18n", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JLabel lblTextureleftside = new JLabel("Texture[west/side]:");

		textField_5 = new JTextField();
		textField_5.setEditable(false);
		textField_5.setColumns(10);

		button = new JButton("Search...");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileFilter(ff);
				jfc.showOpenDialog(null);
				File f = jfc.getSelectedFile();
				if (f == null)
					return;
				if (f.isDirectory())
					return;
				textField_5.setText(f.getAbsolutePath());
			}
		});
		button.setEnabled(false);

		JLabel lblTextureright = new JLabel("Texture[east]:");

		textField_6 = new JTextField();
		textField_6.setEditable(false);
		textField_6.setColumns(10);

		button_1 = new JButton("Search...");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileFilter(ff);
				jfc.showOpenDialog(null);
				File f = jfc.getSelectedFile();
				if (f == null)
					return;
				if (f.isDirectory())
					return;
				textField_6.setText(f.getAbsolutePath());
			}
		});
		button_1.setEnabled(false);

		button_2 = new JButton("Search...");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileFilter(ff);
				jfc.showOpenDialog(null);
				File f = jfc.getSelectedFile();
				if (f == null)
					return;
				if (f.isDirectory())
					return;
				textField_7.setText(f.getAbsolutePath());
			}
		});
		button_2.setEnabled(false);

		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setColumns(10);

		JLabel lblTexturenorth = new JLabel("Texture[north/front]:");

		button_3 = new JButton("Search...");
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileFilter(ff);
				jfc.showOpenDialog(null);
				File f = jfc.getSelectedFile();
				if (f == null)
					return;
				if (f.isDirectory())
					return;
				textField_8.setText(f.getAbsolutePath());
			}
		});
		button_3.setEnabled(false);

		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setColumns(10);

		JLabel lblTexturesouth = new JLabel("Texture[south]:");

		button_4 = new JButton("Search...");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser();
				jfc.setFileFilter(ff);
				jfc.showOpenDialog(null);
				File f = jfc.getSelectedFile();
				if (f == null)
					return;
				if (f.isDirectory())
					return;
				textField_9.setText(f.getAbsolutePath());
			}
		});
		button_4.setEnabled(false);

		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setColumns(10);

		JLabel lblTexturebottom = new JLabel("Texture[down/bottom]:");

		rdbtnTexturemodeAll = new JRadioButton("TextureMode: all");
		rdbtnTexturemodeAll.setEnabled(false);
		rdbtnTexturemodeAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setEnabled(false);
				button_1.setEnabled(false);
				button_2.setEnabled(false);
				button_3.setEnabled(false);
				button_4.setEnabled(false);
				changed();
			}
		});
		buttonGroup.add(rdbtnTexturemodeAll);
		rdbtnTexturemodeAll.setSelected(true);

		rdbtnSides = new JRadioButton("sides");
		rdbtnSides.setEnabled(false);
		rdbtnSides.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setEnabled(true);
				button_1.setEnabled(false);
				button_2.setEnabled(true);
				button_3.setEnabled(false);
				button_4.setEnabled(true);
				changed();
			}
		});
		buttonGroup.add(rdbtnSides);

		rdbtnSingle = new JRadioButton("single");
		rdbtnSingle.setEnabled(false);
		rdbtnSingle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				button.setEnabled(true);
				button_1.setEnabled(true);
				button_2.setEnabled(true);
				button_3.setEnabled(true);
				button_4.setEnabled(true);
				changed();
			}
		});
		buttonGroup.add(rdbtnSingle);
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel
				.setHorizontalGroup(
						gl_contentPanel.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap().addGroup(
										gl_contentPanel
												.createParallelGroup(
														Alignment.LEADING)
												.addGroup(gl_contentPanel.createSequentialGroup()
														.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
																.addComponent(lblAddBkcm, Alignment.TRAILING,
																		GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
																.addGroup(Alignment.TRAILING,
																		gl_contentPanel.createSequentialGroup()
																				.addComponent(lblTextureright,
																						GroupLayout.PREFERRED_SIZE, 102,
																						GroupLayout.PREFERRED_SIZE)
																				.addGap(73)
																				.addComponent(textField_6,
																						GroupLayout.PREFERRED_SIZE, 152,
																						GroupLayout.PREFERRED_SIZE)
																				.addGap(10).addComponent(button_1,
																						GroupLayout.PREFERRED_SIZE, 77,
																						GroupLayout.PREFERRED_SIZE))
																.addGroup(Alignment.TRAILING, gl_contentPanel
																		.createSequentialGroup()
																		.addComponent(lblTexturenorth,
																				GroupLayout.PREFERRED_SIZE, 133,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(ComponentPlacement.RELATED, 42,
																				Short.MAX_VALUE)
																		.addComponent(textField_7,
																				GroupLayout.PREFERRED_SIZE, 152,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(10).addComponent(button_2,
																				GroupLayout.PREFERRED_SIZE, 77,
																				GroupLayout.PREFERRED_SIZE))
																.addGroup(Alignment.TRAILING,
																		gl_contentPanel.createSequentialGroup()
																				.addComponent(lblTexturesouth,
																						GroupLayout.PREFERRED_SIZE, 102,
																						GroupLayout.PREFERRED_SIZE)
																				.addGap(73)
																				.addComponent(textField_8,
																						GroupLayout.PREFERRED_SIZE, 152,
																						GroupLayout.PREFERRED_SIZE)
																				.addGap(10).addComponent(button_3,
																						GroupLayout.PREFERRED_SIZE, 77,
																						GroupLayout.PREFERRED_SIZE))
																.addGroup(Alignment.TRAILING,
																		gl_contentPanel.createSequentialGroup()
																				.addComponent(lblTexturebottom)
																				.addGap(61)
																				.addComponent(textField_9,
																						GroupLayout.PREFERRED_SIZE, 152,
																						GroupLayout.PREFERRED_SIZE)
																				.addGap(10).addComponent(button_4,
																						GroupLayout.PREFERRED_SIZE, 77,
																						GroupLayout.PREFERRED_SIZE))
																.addGroup(Alignment.TRAILING, gl_contentPanel
																		.createSequentialGroup()
																		.addGroup(gl_contentPanel
																				.createParallelGroup(Alignment.LEADING)
																				.addGroup(Alignment.TRAILING,
																						gl_contentPanel
																								.createSequentialGroup()
																								.addComponent(
																										lblTextureleftside,
																										GroupLayout.PREFERRED_SIZE,
																										102,
																										GroupLayout.PREFERRED_SIZE)
																								.addGap(73))
																				.addGroup(gl_contentPanel
																						.createSequentialGroup()
																						.addGroup(gl_contentPanel
																								.createParallelGroup(
																										Alignment.LEADING)
																								.addComponent(lblModid)
																								.addComponent(
																										lblBlockid)
																								.addComponent(lblModel)
																								.addComponent(
																										lblTexture)
																								.addComponent(
																										rdbtnTexturemodeAll))
																						.addGap(64)))
																		.addGroup(gl_contentPanel
																				.createParallelGroup(Alignment.LEADING)
																				.addGroup(gl_contentPanel
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(Alignment.TRAILING,
																								gl_contentPanel
																										.createSequentialGroup()
																										.addGap(19)
																										.addComponent(
																												rdbtnSides)
																										.addPreferredGap(
																												ComponentPlacement.RELATED,
																												122,
																												Short.MAX_VALUE)
																										.addComponent(
																												rdbtnSingle))
																						.addComponent(textField_1,
																								Alignment.TRAILING,
																								GroupLayout.DEFAULT_SIZE,
																								243, Short.MAX_VALUE)
																						.addComponent(textField,
																								Alignment.TRAILING,
																								GroupLayout.DEFAULT_SIZE,
																								243, Short.MAX_VALUE)
																						.addComponent(comboBox,
																								Alignment.TRAILING, 0,
																								243, Short.MAX_VALUE)
																						.addGroup(Alignment.TRAILING,
																								gl_contentPanel
																										.createSequentialGroup()
																										.addComponent(
																												textField_2,
																												GroupLayout.DEFAULT_SIZE,
																												152,
																												Short.MAX_VALUE)
																										.addPreferredGap(
																												ComponentPlacement.UNRELATED)
																										.addComponent(
																												btnSearch)))
																				.addGroup(Alignment.TRAILING,
																						gl_contentPanel
																								.createSequentialGroup()
																								.addComponent(
																										textField_5,
																										GroupLayout.PREFERRED_SIZE,
																										152,
																										GroupLayout.PREFERRED_SIZE)
																								.addGap(10)
																								.addComponent(button,
																										GroupLayout.PREFERRED_SIZE,
																										77,
																										GroupLayout.PREFERRED_SIZE)))))
														.addGap(10))
												.addGroup(gl_contentPanel
														.createSequentialGroup().addComponent(panel,
																GroupLayout.DEFAULT_SIZE, 414, Short.MAX_VALUE)
														.addContainerGap()))));
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
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(rdbtnTexturemodeAll)
						.addComponent(rdbtnSingle).addComponent(rdbtnSides))
				.addPreferredGap(ComponentPlacement.RELATED)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE).addComponent(lblTexture)
						.addComponent(textField_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
								GroupLayout.PREFERRED_SIZE)
						.addComponent(btnSearch))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(4).addComponent(lblTextureleftside))
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(1).addComponent(textField_5,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(button))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(4).addComponent(lblTextureright))
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(1).addComponent(textField_6,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(button_1))
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup().addGap(1).addComponent(
												textField_7, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
										.addComponent(button_2)))
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(22).addComponent(lblTexturenorth)))
				.addGap(18)
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(4).addComponent(lblTexturesouth))
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(1).addComponent(textField_8,
								GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(button_3))
				.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(18)
								.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
										.addGroup(gl_contentPanel.createSequentialGroup().addGap(1).addComponent(
												textField_9, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
												GroupLayout.PREFERRED_SIZE))
										.addComponent(button_4)))
						.addGroup(gl_contentPanel.createSequentialGroup().addGap(22).addComponent(lblTexturebottom)))
				.addGap(18).addComponent(panel, GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE).addGap(79)));

		JLabel lblGermanName = new JLabel("German Name:");

		JLabel lblEnglishName = new JLabel("English Name:");

		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.getDocument().addDocumentListener(dc);
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.getDocument().addDocumentListener(dc);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup().addContainerGap()
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING).addComponent(lblGermanName)
								.addComponent(lblEnglishName))
						.addGap(26)
						.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(textField_4, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE).addComponent(
										textField_3, GroupLayout.DEFAULT_SIZE, 285, Short.MAX_VALUE))
						.addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(Alignment.LEADING).addGroup(gl_panel
				.createSequentialGroup().addContainerGap()
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblGermanName).addComponent(
						textField_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addPreferredGap(ComponentPlacement.UNRELATED)
				.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE).addComponent(lblEnglishName).addComponent(
						textField_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
				.addContainerGap(13, Short.MAX_VALUE)));
		panel.setLayout(gl_panel);
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
						TextureCollector.TexMode mode = TexMode.ALL;
						if (rdbtnTexturemodeAll.isSelected())
							;
						else if (rdbtnSides.isSelected())
							mode = TexMode.SIDES;
						else if (rdbtnSingle.isSelected())
							mode = TexMode.SINGLE;
						TextureCollector texc;
						if (mode == TexMode.SINGLE) {
							texc = new TextureCollector(textField_2.getText(), textField_5.getText(),
									textField_6.getText(), textField_7.getText(), textField_8.getText(),
									textField_9.getText(), mode);
						} else if (mode == TexMode.SIDES) {
							texc = new TextureCollector(textField_2.getText(), textField_5.getText(), null,
									textField_7.getText(), null, textField_9.getText(), mode);
						} else {
							texc = new TextureCollector(textField_2.getText(), null, null, null, null, null, mode);
						}
						MinecraftResEditor.addSpecialBlockModelWithI18n(getMIDTextField().getText(),
								getBIDTextField_1().getText(), (String) (getMDLComboBox().getSelectedItem()), texc,
								textField_3.getText(), textField_4.getText());
						resedit.redesign();
						DialogAddSpecialBlockModel.this.setVisible(false);
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
						DialogAddSpecialBlockModel.this.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		boxInit();
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

	public JComboBox<String> getMDLComboBox() {
		return comboBox;
	}

	public JTextField getTEXPATHTextField_2() {
		return textField_2;
	}

	public JTextField geti18ngermanTextField_3() {
		return textField_3;
	}

	public JTextField geti18nenglishTextField_4() {
		return textField_4;
	}

	public JRadioButton getRdbtnSingle() {
		return rdbtnSingle;
	}

	public JRadioButton getRdbtnSides() {
		return rdbtnSides;
	}

	public JRadioButton getRdbtnTexturemodeAll() {
		return rdbtnTexturemodeAll;
	}

	public JButton getButton_4() {
		return button_4;
	}

	public JButton getButton() {
		return button;
	}

	public JButton getButton_1() {
		return button_1;
	}

	public JButton getButton_2() {
		return button_2;
	}

	public JButton getBtnSearch() {
		return btnSearch;
	}

	public JButton getButton_3() {
		return button_3;
	}
}
