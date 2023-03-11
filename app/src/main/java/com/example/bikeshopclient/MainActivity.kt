package com.example.bikeshopclient

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bikeshopclient.databinding.ActivityMainBinding
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val bikeAdapter: BikeAdapter = BikeAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()

        lifecycleScope.launchWhenStarted {
            binding.progressBar.isVisible = true
            val response = try {
                RetrofitInstance.api.getProducts()
            } catch (e: IOException) {
                Log.e("Main", "IOException")
                e.printStackTrace()
                binding.progressBar.isVisible = false
                return@launchWhenStarted
            }catch (e: HttpException) {
                Log.e("Main", "HTTPexception")
                binding.progressBar.isVisible = false
                return@launchWhenStarted
            }
            if (response.isSuccessful && response.body() != null){
                bikeAdapter.differ.submitList(response.body()!!.data)

            } else {
                Log.e("Main", "Response not successful")
            }
            binding.progressBar.isVisible = false

        }
    }

    private fun setupRecyclerView() {
        binding.rvComponents.apply {
            adapter = bikeAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }
}