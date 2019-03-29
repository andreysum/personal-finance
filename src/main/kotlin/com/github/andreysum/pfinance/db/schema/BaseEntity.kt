package com.github.andreysum.pfinance.db.schema

import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.MappedSuperclass
import javax.persistence.GenerationType

@MappedSuperclass
open class BaseEntity : IdentifiedEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Long? = null
}