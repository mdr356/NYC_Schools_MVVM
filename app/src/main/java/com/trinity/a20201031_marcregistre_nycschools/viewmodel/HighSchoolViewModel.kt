package com.trinity.a20201031_marcregistre_nycschools.viewmodel

import android.content.Context
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.trinity.a20201031_marcregistre_nycschools.api.RetrofitApi
import com.trinity.a20201031_marcregistre_nycschools.model.NycHighSchool
import com.trinity.a20201031_marcregistre_nycschools.repository.HighSchoolRepository
import javax.inject.Inject

class HighSchoolViewModel @Inject constructor(val repository: HighSchoolRepository) : ViewModel() {
    // @JvmField: exposes a Kotlin property as a field in Java
    @JvmField val isLoading = MutableLiveData<Boolean>()
    @JvmField val showErrorDialog = MutableLiveData<Boolean>()

    private var nycHighSchool: MutableLiveData<List<NycHighSchool>> = MutableLiveData()

    fun getNycHighSchoolData(ctx: Context, service: RetrofitApi) : LiveData<List<NycHighSchool>>? {
        repository.highSchoolData(service).observe(ctx as LifecycleOwner, Observer {
            isLoading.postValue(false)
            if(it == null) {
                showErrorDialog.postValue(true)
            }
            else nycHighSchool.postValue(it)
        })
        return nycHighSchool
    }
}