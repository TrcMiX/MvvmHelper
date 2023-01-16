package com.rct.ros.app.ext

import com.blankj.utilcode.util.ActivityUtils
import com.rct.ros.R
import com.rct.ros.app.widget.CustomToolBar


/**
 * 作者　: hegaojian
 * 时间　: 2021/6/9
 * 描述　:
 */

/**
 * 初始化有返回键的toolbar
 */
fun CustomToolBar.initBack(
    titleStr: String = "标题",

    onBack: (toolbar: CustomToolBar) -> Unit,
    backImg: Int = R.drawable.ic_back,
    ): CustomToolBar {
    this.setCenterTitle(titleStr)
    this.getBaseToolBar().setNavigationIcon(backImg)
    this.getBaseToolBar().setNavigationOnClickListener { onBack.invoke(this) }
    return this
}

fun CustomToolBar.initBack(
    titleStr: String = "标题",
    backImg: Int = R.drawable.ic_back,
    onBack: (toolbar: CustomToolBar) -> Unit = {
        ActivityUtils.getTopActivity().onBackPressed()
    },
    rightImg: Int = 0,
    onRight: (() -> Unit)? = null,
): CustomToolBar {
    this.setCenterTitle(titleStr)
    this.getBaseToolBar().setNavigationIcon(backImg)
    this.getBaseToolBar().setNavigationOnClickListener { onBack.invoke(this) }

    this.setIconRes(rightImg, onRight)
    return this
}
