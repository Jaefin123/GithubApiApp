package com.example.githubapiapp.UI

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapiapp.Data.local.datastore.SettingPreference
import com.example.githubapiapp.Data.local.datastore.ViewModelFactory
import com.example.githubapiapp.Data.local.datastore.dataStore
import com.example.githubapiapp.Data.retrofit.Apiconfig
import com.example.githubapiapp.Data.response.User
import com.example.githubapiapp.Data.response.DatagithubResponse
import com.example.githubapiapp.R
import com.example.githubapiapp.UI.DetailActivity.Companion.username
import com.example.githubapiapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity(){


    private lateinit var binding: ActivityMainBinding

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvDataApi.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvDataApi.addItemDecoration(itemDecoration)

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.text
                    searchView.hide()
                    cariUser(searchView.text.toString())
                    false
                }
        }
        lihatSetting()
        Appbar()
        cariUser(shuffle())
    }

    private fun lihatSetting() {
        val pref = SettingPreference.getInstance(application.dataStore)
        val settingViewModel = ViewModelProvider(this, ViewModelFactory(pref)).get(
            SettingViewModel::class.java
        )

        settingViewModel.getThemeSet().observe(this){ isDarkModeActive: Boolean ->

            if (isDarkModeActive) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

        }
    }

    private fun Appbar() {
        binding.AppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.btn_favorite -> {
                    val intent = Intent(this, FavoriteActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.btn_setting -> {
                    val sIntent = Intent(this, SettingActivity::class.java)
                    startActivity(sIntent)
                    true
                }
                else -> false
            }
        }
    }

    fun cariUser(cari: String){
        showLoading(true)
        val client = Apiconfig.getApiService().getUsers(cari)
        client.enqueue(object : Callback<DatagithubResponse> {
            override fun onResponse(
                call: Call<DatagithubResponse>,
                response: Response<DatagithubResponse>
            ) {
                showLoading(false)
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        setUserData(responseBody.items)
                    }
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                    Toast.makeText(this@MainActivity, "gagal", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<DatagithubResponse>, t: Throwable) {
                showLoading(false)
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun setUserData(listUser: ArrayList<User>) {
        val adapter = DataUserAdapter()
        adapter.submitList(listUser)
        binding.rvDataApi.adapter = adapter

        adapter.setOnItemClickCallback(object : DataUserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                val detailAct = Intent(this@MainActivity, DetailActivity::class.java)
                detailAct.putExtra(username,data.login.toString())
                startActivity(detailAct)
            }
        })
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    private fun shuffle(): String{
        val arr = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJK".toCharArray()
        for (i in arr.size - 1 downTo 1) {
            val j = (Math.random() * (i + 1)).toInt()
            val temp = arr[i]
            arr[i] = arr[j]
            arr[j] = temp
        }
        return arr[3].toString()
    }

}