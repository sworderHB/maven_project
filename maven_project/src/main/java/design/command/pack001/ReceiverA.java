package design.command.pack001;

/** 接收者执行与请求相关的操作，它具体实现对请求的业务处理。
 * 即： 最原始的用来处理请求的方类
 */
public class ReceiverA {
    /**
     * 真正执行命令相应的操作
     */
    public void action(){
        System.out.println("执行ReceiverA操作");
    }
}
