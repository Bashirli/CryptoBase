package com.bashirli.cryptobase.service

import com.bashirli.cryptobase.data.CryptoData
import com.bashirli.cryptobase.util.MAIN_URL
import retrofit2.Response
import retrofit2.http.GET

interface Service {

    @GET(MAIN_URL)
    suspend fun getData(): Response<List<CryptoData>>
}