package com.carlos.stockmarket.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

// Data Acess Object para querys do banco de dados room.
@Dao
interface StockDao {

    // Inserir ao banco de dados, caso aja conflito, apenas substitua
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListing(
        companyListingEntities: List<CompanyListingEntity>
    )

    // Excluir tudo da tabela.
    @Query("DELETE FROM companylistingentity")
    suspend fun clearCompanyListing()

    @Query (
        """
          SELECT *
          FROM companylistingentity
          WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR
          UPPER(:query) == symbol
        """
    )
    suspend fun searchCompanyListing(query: String): List<CompanyListingEntity>

}