package com.kryptkode.bookfinder.ui.booksearch

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kryptkode.bookfinder.R
import com.kryptkode.bookfinder.databinding.FragmentBookSearchBinding
import com.kryptkode.bookfinder.util.ToastHelper
import com.kryptkode.bookfinder.util.flowbinding.textChanges
import com.kryptkode.bookfinder.util.viewbinding.viewBinding

class BookSearchFragment : Fragment(R.layout.fragment_book_search) {

    private val binding by viewBinding(FragmentBookSearchBinding::bind)
    private val viewModel: BookSearchViewModel by viewModels { BookSearchViewModel.factory }
    private val adapter = BookListAdapter()
    private lateinit var toastHelper: ToastHelper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toastHelper = ToastHelper(requireContext())
        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        binding.swipeRefresh.isEnabled = false
        binding.swipeRefresh.isRefreshing = false
        binding.recyclerView.adapter = adapter
        viewModel.findBook(binding.addressEditText.textChanges)
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner){
            binding.swipeRefresh.isEnabled = it.loading
            binding.swipeRefresh.isRefreshing = it.loading
            adapter.submitList(it.books)
            if(it.error){
                toastHelper.showMessage(it.errorMessage)
            }
        }
    }
}