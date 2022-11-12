package com.example.myapplication

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityLorempicsumDetailBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


// Koin + MVVM + Coroutine + Flow 를 이용한 상세화면 만들기
// 코드 따라 만들기: url: https://heeeju4lov.tistory.com/11
// 키 등록 필요
// API 설명 주소: https://picsum.photos/
class LoremPicsumDetailActivity : BaseActivity<ActivityLorempicsumDetailBinding>
    (R.layout.activity_lorempicsum_detail) {

    //koin viewModel
    private val viewModel: LoremPicsumViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }
    override fun initViewModel() {
        super.initViewModel()
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
    }

    override fun afterOnCreate() {
        super.afterOnCreate()

        lifecycleScope.launchWhenStarted {
            viewModel.getImageInfo("0")
        }
    }

}

@BindingAdapter("url")
fun setImageViewUrl(view: AppCompatImageView, url: String?) {
    url?.let {
        Glide.with(view.context).load(it).into(view)
    }
}
