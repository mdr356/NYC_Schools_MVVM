package com.trinity.a20201031_marcregistre_nycschools.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.trinity.a20201031_marcregistre_nycschools.api.RetrofitApi
import com.trinity.a20201031_marcregistre_nycschools.model.NycHighSchool
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
interface HighSchoolRepository {
    fun highSchoolData(service: RetrofitApi) : MutableLiveData<List<NycHighSchool>?>
}

class HighSchoolRepositoryImpl @Inject constructor() : HighSchoolRepository {
    private val highSchoolDataResponse: MutableLiveData<List<NycHighSchool>?> =
        MutableLiveData()

    override fun highSchoolData(service: RetrofitApi): MutableLiveData<List<NycHighSchool>?> {
        val call = service.getSchoolDirectory()

        call.enqueue(object : Callback<List<NycHighSchool>> {
            override fun onResponse(call: Call<List<NycHighSchool>>, response: Response<List<NycHighSchool>>) {
                if (response.isSuccessful){
                    return highSchoolDataResponse.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<List<NycHighSchool>>, t: Throwable) {
                highSchoolDataResponse.postValue(null)
            }
        })
        return highSchoolDataResponse
    }
}
