package design.observer222;

import java.util.Observable;
import java.util.Observer;

public class WareHouseObserver implements Observer{
    private String orderState;

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("拉模式: " + o);
        orderState = (String) arg;
        System.out.println("推模式: 库存系统接收到消息 [" + orderState + "], 减少库存");
    }
}
