package com.kryptkode.bookfinder.ui.booksearch

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kryptkode.bookfinder.R
import com.kryptkode.bookfinder.databinding.FragmentBookSearchBinding
import com.kryptkode.bookfinder.util.flowbinding.lifecycleAwareLaunch
import com.kryptkode.bookfinder.util.flowbinding.textChanges
import com.kryptkode.bookfinder.util.imageloader.ImageLoader
import com.kryptkode.bookfinder.util.viewbinding.viewBinding
import kotlinx.coroutines.flow.onEach

class BookSearchFragment : Fragment(R.layout.fragment_book_search) {

    private val binding by viewBinding(FragmentBookSearchBinding::bind)
    private val viewModel: BookSearchViewModel by viewModels { BookSearchViewModel.factory }
    private lateinit var imageLoader: ImageLoader
    private lateinit var adapter: BookListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imageLoader = ImageLoader(requireContext())
        adapter = BookListAdapter(imageLoader)
        setupViews()
        setupObservers()
    }

    private fun setupViews() {
        binding.errorGroup.isVisible = true
        binding.swipeRefresh.isEnabled = false
        binding.swipeRefresh.isRefreshing = false
        binding.recyclerView.adapter = adapter
        binding.searchEditText.textChanges.onEach(viewModel::findBook)
            .lifecycleAwareLaunch(viewLifecycleOwner)
    }

    private fun setupObservers() {
        viewModel.viewState.observe(viewLifecycleOwner) {
            binding.swipeRefresh.isEnabled = it.loading
            binding.swipeRefresh.isRefreshing = it.loading
            adapter.submitList(it.books)
            binding.errorGroup.isVisible = it.error || it.books.isEmpty()
            binding.errorImage.setImageResource(if (it.error) R.drawable.ic_cloud else R.drawable.ic_book)
            binding.errorTextView.text =
                if (it.error) it.errorMessage else getString(R.string.book_search_hint_message)
        }
    }
}