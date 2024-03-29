package com.rct.ros.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.rct.ros.app.api.NetUrl
import com.rct.ros.data.repository.UserRepository
import com.rct.ros.data.response.ApiPagerResponse
import com.win.mvvmhelper.base.BaseViewModel
import com.win.mvvmhelper.ext.rxHttpRequest
import com.win.mvvmhelper.net.LoadingType

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/4
 * 描述　:
 */
class ListViewModel : com.win.mvvmhelper.base.BaseViewModel() {

    private var pageIndex = 1

    var listData = MutableLiveData<ApiPagerResponse<Any>>()

    /**
     * 获取列表数据
     * @param isRefresh Boolean 是否是刷新
     * @param loadingXml Boolean 请求时是否需要展示界面加载中loading
     */
    fun getList(isRefresh: Boolean, loadingXml: Boolean = false) {
        if (isRefresh) {
            //是刷新 玩Android的这个接口pageIndex 是0 开始
            pageIndex = 0
        }
        rxHttpRequest {
            onRequest = {
                listData.value = UserRepository.getList(pageIndex).await()
                //请求成功 页码+1
                pageIndex++
            }
            loadingType = if (loadingXml) LoadingType.LOADING_XML else LoadingType.LOADING_NULL
            requestCode = NetUrl.HOME_LIST
            isRefreshRequest = isRefresh
        }
    }
}