package design.command.pack002;

/**
 * 如果需要修改功能键的功能，例如某个功能键可以实现“自动截屏”，
 * 只需要对应增加一个新的具体命令类，在该命令类与屏幕处理者(ScreenHandler)之间创建一个关联关系，然后将该具体命令类的对象通过配置文件注入到某个功能键即可，原有代码无须修改，符合“开闭原则”。在此过程中，每一个具体命令类对应一个请求的处理者（接收者），通过向请求发送者注入不同的具体命令对象可以使得相同的发送者对应不同的接收者，从而实现“将一个请求封装为一个对象，用不同的请求对客户进行参数化”，客户端只需要将具体命令对象作为参数注入请求发送者，无须直接操作请求的接收者。
 */
public class Client{
    public static void main(String args[]) {
        FBSettingWindow fbsw = new FBSettingWindow("功能键设置");
            
        FunctionButton fb1,fb2;
        fb1 = new FunctionButton("功能键1");
        fb2 = new FunctionButton("功能键1");
        
        Command command1,command2;
        //通过读取配置文件和反射生成具体命令对象
        command1 = (Command)XMLUtil.getBean(0);
        command2 = (Command)XMLUtil.getBean(1);
        
        //将命令对象注入功能键
        fb1.setCommand(command1);
        fb2.setCommand(command2);
        
//        fbsw.addFunctionButton(fb1);
//        fbsw.addFunctionButton(fb2);
//        fbsw.display();
        
        //调用功能键的业务方法
        fb1.onClick();
        fb2.onClick();
    }
}
