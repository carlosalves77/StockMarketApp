package com.carlos.stockmarket.data.mapper

import com.carlos.stockmarket.data.local.CompanyListingEntity
import com.carlos.stockmarket.domain.model.CompanyListing

// Mapear o nossa Entidade do banco de dados com nosso model.
fun CompanyListingEntity.toCompanyListing(): CompanyListing {
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}