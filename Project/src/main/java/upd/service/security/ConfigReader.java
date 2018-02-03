package upd.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import upd.util.ConfigParam;
import upd.util.Constants;

import javax.annotation.PostConstruct;
import java.util.Properties;

@PropertySource("classpath:config.properties")
@Component
public class ConfigReader {

    @Autowired
    private Environment environment;

    private final Properties defaultConfig = new Properties();

    /**
     * Gets value of the specified configuration parameter.
     *
     * @param param Configuration parameter
     * @return Configuration parameter value, empty string if the parameter is not set
     */
    public String getConfig(ConfigParam param) {
        return getConfig(param, "");
    }

    public String getConfig(ConfigParam param, String defaultValue) {
        if (environment.containsProperty(param.toString())) {
            return environment.getProperty(param.toString());
        }
        return defaultConfig.getProperty(param.toString(), defaultValue);
    }

    @PostConstruct
    public void initDefaultConfiguration() {
        defaultConfig.setProperty(ConfigParam.INDEX_FILE.toString(), Constants.INDEX_FILE_LOCATION);
    }
}
