package com.github.andreysum.pfinance.db.dao

import com.github.andreysum.pfinance.db.schema.IdentifiedEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface BaseRepo<E: IdentifiedEntity> : JpaRepository<E, Long>