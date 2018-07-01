package com.haobin.others;

import java.io.File;
import java.util.Enumeration;
import java.util.PropertyResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre><b>
 *   Title: 检测进程是否假死的定时任务
 *          每间隔一定的时间，向某个文件中写入一个字符，要求每次写入后文件的长度不能一样 
 *   Description: 
 * </b><</pre>
 * 
 * @author  haobin
 * @date	2017年11月29日 下午1:50:36
 * @version 1.0
 */
public class MonitorSuspendTask extends TimerTask {
    private static Logger  logger = LoggerFactory.getLogger(MonitorSuspendTask.class);
    
    private String jobName;
//    private String filePath = MonitorSuspendTask.class.getClassLoader().getResource("1.txt").getPath();
    
    
    public static int DEPLAY_SECOND;  //延迟时间 单位是秒
    public static int PERIOD_SECOND;  //间隔时间  单位是秒
    
    public static DateTime firstTime = new DateTime();
    
    
    public static String  FILE_PATH;
    
    static {
        config();
    }
    
    private static void config() {
        // 读取系统配置
        PropertyResourceBundle resourceBundle = (PropertyResourceBundle) PropertyResourceBundle.getBundle("system");
        
        // 将系统设置赋值给类变量
        Enumeration enu = resourceBundle.getKeys();
        while (enu.hasMoreElements())
        {
            String propertyName = enu.nextElement().toString();
            
            if (propertyName.equals("shedule_deplay_second"))
                DEPLAY_SECOND = Integer.parseInt( resourceBundle.getString("shedule_deplay_second") );
            if (propertyName.equals("shedule_period_second"))
                PERIOD_SECOND = Integer.parseInt( resourceBundle.getString("shedule_period_second") );
            
            if (propertyName.equals("monitor_suspend_task_logfile_path"))
                FILE_PATH = resourceBundle.getString("monitor_suspend_task_logfile_path") ;
        }
    }
    
    public MonitorSuspendTask(String jobName) {
        super();
        this.jobName = jobName;
    }

    @Override
    public void run() {
        try {
            DateTime dateTime = new DateTime();
            
            if ( firstTime.plusMinutes(1).isAfter(dateTime) ) {
                logger.info( writeRandomCharacterToFile(FILE_PATH) );
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(" MonitorSuspendTask 任务执行失败了");
        }
    }
    
    /**
     * <pre class="code"><b>{@code
     *   Title: 写字符A到 fileAbsolutePath指定的文件，
     *          要求每次写入后文件的长度不能和以前的长度一样
     *   
     *   具体算法是：
     *          初始化文件的内容为字符A
     *          如果文件长度到了或超过了10个，则清空文件，然后把单个字符A写入文件
     *          否则追加字符A到文件
     *
     * @param fileAbsolutePath
     * @return
     * @throws Exception  
     * 
     * @author  haobin
     * @date    2017年11月29日 上午11:39:41
     * </b></pre>
     */
    public  String writeRandomCharacterToFile(String fileAbsolutePath) throws Exception {
        File file = new File(fileAbsolutePath);
        
        if ( !file.exists() ) {
            return "文件不存在!";
        }
        
        String contentStr = FileUtils.readFileToString(file, "UTF-8");
        
        if (contentStr.length() < 10) {
            FileUtils.writeStringToFile(file, contentStr+"A", "UTF-8", false);
            contentStr = contentStr+"A";
        }
        else {
            FileUtils.writeStringToFile(file, "A", "UTF-8", false);
            contentStr = "A";
        }
        
        return contentStr;
    }

    /**
     * <pre class="code"><b>{@code
     *   Title: 启动任务的入口
     *  
     * 
     * @author  haobin
     * @date    2017年11月29日 下午1:49:45
     * </b></pre>
     */
    public static void taskStart() {
        Timer timer = new Timer();
    
        // 从现在开始 1 秒钟之后，每隔 1 秒钟执行一次 job1
        timer.schedule(new MonitorSuspendTask("job1"), DEPLAY_SECOND*1000, PERIOD_SECOND*1000);
    }
    
    public static void main(String[] args) throws Exception{
        String filePath = MonitorSuspendTask.class.getClassLoader().getResource("1.txt").getPath();
        MonitorSuspendTask timerTest = new MonitorSuspendTask("");
        for (int i=0; i<100; ++i) {
            System.out.println( timerTest.writeRandomCharacterToFile(filePath) );
            Thread.sleep(1000);
        }
//        taskStart();
    }

}
