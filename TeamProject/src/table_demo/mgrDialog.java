package table_demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class mgrDialog extends javax.swing.JDialog {
	String[] rowText;
	String[] editText;
	TableSelectionDemo tableDemo;
	int columnCount;
	JTextField edits[];
	JPanel loginPanel;
	JPanel mgrPanel;
	mgrDialog(TableSelectionDemo table, String[] text, int columnCount){
		super();
		this.tableDemo = table;
		this.rowText = text;
		this.columnCount = columnCount;
	}
	
	void setup() {
		setTitle("관리자");
		setLocation(630,730);
		JPanel pane = new JPanel(new BorderLayout());
		//로그인창
		setupLogin();
		pane.add(loginPanel,BorderLayout.CENTER);
		
		//관리자 화면
		mgrPanel = new JPanel();
		mgrPanel.setLayout(new BorderLayout());
		
		JPanel topPanel = new JPanel();
		topPanel.setLayout(new FlowLayout());
		
		edits = new JTextField[columnCount];
		for(int i = 0; i < columnCount; i++) {
			edits[i] = new JTextField("", 10);
			topPanel.add(edits[i]);
		}
		
    	for (int i = 0; i < rowText.length; i++) {
    		edits[i].setText(rowText[i]);
    	}
    	mgrPanel.add(topPanel,BorderLayout.CENTER);
		
    	JPanel BtnPanel = new JPanel();
    	BtnPanel.setLayout(new FlowLayout());
    	
		JButton editBtn = new JButton("수정");
		editBtn.setActionCommand("수정");
		BtnPanel.add(editBtn);
		
		JButton addBtn = new JButton("추가");
		addBtn.setActionCommand("추가");
		BtnPanel.add(addBtn);
		
		JButton deleteBtn = new JButton("삭제");
		deleteBtn.setActionCommand("삭제");
		BtnPanel.add(deleteBtn);
		mgrPanel.add(BtnPanel,BorderLayout.SOUTH);
		
		pane.add(mgrPanel,BorderLayout.CENTER);
		mgrPanel.setVisible(false);
		this.setMinimumSize(new Dimension(630,100));
		setContentPane(pane);
		
        editBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (e.getActionCommand().equals("수정")) {
        			editText = getEditTexts();
        			tableDemo.updateRow(editText);
            	}
           }
        });
        addBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (e.getActionCommand().equals("추가")) {
        			editText = getEditTexts();
        			tableDemo.addRow(editText);
            	}
           }
        });
        deleteBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		if (e.getActionCommand().equals("삭제")) {
        			tableDemo.removeRow();
            	}
           }
        });
	}
	String[] getEditTexts() {
		String[] editTexts = new String[columnCount];
		for (int i = 0; i < columnCount; i++)
			editTexts[i] = edits[i].getText();
		return editTexts;
	}
	// 현재 선택된 행의 데이터를 편집창에 하나씩 넣어줌
    void moveSelectedToEdits(String[] rowTexts) {
    	for (int i = 0; i < rowTexts.length; i++) {
    		edits[i].setText(rowTexts[i]);
    	}
	}
	
	void setupLogin() {
		loginPanel = new JPanel();
		loginPanel.setBounds(0, 0, 630, 100);
		loginPanel.setLayout(null);

		JPanel loginBox = new JPanel();
		loginBox.setBounds(0, 0, 630, 100);
		loginPanel.add(loginBox);

		JLabel label = new JLabel("ID: ");
		JLabel pswrd = new JLabel("Password: ");
		JTextField txtID = new JTextField(10);
		JPasswordField txtPass = new JPasswordField(10);// 암호화된 텍스트 필드
		JButton btnLogin = new JButton("Log In");
		JButton btnLoginBack = new JButton("back");

		loginBox.add(label);
		loginBox.add(txtID);
		loginBox.add(pswrd);
		loginBox.add(txtPass);
		loginBox.add(btnLogin);
		loginBox.add(btnLoginBack);

		btnLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				//임시 아이디,비번
				String id = "kgu";
				String pass = "1234";

				if (id.equals(txtID.getText()) && pass.equals(txtPass.getText())) {
					JOptionPane.showMessageDialog(null, "You have logged in successfully");
					loginPanel.setVisible(false);
					mgrPanel.setVisible(true);
					txtID.setText("");
					txtPass.setText("");

				} else {
					JOptionPane.showMessageDialog(null, "You failedd to log In");
				}
			}
		});
		// 뒤로가기 버튼 리스너
		btnLoginBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginPanel.setVisible(false);
				end();
				
			}
		});
	}
	void end() {
		this.dispose();
	}
}
