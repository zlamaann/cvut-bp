package upd.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;

@EnableAspectJAutoProxy(proxyTargetClass = true)
@Configuration
@EnableMBeanExport
@Import({WebAppConfig.class, PersistenceConfig.class, ServiceConfig.class})
public class AppConfig {
}
