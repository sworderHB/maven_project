package design.facade.pack001;

import org.apache.log4j.Logger;

/**
 * Disk子系统类
 * @author Administrator
 *
 */
public class Disk
{
    public static final Logger LOGGER = Logger.getLogger(Disk.class);
    public void start()
    {
        LOGGER.info("Disk is start...");
    }
    
    public void shutDown()
    {
        LOGGER.info("Disk is shutDown...");
    }
}
