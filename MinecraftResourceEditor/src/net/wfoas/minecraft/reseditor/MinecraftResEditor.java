package net.wfoas.minecraft.reseditor;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MinecraftResEditor {
	static ResEditorWindow rese;

	public static ImageIcon CR, NOTE;

	public static ImageIcon downScale(ImageIcon ii) {
		ii.setImage(ii.getImage().getScaledInstance(16, 16, 4));
		return ii;
	}

	public static ImageIcon downScale(ImageIcon ii, int width, int height) {
		ii.setImage(ii.getImage().getScaledInstance(width, height, 4));
		return ii;
	}

	public static ImageIcon readClassImage(String s) {
		return readImage(MinecraftResEditor.class.getResource(s));
	}

	public static ImageIcon readImage(URL url) {
		ImageIcon ii = new ImageIcon(url);
		return ii;
	}

	public static void main(String... args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

		CR = readClassImage("/res/CRAFTING_RECIPES.png");
		NOTE = readClassImage("/res/NOTES.png");
		rese = new ResEditorWindow();
		String username = System.getProperty("user.home");
		File f = new File(username, "git/GameHelper");
		if (!f.exists()) {
			f = new File(username, "git/gamehelper-mc-189");
		}
		if (!f.exists()) {
			JOptionPane.showMessageDialog(
					null,
					"The GameHelper repository wasn't found!"
							+ System.lineSeparator() + "Code: 0x2s", "Error",
					JOptionPane.ERROR_MESSAGE);
			System.exit(-1);
		}
		rese.openRepository(f);
		rese.redesign();
		rese.setVisible(true);
	}

	protected static String readFileIntoSingleString(File path) {
		try {
			BufferedReader writer = new BufferedReader(new InputStreamReader(
					new FileInputStream(path), StandardCharsets.UTF_8));
			String ln = null;
			String doc = "";
			while ((ln = writer.readLine()) != null) {
				if (ln.startsWith("//")) {
					continue;
				}
				doc = doc + ln + System.lineSeparator();
			}
			writer.close();
			return doc;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected static void save(File path, String c) {
		if (path.exists())
			path.delete();
		try {
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(path), StandardCharsets.UTF_8));
			writer.write(c);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected static void delete(File path) {
		path.delete();
	}

	public static void addBlockModelWithI18n(String modid, String blockid,
			String model, String pathtotex, String ger, String eng) {
		addModel(modid, blockid, model, pathtotex);
		addOrReplaceBlock(ger, blockid, modid, "de_DE");
		addOrReplaceBlock(eng, blockid, modid, "en_US");
	}

	public static void addModel(String modid, String blockid, String model,
			String pathtotex) {
		File langFile = new File(rese.repository,
				"minecraft-res-editor/models/" + model + ".gh_mdl");
		String bmdl = readFileIntoSingleString(langFile);
		bmdl = bmdl.replaceAll("%%modid%%", modid);
		bmdl = bmdl.replaceAll("%%blockid%%", blockid);
		save(new File(rese.repository,
				"src/main/resources/assets/gamehelper/models/block/" + blockid
						+ ".json"), bmdl);
		//
		File imf = new File(rese.repository,
				"minecraft-res-editor/item-models/" + model + ".gh_mdl");
		String bimdl = readFileIntoSingleString(imf);
		bimdl = bimdl.replaceAll("%%modid%%", modid);
		bimdl = bimdl.replaceAll("%%blockid%%", blockid);
		save(new File(rese.repository,
				"src/main/resources/assets/gamehelper/models/item/" + blockid
						+ ".json"), bimdl);
		//
		File bs = new File(rese.repository, "minecraft-res-editor/blockstates/"
				+ model + ".gh_bs");
		String bibs = readFileIntoSingleString(bs);
		bibs = bibs.replaceAll("%%modid%%", modid);
		bibs = bibs.replaceAll("%%blockid%%", blockid);
		save(new File(rese.repository,
				"src/main/resources/assets/gamehelper/blockstates/" + blockid
						+ ".json"), bibs);
		try {
			Files.copy(new File(pathtotex).toPath(), new File(rese.repository,
					"src/main/resources/assets/gamehelper/textures/blocks/"
							+ blockid + ".png").toPath(),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addTextString(File f, String blockid, String modid,
			String i18n, String eng_ger) {
		String doc = null;
		try {
			BufferedReader writer = new BufferedReader(new InputStreamReader(
					new FileInputStream(f), StandardCharsets.UTF_8));
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
					new FileOutputStream(f, false), StandardCharsets.UTF_8));
			for (String ds : sep) {
				bw.write(ds);
				bw.newLine();
			}
			bw.write(i18n + "=" + eng_ger);
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void deleteTextString(File f, String blockid, String modid,
			String i18n) {
		String doc = null;
		try {
			BufferedReader writer = new BufferedReader(new InputStreamReader(
					new FileInputStream(f), StandardCharsets.UTF_8));
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
		List<String> sll = new ArrayList<String>();
		for (String s : sep) {
			if (s.toLowerCase().startsWith(
					"tile." + modid + "." + blockid + ".name")) {
				continue;
			} else
				sll.add(s);
		}
		try {
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(f, false), StandardCharsets.UTF_8));
			for (String ds : sll) {
				bw.write(ds);
				bw.newLine();
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addOrReplaceBlock(String i18n, String blockid,
			String modid, String file) {
		File langFile = new File(rese.repository,
				"src/main/resources/assets/gamehelper/lang/" + file + ".lang");
		deleteTextString(langFile, blockid, modid, i18n);
		addTextString(langFile, blockid, modid, "tile." + modid + "." + blockid
				+ ".name", i18n);
	}

	public static void deleteBlockModel(String blockid) {
		int choix = JOptionPane.showConfirmDialog(null,
				"Are you sure you want to delete model '" + blockid + "'?");
		if (choix == JOptionPane.YES_OPTION) {
			delete(new File(rese.repository,
					"src/main/resources/assets/gamehelper/models/block/"
							+ blockid + ".json"));
			delete(new File(rese.repository,
					"src/main/resources/assets/gamehelper/models/item/"
							+ blockid + ".json"));
			delete(new File(rese.repository,
					"src/main/resources/assets/gamehelper/blockstates/"
							+ blockid + ".json"));
			delete(new File(rese.repository,
					"src/main/resources/assets/gamehelper/textures/blocks/"
							+ blockid + ".png"));
			deleteTextString(new File(rese.repository,
					"src/main/resources/assets/gamehelper/lang/de_DE.lang"),
					blockid, "gamehelper", "tile.gamehelper." + blockid
							+ ".name");
			deleteTextString(new File(rese.repository,
					"src/main/resources/assets/gamehelper/lang/en_US.lang"),
					blockid, "gamehelper", "tile.gamehelper." + blockid
							+ ".name");
		} else
			return;
	}

	protected static String slgh_name;

	protected static void readName() {
		File f = new File(rese.repository, "slgh_proj/project.slgh_proj");
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					new FileInputStream(f)));
			String str = null;
			List<String> slist = new ArrayList<String>();
			while ((str = reader.readLine()) != null) {
				if (str.startsWith("#") || str.endsWith("#"))
					continue;
				slist.add(str);
			}
			reader.close();
			String proj_name = null;
			for (String s : slist) {
				if (s.startsWith("main_proj")) {
					proj_name = s.split(":")[1];
				}
			}
			if (proj_name == null)
				return;
			for (String s : slist) {
				if (s.startsWith(proj_name)) {
					slgh_name = s.split(":")[1];
					return;
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String slghGetProjectName() {

		return "[SLGH] " + slgh_name;
	}
}