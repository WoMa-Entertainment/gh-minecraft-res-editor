package net.wfoas.minecraft.reseditor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import net.wfoas.minecraft.reseditor.notescr.InfoNote;
import net.wfoas.minecraft.reseditor.textandiconlist.DisplayableEntry;
import net.wfoas.minecraft.reseditor.textandiconlist.TextAndIcon;
import net.wfoas.minecraft.reseditor.textandiconlist.TextAndIconList;

public class ResEditorWindow extends JFrame {

	private static final long serialVersionUID = 6229273644008449868L;
	private JPanel contentPane;
	File repository;
	JPanel panel, panel_1, panel_35;

	public ResEditorWindow() {
		setTitle("MinecraftResourceEditor - GameHelper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 480);
		slghInit();
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnGamehelperOpen = new JMenu("GameHelper");
		menuBar.add(mnGamehelperOpen);

		JMenuItem mntmOpenRepository = new JMenuItem("Open Repository...");
		mntmOpenRepository.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		mnGamehelperOpen.add(mntmOpenRepository);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		panel = new JPanel();
		tabbedPane.addTab("Language", null, panel, "Change Language files...");

		panel_1 = new JPanel();
		tabbedPane.addTab("[Block] Models and Textures", null, panel_1,
				"Change models and textures of blocks...");

		panel_35 = new JPanel();
		tabbedPane
				.addTab("SLGH",
						null,
						panel_35,
						"<html><body>View & edit old SLGH_FTPE projects...<br>This tool is only capable of editing stuff in the GameHelper repository</body></html>");
		redesign();
	}

	public void redesign() {
		panel.setLayout(null);
		panel.removeAll();
		panel_1.setLayout(null);
		panel_1.removeAll();
		panel_35.setLayout(null);
		panel_35.removeAll();
		JTabbedPane tabbedPane_1 = new JTabbedPane(JTabbedPane.TOP);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(tabbedPane_1, GroupLayout.DEFAULT_SIZE,
								569, Short.MAX_VALUE).addContainerGap()));
		gl_panel.setVerticalGroup(gl_panel.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel.createSequentialGroup()
						.addGap(5)
						.addComponent(tabbedPane_1, GroupLayout.DEFAULT_SIZE,
								369, Short.MAX_VALUE).addContainerGap()));

		JPanel panel_2 = new JPanel();
		tabbedPane_1.addTab("German", null, panel_2, "Edit German language...");
		JScrollPane scrollPane = new JScrollPane();
		JButton btnSave = new JButton("Save");
		btnSave.setVisible(false);
		JButton btnReload = new JButton("Reload from Source");

		JButton btnAddIn = new JButton("Add i18n...");
		btnAddIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//
				AddI18n mod = new AddI18n("de_DE");
				mod.setVisible(true);
			}
		});

		JButton btnDeleteIn = new JButton("Delete i18n...");
		btnDeleteIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteI18n mod = new DeleteI18n("de_DE");
				mod.setVisible(true);
			}
		});
		GroupLayout gl_panel_2 = new GroupLayout(panel_2);
		gl_panel_2
				.setHorizontalGroup(gl_panel_2
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_2
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel_2
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																scrollPane,
																GroupLayout.DEFAULT_SIZE,
																554,
																Short.MAX_VALUE)
														.addGroup(
																gl_panel_2
																		.createSequentialGroup()
																		.addComponent(
																				btnSave)
																		.addGap(18)
																		.addComponent(
																				btnDeleteIn)
																		.addGap(18)
																		.addComponent(
																				btnAddIn)
																		.addGap(18)
																		.addComponent(
																				btnReload)))
										.addContainerGap()));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						gl_panel_2
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 283,
										Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(
										gl_panel_2
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(btnReload)
												.addComponent(btnAddIn)
												.addComponent(btnDeleteIn)
												.addComponent(btnSave))
								.addContainerGap()));

		JTextPane textPane = new JTextPane();
		textPane.setEditable(false);
		scrollPane.setViewportView(textPane);
		reloadText("de_DE", textPane);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// saveText("de_DE", textPane);
			}
		});
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadText("de_DE", textPane);
			}
		});
		panel_2.setLayout(gl_panel_2);
		JPanel panel_3 = new JPanel();
		tabbedPane_1.addTab("English", null, panel_3,
				"Edit English language...");

		JScrollPane scrollPane_1 = new JScrollPane();

		JButton button = new JButton("Reload from Source");

		JButton button_1 = new JButton("Save");
		button_1.setVisible(false);
		JButton btnAddIn_1 = new JButton("Add i18n...");
		btnAddIn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddI18n mod = new AddI18n("en_US");
				mod.setVisible(true);
			}
		});

		JButton btnDeleteIn_1 = new JButton("Delete i18n...");
		btnDeleteIn_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DeleteI18n mod = new DeleteI18n("en_US");
				mod.setVisible(true);
			}
		});
		GroupLayout gl_panel_3 = new GroupLayout(panel_3);
		gl_panel_3
				.setHorizontalGroup(gl_panel_3
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_3
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel_3
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																scrollPane_1,
																GroupLayout.DEFAULT_SIZE,
																554,
																Short.MAX_VALUE)
														.addGroup(
																gl_panel_3
																		.createSequentialGroup()
																		.addComponent(
																				button_1,
																				GroupLayout.PREFERRED_SIZE,
																				57,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(18)
																		.addComponent(
																				btnDeleteIn_1)
																		.addGap(18)
																		.addComponent(
																				btnAddIn_1)
																		.addGap(18)
																		.addComponent(
																				button,
																				GroupLayout.PREFERRED_SIZE,
																				127,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						gl_panel_3
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane_1,
										GroupLayout.DEFAULT_SIZE, 283,
										Short.MAX_VALUE)
								.addGap(11)
								.addGroup(
										gl_panel_3
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(button)
												.addComponent(btnAddIn_1)
												.addComponent(btnDeleteIn_1)
												.addComponent(button_1))
								.addContainerGap()));

		JTextPane textPane_1 = new JTextPane();
		textPane_1.setEditable(false);
		reloadText("en_US", textPane_1);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// saveText("en_US", textPane_1);
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reloadText("en_US", textPane_1);
			}
		});
		scrollPane_1.setViewportView(textPane_1);
		panel_3.setLayout(gl_panel_3);
		panel.setLayout(gl_panel);
		JPanel panel_21 = new JPanel();
		panel_21.setBorder(new TitledBorder(null, "List", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		JPanel panel_31 = new JPanel();
		panel_31.setBorder(new TitledBorder(null, "Available Models",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JButton btnAddModelWith = new JButton("Add model with texture");
		btnAddModelWith.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogAddModelWithTexture mod = new DialogAddModelWithTexture(
						ResEditorWindow.this);
				mod.setVisible(true);
			}
		});

		JButton btnDeleteBlock = new JButton("Delete block");
		btnDeleteBlock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DialogDeleteBlock mod = new DialogDeleteBlock(
						ResEditorWindow.this);
				mod.setVisible(true);
			}
		});
		GroupLayout gl_panel_1 = new GroupLayout(panel_1);
		gl_panel_1
				.setHorizontalGroup(gl_panel_1
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_1
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.TRAILING)
														.addGroup(
																gl_panel_1
																		.createSequentialGroup()
																		.addComponent(
																				btnDeleteBlock)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				btnAddModelWith))
														.addComponent(
																panel_21,
																GroupLayout.DEFAULT_SIZE,
																277,
																Short.MAX_VALUE))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addComponent(panel_31,
												GroupLayout.DEFAULT_SIZE, 282,
												Short.MAX_VALUE)
										.addContainerGap()));
		gl_panel_1
				.setVerticalGroup(gl_panel_1
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								gl_panel_1
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												gl_panel_1
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																gl_panel_1
																		.createSequentialGroup()
																		.addComponent(
																				panel_21,
																				GroupLayout.DEFAULT_SIZE,
																				324,
																				Short.MAX_VALUE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addGroup(
																				gl_panel_1
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								btnAddModelWith)
																						.addComponent(
																								btnDeleteBlock))
																		.addGap(3))
														.addComponent(
																panel_31,
																GroupLayout.DEFAULT_SIZE,
																361,
																Short.MAX_VALUE))
										.addContainerGap()));

		DefaultListModel<String> stringlist = new DefaultListModel<>();
		addAll(readAvailModels(), stringlist);

		JScrollPane scrollPane_2 = new JScrollPane();
		GroupLayout gl_panel_31 = new GroupLayout(panel_31);
		gl_panel_31.setHorizontalGroup(gl_panel_31.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_31
						.createSequentialGroup()
						.addGap(11)
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE,
								249, Short.MAX_VALUE).addContainerGap()));
		gl_panel_31.setVerticalGroup(gl_panel_31.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_31
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane_2, GroupLayout.DEFAULT_SIZE,
								316, Short.MAX_VALUE).addContainerGap()));
		JList<String> list_1 = new JList<String>();
		scrollPane_2.setViewportView(list_1);
		list_1.setModel(stringlist);
		panel_31.setLayout(gl_panel_31);
		DefaultListModel<String> stringlist1 = new DefaultListModel<>();
		addAll(readModels(), stringlist1);
		JScrollPane scrollPane_3 = new JScrollPane();
		GroupLayout gl_panel_21 = new GroupLayout(panel_21);
		gl_panel_21.setHorizontalGroup(gl_panel_21.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_21
						.createSequentialGroup()
						.addContainerGap()
						.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE,
								245, Short.MAX_VALUE).addContainerGap()));
		gl_panel_21.setVerticalGroup(gl_panel_21.createParallelGroup(
				Alignment.LEADING).addGroup(
				gl_panel_21
						.createSequentialGroup()
						.addComponent(scrollPane_3, GroupLayout.DEFAULT_SIZE,
								290, Short.MAX_VALUE).addContainerGap()));
		JList<String> list = new JList<String>();
		scrollPane_3.setViewportView(list);
		list.setModel(stringlist1);
		panel_21.setLayout(gl_panel_21);
		panel_1.setLayout(gl_panel_1);
		// lblNewLabel_1.setIcon();
		JLabel lblNewLabel;
		try {
			lblNewLabel = new JLabel(new ImageIcon(new File(repository,
					"slgh_proj/logo.slgh_b").toURI().toURL()));
			lblNewLabel.setSize(64 + 5, 64 + 5);
			lblNewLabel.setLocation(12 + 5, 12 + 5);
			lblNewLabel.setBounds(12 + 5, 12 + 5, 64 + 5, 64 + 5);

			JLabel lblNewLabel_1 = new JLabel(
					MinecraftResEditor.slghGetProjectName());

			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			GroupLayout gl_panel_261 = new GroupLayout(panel_35);
			gl_panel_261.setHorizontalGroup(gl_panel_261
					.createParallelGroup(Alignment.LEADING)
					.addGroup(
							gl_panel_261
									.createSequentialGroup()
									.addContainerGap()
									.addComponent(lblNewLabel,
											GroupLayout.PREFERRED_SIZE, 59,
											GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(
											ComponentPlacement.UNRELATED)
									.addComponent(lblNewLabel_1)
									.addContainerGap(458, Short.MAX_VALUE))
					.addComponent(tabbedPane, Alignment.TRAILING,
							GroupLayout.DEFAULT_SIZE, 589, Short.MAX_VALUE));
			gl_panel_261
					.setVerticalGroup(gl_panel_261
							.createParallelGroup(Alignment.LEADING)
							.addGroup(
									gl_panel_261
											.createSequentialGroup()
											.addGroup(
													gl_panel_261
															.createParallelGroup(
																	Alignment.LEADING)
															.addGroup(
																	gl_panel_261
																			.createSequentialGroup()
																			.addGap(32)
																			.addComponent(
																					lblNewLabel_1))
															.addGroup(
																	gl_panel_261
																			.createSequentialGroup()
																			.addContainerGap()
																			.addComponent(
																					lblNewLabel,
																					GroupLayout.PREFERRED_SIZE,
																					60,
																					GroupLayout.PREFERRED_SIZE)))
											.addPreferredGap(
													ComponentPlacement.RELATED)
											.addComponent(tabbedPane,
													GroupLayout.DEFAULT_SIZE,
													306, Short.MAX_VALUE)));

			JPanel panel_4 = new JPanel();// Notes
			tabbedPane.addTab("Notes", null, panel_4, "Notes");

			JScrollPane scrollPane_4 = new JScrollPane();

			JScrollPane scrollPane_5 = new JScrollPane();
			GroupLayout gl_panel_4 = new GroupLayout(panel_4);
			gl_panel_4.setHorizontalGroup(gl_panel_4.createParallelGroup(
					Alignment.LEADING).addGroup(
					gl_panel_4
							.createSequentialGroup()
							.addContainerGap()
							.addComponent(scrollPane_4,
									GroupLayout.PREFERRED_SIZE, 160,
									GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane_5,
									GroupLayout.DEFAULT_SIZE, 394,
									Short.MAX_VALUE).addContainerGap()));
			gl_panel_4.setVerticalGroup(gl_panel_4.createParallelGroup(
					Alignment.LEADING).addGroup(
					Alignment.TRAILING,
					gl_panel_4
							.createSequentialGroup()
							.addContainerGap()
							.addGroup(
									gl_panel_4
											.createParallelGroup(
													Alignment.TRAILING)
											.addComponent(scrollPane_5,
													Alignment.LEADING,
													GroupLayout.DEFAULT_SIZE,
													256, Short.MAX_VALUE)
											.addComponent(scrollPane_4,
													Alignment.LEADING,
													GroupLayout.DEFAULT_SIZE,
													256, Short.MAX_VALUE))
							.addContainerGap()));

			JTextPane textPane_2 = new JTextPane();
			scrollPane_5.setViewportView(textPane_2);
			slghReload();

			JList<TextAndIcon> list_2 = TextAndIconList
					.createJListWithTextAndIcon(getNotes());
			scrollPane_4.setViewportView(list_2);
			panel_4.setLayout(gl_panel_4);

			JPanel panel_5 = new JPanel();
			tabbedPane.addTab("Crafting Recipes", null, panel_5,
					"Crafting Recipes");
			GroupLayout gl_panel_5 = new GroupLayout(panel_5);
			gl_panel_5.setHorizontalGroup(gl_panel_5.createParallelGroup(
					Alignment.LEADING).addGap(0, 584, Short.MAX_VALUE));
			gl_panel_5.setVerticalGroup(gl_panel_5.createParallelGroup(
					Alignment.LEADING).addGap(0, 278, Short.MAX_VALUE));
			panel_5.setLayout(gl_panel_5);
			panel_35.setLayout(gl_panel_261);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
	}

	public DefaultListModel<String> addAll(List<String> slist,
			DefaultListModel<String> sl) {
		for (String s : slist) {
			sl.addElement(s);
		}
		return sl;
	}

	List<String> note_list, cr_list;

	public void slghInit() {
		note_list = new ArrayList<String>();
		cr_list = new ArrayList<String>();
	}

	List<String> readFiles(File dir) {
		List<String> s = new ArrayList<String>();
		File[] fe = dir.listFiles();
		if(fe == null)
			return s;
		for (File f : fe) {
			if (f.getName().endsWith(".slghnot")
					|| f.getName().endsWith(".slghrec")) {
				s.add(f.getName().replaceAll(".slghrec", "")
						.replaceAll(".slghnot", ""));
			}
		}
		return s;
	}

	public void slghReload() {
		note_list.clear();
		cr_list.clear();
		note_list.addAll(readFiles(new File(repository, "slgh_proj/notes")));
		cr_list.addAll(readFiles(new File(repository, "slgh_proj/recipes")));
	}

	public String getAuthor(File f) {
		BufferedReader r;
		try {
			r = new BufferedReader(
					new InputStreamReader(new FileInputStream(f)));
			String ln = null;
			while ((ln = r.readLine()) != null) {
				if (ln.toLowerCase().startsWith("#author=")
						|| ln.toLowerCase().endsWith("=author#")) {
					r.close();
					return ln.replaceAll("#author=", "").replaceAll("=author#",
							"");
				}
			}
			r.close();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public DisplayableEntry[] getNotes() {
		DisplayableEntry[] en = new DisplayableEntry[note_list.size()];
		for (int i = 0; i < en.length; i++) {
			String author = getAuthor(new File(repository, "slgh_proj/notes/" + note_list.get(i) + ".slghnot"));
			en[i] = new DisplayableEntry(new InfoNote(author, note_list.get(i)));
		}
		return en;
	}

	public List<String> readAvailModels() {
		List<String> array = new ArrayList<String>();
		if (repository == null)
			return array;
		File langFile = new File(repository, "minecraft-res-editor/models");
		File bsFolder = new File(repository, "minecraft-res-editor/blockstates");
		File imFolder = new File(repository, "minecraft-res-editor/item-models");
		File[] a = langFile.listFiles();
		if (a == null)
			return array;
		for (File f : a) {
			if (f.toString().endsWith(".gh_mdl")) {
				File f23 = new File(bsFolder, f.getName().replace(".gh_mdl",
						".gh_bs"));
				File d24 = new File(imFolder, f.getName());
				if (f23.exists())
					if (d24.exists())
						array.add(f.getName().replace(".gh_mdl", ""));
			}
		}
		return array;
	}

	public List<String> readModels() {
		List<String> array = new ArrayList<String>();
		if (repository == null)
			return array;
		File langFile = new File(repository,
				"src/main/resources/assets/gamehelper/models/block");
		File bsFolder = new File(repository,
				"src/main/resources/assets/gamehelper/blockstates");
		File imFolder = new File(repository,
				"src/main/resources/assets/gamehelper/models/item");
		File[] a = langFile.listFiles();
		if (a == null)
			return array;
		for (File f : a) {
			if (f.toString().endsWith(".json")) {
				File f23 = new File(bsFolder, f.getName());
				File d24 = new File(imFolder, f.getName());
				if (f23.exists())
					if (d24.exists())
						array.add(f.getName().replace(".json", ""));
			}
		}
		return array;
	}

	public void reloadText(String lang_file, JTextPane text) {
		if (repository == null)
			return;
		File langFile = new File(repository,
				"src/main/resources/assets/gamehelper/lang/" + lang_file
						+ ".lang");
		try {
			BufferedReader writer = new BufferedReader(new InputStreamReader(
					new FileInputStream(langFile), StandardCharsets.UTF_8));
			text.setText(null);
			String ln = null;
			String doc = "";
			while ((ln = writer.readLine()) != null) {
				doc = doc + ln + System.lineSeparator();
			}
			writer.close();
			text.setText(doc);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveText(String lang_file, JTextPane text) {
		File langFile = new File(repository,
				"src/main/resources/assets/gamehelper/lang/" + lang_file
						+ ".lang");
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(langFile, false),
					StandardCharsets.UTF_8));
			String text_s = text.getText();
			if (text_s == null) {
				writer.close();
				return;
			}
			writer.write(text_s);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addI18n(String key, String lang, String value) {
		String doc = null;
		try {
			BufferedReader writer = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(repository,
							"src/main/resources/assets/gamehelper/lang/" + lang
									+ ".lang")), StandardCharsets.UTF_8));
			String ln = null;
			doc = "";
			while ((ln = writer.readLine()) != null) {
				doc = doc + ln + System.lineSeparator();
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] sep = doc.split(System.lineSeparator());
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File(repository,
							"src/main/resources/assets/gamehelper/lang/" + lang
									+ ".lang"), false), StandardCharsets.UTF_8));
			for (String ds : sep) {
				bw.write(ds);
				bw.newLine();
			}
			bw.write(key + "=" + value);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void deleteI18n(String key, String lang) {
		String doc = null;
		try {
			BufferedReader writer = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(repository,
							"src/main/resources/assets/gamehelper/lang/" + lang
									+ ".lang")), StandardCharsets.UTF_8));
			String ln = null;
			doc = "";
			while ((ln = writer.readLine()) != null) {
				doc = doc + ln + System.lineSeparator();
			}
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String[] sep = doc.split(System.lineSeparator());
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(new File(repository,
							"src/main/resources/assets/gamehelper/lang/" + lang
									+ ".lang"), false), StandardCharsets.UTF_8));
			for (String ds : sep) {
				if (ds.toLowerCase().startsWith(key))
					continue;
				bw.write(ds);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void openRepository(File repo) {
		boolean b = false;
		boolean subRepo = false;
		boolean subRepo1 = false;
		File[] a = repo.listFiles();
		if (a == null) {
			JOptionPane.showMessageDialog(
					null,
					"The GameHelper repository wasn't found!"
							+ System.lineSeparator() + "Code: 0x1o", "Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		for (File f : a) {
			if (f.getName().endsWith("README.md")) {
				b = true;
			} else if (f.getName().endsWith("gamehelper-mc-189")) {
				subRepo = true;
			} else if (f.getName().endsWith("GameHelper")) {
				subRepo1 = true;
			}
		}
		if (b) {
			repository = repo;
		} else {
			if (subRepo) {
				repository = new File(repo, "gamehelper-mc-189");
			} else if (subRepo1) {
				repository = new File(repo, "GameHelper");
			}
		}
		MinecraftResEditor.readName();
	}
}