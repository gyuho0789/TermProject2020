package mgr;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Manager {
	public ArrayList<Manageable> mList = new ArrayList<>();

	public void readAll(String filename, Factory fac) {
		Scanner filein = openFile(filename);
		Manageable m = null;
		while (filein.hasNext()) {
			m = fac.create();
			m.read(filein);
			mList.add(m);
		}
		filein.close();
	}

	public void printAll() {
		for (Manageable m : mList) {
			m.print();
		}
	}

	public Manageable find(String kwd) {
		for (Manageable m : mList)
			if (m.matches(kwd))
				return m;
		return null;
	}

	public List<Manageable> findAll(String kwd) {
		List<Manageable> results = new ArrayList<>();
		for (Manageable m : mList)
			if (m.matches(kwd))
				results.add(m);
		return results;
	}

	public void search(Scanner keyScanner) {
		String kwd = null;
		while (true) {
			System.out.print(">> ");
			kwd = keyScanner.next();
			if (kwd.equals("end"))
				break;
			for (Manageable m : mList) {
				if (m.matches(kwd))
					m.print();
			}
		}
	}

	public Scanner openFile(String filename) {
		Scanner filein = null;
		try {
			filein = new Scanner(new File(filename));
		} catch (Exception e) {
			System.out.println(filename + ": 파일 없음");
			System.exit(0);
		}
		return filein;
	}
}
