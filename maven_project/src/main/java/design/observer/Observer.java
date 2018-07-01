package design.observer;

public interface Observer {
    /** 
     * 收到被观察者变更通知后的处理方法
     * 观察者之所以要定义更新方法接口，是因为被观察者的状态发生了变化后，
     * 被观察者要在notifyObservers()方法中调用观察者的数据变更后的后续操作
     * */
    public void receiveUpdateNoticeHandle(float temprature);
}
