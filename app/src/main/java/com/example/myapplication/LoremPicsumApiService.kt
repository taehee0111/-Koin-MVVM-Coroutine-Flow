package com.example.myapplication

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path

//API 설명 주소: https://picsum.photos/
//리스트 읽어오기: https://picsum.photos/v2/list
interface LoremPicsumApiService {

    //retrofit2
    // => id/{image}
    // https://picsum.photos/id/0/info
    @GET("/id/{imageId}/info")
//    suspend fun imageInfo(@Path("imageId") imageId: String): retrofit2.Response<ResponseBody>
    suspend fun imageInfo(@Path("imageId") imageId: String): retrofit2.Response<ResponseBody>

}