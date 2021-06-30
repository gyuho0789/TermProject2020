package table_demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import facade.DataEngineInterface;
import facade.UIData;

public class TableSelectionDemo extends JPanel implements ListSelectionListener {
	private static final long serialVersionUID = 1L;
	TableController tableController;
	DefaultTableModel tableModel;
	DataEngineInterface dataMgr;
	BottomPane bottom;// 아래 편집창
	mgrDialog mgrdlg;
	int selectedIndex = -1;
	JTable table;

	public TableSelectionDemo() {
		super(new BorderLayout());
	}

	void addComponentsToPane(DataEngineInterface mgr, boolean hasBot) {// 데이터엔진인터페이스로 데이터 받아옴
		init(mgr, hasBot);
		JScrollPane center = new JScrollPane(table);
		add(center, BorderLayout.CENTER);
		setupTopPane();// 검색창
		if (hasBot) {
			bottom = new BottomPane(this);
			bottom.init(mgr.getColumnCount());// 열의 개수만큼 편집창 만들어줌
			add(bottom, BorderLayout.PAGE_END);
		}

	}

	@SuppressWarnings("serial")
	void init(DataEngineInterface mgr, boolean hasBot) {
		dataMgr = mgr;
		tableModel = new DefaultTableModel(mgr.getColumnNames(hasBot), 0) { // 셀 수정 못하게 하는 부분
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		loadData("");

		table = new JTable(tableModel);
		ListSelectionModel rowSM = table.getSelectionModel();
		rowSM.addListSelectionListener(this);
		table.setPreferredScrollableViewportSize(new Dimension(400, 220));
		table.setFillsViewportHeight(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	}

	// 매니저에서 검색된 객체들을 테이블에 보여준다. kwd가 ""면 모두 출력
	void loadData(String kwd) {
		List<?> result = dataMgr.search(kwd);
		tableModel.setRowCount(0);
		for (Object m : result)
			tableModel.addRow(((UIData) m).getUiTexts());
	}

	void setupTopPane() {
		JPanel topPane = new JPanel();
		JTextField kwdTextField = new JTextField("", 20);
		topPane.add(kwdTextField, BorderLayout.LINE_START);
		JButton search = new JButton("검색");
		topPane.add(search, BorderLayout.LINE_END);
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getActionCommand().equals("검색")) {
					loadData(kwdTextField.getText());
				}
			}
		});
		add(topPane, BorderLayout.PAGE_START);
	}

	void showDetail() {
		if (selectedIndex < 0)
			return;
		String[] rowTexts = new String[tableModel.getColumnCount()];
		for (int i = 0; i < rowTexts.length; i++)
			rowTexts[i] = (String) tableModel.getValueAt(selectedIndex, i);
		DetailDialog dlg = new DetailDialog(dataMgr, rowTexts);
		dlg.setup();
		dlg.pack();
		dlg.setVisible(true);
	}

	void showMgr(int columnCount) {
		if (selectedIndex < 0)
			return;
		String[] rowTexts = new String[tableModel.getColumnCount()];
		for (int i = 0; i < rowTexts.length; i++)
			rowTexts[i] = (String) tableModel.getValueAt(selectedIndex, i);
		mgrdlg = new mgrDialog(this, rowTexts, columnCount);
		mgrdlg.setup();
		mgrdlg.pack();
		mgrdlg.setVisible(true);
	}

	void addRow(String[] editTexts) { // 행 추가, 새로운 객체 생성해서 엔진에서 추가
		try {
			dataMgr.addNewItem(editTexts);
		} catch (Exception ex) { // 추가 중 오류 발생
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "추가 데이터 오류");
			return;
		}
		tableModel.addRow(editTexts);
	}

	void removeRow() {
		if (selectedIndex < 0)
			return;
		String key = (String) tableModel.getValueAt(selectedIndex, 0);
		dataMgr.remove(key);
		tableModel.removeRow(selectedIndex);
	}

	void updateRow(String[] editTexts) {
		if (selectedIndex < 0)
			return;
		try {
			dataMgr.update(editTexts);
		} catch (Exception ex) { // SongMgr에서 수정 중 오류 발생
			ex.printStackTrace();
			JOptionPane.showMessageDialog(null, "수정 중 데이터 오류");
			return;
		}
		for (int i = 0; i < editTexts.length; i++) {
			tableModel.setValueAt(editTexts[i], selectedIndex, i);
		}
	}

	// 선택된 행이 변경되면 그 내용을 편집창으로 보냄
	public void valueChanged(ListSelectionEvent e) {
		ListSelectionModel lsm = (ListSelectionModel) e.getSource();
		if (!lsm.isSelectionEmpty()) {
			selectedIndex = lsm.getMinSelectionIndex();
			String[] rowTexts = new String[tableModel.getColumnCount()];
			for (int i = 0; i < rowTexts.length; i++)
				rowTexts[i] = (String) tableModel.getValueAt(selectedIndex, i);
			bottom.moveSelectedToEdits(rowTexts);
		}
	}

}