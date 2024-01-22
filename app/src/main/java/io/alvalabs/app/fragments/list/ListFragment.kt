package io.alvalabs.app.fragments.list

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import coil.load
import com.example.domain.Response
import com.example.domain.model.Meal
import io.alvalabs.app.R
import io.alvalabs.app.databinding.FragmentListBinding
import io.alvalabs.app.databinding.ItemListMealBinding
import io.alvalabs.app.fragments.BaseFragment
import io.alvalabs.app.utils.BaseAdapter
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListFragment : BaseFragment<FragmentListBinding>(R.layout.fragment_list) {

    private val listViewModel: ListViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            listViewModel.meals.collect {

                when (it) {
                    is Response.Loading -> {
                        loading(true)
                    }

                    is Response.Data -> {
                        loading(false)

                        if (it.data.isEmpty()) {
                            dataBinding.empty = true
                        } else {
                            dataBinding.empty = false
                            dataBinding.adapter = BaseAdapter(io.alvalabs.app.utils.RecyclerItemWrapper<ItemListMealBinding,Meal>
                                (R.layout.item_list_meal, it.data.toMutableList()) { binding, item, pos->
                                binding.item = item
                                binding.imageView.load(item.picture)
                                binding.root.setOnClickListener {
                                    findNavController().navigate(
                                        ListFragmentDirections.actionListToDetail(item.id)
                                    )
                                }
                            }
                            )
                        }

                    }
                    is Response.Error -> {
                        loading(false)
                        // can be expanded to be a bottom sheet or ...
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
                        Log.e("Error", it.throwable.message!!)
                    }
                    null -> {}
                }


            }
        }

        dataBinding.btnRefresh.setOnClickListener {
            listViewModel.refresh()
        }
    }

}