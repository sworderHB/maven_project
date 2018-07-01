package design.command.pack001;

public class Client {
    public static void main(String[] args) {
//        f();
//        g();
    }

    /**
     * new出命令对象
     */
    public static void f() {
        //创建命令对象，设定它的接收者
        Command commandA = new ConcreteCommandA();

        //创建请求者，把命令对象设置进去
        Invoker invoker = new Invoker();
        invoker.setCommand(commandA);
        //执行方法
        invoker.sendRequest();


        //创建接收者
        //创建命令对象，设定它的接收者
        Command commandB = new ConcreteCommandB();
        invoker.setCommand(commandB);
        
        
        // 最后这一行才是真正的调用执行代码，上面的代码都是装配准备过程
        invoker.sendRequest();
    }

    /**
     * 命令对象通过xml配置文件动态制定
     * 这样程序可扩展性大大增强
     * 以后想使用任何命令对象来处理客户请求，只修添加具体的命令对象和相关的Receiver对象，
     * 然后修改配置文件即可，程序代码不用做任何改动
     */
    public static void g() {
        Command command = (Command) XMLUtil.getBean("configCommand.xml");
        //创建请求者，把命令对象设置进去
        Invoker invoker = new Invoker();
        invoker.setCommand(command);


        // 最后这一行才是真正的调用执行代码，上面的代码都是装配准备过程
        invoker.sendRequest();
    }
}
