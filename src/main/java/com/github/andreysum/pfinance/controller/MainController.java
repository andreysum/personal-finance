package com.github.andreysum.pfinance.controller;

import com.github.andreysum.pfinance.db.dao.CategoryRepo;
import com.github.andreysum.pfinance.db.schema.CategoryEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.stream.Collectors;

/**
 * Handles saving category table controller.
 *
 * @author andreysum
 */
@Component
public class MainController {
    private static final int FIRST_ROW = 0;
    private final CategoryRepo categoryRepo;
    @FXML
    private TableView<CategoryEntity> table;
    private TableView.TableViewSelectionModel<CategoryEntity> selectionModel;

    public MainController(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @FXML
    public void initialize() {
        populateTable();
        setupSelectionModel();
    }

    private void populateTable() {
        ObservableList<CategoryEntity> categories =
                categoryRepo.findAll().stream().collect(Collectors.toCollection(FXCollections::observableArrayList));
        table.getItems().addAll(categories);
    }

    private void setupSelectionModel() {
        selectionModel = table.getSelectionModel();
        selectionModel.cellSelectionEnabledProperty().setValue(true);
        selectionModel.select(FIRST_ROW);
    }

    @FXML
    @Transactional
    protected void onEditCommit(TableColumn.CellEditEvent<CategoryEntity, String> event) {
        TablePosition<CategoryEntity, String> pos = event.getTablePosition();
        String newCategoryTitle = event.getNewValue();
        int row = pos.getRow();
        CategoryEntity category = event.getTableView().getItems().get(row);
        category.setTitle(newCategoryTitle);
        categoryRepo.save(category);
    }
}
