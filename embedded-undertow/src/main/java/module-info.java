module ServletsEmbeddedUndertow {
    exports servlets.undertow;

    requires java.servlet;
    requires undertow.core;
    requires undertow.servlet;

    uses javax.servlet.ServletContainerInitializer;
}
