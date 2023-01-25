package com.bashirli.cryptobase.repo

import com.bashirli.cryptobase.data.CryptoData
import com.bashirli.cryptobase.service.Service
import com.bashirli.cryptobase.util.Resource
import javax.inject.Inject

class DownloadRepo @Inject constructor(
    var service: Service
    ) : Repo {
    override suspend fun getData(): Resource<List<CryptoData>> {
        return try {
           var response=service.getData()
            if(response.isSuccessful){
                response.body()?.let {
                    return@let Resource.success(it)
                }?:Resource.error("Error",null)
            }else{
                return Resource.error("Error",null)
            }
        }catch (e:Exception){
            return Resource.error(e.localizedMessage,null)
        }
    }
}