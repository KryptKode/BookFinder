package com.kryptkode.bookfinder.ui.booksearch

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.kryptkode.bookfinder.R
import com.kryptkode.bookfinder.databinding.FragmentBookSearchBinding
import com.kryptkode.bookfinder.util.viewbinding.viewBinding

class BookSearchFragment : Fragment(R.layout.fragment_book_search) {

    private val binding by viewBinding(FragmentBookSearchBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}