package com.example.myapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

//뷰 모델
// 레파지토리 주입: 레파지토리 -> 뷰 모델
class LoremPicsumViewModel(private val repository: LoremPicsumRepository) : ViewModel() {

    private val _imageInfo: MutableLiveData<ImageInfo> = MutableLiveData()
    val imageInfo: LiveData<ImageInfo> get() = _imageInfo

    private val _errorMsg: MutableLiveData<String> = MutableLiveData()

    fun getImageInfo(imageId: String) {
        viewModelScope.launch {
            repository.getImageInfo(imageId)
                .catch {
                    _errorMsg.postValue(it.message)
                }.collectLatest {
                    _imageInfo.postValue(it)
                }
        }
    }

}