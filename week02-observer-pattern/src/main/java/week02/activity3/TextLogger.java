package week02.activity3;

// New observer: added without touching DataModel at all.
public class TextLogger implements DataObserver {
    @Override
    public void onDataChanged(int newValue) {
        System.out.println("LOG: value changed to " + newValue);
    }
}
