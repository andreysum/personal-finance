package com.github.andreysum.pfinance.db

import org.springframework.data.annotation.AccessType
import javax.persistence.*

/**
 * Категория.
 *
 * @author andreysum
 */
@Entity
@Table(name = "category")
@AccessType(AccessType.Type.FIELD)
class CategoryEntity : BaseEntity {
    protected constructor()
    constructor(type: CategoryType)

    @Enumerated
    val type = CategoryType.SAVING
    var title: String? = null
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentid")
    var parent: CategoryEntity? = null
    var totalAmount: Double? = null
}
