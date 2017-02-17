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
import java.nio.charset.StandardCharsets;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class MinecraftResEditor {
	static ResEditorWindow rese;

	public static void main(String... args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		rese = new ResEditorWindow();
		String username = System.getProperty("user.home");
		File f = new File(username, "git/GameHelper");
		System.out.println(username + "/git/GameHelper");
		rese.openRepository(f);
		rese.redesign();
		rese.setVisible(true);
	}

	protected static String readFileIntoSingleString(File path) {
		try {
			BufferedReader writer = new BufferedReader(
					new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
			String ln = null;
			String doc = "";
			while ((ln = writer.readLine()) != null) {
				if (ln.startsWith("//")) {
					// System.out.println("Discard: " + ln);
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
			BufferedWriter writer = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(path), StandardCharsets.UTF_8));
			writer.write(c);
			writer.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void addModel(String modid, String blockid, String model, String pathtotex) {
		File langFile = new File(rese.repository, "minecraft-res-editor/models/" + model + ".gh_mdl");
		String bmdl = readFileIntoSingleString(langFile);
		bmdl = bmdl.replaceAll("%%modid%%", modid);
		bmdl = bmdl.replaceAll("%%blockid%%", blockid);
		save(new File(rese.repository, "src/main/resources/assets/gamehelper/models/block/" + blockid + ".json"), bmdl);
		//
		File imf = new File(rese.repository, "minecraft-res-editor/item-models/" + model + ".gh_mdl");
		String bimdl = readFileIntoSingleString(imf);
		bimdl = bimdl.replaceAll("%%modid%%", modid);
		bimdl = bimdl.replaceAll("%%blockid%%", blockid);
		save(new File(rese.repository, "src/main/resources/assets/gamehelper/models/item/" + blockid + ".json"), bimdl);
		//
		File bs = new File(rese.repository, "minecraft-res-editor/blockstates/" + model + ".gh_bs");
		String bibs = readFileIntoSingleString(bs);
		bibs = bibs.replaceAll("%%modid%%", modid);
		bibs = bibs.replaceAll("%%blockid%%", blockid);
		save(new File(rese.repository, "src/main/resources/assets/gamehelper/blockstates/" + blockid + ".json"), bimdl);
		try {
			Files.copy(new File(pathtotex).toPath(),
					new File(rese.repository,
							"src/main/resources/assets/gamehelper/textures/blocks/" + blockid + ".png").toPath(),
					StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
