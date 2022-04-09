package servlets.fragment;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public final class ServeMetaInfResources extends HttpServlet implements ServletContainerInitializer {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var filePath = Paths.get("/META-INF/resources", Objects.requireNonNullElse(req.getPathInfo(), "/")).normalize();

        if (!filePath.startsWith(Paths.get("/META-INF/resources/")))
            return;

        try (var resourceStream = ServeMetaInfResources.class.getResource("/" + StreamSupport.stream(filePath.spliterator(), false).map(Path::toString).collect(Collectors.joining("/"))).openStream()) {
            resourceStream.transferTo(resp.getOutputStream());
        }
    }

    @Override
    public void onStartup(Set<Class<?>> set, ServletContext sc) throws ServletException {
        var registration = sc.addServlet(ServeMetaInfResources.class.getCanonicalName(), ServeMetaInfResources.class);
        registration.addMapping("/*");
        registration.setLoadOnStartup(1);
    }
}
