package servlets.jetty;

import java.net.InetSocketAddress;
import java.util.ServiceLoader;
import javax.servlet.ServletContainerInitializer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContainerInitializerHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;

public final class Main {

    public static void main(String[] args) throws Exception {
        var context = new ServletContextHandler();
        context.setContextPath("/");

        for (var service : ServiceLoader.load(ServletContainerInitializer.class))
            context.addServletContainerInitializer(new ServletContainerInitializerHolder(service));

        var server = new Server(new InetSocketAddress(8080));
        server.setHandler(context);
        server.start();
    }

    private Main(){}
}
