package com.trinity.a20201031_marcregistre_nycschools.repository

import androidx.lifecycle.MutableLiveData
import com.trinity.a20201031_marcregistre_nycschools.api.RetrofitApi
import com.trinity.a20201031_marcregistre_nycschools.model.SatScores
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
interface SatRepository {
    fun highSchoolSatScores(service: RetrofitApi) : MutableLiveData<SatScores>
}

class SatRepositoryImpl @Inject constructor() : SatRepository {
    private val highSchoolDataResponse: MutableLiveData<SatScores> =
        MutableLiveData()

    override fun highSchoolSatScores(service: RetrofitApi): MutableLiveData<SatScores> {
        val call = service.getSchoolSatScores()

        call.enqueue(object : Callback<SatScores> {
            override fun onResponse(call: Call<SatScores>, response: Response<SatScores>) {
                if (response.isSuccessful){
                    return highSchoolDataResponse.postValue(response.body())
                }
            }
            override fun onFailure(call: Call<SatScores>, t: Throwable) {
                highSchoolDataResponse.postValue(null)
            }
        })
        return highSchoolDataResponse
    }
}
