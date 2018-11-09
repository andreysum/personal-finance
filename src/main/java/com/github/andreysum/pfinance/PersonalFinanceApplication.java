package com.github.andreysum.pfinance;

import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

/**
 * Главный класс JavaFX приложения.
 *
 * @author Andrey Sumtsov
 */
public class PersonalFinanceApplication extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Spring context initialization
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring/application-context.xml");


        // bugfix with fonts blur in linux
        System.setProperty("prism.lcdtext", "false");

        final FXMLLoader loader = new FXMLLoader();
        // getting controllers through the Spring context
        loader.setControllerFactory(ctx::getBean);

        // stage initialization
        Resource fxmlDefinition = ctx.getResource("javafx/main.fxml");
        Parent root = loader.load(fxmlDefinition.getInputStream());
        primaryStage.setTitle("Персональные финансы");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
