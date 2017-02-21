package net.wfoas.git;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PMGUI extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JLabel lblMainTask;
	private JLabel lblSubtitle;
	private JLabel lblProgress;

	public PMGUI() {
		setTitle("<Title>");
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setResizable(false);
		setBounds(100, 100, 550, 175);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);

		progressBar = new JProgressBar();

		lblMainTask = new JLabel("<Main Task>");

		lblSubtitle = new JLabel("<Subtitle>");

		lblProgress = new JLabel("<Progress>");
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addContainerGap()
						.addGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(progressBar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 514,
										Short.MAX_VALUE)
								.addComponent(lblMainTask)
								.addGroup(gl_contentPanel.createSequentialGroup().addComponent(lblSubtitle)
										.addPreferredGap(ComponentPlacement.RELATED, 432, Short.MAX_VALUE)
										.addComponent(lblProgress)))
						.addContainerGap()));
		gl_contentPanel.setVerticalGroup(gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup().addGap(23).addComponent(lblMainTask).addGap(18)
						.addComponent(progressBar, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
						.addGap(18).addGroup(gl_contentPanel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSubtitle).addComponent(lblProgress))
						.addContainerGap(29, Short.MAX_VALUE)));
		contentPanel.setLayout(gl_contentPanel);
	}

	protected JLabel getMainTask() {
		return lblMainTask;
	}

	public void setMainTask(String t) {
		getMainTask().setText(t);
	}

	public void setSubtitle(String t) {
		getSubtitle().setText(t);
	}

	protected JLabel getSubtitle() {
		return lblSubtitle;
	}

	protected JLabel getProgress() {
		return lblProgress;
	}

	int max = 0, count = 0;
	private JProgressBar progressBar;

	public void setMax(int max) {
		this.max = max;
		getProgress().setText(count + "/" + max);
		getProgressBar().setMaximum(max);

	}

	public void setCount(int count) {
		this.count = count;
		getProgress().setText(count + "/" + max);
		getProgressBar().setValue(count);
	}

	public int getMax() {
		return max;
	}

	public int getCount() {
		return count;
	}

	public void setTitle(String t) {
		super.setTitle(t);
	}

	protected JProgressBar getProgressBar() {
		return progressBar;
	}
}