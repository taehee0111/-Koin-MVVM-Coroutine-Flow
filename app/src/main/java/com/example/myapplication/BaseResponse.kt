package com.example.myapplication

//Rest Api 수신 => ImageInfo 에 값 추가
abstract class BaseResponse<M> {
    abstract fun mapper(): M
}