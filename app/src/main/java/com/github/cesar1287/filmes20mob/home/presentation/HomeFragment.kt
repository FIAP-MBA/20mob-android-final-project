package com.github.cesar1287.filmes20mob.home.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.cesar1287.filmes20mob.databinding.FragmentHomeBinding
import com.github.cesar1287.filmes20mob.home.adapter.HomeAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var homeBinding: FragmentHomeBinding? = null
    private val homeViewModel: HomeViewModel by viewModel()

    private val watchMoviesAdapter : HomeAdapter by lazy{
        HomeAdapter{
            it?.let {
//                val intent = Intent(activity, MovieDetailsActivity::class.java)
//                intent.putExtra(KEY_INTENT_MOVIE_ID, it.id)
//                startActivity(intent)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return homeBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadContent()
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        homeBinding?.rvHomeMoviesList?.apply{
            layoutManager = LinearLayoutManager(this.context)
            adapter = watchMoviesAdapter
        }
    }

    private fun loadContent(){
        homeViewModel.moviesPagedList?.observe(viewLifecycleOwner){ pagedList ->
            watchMoviesAdapter.submitList(pagedList)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        homeBinding = null
    }
}