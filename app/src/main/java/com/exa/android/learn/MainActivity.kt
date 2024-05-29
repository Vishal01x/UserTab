package com.exa.android.learn

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.exa.android.learn.DataTypes.UserX
import com.exa.android.learn.adapter.RecycleAdapter
import com.exa.android.learn.api.DataApi
import com.exa.android.learn.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private val url = "https://dummyjson.com/"
    lateinit var binding: ActivityMainBinding
    lateinit var userAdapter: RecycleAdapter
    lateinit var recyclerview: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        recyclerview = binding.recycleView
        recyclerview.layoutManager = LinearLayoutManager(this)

        userAdapter = RecycleAdapter(emptyList())
        recyclerview.adapter = userAdapter

        fetchData()
            userAdapter.onRecycleViewClick(object : RecycleAdapter.onItemClickListner {
                override fun onItemClick(data : List<UserX>,position: Int) {
                   val intent = Intent(this@MainActivity, MainActivity2::class.java)
                    intent.putExtra("image_res", data[position].image)
                   startActivity(intent)
                }
            })
        }


    fun fetchData() {

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(DataApi::class.java)  // or .create<DataApi>()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = retrofitBuilder.data()
                if (response.isSuccessful) {
                    val user_data = response.body()
                    user_data?.let {
                        withContext(Dispatchers.Main) {
                            userAdapter.updateData(it.users)
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        Log.e("MainActivity", "Error: ${response.code()}")
                    }

                }

            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("MainActivity", "Network Error: ${e.message}")
                }
            }
        }
    }
}
