package com.cybage.myapplication.views.views

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.cybage.myapplication.R
import com.cybage.myapplication.databinding.ActivityMainBinding
import com.cybage.myapplication.views.di.MyApplication
import com.cybage.myapplication.views.networkservice.RetrofitService
import com.cybage.myapplication.views.repository.MovieRepository
import com.cybage.myapplication.views.viewmodels.MainViewModel
import com.cybage.myapplication.views.viewmodels.MainViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var viewModel: MainViewModel
    val adapter = MovieAdapter()
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewmodel=viewModel
        binding.setLifecycleOwner(this)
        binding.searchMovieList.adapter = adapter
        binding.searchMovieList.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        viewModel.movieList.observe(this, Observer {
            adapter.setMovieList(it)
        })
        viewModel.errorMessage.observe(this, Observer {
            if(it.isNotEmpty())
            {
                binding.txterror.visibility=View.VISIBLE
                binding.txterror.text=it.toString()
            }
            else
                binding.txterror.visibility=View.GONE

        })
        viewModel.loading.observe(this, Observer {
            when(it) {
                true -> binding.progressBar.visibility = View.VISIBLE
                false -> binding.progressBar.visibility = View.GONE
        }
            })

       binding.searchviewMovieList.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
           override fun onQueryTextSubmit(query: String): Boolean {
             if(query.isNotEmpty())
                 viewModel.getAllMovies(query)
               return false
           }
           override fun onQueryTextChange(newText: String): Boolean {

               if(newText.isEmpty())
                   adapter.movies.clear()
               adapter.notifyDataSetChanged()
               return false
           }
       })




    }
}