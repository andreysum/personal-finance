package com.github.andreysum.pfinance;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * Определяет порядок загрузки FX-компонентов.
 */
@Component
public class SpringStageLoader implements ApplicationContextAware {
    private static final String FXML_DIR = "/javafx/";
    private static final String MAIN_STAGE = "main";
    private static ApplicationContext staticContext;
    private static String staticTitle;
    @Value("${applicationTitle}")
    private String title;

    /**
     * Загрузка корневого узла и его дочерних элементов из fxml шаблона
     *
     * @return объект типа Parent
     * @throws IOException бросает исключение ввода-вывода
     */
    private static Parent load() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        final String fxmlPath = FXML_DIR + MAIN_STAGE + ".fxml";
        loader.setLocation(SpringStageLoader.class.getResource(fxmlPath));
        // setLocation необходим для корректной того чтобы loader видел наши кастомные котнролы
        loader.setClassLoader(SpringStageLoader.class.getClassLoader());
        loader.setControllerFactory(staticContext::getBean);
        return loader.load(SpringStageLoader.class.getResourceAsStream(fxmlPath));
    }

    /**
     * Реализуем загрузку главной сцены. На закрытие сцены стоит обработчик, которых выходит из приложения
     *
     * @return главную сцену
     * @throws IOException бросает исключение ввода-вывода
     */
    static Stage loadMain() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(load()));
        stage.setOnHidden(event -> Platform.exit());
        stage.setTitle(staticTitle);
        return stage;
    }

    /**
     * Передаем данные в статические поля в реализации метода интерфейса ApplicationContextAware, т.к. методы их использующие тоже статические
     */
    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        SpringStageLoader.staticContext = context;
        SpringStageLoader.staticTitle = title;
    }
}
