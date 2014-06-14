package name.dargiri;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @author dionis on 14/06/14.
 */
@Component
public class SpringAppContext implements ApplicationContextAware {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringAppContext.class);

    private static volatile ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        LOGGER.info("Setting spring app context");
        SpringAppContext.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
