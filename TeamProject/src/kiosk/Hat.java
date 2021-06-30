package kiosk;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import facade.UIData;
import mgr.Manageable;

public class Hat implements Manageable, UIData {
	List<String> itemSize = new ArrayList<>();
	List<Integer> itemCount = new ArrayList<>();
	String code;
	String name;
	String brandname;
	String color;
	int price;

	public void read(Scanner scan) {
		code = scan.next();
		name = scan.nextLine();
		brandname = scan.next();
		color = scan.next();
		price = scan.nextInt();
		while (true) {
			String size = scan.next();
			if (size.contentEquals("0"))
				break;
			itemSize.add(size);
			int count = scan.nextInt();
			itemCount.add(count);
		}
	}

	public void print() {
		System.out.printf("[%s]%s [%s] %s %d", code, name, brandname, color, price);

		System.out.println();
	}

	public boolean matches(String kwd) {
		if (name.contains(kwd)) // ""일 때 true
			return true;
		if (kwd.contentEquals(name))
			return true;
		if (kwd.contentEquals(brandname))
			return true;
		if (kwd.contentEquals(color))
			return true;
		return false;
	}

	public boolean matches(String[] kwdArr) {
		for (String kwd : kwdArr) {
			if (!matches(kwd))
				return false;
		}
		return true;
	}

	public void set(Object[] row) {// GUI얻은 데이터를 저장함 String이여도 상관 없음
		code = (String) row[0];
		name = (String) row[1];
		brandname = (String) row[2];
		color = (String) row[3];
		price = Integer.parseInt((String) row[4]);
	}

	public String[] getUiTexts() {
		String[] texts = new String[5];
		texts[0] = code;
		texts[1] = name;
		texts[2] = brandname;
		texts[3] = color;
		texts[4] = "" + price;
		return texts;
	}

	public String[] getItemSize() {
		String[] result = new String[itemSize.size()];
		for (int i = 0; i < itemSize.size(); i++) {
			result[i] = itemSize.get(i);
		}
		return result;
	}

	public String[] getItemCount() {
		String[] result = new String[itemCount.size()];
		for (int i = 0; i < itemCount.size(); i++) {
			String count = Integer.toString(itemCount.get(i));
			result[i] = count;
		}
		return result;
	}
}
