package servlets.fragment;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class HelloWorldServlet extends HttpServlet implements ServletContainerInitializer {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getOutputStream().print("Hello World!");
    }

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext sc) throws ServletException {
        var registration = sc.addServlet(HelloWorldServlet.class.getCanonicalName(), HelloWorldServlet.class);
        registration.addMapping("/helloWorld");
        registration.setLoadOnStartup(1);
    }
}
