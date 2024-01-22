package io.alvalabs.app.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class BaseAdapter<DB : ViewDataBinding , DataType> (
    private val wrapper: RecyclerItemWrapper<DB,DataType>
) : RecyclerView.Adapter<BaseAdapter.BaseAdapterVH<DB>>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseAdapterVH<DB> =
        BaseAdapterVH(
            DataBindingUtil.inflate(LayoutInflater.from(parent.context), wrapper.layout, parent, false)
        )

    override fun onBindViewHolder(
        holder: BaseAdapterVH<DB>,
        position: Int
    ) {
        wrapper.bindingFun.invoke(holder.binding,wrapper.list[position],position)
    }

    override fun getItemCount(): Int = wrapper.list.size

    class BaseAdapterVH<DB : ViewDataBinding>(var binding : DB) : RecyclerView.ViewHolder(binding.root)
}


data class RecyclerItemWrapper<DB : ViewDataBinding , DataType>(
    @LayoutRes
    var layout : Int,var list : MutableList<DataType>,
    val bindingFun : (binding : DB,item : DataType,pos : Int) -> Unit
)