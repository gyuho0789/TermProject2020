package kiosk;

import java.util.List;

import facade.DataEngineInterface;
import mgr.Manageable;

public class BagMgr implements DataEngineInterface {
	private static BagMgr mgr = null;

	private BagMgr() {
	}

	public static BagMgr getInstance() {
		if (mgr == null)
			mgr = new BagMgr();
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
		return 5;
	}

	@Override
	public void readAll(String filename) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Manageable> search(String kwd) {
		return MyKiosk.BagMgr.findAll(kwd);
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
		return 4;
	}
}
