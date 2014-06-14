package name.dargiri;

/**
 * @author dionis on 14/06/14.
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springsource.loaded.ReloadEventProcessorPlugin;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author dionis on 09/06/14.
 */
public class SpringReloadPlugin implements ReloadEventProcessorPlugin {
    private static final Logger LOGGER = LoggerFactory.getLogger(SpringReloadPlugin.class);

    @Override
    public boolean shouldRerunStaticInitializer(String s, Class<?> aClass, String s2) {
        LOGGER.info("Should re-run static initializer? [{}], [{}], [{}]", s, aClass, s2);
//        reloadAppContextIfNeeded(aClass);
        return false;
    }

    @Override
    public synchronized void reloadEvent(String s, Class<?> aClass, String s2) {
        LOGGER.info("Reload event [{}], [{}], [{}]", s, aClass, s2);
        reloadAppContextIfNeeded(aClass);
    }

    private void reloadAppContextIfNeeded(Class<?> aClass) {
        ConfigurableWebApplicationContext ctxt = (ConfigurableWebApplicationContext) SpringAppContext.getApplicationContext();
        boolean webAppContextInitializationNeeded = false;
        try {
            Map<String, Object> beansOfType = ctxt.getBeansOfType((Class) aClass);

            if(beansOfType.isEmpty()) {
                Class<?>[] interfaces = aClass.getInterfaces();
                if (interfaces != null) {
                    for (Class<?> anInterface : interfaces) {
                        beansOfType = ctxt.getBeansOfType((Class) anInterface);
                        if(!beansOfType.isEmpty()) {
                            LOGGER.info("Found beans for interface [{}]", anInterface);
                            break;
                        }
                    }
                }
            }



            LOGGER.info("Found beans of type: ");
            for (Map.Entry<String, Object> entry : beansOfType.entrySet()) {
                LOGGER.info(" --> [{}]", entry);
            }
            webAppContextInitializationNeeded |= !beansOfType.isEmpty();

            //if jpa entity
            Annotation[] annotations = aClass.getAnnotations();
            if (annotations != null) {
                for (Annotation annotation : annotations) {
                    //detect that it's hibernate stuff
                    webAppContextInitializationNeeded |= annotation.annotationType().getSimpleName().equalsIgnoreCase("Entity");
                    LOGGER.info("Annotation: [{}]", annotation.annotationType().getSimpleName());
                }
            }

            Set<String> keyClasses = new HashSet<>(Arrays.asList(".SpringReloadPlugin", ".BaseLauncher"));
            webAppContextInitializationNeeded |= keyClasses.contains(aClass.getSimpleName());
        } catch (BeansException e) {
            e.printStackTrace();
        }


        if (webAppContextInitializationNeeded) {
            long start = System.currentTimeMillis();
            LOGGER.info("Refreshing app-context");
            ctxt.refresh();
            LOGGER.info("Refreshed app-context");
            long end = System.currentTimeMillis();
            LOGGER.info("App context reloaded in [{}] ms", (end - start));
        } else {
            LOGGER.info("Refresh of web app not needed");
        }
    }
}
