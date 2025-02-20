package com.example.githubapiapp.UI

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubapiapp.Adapter.FollowerAdapter
import com.example.githubapiapp.Data.response.Follow
import com.example.githubapiapp.Data.retrofit.Apiconfig
import com.example.githubapiapp.databinding.FragmentFollowingBinding
import com.loopj.android.http.AsyncHttpClient.log
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class followingFragment : Fragment() {

    lateinit var binding: FragmentFollowingBinding

    companion object {
        const val ARG_NAME = "app_name"
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFollowingBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = this.arguments?.getString(followersFragment.ARG_NAME)

        if (username != null){
            findFollowing(username)
        }
    }

    fun findFollowing(user: String){
        showLoading(true)
        val client = Apiconfig.getFollowingService().getFollowing(user)
        client.enqueue(object: Callback<List<Follow>>{
            override fun onResponse(call: Call<List<Follow>>, response: Response<List<Follow>>) {
                showLoading(false)
                if (response.isSuccessful){
                    val responseBody = response.body()
                    if (responseBody != null){
                        setFollowingData(responseBody)
                    }
                }else{
                    Log.e("followingFragment", "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<Follow>>, t: Throwable) {
                showLoading(false)
                log.e("followingFragment", "onFailure:${t.message}")
            }

        })
    }

    fun setFollowingData(data: List<Follow>){
        binding.rvFollowing.layoutManager = LinearLayoutManager(context)
        binding.rvFollowing.adapter = FollowerAdapter(data)
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}