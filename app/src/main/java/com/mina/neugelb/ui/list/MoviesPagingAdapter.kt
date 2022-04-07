package com.mina.neugelb.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.mina.neugelb.R
import com.mina.neugelb.data.model.MovieListUiModel
import com.mina.neugelb.databinding.ItemMovieBinding

class MoviesPagingAdapter(private val clickCallback: (MovieListUiModel?) -> Unit) :
    PagingDataAdapter<MovieListUiModel,
            MoviesPagingAdapter.ImageViewHolder>(diffCallback) {

    inner class ImageViewHolder(
        val binding: ItemMovieBinding
    ) :
        ViewHolder(binding.root)

    companion object {
        val diffCallback = object : DiffUtil.ItemCallback<MovieListUiModel>() {
            override fun areItemsTheSame(
                oldItem: MovieListUiModel,
                newItem: MovieListUiModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: MovieListUiModel,
                newItem: MovieListUiModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val movie = getItem(position)

        holder.binding.apply {
            cardViewItemContainer.setOnClickListener {
                clickCallback.invoke(movie)
            }
            holder.itemView.apply {
                movie?.let {
                    tvTitle.text = it.movieTitle
                    Glide.with(this).load(it.imgUrl).placeholder(
                        ContextCompat.getDrawable(
                            context,
                            R.drawable.movie_placeholder
                        )
                    ).centerCrop()
                        .into(holder.binding.imgMovie)
                }
            }
        }
    }


}