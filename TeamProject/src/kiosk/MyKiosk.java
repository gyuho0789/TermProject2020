package kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import mgr.Factory;
import mgr.Manageable;
import mgr.Manager;

public class MyKiosk {
	Scanner scan = new Scanner(System.in);

	private static MyKiosk mykiosk = null;

	private MyKiosk() {
	}

	public static MyKiosk getInstance() {
		if (mykiosk == null)
			mykiosk = new MyKiosk();
		return mykiosk;
	}

	static Manager outerMgr = new Manager();
	static Manager TopMgr = new Manager();
	static Manager PantsMgr = new Manager();
	static Manager HatMgr = new Manager();
	static Manager BagMgr = new Manager();

	public void run() {
		outerMgr.readAll("outer.txt", new Factory() {
			public Manageable create() {
				return new Outer();
			}
		});
		TopMgr.readAll("top.txt", new Factory() {
			public Manageable create() {
				return new Top();
			}
		});
		PantsMgr.readAll("pants.txt", new Factory() {
			public Manageable create() {
				return new Pants();
			}
		});
		HatMgr.readAll("hat.txt", new Factory() {
			public Manageable create() {
				return new Hat();
			}
		});
		BagMgr.readAll("bag.txt", new Factory() {
			public Manageable create() {
				return new Bag();
			}
		});
		outerMgr.printAll();
		TopMgr.printAll();
		PantsMgr.printAll();
		BagMgr.printAll();
		BagMgr.printAll();
	}

	static List<String> getStringList(Scanner scan, String end) {
		List<String> strList = new ArrayList<>();
		String tmp;
		while (true) {
			tmp = scan.next();
			if (tmp.contentEquals(end))
				break;
			strList.add(tmp);
		}
		return strList;
	}

	public static boolean isNumeric(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static void main(String[] args) {
		MyKiosk m = new MyKiosk();
		m.run();
	}

}
