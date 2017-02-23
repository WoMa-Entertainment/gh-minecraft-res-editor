package net.wfoas.woma.slgh.ncr;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import net.wfoas.minecraft.reseditor.MinecraftResEditor;
import net.wfoas.woma.slgh.ncr.i.CRElement;
import net.wfoas.woma.slgh.ncr.i.NoteElement;

public class SLGH {
	public static void createNote() {
		new NewNoteDialog().setVisible(true);
	}

	public static void createCR() {
		new NewCRDialog().setVisible(true);
	}

	public void writeOutNote(File f, NoteElement e) {
		try {
			f.createNewFile();
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(f, false), StandardCharsets.UTF_8));
			bw.write("#author=" + e.getAuthor() + "=author#");
			bw.newLine();
			bw.write(e.getContent());
			bw.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		MinecraftResEditor.rese.redesign();
	}

	public CRElement readCR(File f, String name, String author) {
		String content = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			String line = null;
			while ((line = br.readLine()) != null) {
				if (line.startsWith("#author=") || line.endsWith("=author#"))
					continue;
				content = content + line + System.lineSeparator();
			}
			br.close();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
		if (content == null || content.isEmpty())
			return null;
		CRElement c = new CRElement(author, content);
		return c;
	}

	public NoteElement readNote(File f, String name, String author) {
		String content = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
			String ln = null;
			while ((ln = br.readLine()) != null) {
				if (ln.startsWith("#author=") || ln.endsWith("=author#")) {
					continue;
				}
				if (content.isEmpty()) {
					content = ln;
				} else {
					content = content + System.lineSeparator() + ln;
				}
			}
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (content == null || content.isEmpty())
			return null;
		NoteElement nt = new NoteElement(name, author, content);
		return nt;
	}

	public void writeOutCR(File f, CRElement e) {
		try {
			f.createNewFile();
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(f, false), StandardCharsets.UTF_8));
			bw.write("#author=" + e.getAuthor() + "=author#");
			bw.newLine();
			bw.write(e.serialize());
			bw.close();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
		MinecraftResEditor.rese.redesign();
	}

	public static void createNote(String author, String name) {
		createNote(author, new File(MinecraftResEditor.rese.repository, "slgh_proj/notes/" + name + ".slghnot"), name);
	}

	public static void createCR(String author, String name) {
		createCR(author, new File(MinecraftResEditor.rese.repository, "slgh_proj/recipes/" + name + ".slghrec"), name);
	}

	public static void createNote(String author, File f, String name) {
		try {
			f.createNewFile();
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(f, false), StandardCharsets.UTF_8));
			bw.write("#author=" + MinecraftResEditor.getName() + "=author#");
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		MinecraftResEditor.rese.redesign();
	}

	public static void deleteNote() {
		new DeleteNote(MinecraftResEditor.rese).setVisible(true);
	}

	public static void deleteNote(String name) {
		deleteNote(name, new File(MinecraftResEditor.rese.repository, "slgh_proj/notes/" + name + ".slghnot"));
	}

	public static void deleteNote(String name, File f) {
		f.delete();
		MinecraftResEditor.rese.redesign();
	}

	public static void deleteCR() {
		new DeleteCR(MinecraftResEditor.rese).setVisible(true);
	}

	public static void deleteCR(String name) {
		deleteNote(name, new File(MinecraftResEditor.rese.repository, "slgh_proj/recipes/" + name + ".slghrec"));
	}

	public static void deleteCR(String name, File f) {
		f.delete();
		MinecraftResEditor.rese.redesign();
	}

	public static void createCR(String author, File f, String name) {
		try {
			f.createNewFile();
			BufferedWriter bw = new BufferedWriter(
					new OutputStreamWriter(new FileOutputStream(f, false), StandardCharsets.UTF_8));
			bw.write("#author=" + MinecraftResEditor.getName() + "=author#");
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		MinecraftResEditor.rese.redesign();
	}

	public static List<String> getNotes() {
		File f = new File(MinecraftResEditor.rese.repository, "slgh_proj/notes/");
		List<String> list = new ArrayList<String>();
		for (File f1 : f.listFiles()) {
			if (f1.getName().toLowerCase().endsWith(".slghnot")) {
				list.add(f1.getName().replace(".slghnot", ""));
			}
		}
		return list;
	}

	public static List<String> getCR() {
		File f = new File(MinecraftResEditor.rese.repository, "slgh_proj/recipes/");
		List<String> list = new ArrayList<String>();
		for (File f1 : f.listFiles()) {
			if (f1.getName().toLowerCase().endsWith(".slghrec")) {
				list.add(f1.getName().replace(".slghrec", ""));
			}
		}
		return list;
	}
}
