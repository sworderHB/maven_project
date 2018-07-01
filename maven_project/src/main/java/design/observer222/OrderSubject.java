package design.observer222;

import java.lang.reflect.Field;
import java.util.Observable;
import java.util.Vector;

public class OrderSubject extends Observable{
    private String state;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        this.setChanged();
        this.notifyObservers(state);
    }

    @Override
    public String toString() {
        String result = "OrderSubject{";
        try {
            Field obs = Observable.class.getDeclaredField("obs");
            obs.setAccessible(true);
            Vector vector = (Vector) obs.get(this);
            result += vector;
        } catch (NoSuchFieldException | IllegalAccessException ignored) {
        }
        return result +
                "state='" + state + '\'' +
                '}';
    }
}
