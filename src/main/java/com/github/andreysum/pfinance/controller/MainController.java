package com.github.andreysum.pfinance.controller;

import com.github.andreysum.pfinance.db.dao.CategoryRepo;
import com.github.andreysum.pfinance.db.schema.CategoryEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Handles saving category table controller.
 *
 * @author andreysum
 */
@Component
public class MainController {
    private final CategoryRepo categoryRepo;
    @FXML
    private TableView<CategoryEntity> savingCategoryTable;

    public MainController(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @FXML
    public void initialize() {
        ObservableList<CategoryEntity> categories =
                categoryRepo.findAll().stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
        savingCategoryTable.getItems().addAll(categories);
    }
}
