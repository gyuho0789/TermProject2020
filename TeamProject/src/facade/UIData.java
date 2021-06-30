package facade;

import java.util.List;


public interface UIData {
    void set(Object[] uitexts);//UI가 가지고 있는것을 객체에 넘겨줌
    String[] getUiTexts();//addrow에 들어갈 내용을 주는 메소드
    String[] getItemSize();
    String[] getItemCount();
}
