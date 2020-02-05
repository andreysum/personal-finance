package com.github.andreysum.pfinance;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Главный класс JavaFX приложения.
 *
 * @author Andrey Sumtsov
 */
@Component
public class PersonalFinanceApplication extends Application {
    private static ClassPathXmlApplicationContext ctx;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void init() {
        ctx = new ClassPathXmlApplicationContext("spring/application-context.xml");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        SpringStageLoader.loadMain().show();
    }

    @Override
    public void stop() {
        ctx.close();
    }
}
