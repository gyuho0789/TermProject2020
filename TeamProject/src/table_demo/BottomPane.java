package table_demo;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

import javax.swing.JButton;
import javax.swing.JTextField;

@SuppressWarnings("serial")
class BottomPane extends JPanel implements ActionListener {//�ϴ� �г� �κ� Ŭ����
	JTextField edits[];
	int columnCount;
	TableSelectionDemo tableDemo;
	public BottomPane() {}
	public BottomPane(TableSelectionDemo Table) {
		this.tableDemo = Table;
	}
	void init(int columnCount) {
		this.columnCount = columnCount;
		edits = new JTextField[columnCount];
		setLayout(new FlowLayout());
		for (int i = 0; i < columnCount; i++) {
			edits[i] = new JTextField("", 10);
			add(edits[i]);
		}
		JButton detailBtn = new JButton("�󼼺���");
		detailBtn.setActionCommand("Detail");
		detailBtn.addActionListener(this);
		add(detailBtn);
		
		JButton mgrBtn = new JButton("������");
		mgrBtn.setActionCommand("mgr");
		mgrBtn.addActionListener(this);
		add(mgrBtn);
		
		setLayout(new FlowLayout());

	}

	void clearEdits() {
		for (JTextField edit : edits)
			edit.setText("");
	}
	// ����â�� ������ �迭�� �־� ������. �׸��� �߰��� ������ ����� ������
	String[] getEditTexts() {
		String[] editTexts = new String[columnCount];
		for (int i = 0; i < columnCount; i++)
			editTexts[i] = edits[i].getText();
		return editTexts;
	}
	// ���� ���õ� ���� �����͸� ����â�� �ϳ��� �־���
    void moveSelectedToEdits(String[] rowTexts) {
    	for (int i = 0; i < rowTexts.length; i++) {
    		edits[i].setText(rowTexts[i]);
    	}
	}
	public void actionPerformed(ActionEvent e) {
		
		
		
		switch (e.getActionCommand()) {
		case "mgr":
			tableDemo.showMgr(columnCount);
			clearEdits();
			break;
		case "Detail":
    		tableDemo.showDetail();
    		break;
    	default: break;
		}
	}
}

