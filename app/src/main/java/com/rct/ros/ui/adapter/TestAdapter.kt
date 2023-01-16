package com.rct.ros.ui.adapter

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.rct.ros.R

/**
 * 作者　: hegaojian
 * 时间　: 2020/11/18
 * 描述　:
 */
class TestAdapter(data:ArrayList<Any>) :BaseQuickAdapter<Any,BaseViewHolder>(R.layout.item_test),LoadMoreModule{
    
    override fun convert(holder: BaseViewHolder, item: Any) {
        holder.setText(R.id.item_test_button,item.toString())
    }

}