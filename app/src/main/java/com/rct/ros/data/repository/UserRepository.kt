package com.rct.ros.data.repository

import com.rct.ros.app.api.NetUrl
import com.rct.ros.data.response.ApiPagerResponse
import com.rct.ros.data.response.UserInfo
import rxhttp.wrapper.coroutines.Await
import rxhttp.wrapper.param.RxHttp
import rxhttp.wrapper.param.toResponse

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/2
 * 描述　: 数据仓库
 */
object UserRepository {

    /**
     * 登录
     */
    fun login(userName: String, password: String): Await<UserInfo> {
        return RxHttp.postForm(NetUrl.LOGIN)
            .add("username", userName)
            .add("password", password)
            .toResponse()
    }

    /**
     * 获取列表信息
     */
    fun getList(pageIndex: Int): Await<ApiPagerResponse<Any>> {
        return RxHttp.get(NetUrl.HOME_LIST, pageIndex)
            .toResponse()
    }

}

