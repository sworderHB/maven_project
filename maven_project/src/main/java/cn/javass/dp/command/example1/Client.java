package cn.javass.dp.command.example1;

public class Client {
    public static void main(String[] args) {
        //创建接受者
        Receiver receiver = new Receiver();
        //创建命令对象，设定它的接收者
        Command command = new ConcreteCommand(receiver);
        //创建Invoker，把命令对象设置进去
        Invoker invoker = new Invoker();
        invoker.setCommand(command);


        // 最后这一行才是真正的调用执行代码，上面的代码都是装配准备过程
        invoker.runCommand();
    }
}
