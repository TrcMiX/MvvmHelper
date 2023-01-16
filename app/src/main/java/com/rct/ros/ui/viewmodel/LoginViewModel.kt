package com.rct.ros.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import com.rct.ros.app.api.NetUrl
import com.rct.ros.data.repository.UserRepository
import com.rct.ros.data.response.UserInfo
import com.win.mvvmhelper.base.BaseViewModel
import com.win.mvvmhelper.core.databinding.BooleanObservableField
import com.win.mvvmhelper.core.databinding.StringObservableField
import com.win.mvvmhelper.ext.logA
import com.win.mvvmhelper.ext.logI
import com.win.mvvmhelper.ext.rxHttpRequest
import com.win.mvvmhelper.net.LoadingType
import rxhttp.async

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/3
 * 描述　:
 */
class LoginViewModel : com.win.mvvmhelper.base.BaseViewModel() {

    //账户名
    val userName = StringObservableField()

    //密码
    val password = StringObservableField()

    //是否显示明文密码（登录注册界面）
    var isShowPwd = BooleanObservableField()

    //登录请求信息
    val loginData = MutableLiveData<UserInfo>()

    /**
     * 登录
     * @param phoneNumber String
     * @param password String
     */
    fun login(phoneNumber: String, password: String) {
        rxHttpRequest {
            onRequest = {
                loginData.value = UserRepository.login(phoneNumber,password).await()
            }
            loadingType = LoadingType.LOADING_DIALOG //选传
            loadingMessage = "正在登录中....." // 选传
            requestCode = NetUrl.LOGIN // 如果要判断接口错误业务 - 必传
        }
    }


    /**
     * 演示一个串行 请求 写法
     * @param phoneNumber String
     * @param password String
     */
    fun test1(phoneNumber: String, password: String) {
        rxHttpRequest {
            onRequest = {
                //下面2个接口按顺序请求，先请求 getList接口 请求成功后 再执行 login接口， 其中有任一接口请求失败都会走错误回调
                val listData = UserRepository.getList(0).await()
                "打印一下List的大小${listData.size}".logI()
                val loginData = UserRepository.login(phoneNumber, password).await()
                loginData.username.logI()
                "打印一下用户名${loginData.username}".logI()
            }
            loadingType = LoadingType.LOADING_DIALOG
            loadingMessage = "正在登录中....." // 选传
            requestCode = NetUrl.LOGIN // 如果要判断接口错误业务 - 必传
        }
    }

    /**
     * 演示一个并行 请求 写法
     * @param phoneNumber String
     * @param password String
     */
    fun test2(phoneNumber: String, password: String) {
        rxHttpRequest {
            onRequest = {
                //下面2个接口同时请求，2个接口都请求成功后 最后合并值。 其中有任一接口请求失败都会走错误回调
                val listData = UserRepository.getList(0).async(this)
                val loginData = UserRepository.login(phoneNumber, password).async(this)
                //得到合并值
                val mergeValue = loginData.await().username + listData.await().hasMore()
                //打印一下
                mergeValue.logA()
            }
            loadingType = LoadingType.LOADING_DIALOG
            loadingMessage = "正在登录中....." // 选传
            requestCode = NetUrl.LOGIN // 如果要判断接口错误业务 - 必传
        }
    }

}