package com.github.andreysum.pfinance.service.db.gen

import com.github.andreysum.pfinance.db.schema.CategoryEntity
import com.github.andreysum.pfinance.db.dao.CategoryRepo
import org.springframework.stereotype.Service

@Service
class CategoryGenerator (private val repo: CategoryRepo): Generating {
    override fun generate() {
        if (repo.count() == 0L)
        listOf("Автомобиль", "Квартплата", "Квартира", "Наш ДР", "Одежда",
                "Отпуск", "Текущие", "Роднуле", "На каляску", "Спорт", "Налоги",
                "НЗ", "Инвестиции", "Декретные")
                .forEach { title ->  repo.save(CategoryEntity(title)) }
        repo.flush()
    }
}