package design.observer;

public class Client {
    public static void main(final String[] args) {
        final ConcreteSubject sb = new ConcreteSubject();
        sb.setTemperature((float) 20.00);

        ConcreteObserver concreteObserver = new ConcreteObserver(sb);
        System.out.println(concreteObserver.getTemperature());
        
        sb.setTemperature((float) 21.00);
        System.out.println(concreteObserver.getTemperature());
        
        sb.setTemperature((float) 33.00);
        System.out.println(concreteObserver.getTemperature());
    }
}
