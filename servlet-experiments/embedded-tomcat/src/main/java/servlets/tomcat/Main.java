package servlets.tomcat;

import java.util.ServiceLoader;
import javax.servlet.ServletContainerInitializer;
import org.apache.catalina.startup.Tomcat;

public final class Main {

    public static void main(String[] args) throws Exception {
        var tomcat = new Tomcat();
        tomcat.getConnector().setPort(8080);

        var context = tomcat.addContext("", null);
        for (var service : ServiceLoader.load(ServletContainerInitializer.class))
            context.addServletContainerInitializer(service, null);

        tomcat.start();
        tomcat.getServer().await();
    }

    private Main() {}
}
