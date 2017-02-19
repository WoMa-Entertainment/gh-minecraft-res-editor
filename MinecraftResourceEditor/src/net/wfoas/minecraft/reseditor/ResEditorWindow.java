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
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
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

public class ResEditorWindow extends JFrame {

	private static final long serialVersionUID = 6229273644008449868L;
	private JPanel contentPane;
	File repository;
	JPanel panel, panel_1;

	public ResEditorWindow() {
		setTitle("MinecraftResourceEditor - GameHelper");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 620, 480);

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
		redesign();
	}

	public void redesign() {
		panel.setLayout(null);
		panel.removeAll();
		panel_1.setLayout(null);
		panel_1.removeAll();
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

		JButton btnReload = new JButton("Reload from Source");
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
																544,
																Short.MAX_VALUE)
														.addGroup(
																gl_panel_2
																		.createSequentialGroup()
																		.addComponent(
																				btnReload)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				btnSave)))
										.addContainerGap()));
		gl_panel_2.setVerticalGroup(gl_panel_2.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						gl_panel_2
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane,
										GroupLayout.DEFAULT_SIZE, 285,
										Short.MAX_VALUE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addGroup(
										gl_panel_2
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(btnSave)
												.addComponent(btnReload))
								.addContainerGap()));

		JTextPane textPane = new JTextPane();
		scrollPane.setViewportView(textPane);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveText("de_DE", textPane);
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
																Alignment.LEADING)
														.addComponent(
																scrollPane_1,
																GroupLayout.DEFAULT_SIZE,
																544,
																Short.MAX_VALUE)
														.addGroup(
																Alignment.TRAILING,
																gl_panel_3
																		.createSequentialGroup()
																		.addGap(350)
																		.addComponent(
																				button,
																				GroupLayout.PREFERRED_SIZE,
																				127,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				button_1,
																				GroupLayout.PREFERRED_SIZE,
																				57,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		gl_panel_3.setVerticalGroup(gl_panel_3.createParallelGroup(
				Alignment.LEADING)
				.addGroup(
						gl_panel_3
								.createSequentialGroup()
								.addContainerGap()
								.addComponent(scrollPane_1,
										GroupLayout.DEFAULT_SIZE, 285,
										Short.MAX_VALUE)
								.addGap(11)
								.addGroup(
										gl_panel_3
												.createParallelGroup(
														Alignment.BASELINE)
												.addComponent(button_1)
												.addComponent(button))
								.addContainerGap()));

		JTextPane textPane_1 = new JTextPane();
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveText("en_US", textPane_1);
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
	}

	public DefaultListModel<String> addAll(List<String> slist,
			DefaultListModel<String> sl) {
		for (String s : slist) {
			sl.addElement(s);
		}
		return sl;
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
	}
}