package org.d3if4078.limassegiempat.network

import org.d3if4078.limassegiempat.model.BangunRuang
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface ApiBangunRuang {

    @GET("bangun-ruang.json")
    fun getBangunRuang() : Call<List<BangunRuang>>

    companion object {

        var BASE_URL = "https://raw.githubusercontent.com/girasm22/Assessment-2-Mobpro/master/api/"

        fun create() : ApiBangunRuang {
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()

            return retrofit.create(ApiBangunRuang::class.java)

        }
    }
}