package kiosk;

import java.util.List;

import facade.DataEngineInterface;
import mgr.Manageable;

public class TopMgr implements DataEngineInterface {
	private static TopMgr mgr = null;

	private TopMgr() {
	}

	public static TopMgr getInstance() {
		if (mgr == null)
			mgr = new TopMgr();
		return mgr;
	}

	private String[] userHeaders = { "Name", "Brandname", "Color", "Price" };
	private String[] mgrHeaders = { "Code", "Name", "Brand", "Color", "Price" };

	public String[] getColumnNames(boolean b) {
		if (b) {
			return mgrHeaders;
		}
		return userHeaders;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public void readAll(String filename) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Manageable> search(String kwd) {
		return MyKiosk.TopMgr.findAll(kwd);
	}

	@Override
	public void addNewItem(String[] uiTexts) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(String[] uiTexts) {
		// TODO Auto-generated method stub

	}

	@Override
	public void remove(String kwd) {
		// TODO Auto-generated method stub

	}

	public int check() {
		return 1;
	}

}
