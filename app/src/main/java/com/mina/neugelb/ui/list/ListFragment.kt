package com.mina.neugelb.ui.list

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.mina.neugelb.data.model.MovieListUiModel
import com.mina.neugelb.databinding.FragmentListBinding
import com.mina.neugelb.ui.State
import com.mina.neugelb.ui.base.BaseFragment
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ListFragment : BaseFragment() {
    private val viewModel: ListViewModel by viewModel()
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!

    private lateinit var moviesPagingAdapter: MoviesPagingAdapter
    private var getAllMoviesJob: Job? = null
    private var searchMoviesJob: Job? = null

    private val autoCompleteSugession = mutableListOf<String>()

    private fun navigateToDetails(movie: MovieListUiModel?) {
        val action = ListFragmentDirections.viewMovieDetails(movie)
        findNavController().navigate(action)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.autoCompleteSearch.doOnTextChanged { text, start, before, count ->
            text?.toString()?.let {
                if (it.isNotEmpty()) {
                    if (text.last().isWhitespace()) {
                        setupAutoCompleteData()
                    } else {
                        search(it)
                    }
                } else {
                    reFetchAllMovies()
                }
            } ?: kotlin.run {
                reFetchAllMovies()
            }
        }

        binding.ilSearch.setEndIconOnClickListener {
            binding.autoCompleteSearch.text.clear()
        }

        setupRc()
        getAllMovies()
    }

    private fun setupAutoCompleteData() {
        autoCompleteSugession.clear()
        autoCompleteSugession.addAll(
            viewModel.getAutoCompleteCurrentMoviesNames(
                moviesPagingAdapter.snapshot().items
            )
        )
        binding.autoCompleteSearch.setAdapter(
            ArrayAdapter<String>(
                requireContext(),
                R.layout.simple_dropdown_item_1line,
                autoCompleteSugession
            )
        )
    }

    private fun reFetchAllMovies() {
        moviesPagingAdapter.submitData(lifecycle, PagingData.empty())
        getAllMovies()
    }

    private fun search(query: String) {
        moviesPagingAdapter.submitData(lifecycle, PagingData.empty())
        getAllMoviesJob?.cancel()
        viewModel.filterData(query)
        searchMoviesJob = lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.moviesData.collect {
                    moviesPagingAdapter.submitData(lifecycle, it)
                }
            }
        }
    }

    private fun getAllMovies() {
        searchMoviesJob?.cancel()
        getAllMoviesJob = lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.getMovies()
                    .collect {
                        when (it) {
                            is State.DataState -> {
                                binding
                                moviesPagingAdapter.submitData(lifecycle, it.data)
                                setupAutoCompleteData()
                            }
                            is State.ErrorState -> Toast.makeText(
                                requireContext(),
                                "error ${it.exception}",
                                Toast.LENGTH_LONG
                            ).show()
                            is State.LoadingState ->
                                Log.d("listFragment", "getAllMovies: Loading")
                        }

                    }
            }
        }
    }

    private fun setupRc() {
        moviesPagingAdapter = MoviesPagingAdapter {
            navigateToDetails(it)
        }

        binding.rcMovies.apply {
            adapter = moviesPagingAdapter
            layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )
            setHasFixedSize(true)
        }
    }
}