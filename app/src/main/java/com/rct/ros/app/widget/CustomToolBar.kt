package com.rct.ros.app.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.Toolbar
import com.rct.ros.R
import com.rct.ros.app.ext.onClick

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/17
 * 描述　: 没封装啥，就特么加了一个toolbar 然后中间放了一个文字 有其他的需求后期再加
 */
class CustomToolBar : FrameLayout {

    private lateinit var toolBar: Toolbar
    private lateinit var ivRight: ImageView
    private lateinit var toolBarTitle: AppCompatTextView

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    private fun init(context: Context, attrs: AttributeSet) {
        val view = LayoutInflater.from(context).inflate(R.layout.toolbar_layout_custom, this)
        toolBar = view.findViewById(R.id.toolBar)
        toolBar.title = ""
        toolBarTitle = view.findViewById(R.id.toolbarTitle)
        ivRight = view.findViewById(R.id.iv_right)
    }

    fun setCenterTitle(titleStr: String) {
        toolBarTitle.text = titleStr
    }

    fun setCenterTitle(titleResId: Int) {
        toolBarTitle.text = context.getString(titleResId)
    }

    fun setIconRes(resId: Int, method: (() -> Unit)? = null) {
        ivRight.setImageResource(resId)
        ivRight.onClick {
            method?.invoke()
        }
    }

    fun setBackEvent(resId: Int = R.mipmap.icon_back, method: (() -> Unit)? = null) {
        toolBar.run {
            setNavigationIcon(resId)
            setNavigationOnClickListener { method?.invoke() }
        }

    }

    fun setCenterTitleColor(colorResId: Int) {
        toolBarTitle.setTextColor(colorResId)
    }

    fun setToolbarBackGround(colorResId: Int) {
        toolBar.setBackgroundColor(colorResId)
    }

    fun getBaseToolBar(): Toolbar {
        return toolBar
    }
}
