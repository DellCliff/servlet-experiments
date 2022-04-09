module ServletsWebFragment {
    exports servlets.fragment;

    provides javax.servlet.ServletContainerInitializer with
        servlets.fragment.HelloWorldServlet,
        servlets.fragment.ServeMetaInfResources;

    requires java.servlet;
}
