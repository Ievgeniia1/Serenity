package serenity.bdd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EnvironmentPropertyLoader {

    private static Logger log = LoggerFactory.getLogger(EnvironmentPropertyLoader.class);

    public static Logger getLog() {
        return log;
    }


    public static String getProperty(final String propertyName){
        String property = System.getProperty(propertyName);
        return property;
    }


}
