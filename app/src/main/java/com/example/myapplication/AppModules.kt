package com.example.myapplication

import android.util.Log
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiConstants {
    companion object {
        const val API_LOREM_PICSUM = "api.lorem_picsum"
    }
}

object AppModules {
    // 데이터 (정보) DI 주입 => 레파지토리에 API 주입
    private val repositories = module {
        factory { LoremPicsumRepository(service = get(named(ApiConstants.API_LOREM_PICSUM))) }
    }

    // 뷰 모델 (표시)
    private val viewModels = module {
        viewModel {
            LoremPicsumViewModel(repository = get())
        }
    }

    // 통신
    private val etc = module {
        factory {
            GsonBuilder()
                .enableComplexMapKeySerialization()
                .setPrettyPrinting()
                .create()
        }
        single(named(ApiConstants.API_LOREM_PICSUM)) {
            var builder = Retrofit.Builder()
                .client(OkHttpClient.Builder().run {
                    connectTimeout(10, TimeUnit.SECONDS)
                    readTimeout(10, TimeUnit.SECONDS)
                    writeTimeout(10, TimeUnit.SECONDS)
                    build()
                })
                .baseUrl("https://picsum.photos")
                .addConverterFactory(GsonConverterFactory.create(get()))
                .build()
                .create(LoremPicsumApiService::class.java)
            Log.d("tag","");
            builder


        }
    }
    val modules = listOf(etc, repositories, viewModels)

}