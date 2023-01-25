package com.bashirli.cryptobase.repo

import com.bashirli.cryptobase.data.CryptoData
import com.bashirli.cryptobase.util.Resource

interface Repo {
    suspend fun getData():Resource<List<CryptoData>>
}