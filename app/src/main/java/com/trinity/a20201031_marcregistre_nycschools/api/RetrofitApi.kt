package com.trinity.a20201031_marcregistre_nycschools.api

import com.trinity.a20201031_marcregistre_nycschools.model.NycHighSchool
import com.trinity.a20201031_marcregistre_nycschools.model.SatScores
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitApi {

    @GET("resource/s3k6-pzi2.json")
    fun getSchoolDirectory(): Call<List<NycHighSchool>>

    @GET("resource/f9bf-2cp4.json")
    fun getSchoolSatScores(
        @Query("school_name") schoolName: String
    ): Call<SatScores>

}