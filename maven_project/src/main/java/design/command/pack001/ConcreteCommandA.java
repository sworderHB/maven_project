package design.command.pack001;

/**
 * 具体命令类是抽象命令类的子类，实现了在抽象命令类中声明的方法，它对应具体的接收者对象， 将接收者对象的动作绑定其中。在实现execute()方法时，将调用接收者对象的相关操作(Action)
 */
public class ConcreteCommandA implements Command {
    // 郝斌： 这里设置setXxx方法没有意义，因为Command接口中一般没法定义setXxxx方法，
    // 郝斌： 不同的Receiver可能都完全不一样， 即不同的Receiver一般没法抽象成一个统一的接口或抽象类
    // 持有相应的接收者对象
    private ReceiverA receiver = null;

    // Receiver既可以在构造函数中设置，也可以在Client中new出来，然后再设置到ConcreteCommand对象中
    public ConcreteCommandA() {
        this.receiver = new ReceiverA();
    }

    @Override
    public void execute() {
        // 通常会转调接收者对象的相应方法，让接收者来真正执行功能
        receiver.action();
    }
}
