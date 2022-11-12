package com.example.myapplication

//RestApi 수신 데이터 => ImageInfo 클래스로 반환
class ResImageInfo {
    //실제 api 데이터 수신
    data class Response(
        val id: String?,
        val author: String?,
        val width: Int?,
        val height: Int?,
        val url: String?,
        val download_url: String?
    ) : BaseResponse<ImageInfo>() {

        //사용할 데이터 표기
        override fun mapper(): ImageInfo {
            return ImageInfo(
                id = id,
                author = author,
                image_url = download_url
            )
        }
    }
}