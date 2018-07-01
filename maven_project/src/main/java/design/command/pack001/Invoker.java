package design.command.pack001;

/**
 * 调用者即请求发送者，它通过命令对象来执行请求。 一个调用者并不需要在设计时确定其接收者，
 * 因此它只与抽象命令类之间存在关联关系。
 * 在程序运行时可以将一个具体命令对象注入其中，
 * 再调用具体命令对象的execute()方法， 从而实现间接调用请求接收者的相关操作。
 * 
 * Invoker是提供给Client使用的，即发送命令请求的操作客户端是通过操作Invoker来完成的，
 * 或者说这是使用命令对象的入口
 */
public class Invoker {
    /**
     * 持有命令对象
     */
    private Command command = null;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    /**
     * 正在接受Client请求的方法，是命令对象的入口
     */
    public void sendRequest() {
        command.execute();
    }
}
