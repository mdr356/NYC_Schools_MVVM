package com.trinity.a20201031_marcregistre_nycschools.viewmodel

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.trinity.a20201031_marcregistre_nycschools.api.RetrofitApi
import com.trinity.a20201031_marcregistre_nycschools.model.SatScores
import com.trinity.a20201031_marcregistre_nycschools.repository.SatRepository
import javax.inject.Inject

class SatScoresViewModel @Inject constructor(val repository: SatRepository) : ViewModel() {
    // @JvmField: exposes a Kotlin property as a field in Java
    @JvmField val isLoading = MutableLiveData<Boolean>()
    @JvmField val showErrorDialog = MutableLiveData<Boolean>()

    var nycSatScores: MutableLiveData<SatScores> = MutableLiveData()

    fun getSatScoresData(ctx: Context, service: RetrofitApi) : LiveData<SatScores> {
        repository.highSchoolSatScores(service).observe(ctx as LifecycleOwner, Observer {
            isLoading.postValue(false)
            if(it == null) {
                showErrorDialog.postValue(true)
            }
            else nycSatScores.value = it
        })
        return nycSatScores
    }
}
