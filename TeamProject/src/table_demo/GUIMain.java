package table_demo;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import kiosk.*;

public class GUIMain {
	static MyKiosk mykiosk = MyKiosk.getInstance();

	public static void main(String args[]) {
		mykiosk.run();// ������ ���� �а�
		startGUI();// GUI�� ���� ������ ����
	}

	public static void startGUI() {
		// Schedule a job for the event-dispatching thread:
		// creating and showing this application's GUI.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}

	/**
	 * Create the GUI and show it. For thread safety, this method should be invoked
	 * from the event-dispatching thread.
	 */
	private static JPanel startPage;
	private static JPanel mainPanel;
	private static JPanel chatPanel;

	private static void createAndShowGUI() {
		// Create and set up the window.

		JFrame mainFrame = new JFrame("MyKiosk");
		mainFrame.setBounds(550, 300, 800, 600);

		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);

		// ���� ������
		setupStartPage();
		mainFrame.getContentPane().add(startPage);

		// ��ǰ ��� �г�
		setupMainPanel();
		mainFrame.getContentPane().add(mainPanel);

		// Display the window.
		mainFrame.pack();
		mainFrame.setVisible(true);

		mainPanel.setVisible(false);

	}
	//ä�� �г� ����
	private static void setupChat() {
		chatPanel = new JPanel();
		chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
		String newline = "\n";
		String user = "����� : ";
		JPanel upperPanel = new JPanel();
		JPanel lowerPanel = new JPanel();
		JTextField textField = new JTextField(65);
		JTextArea textArea = new JTextArea(15, 65);

		chatPanel.add(upperPanel);
		chatPanel.add(lowerPanel);
		upperPanel.add(textArea);
		lowerPanel.add(textField);

		textField.requestFocus();

		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textField.getText();
				textArea.append(user + text + newline);
				textField.setText("");
				textArea.setCaretPosition(textArea.getDocument().getLength());
			}
		});
	}

	// ���� �г� ���� �޼ҵ�
	private static void setupMainPanel() {
		mainPanel = new JPanel();

		mainPanel.setBounds(0, 0, 600, 400);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JTabbedPane jtab = new JTabbedPane();// ��ǰ ��
		// Create and set up the content pane.
		TableSelectionDemo outerPane = new TableSelectionDemo();
		outerPane.addComponentsToPane(OuterMgr.getInstance(), true); // �̱��� �����۸޴����� ������ ��ü�� ��������

		TableSelectionDemo topPane = new TableSelectionDemo();
		topPane.addComponentsToPane(TopMgr.getInstance(), true);

		TableSelectionDemo pantsPane = new TableSelectionDemo();
		pantsPane.addComponentsToPane(PantsMgr.getInstance(), true);

		TableSelectionDemo hatPane = new TableSelectionDemo();
		hatPane.addComponentsToPane(HatMgr.getInstance(), true);

		TableSelectionDemo bagPane = new TableSelectionDemo();
		bagPane.addComponentsToPane(BagMgr.getInstance(), true);

		setupChat();

		jtab.add("�ƿ���", outerPane);
		jtab.add("����", topPane);
		jtab.add("����", pantsPane);
		jtab.add("����", hatPane);
		jtab.add("����", bagPane);
		jtab.add("ä��", chatPanel);
		mainPanel.add(jtab);

		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout());
		JButton btnMainBack = new JButton("�ڷΰ���");
		bottom.add(btnMainBack);

		mainPanel.add(bottom);

		// �ڷΰ��� ��ư �̺�Ʈ ������
		btnMainBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startPage.setVisible(true);
				mainPanel.setVisible(false);
			}
		});

	}

	// ���� �г� ���� �޼ҵ�
	private static void setupStartPage() {
		startPage = new JPanel();
		startPage.setBounds(0, 0, 800, 600);
		// startPage.setBackground(Color.PINK);
		startPage.setLayout(null);

		// ���� ��ư
		JButton btnstart = new JButton("����");
		btnstart.setBounds(350, 220, 100, 50);
		startPage.add(btnstart);

		JTextField txtMykiosk = new JTextField();
		txtMykiosk.setEnabled(false);
		txtMykiosk.setFont(new Font("����", Font.PLAIN, 41));
		txtMykiosk.setText("MyKiosk");
		txtMykiosk.setHorizontalAlignment(JTextField.CENTER);
		txtMykiosk.setBounds(0, 80, 800, 100);
		startPage.add(txtMykiosk);
		txtMykiosk.setColumns(10);

		// ���� ��ư �̺�Ʈ ������
		btnstart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(true);
				startPage.setVisible(false);

			}
		});
	}

}