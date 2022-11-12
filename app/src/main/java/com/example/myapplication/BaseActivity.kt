package com.example.myapplication

import android.os.Bundle
import android.os.PersistableBundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class BaseActivity<T : ViewDataBinding>
    (@LayoutRes private val layoutId: Int) : AppCompatActivity() {

    protected lateinit var binding: T
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        beforeSetContentView()
        binding = DataBindingUtil.setContentView(this, layoutId)

        initView()
        initViewModel()
        initListener()
        afterOnCreate()
    }

    //뷰가 표시되기전
    protected open fun beforeSetContentView() {}

    //뷰 초기화
    protected open fun initView() {}

    //뷰모델 등록
    protected open fun initViewModel() {}

    //리스너 등록
    protected open fun initListener() {}

    //뷰 생성 후
    protected open fun afterOnCreate() {}
}