package io.alvalabs.app.fragments.detail

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import coil.load
import com.example.domain.Response
import io.alvalabs.app.R
import io.alvalabs.app.databinding.FragmentDetailBinding
import io.alvalabs.app.fragments.BaseFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DetailFragment : BaseFragment<FragmentDetailBinding>(R.layout.fragment_detail) {


    // This property is only valid between onCreateView and
    // onDestroyView.

    val args: DetailFragmentArgs by navArgs<DetailFragmentArgs>()
    private val detailViewModel: DetailViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            detailViewModel.meal.collect {
                when(it) {
                    is Response.Loading -> {
                        loading(true)
                    }

                    is Response.Data -> {
                        loading(false)
                        dataBinding.item = it.data
                        // you can also define some ext for dataBinding but I didn't have time for it :(
                        dataBinding.img.load(it.data.picture)
                    }
                    is Response.Error -> {
                        loading(false)
                        // can be expanded to be a bottom sheet or ...
                        Toast.makeText(requireContext(), "Error", Toast.LENGTH_LONG).show()
                        Log.e("Error", it.throwable.message!!)
                    }
                    null -> {
                        Log.e("Error", "null")
                    }
                }
            }
        }

        detailViewModel.getMeal(args.id)

    }
}