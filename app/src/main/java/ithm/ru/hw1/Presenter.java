package ithm.ru.hw1;

public class Presenter {

    private ModelInterface mModel;
    private MainView view;

    public Presenter(MainView view, ModelInterface mModel) {
        this.mModel = mModel;
        this.view = view;
    }

    private int calcNewModelValue(int modelElementIndex) {
        int currentValue = mModel.getElementValueAtIndex(modelElementIndex);
        return currentValue + 1;
    }

    public void incSec() {
        int newModelValue = calcNewModelValue(0);
        mModel.setElementValueAtIndex(0, newModelValue);
        view.setSeconds(newModelValue);
    }

    public void incMin() {
        int newModelValue = calcNewModelValue(1);
        mModel.setElementValueAtIndex(1, newModelValue);
        view.setMinute(newModelValue);
    }

    public void incHours() {
        int newModelValue = calcNewModelValue(2);
        mModel.setElementValueAtIndex(2, newModelValue);
        view.setHours(newModelValue);
    }
}


