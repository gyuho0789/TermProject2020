package kiosk;

import java.util.List;

import facade.DataEngineInterface;
import mgr.Manageable;

public class OuterMgr implements DataEngineInterface {
	private static OuterMgr mgr = null;

	private OuterMgr() {
	}

	public static OuterMgr getInstance() {
		if (mgr == null)
			mgr = new OuterMgr();
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
		return MyKiosk.outerMgr.findAll(kwd);
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
		return 0;
	}
}
