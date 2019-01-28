package ithm.ru.hw1;

import java.util.ArrayList;
import java.util.List;


public class Model implements ModelInterface {

    private List<Integer> mList;

    public Model() {
        mList = new ArrayList<>(3);
        mList.add(0);
        mList.add(0);
        mList.add(0);
    }

    @Override
    public int getElementValueAtIndex(int _index) {
        return mList.get(_index);
    }

    @Override
    public void setElementValueAtIndex(int _index, int value) {
        mList.set(_index, value);
    }
}
