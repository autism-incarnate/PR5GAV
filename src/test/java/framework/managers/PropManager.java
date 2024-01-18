package framework.managers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropManager {
    private final Properties props = new Properties();
    private static PropManager prop_ref = null;

    private PropManager() {
        loadProps();
        loadCustomProperties();
    }

    public static PropManager getPropInstance() {
        if(prop_ref == null) {
            prop_ref = new PropManager();
        }
        return prop_ref;
    }

    public String getProp(String key) {
        return props.getProperty(key);
    }
    private void loadProps() {
        try {
            props.load(new FileInputStream("src/main/resources/app.properties"));
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void loadCustomProperties() {
        props.forEach((key, value) -> System.getProperties()
                .forEach((customUserKey, customUserValue) -> {
                    if (key.toString().equals(customUserKey.toString()) &&
                            !value.toString().equals(customUserValue.toString())) {
                        props.setProperty(key.toString(), customUserValue.toString());
                    }
                }));
    }
}
