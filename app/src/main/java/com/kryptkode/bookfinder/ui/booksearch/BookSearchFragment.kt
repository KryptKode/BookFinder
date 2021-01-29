package com.kryptkode.bookfinder.ui.booksearch

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kryptkode.bookfinder.R
import com.kryptkode.bookfinder.databinding.FragmentBookSearchBinding
import com.kryptkode.bookfinder.util.viewbinding.viewBinding

class BookSearchFragment : Fragment(R.layout.fragment_book_search) {

    private val binding by viewBinding(FragmentBookSearchBinding::bind)
    private val viewModel: BookSearchViewModel by viewModels { BookSearchViewModel.factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewState.observe(viewLifecycleOwner){
            binding.swipeRefresh.isRefreshing = it.loading
        }
    }
}