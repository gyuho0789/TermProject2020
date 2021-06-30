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
		mykiosk.run();// 파일을 먼저 읽고
		startGUI();// GUI가 메인 쓰레드 실행
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

		// 시작 페이지
		setupStartPage();
		mainFrame.getContentPane().add(startPage);

		// 상품 목록 패널
		setupMainPanel();
		mainFrame.getContentPane().add(mainPanel);

		// Display the window.
		mainFrame.pack();
		mainFrame.setVisible(true);

		mainPanel.setVisible(false);

	}
	//채팅 패널 생성
	private static void setupChat() {
		chatPanel = new JPanel();
		chatPanel.setLayout(new BoxLayout(chatPanel, BoxLayout.Y_AXIS));
		String newline = "\n";
		String user = "사용자 : ";
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

	// 메인 패널 생성 메소드
	private static void setupMainPanel() {
		mainPanel = new JPanel();

		mainPanel.setBounds(0, 0, 600, 400);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JTabbedPane jtab = new JTabbedPane();// 상품 텝
		// Create and set up the content pane.
		TableSelectionDemo outerPane = new TableSelectionDemo();
		outerPane.addComponentsToPane(OuterMgr.getInstance(), true); // 싱글톤 아이템메니저의 유일한 객체를 돌려받음

		TableSelectionDemo topPane = new TableSelectionDemo();
		topPane.addComponentsToPane(TopMgr.getInstance(), true);

		TableSelectionDemo pantsPane = new TableSelectionDemo();
		pantsPane.addComponentsToPane(PantsMgr.getInstance(), true);

		TableSelectionDemo hatPane = new TableSelectionDemo();
		hatPane.addComponentsToPane(HatMgr.getInstance(), true);

		TableSelectionDemo bagPane = new TableSelectionDemo();
		bagPane.addComponentsToPane(BagMgr.getInstance(), true);

		setupChat();

		jtab.add("아우터", outerPane);
		jtab.add("상의", topPane);
		jtab.add("하의", pantsPane);
		jtab.add("모자", hatPane);
		jtab.add("가방", bagPane);
		jtab.add("채팅", chatPanel);
		mainPanel.add(jtab);

		JPanel bottom = new JPanel();
		bottom.setLayout(new FlowLayout());
		JButton btnMainBack = new JButton("뒤로가기");
		bottom.add(btnMainBack);

		mainPanel.add(bottom);

		// 뒤로가기 버튼 이벤트 리스너
		btnMainBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startPage.setVisible(true);
				mainPanel.setVisible(false);
			}
		});

	}

	// 시작 패널 생성 메소드
	private static void setupStartPage() {
		startPage = new JPanel();
		startPage.setBounds(0, 0, 800, 600);
		// startPage.setBackground(Color.PINK);
		startPage.setLayout(null);

		// 시작 버튼
		JButton btnstart = new JButton("시작");
		btnstart.setBounds(350, 220, 100, 50);
		startPage.add(btnstart);

		JTextField txtMykiosk = new JTextField();
		txtMykiosk.setEnabled(false);
		txtMykiosk.setFont(new Font("돋움", Font.PLAIN, 41));
		txtMykiosk.setText("MyKiosk");
		txtMykiosk.setHorizontalAlignment(JTextField.CENTER);
		txtMykiosk.setBounds(0, 80, 800, 100);
		startPage.add(txtMykiosk);
		txtMykiosk.setColumns(10);

		// 시작 버튼 이벤트 리스너
		btnstart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainPanel.setVisible(true);
				startPage.setVisible(false);

			}
		});
	}

}