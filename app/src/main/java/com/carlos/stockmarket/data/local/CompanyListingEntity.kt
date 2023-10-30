package com.carlos.stockmarket.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

// Tabela do Banco.
@Entity
data class CompanyListingEntity(
    val name: String,
    val symbol: String,
    val exchange: String,
    @PrimaryKey
    val id: Int? = null

)
