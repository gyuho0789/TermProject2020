package facade;

import java.util.List;

import mgr.Manageable;

public interface DataEngineInterface {
	int getColumnCount();
	String[] getColumnNames(boolean b);
	// 파일에서 Manager로 데이터를 모두 읽어들임
	void readAll(String filename);
	// 키워드에 매치되는 것을 모두 찾아 리스트로 반환
	List<Manageable> search(String kwd);
	// UI 테이블의 행에 있는 데이터를 스트링 배열로 받아와서 새로운 객체 추가
	void addNewItem(String[] uiTexts);
	// UI 테이블의 행에 있는 데이터를 스트링 배열로 받아와서 해당 객체 수정
	void update(String[] uiTexts);
	// UI 테이블의 행의 첫번째 데이터를 키로 받아와 해당 객체를 찾아 삭제
	void remove(String kwd);
	int check();
}
