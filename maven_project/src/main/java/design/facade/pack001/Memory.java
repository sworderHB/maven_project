package design.facade.pack001;

import org.apache.log4j.Logger;

/**
 * Memory子系统类
 * @author Administrator
 *
 */
public class Memory
{
    public static final Logger LOGGER = Logger.getLogger(Memory.class);
    public void start()
    {
        LOGGER.info("Memory is start...");
    }
    
    public void shutDown()
    {
        LOGGER.info("Memory is shutDown...");
    }
}
