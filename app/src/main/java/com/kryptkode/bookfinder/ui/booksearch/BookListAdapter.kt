package com.kryptkode.bookfinder.ui.booksearch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kryptkode.bookfinder.R
import com.kryptkode.bookfinder.data.model.Book
import com.kryptkode.bookfinder.databinding.ItemBookBinding

class BookListAdapter : ListAdapter<Book, BookListAdapter.BookListViewHolder>(DIFF_UTIL) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookListViewHolder {
        return BookListViewHolder(
            ItemBookBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class BookListViewHolder(
        private val binding: ItemBookBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(book: Book) {
            //TODO: Load image
            binding.titleTextView.text = book.title
            binding.authorsTextView.text = book.author
            binding.ratingBar.rating = book.rating
            binding.pageCountTextView.text = getString(R.string.book_pages_text, book.pages)
            binding.ratingCountTextView.text =
                getString(R.string.book_rating_count_text, book.ratingCount)
        }

        private fun getString(resId: Int, vararg args: Any): String {
            return binding.root.context.getString(resId, *args)
        }
    }

    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
        }
    }
}
