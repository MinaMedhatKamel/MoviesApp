package com.mina.neugelb.ui.list

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mina.neugelb.data.model.Result
import com.mina.neugelb.databinding.ItemMovieBinding
import com.squareup.picasso.Picasso

class MoviesAdapter(
    val context: Context,
    val dataList: List<Result>,
    private val clickCallback: (Result?) -> Unit
) : RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemMovieBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMovieBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        dataList[position].let {
//            holder.binding.lnContainer.setOnClickListener {
//                clickCallback.invoke(dataList[position])
//            }

            Picasso.get().load("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png").into(holder.binding.imgMovie);
            holder.binding.tvTitle.text = it.title

        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

}