package com.teivar.prices.config;

import com.teivar.prices.controllers.Controller;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.util.Callback;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.IOException;
import java.io.InputStream;

public class SpringFXMLLoader {

    private static final ApplicationContext APPLICATION_CONTEXT = new AnnotationConfigApplicationContext(DataConfig.class);

    public static Controller load(String url) {
        InputStream fxmlStream = null;
        try {
            fxmlStream = SpringFXMLLoader.class.getResourceAsStream(url);
            FXMLLoader loader = new FXMLLoader();
            loader.setControllerFactory(new Callback<Class<?>, Object>() {
                @Override
                public Object call(Class<?> aClass) {
                    return APPLICATION_CONTEXT.getBean(aClass);
                }
            });

            Node view = (Node) loader.load(fxmlStream);
            Controller controller = loader.getController();
            controller.setView(view);

            return controller;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fxmlStream != null) {
                try {
                    fxmlStream.close();
                } catch (IOException e) {
                }
            }
        }
    }
}