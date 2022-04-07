package com.mina.neugelb.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mina.neugelb.databinding.FragmentDetailsBinding
import com.mina.neugelb.ui.base.BaseFragment
import jp.wasabeef.glide.transformations.BlurTransformation


class DetailsFragment : BaseFragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    private val safeArgs: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        safeArgs.movieObj?.let {
            binding.tvTitle.text = it.movieTitle
            binding.tvVote.text = it.voteAvg.toString()
            binding.tvRatingCount.text = it.vote_count.toString()
            binding.tvDescription.text = it.description
            Glide.with(this).load(it.imgUrl)
                .apply(RequestOptions.bitmapTransform(BlurTransformation(10, 1)))
                .into(binding.blurImage)
            Glide.with(this).load(it.imgUrl)
                .into(binding.posterImage)
        }

    }

}