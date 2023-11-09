package com.carlos.stockmarket.data.repository

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.carlos.stockmarket.data.local.StockDatabase
import com.carlos.stockmarket.data.mapper.toCompanyListing
import com.carlos.stockmarket.data.remote.StockApi
import com.carlos.stockmarket.domain.model.CompanyListing
import com.carlos.stockmarket.domain.repository.StockRepository
import com.carlos.stockmarket.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    val api: StockApi,
    val db: StockDatabase
) : StockRepository {

 private val dao = db.dao

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
       return flow {
        emit(Resource.Loading(true))
            val localListings = dao.searchCompanyListing(query)

           emit(Resource.Success(
               data = localListings.map {
                  it.toCompanyListing()
               }
           ))

           val isDbEmpty = localListings.isEmpty() && query.isEmpty()
           val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote
           if (shouldJustLoadFromCache) {
               emit(Resource.Loading(false))
               return@flow
           }

           val remoteListing = try {
           val response = api.getListings()
               response.byteStream()
           } catch (e: IOException) {
               e.printStackTrace()
               emit(Resource.Error("Couldn't load data"))

           } catch (e: HttpException) {
               e.printStackTrace()
               emit(Resource.Error("Couldn't load data"))
           }
       }

    }


}