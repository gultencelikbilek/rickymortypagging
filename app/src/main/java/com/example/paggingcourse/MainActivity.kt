package com.example.paggingcourse

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.paggingcourse.adapter.RickMortyAdapter
import com.example.paggingcourse.databinding.ActivityMainBinding
import com.example.paggingcourse.viewmodel.RickMortyViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var mAdapter : RickMortyAdapter
    val viewModel : RickMortyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setUpRv()
        loadingData()


    }

    private fun loadingData() {
        lifecycleScope.launch {
            viewModel.listData.collect {
                mAdapter.submitData(it)
            }
        }
    }

    private fun setUpRv() {
        mAdapter = RickMortyAdapter()
        binding.recyclerview.apply {
            layoutManager = StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL)

            adapter = mAdapter
            setHasFixedSize(true)
        }
    }
}