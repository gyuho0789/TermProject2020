package table_demo;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import facade.DataEngineInterface;
import facade.UIData;

public class DetailDialog extends javax.swing.JDialog{
	DataEngineInterface dataMgr;
	String[] rowText;
	Image img;
	String[] itemSize;
	String[] itemCount;
	
	public DetailDialog(DataEngineInterface mgr,String[] text) {
		dataMgr = mgr;
		this.rowText = text;
	}
	void setup() {
		setTitle("상세보기");
		setLocation(1000,430);
		JPanel pane = new JPanel(new BorderLayout());
		
		check();
		getData();
		
		JPanel topPane = new JPanel();
		JLabel topText = new JLabel(rowText[1]+"의 위치 및 수량");
		topPane.add(topText);
		pane.add(topPane,BorderLayout.NORTH);
		
		JPanel centerPanel = new JPanel(new BorderLayout());
		Image map = img.getScaledInstance(250, 250, Image.SCALE_DEFAULT);
		ImageIcon icon = new ImageIcon(map);
		JLabel mapImg = new JLabel(icon);
		mapImg.setIcon(icon);
		centerPanel.add(mapImg,BorderLayout.CENTER);
		
		JPanel dlgBot = new JPanel();
		dlgBot.setLayout(new GridLayout(0,2));
		
		JLabel text1 = new JLabel(" 사이즈");
		JLabel text2 = new JLabel("수량");
		
		dlgBot.add(text1);
		dlgBot.add(text2);
		
		JLabel size[] = new JLabel[itemSize.length];
		JLabel count[] = new JLabel[itemCount.length];
		for(int i = 0; i < itemSize.length; i++) {
			size[i] = new JLabel("    "+itemSize[i]);
			count[i] = new JLabel(itemCount[i]+" 개");
			dlgBot.add(size[i]);
			dlgBot.add(count[i]);
		}

		centerPanel.add(dlgBot,BorderLayout.SOUTH);
		pane.add(centerPanel,BorderLayout.CENTER);
		
		JButton backBtn = new JButton("확인");
		pane.add(backBtn,BorderLayout.PAGE_END);
		
		this.setMinimumSize(new Dimension(250,300));
		setContentPane(pane);
		
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}
	void check() {
		switch(dataMgr.check()) {
		case 0: 
			img = new ImageIcon("outer.png").getImage(); 
			break;
		case 1: 
			img = new ImageIcon("top.png").getImage(); 
			break;
		case 2: 
			img = new ImageIcon("pants.png").getImage(); 
			break;
		case 3: 
			img = new ImageIcon("hat.png").getImage(); 
			break;
		case 4: 
			img = new ImageIcon("bag.png").getImage(); 
			break;
		}
		
	}
	void getData() {
		List<?> result = dataMgr.search(rowText[1]);
		for(int i = 0; i < result.size(); i++) {
			Object m = result.get(0);
			itemSize = ((UIData)m).getItemSize();
			itemCount = ((UIData)m).getItemCount();
		}
		
	}

}
