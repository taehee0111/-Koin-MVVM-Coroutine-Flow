package com.example.myapplication

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

// DI: 레파지토리에 서비스 주입
// 서비스: ImageAPi 읽어오는 서비스
// 레파지토리를 통해 이미지를 가져오기
class LoremPicsumRepository(private val service: LoremPicsumApiService) {

    fun getImageInfo(imageId: String): Flow<ImageInfo> = flow {
        val response = service.imageInfo(imageId)
        Log.d("tag", "response");
        if (response.isSuccessful) {
            kotlin.runCatching {
                Gson().fromJson(
                    response.body()?.string(),
                    ResImageInfo.Response::class.java
                )
            }.onSuccess {
                emit(it.mapper())
            }.onFailure {
                throw RuntimeException("invalid JSON File.")
            }
        } else {
            throw java.lang.RuntimeException("Response is Failed");

        }
    }
}