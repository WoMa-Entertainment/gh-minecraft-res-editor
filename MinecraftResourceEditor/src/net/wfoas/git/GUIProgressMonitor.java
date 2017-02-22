package net.wfoas.git;

import javax.swing.SwingUtilities;

import org.eclipse.jgit.lib.BatchingProgressMonitor;
import org.eclipse.jgit.lib.ProgressMonitor;

public class GUIProgressMonitor extends BatchingProgressMonitor {

	volatile PMGUI gui;

	public GUIProgressMonitor(String git) {
		SwingUtilities.invokeLater(() -> {
			gui = new PMGUI();
			gui.setTitle("Git Tasks - " + git);
			if (git.equalsIgnoreCase("Pull")) {
				gui.setMainTask("Pull from remote...");
				gui.setSubtitle("Pulling...");
			} else if (git.equalsIgnoreCase("Push")) {
				gui.setMainTask("Push to remote...");
				gui.setSubtitle("Pushing...");
			}
			gui.setMax(1);
			gui.setCount(0);
			gui.setVisible(true);
		});
	}

	public void finish() {
		gui.setCount(gui.getMax());
	}

	public void cleanUp() {
		SwingUtilities.invokeLater(() -> {
			gui.dispose();
		});
	}

	@Override
	protected void onUpdate(String taskName, int workCurr) {
		// do nothing
	}

	@Override
	protected void onEndTask(String taskName, int workCurr) {
		// do nothing
	}

	@Override
	protected void onUpdate(String taskName, int workCurr, int workTotal, int percentDone) {
		SwingUtilities.invokeLater(() -> {
			gui.setSubtitle(taskName);
			gui.setMax(workTotal);
			gui.setCount(workCurr);
			gui.repaint();
		});
	}

	@Override
	protected void onEndTask(String taskName, int workCurr, int workTotal, int percentDone) {
		SwingUtilities.invokeLater(() -> {
			gui.setSubtitle(taskName);
			gui.setMax(workTotal);
			gui.setCount(workCurr);
			gui.repaint();
		});
	}
}