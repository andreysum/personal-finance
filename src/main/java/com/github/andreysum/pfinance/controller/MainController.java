package com.github.andreysum.pfinance.controller;

import java.util.List;

import javax.annotation.PostConstruct;

import com.github.andreysum.pfinance.db.dao.CategoryRepo;
import com.github.andreysum.pfinance.db.schema.CategoryEntity;

import org.springframework.stereotype.Component;

import javafx.fxml.FXML;
import javafx.scene.control.TreeTableView;

/**
 * Handles saving category table controller.
 *
 * @author andreysum
 */
@Component
public class MainController {
    private final CategoryRepo categoryRepo;
    @FXML
    private TreeTableView<CategoryEntity> table;

    public MainController(CategoryRepo categoryRepo) {
        this.categoryRepo = categoryRepo;
    }

    @PostConstruct
    public void init() {
        List<CategoryEntity> categories = categoryRepo.findAll();
    }
}
