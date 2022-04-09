module ServletsEmbeddedTomcat {
    exports servlets.tomcat;

    requires org.apache.tomcat.embed.core;

    uses javax.servlet.ServletContainerInitializer;
}
