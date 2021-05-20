package com.merqueo.utils

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


/**
 * Custom Class used to manage the recycler view into the application
 * @author Edson Joel Nieto Ardila
 * @since 1.0.0
 * */
@Suppress("UNCHECKED_CAST")
abstract class GenericAdapter<T, D> : RecyclerView.Adapter<RecyclerView.ViewHolder> {

    var mContext: Context? = null
    var listItems: ArrayList<T> = arrayListOf()

    abstract fun getLayoutResId(): Int

    abstract fun onBindData(model: T, position: Int, dataBinding: D)

    constructor(context: Context?, arrayList: ArrayList<T>) {
        mContext = context
        this.listItems = arrayList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val dataBinding: ViewDataBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), getLayoutResId(), parent, false)
        return ItemViewHolder(dataBinding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        onBindData(listItems.get(position), position, (holder as GenericAdapter<*, *>.ItemViewHolder).mDataBinding as D)
    }

    override fun getItemCount(): Int {
        return listItems.size
    }

    open fun addItems(arrayList: ArrayList<T>) {
        listItems.clear()
        listItems.addAll(arrayList)
        notifyDataSetChanged()
    }

    open fun getItem(position: Int): T {
        return listItems[position]
    }

    inner class ItemViewHolder(binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var mDataBinding: D = binding as D
    }
}