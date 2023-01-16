package com.rct.ros.app.ext

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.text.InputType
import android.widget.DatePicker
import androidx.fragment.app.Fragment
import com.google.gson.reflect.TypeToken
import com.qmuiteam.qmui.widget.dialog.QMUIDialog
import com.rct.ros.app.base.BaseActivity
import java.io.Serializable
import java.util.*


inline fun <reified T : BaseActivity<*, *>> Activity.startActivity() {
    startActivity(Intent(this, T::class.java))

}

inline fun <reified T : BaseActivity<*, *>> Context.nav(vararg params: Pair<String, Any>) {
    startActivity(Intent(this, T::class.java).putExtras(*params))
}

inline fun <reified TARGET : Activity> Fragment.nav(
    vararg params: Pair<String, Any>
) = activity?.run {
    startActivity(Intent(this, TARGET::class.java).putExtras(*params))
}

inline fun <reified TARGET : Activity> Activity.startActivity(
    vararg params: Pair<String, Any>
) {
    startActivity(Intent(this, TARGET::class.java).putExtras(*params))
}


inline fun Context.showMessageDialog(
    title: String,
    message: String,
    yesActionStr: String = "确定",
    noActionStr: String = "取消",
    crossinline yes: () -> Unit,
    crossinline no: (dialog: QMUIDialog) -> Unit = { dialog -> dialog.dismiss() },
): QMUIDialog? {
    return QMUIDialog.MessageDialogBuilder(this)
        .run {
            setCancelable(false)
            setCanceledOnTouchOutside(false)

            setTitle(title)
            setMessage(message)
            if (noActionStr.isNotBlank()) {
                addAction(noActionStr) { dialog, _ ->
                    no(dialog)
                }
            }

            addAction(yesActionStr) { dialog, _ ->
                dialog.dismiss()
                yes()
            }
            show()
        }


}


//显示密码确认框
inline fun Context.showPasswordDialog(
    title: String="请输入密码",
    yesActionStr: String = "确定",
    noActionStr: String = "取消",
    crossinline yes: (password: String, dialog: QMUIDialog) -> Unit
) {
    val builder = QMUIDialog.EditTextDialogBuilder(this)

    builder.setTitle(title)
        .setInputType(InputType.TYPE_CLASS_NUMBER)
        .setPlaceholder("在此输入密码")
        .setCancelable(false)
        .setCanceledOnTouchOutside(false)
        .addAction(
            noActionStr
        ) { dialog, index ->
            dialog.dismiss()

        }
        .addAction(
            yesActionStr
        ) { dialog, index ->
            yes(builder.editText.text.toString(), dialog)
        }.show()
}


inline fun Context.showDatePicker(
    crossinline method: (date: String) -> Unit
) {
    val calendar = Calendar.getInstance()
    DatePickerDialog(
        this, { view: DatePicker?, year: Int, monthOfYear: Int, dayOfMonth: Int ->
            val date = String.format("%d-%02d-%02d", year, monthOfYear + 1, dayOfMonth)
            method.invoke(date)
        }, calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )
        .show()
}


inline fun <reified T> genericType() = object : TypeToken<T>() {}.type


/**
 *  [Intent]的扩展方法，用来批量put键值对
 *  示例：
 *  <pre>
 *      intent.putExtras(
 *          "Key1" to "Value",
 *          "Key2" to 123,
 *          "Key3" to false,
 *          "Key4" to arrayOf("4", "5", "6")
 *      )
 * </pre>
 *
 * @param params 键值对
 */
fun Intent.putExtras(vararg params: Pair<String, Any>): Intent {
    if (params.isEmpty()) return this
    params.forEach { (key, value) ->
        when (value) {
            is Int -> putExtra(key, value)
            is Byte -> putExtra(key, value)
            is Char -> putExtra(key, value)
            is Long -> putExtra(key, value)
            is Float -> putExtra(key, value)
            is Short -> putExtra(key, value)
            is Double -> putExtra(key, value)
            is Boolean -> putExtra(key, value)
            is Bundle -> putExtra(key, value)
            is String -> putExtra(key, value)
            is IntArray -> putExtra(key, value)
            is ByteArray -> putExtra(key, value)
            is CharArray -> putExtra(key, value)
            is LongArray -> putExtra(key, value)
            is FloatArray -> putExtra(key, value)
            is Parcelable -> putExtra(key, value)
            is ShortArray -> putExtra(key, value)
            is DoubleArray -> putExtra(key, value)
            is BooleanArray -> putExtra(key, value)
            is CharSequence -> putExtra(key, value)
            is Array<*> -> {
                when {
                    value.isArrayOf<String>() ->
                        putExtra(key, value as Array<String?>)
                    value.isArrayOf<Parcelable>() ->
                        putExtra(key, value as Array<Parcelable?>)
                    value.isArrayOf<CharSequence>() ->
                        putExtra(key, value as Array<CharSequence?>)
                    else -> putExtra(key, value)
                }
            }
            is Serializable -> putExtra(key, value)
        }
    }
    return this
}

