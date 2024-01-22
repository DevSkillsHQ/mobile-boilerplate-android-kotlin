package io.alvalabs.app.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import io.alvalabs.app.R
import io.alvalabs.app.databinding.FragmentBaseBinding

abstract class BaseFragment<T : ViewDataBinding>(
    private val layout : Int
) : Fragment(R.layout.fragment_base){

    private var _dataBinding : T? = null
    protected val dataBinding get() = _dataBinding!!

    private var _dataBindingOuter : FragmentBaseBinding? = null
    private val dataBindingOuter get() = _dataBindingOuter!!

    override fun onDestroyView() {
        super.onDestroyView()
        _dataBindingOuter = null
        _dataBinding = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _dataBindingOuter =
            FragmentBaseBinding.inflate(inflater,container,false)
        _dataBinding = DataBindingUtil.inflate(layoutInflater,layout,dataBindingOuter.viewAdder,true)
        return dataBindingOuter.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    protected fun loading(on: Boolean) {
        dataBindingOuter.loading = on
    }



}