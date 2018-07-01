package design.observer222;

import org.junit.Test;
import com.google.common.eventbus.DeadEvent;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

public class Producer {

    @Test
    public void client() {
        EventBus bus = new EventBus("observer-pattern");
        bus.register(new Observer() {

            @Subscribe
            @Override
            public void update(Event event) {
                System.out.println("库存系统接收到消息 [" + event.getState() + "], 减少库存");
            }
        });
        bus.register(new Observer() {

            @Subscribe
            @Override
            public void update(Event event) {
                System.out.println("支付系统接收到消息 [" + event.getState() + "], 正在收钱");
            }
        });

        // 不用实现接口, 直接给出一个Object对象也可
        bus.register(new Object() {

            @Subscribe
            public void onEvent(Event event) {
                System.out.println("关系系统接收到消息 [" + event.getState() + "], 当前用户关注店铺");
            }

            @Subscribe
            public void onEventFun(Event event) {
                System.out.println("我就是来打酱油的o(╯□╰)o");
            }
        });

        // 注册DeadEvent
        bus.register(new Object() {
            @Subscribe
            public void onDead(DeadEvent dead) {
//                LOGGER.log(Level.WARNING, "没有消费者接收" + dead);
                System.out.println("没有消费者接收" + dead);
            }
        });

        // 发布消息
        bus.post(new Event() {
            @Override
            public String getState() {
                return "付钱成功";
            }
        });

        bus.post("dead event O(∩_∩)O~");
    }
}
