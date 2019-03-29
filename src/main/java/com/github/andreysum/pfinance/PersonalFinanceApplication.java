package com.github.andreysum.pfinance;

import java.io.IOException;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Главный класс JavaFX приложения.
 *
 * @author Andrey Sumtsov
 */
public class PersonalFinanceApplication extends Application {
    private static ClassPathXmlApplicationContext ctx;
    private Parent root;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() throws IOException {
        ctx = new ClassPathXmlApplicationContext("spring/application-context.xml");

        final FXMLLoader loader = new FXMLLoader();
        // getting controllers through the Spring context
        loader.setControllerFactory(ctx::getBean);

        // stage initialization
        Resource fxmlDefinition = ctx.getResource("javafx/main.fxml");
        root = loader.load(fxmlDefinition.getInputStream());

        // bugfix with fonts blur in linux
        System.setProperty("prism.lcdtext", "false");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Spring context initialization


        primaryStage.setTitle("Персональные финансы");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    @Override
    public void stop() {
        ctx.close();
    }
}
