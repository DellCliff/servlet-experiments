package servlets.undertow;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.servlet.Servlets;
import io.undertow.servlet.api.ServletContainerInitializerInfo;
import java.util.ServiceLoader;
import javax.servlet.ServletContainerInitializer;

public final class Main {

    public static void main(String[] args) throws Exception {
        var deploymentInfo = Servlets.deployment()
            .setClassLoader(Main.class.getClassLoader())
            .setDeploymentName(Main.class.getCanonicalName())
            .setContextPath("/");

        for (var service : ServiceLoader.load(ServletContainerInitializer.class))
            deploymentInfo = deploymentInfo.addServletContainerInitializer(new ServletContainerInitializerInfo(service.getClass(), null));

        var deploymentManager = Servlets.defaultContainer().addDeployment(deploymentInfo);
        deploymentManager.deploy();

        Undertow.builder()
            .addHttpListener(8080, "localhost")
            .setHandler(Handlers.path(Handlers.redirect("/")).addPrefixPath("/", deploymentManager.start()))
            .build()
            .start();
    }

    private Main() {}
}
