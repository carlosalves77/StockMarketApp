package com.carlos.stockmarket.domain.repository

import com.carlos.stockmarket.domain.model.CompanyListing
import com.carlos.stockmarket.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    suspend fun getCompanyListings(
      fetchFromRemote: Boolean,
      query: String
    ): Flow<Resource<List<CompanyListing>>>


}