package com.rct.ros.app.databinding

import android.os.SystemClock
import android.text.Editable
import android.text.InputType
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.DrawableRes
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.rct.ros.R
import com.ruffian.library.widget.iface.RHelper
import com.win.mvvmhelper.ext.textString

/**
 * 作者　: hegaojian
 * 时间　: 2019/12/23
 * 描述　: 自定义 BindingAdapter
 */
object CustomBindAdapter {

    @BindingAdapter(value = ["checkChange"])
    @JvmStatic
    fun checkChange(checkbox: CheckBox, listener: CompoundButton.OnCheckedChangeListener?) {
        checkbox.setOnCheckedChangeListener(listener)
    }

    @BindingAdapter(value = ["showPwd"])
    @JvmStatic
    fun showPwd(view: EditText, boolean: Boolean) {
        if (boolean) {
            view.inputType = InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
        } else {
            view.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        view.setSelection(view.textString().length)
    }


    @BindingAdapter(value = ["imageUrl"])
    @JvmStatic
    fun imageUrl(view: ImageView, url: Int?) {
        Glide.with(view.context.applicationContext)
            .load(url)
            .into(view)
    }

    @BindingAdapter(value = ["imageUrl"])
    @JvmStatic
    fun imageUrl(view: ImageView, url: String?) {
        Glide.with(view.context.applicationContext)
            .load(url.toString())
            .into(view)
    }




    @BindingAdapter(value = ["imageResName"])
    @JvmStatic
    fun imageResName(view: ImageView, resName: String) {
        view.setImageResource(
            view.context.resources.getIdentifier(
                resName.toLowerCase(),
                "drawable",
                view.context.packageName
            )
        )
    }

    @BindingAdapter(value = ["imageResId"])
    @JvmStatic
    fun imageResId(view: ImageView, @DrawableRes resId: Int) {
        if (resId == 0) {
            view.setImageDrawable(null)
        } else {
            view.setImageResource(resId)
        }
    }

    @BindingAdapter(value = ["isVisible"])
    @JvmStatic
    fun isVisible(view: View, isVisible: Boolean) {
        view.isVisible = isVisible
    }

    @BindingAdapter(value = ["isInvisible"])
    @JvmStatic
    fun isInvisible(view: View, isInvisible: Boolean) {
        view.isInvisible = isInvisible
    }


    @BindingAdapter(value = ["background_normal"])
    @JvmStatic
    fun background_normal(view: View, resId: Int) {
        if (view is RHelper<*>) {
            (view  ).helper.backgroundColorNormal = resId
        }
    }


    @BindingAdapter(value = ["afterTextChanged"])
    @JvmStatic
    fun EditText.afterTextChanged(action: (String) -> Unit) {
        addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                action(s.toString())
            }
        })
    }

    @BindingAdapter("noRepeatClick")
    @JvmStatic
    fun setOnClick(view: View, clickListener: () -> Unit) {
        val mHits = LongArray(2)
        view.setOnClickListener {
            System.arraycopy(mHits, 1, mHits, 0, mHits.size - 1)
            mHits[mHits.size - 1] = SystemClock.uptimeMillis()
            if (mHits[0] < SystemClock.uptimeMillis() - 500) {
                clickListener.invoke()
            }
        }
    }


}