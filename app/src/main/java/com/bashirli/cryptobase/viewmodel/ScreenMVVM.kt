package com.bashirli.cryptobase.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bashirli.cryptobase.data.CryptoData
import com.bashirli.cryptobase.repo.DownloadRepo
import com.bashirli.cryptobase.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import javax.inject.Inject


@HiltViewModel
class ScreenMVVM @Inject constructor(
   private var repo: Repo
) :ViewModel() {

    private var _loading=MutableLiveData<Boolean>()
    val loading:LiveData<Boolean>
    get() = _loading

    private var _success=MutableLiveData<Boolean>()
    val success:LiveData<Boolean>
        get() = _success

    private var _errorData=MutableLiveData<Boolean>()
    val errorData:LiveData<Boolean>
        get() = _errorData

    var errorMessage:String?=null

    lateinit var cryptoData:ArrayList<CryptoData>

    var exceptionHandler= CoroutineExceptionHandler { coroutineContext, throwable ->
        errorMessage=throwable.localizedMessage
        _errorData.value=true
        _loading.value=false
    }

   private var job: Job?=null

    fun loadData(){
    _loading.value=true

    job= CoroutineScope(Dispatchers.IO+exceptionHandler).launch {
        var resource=repo.getData()
        withContext(Dispatchers.Main){
            resource.data?.let {
                cryptoData=ArrayList(it)
                _loading.value=false
                _success.value=true


            }
        }
    }

    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}