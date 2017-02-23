package net.wfoas.minecraft.reseditor;

import java.io.File;
import java.io.IOException;

public class SubstManager {
	public static void subst(File f) {
		ProcessBuilder pb = new ProcessBuilder("subst", "V:", f.getAbsolutePath());
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void unsubst() {
		ProcessBuilder pb = new ProcessBuilder("subst", "V:", "/d");
		try {
			pb.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
