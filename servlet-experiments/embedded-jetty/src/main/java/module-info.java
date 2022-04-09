module ServletsEmbeddedJetty {
    exports servlets.jetty;

    requires org.eclipse.jetty.servlet;

    uses javax.servlet.ServletContainerInitializer;
}
