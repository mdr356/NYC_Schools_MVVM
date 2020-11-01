package com.trinity.a20201031_marcregistre_nycschools.api

import com.trinity.a20201031_marcregistre_nycschools.model.NycHighSchool
import com.trinity.a20201031_marcregistre_nycschools.model.SatScores
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitApi {

    @GET("resource/s3k6-pzi2.json")
    fun getSchoolDirectory(): Call<List<NycHighSchool>>

    @GET("resource/f9bf-2cp4.json")
    fun getSchoolSatScores(): Call<SatScores>

}