package com.example.retrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.retrofit.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class MainActivity : AppCompatActivity() {

    private val baseURL = "https://jsonplaceholder.typicode.com/"

    lateinit var mainBinding: ActivityMainBinding
    var postList = ArrayList<Posts>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        val view = mainBinding.root
        setContentView(view)

        showPosts()
    }

    fun showPosts(){

        val retrofit = Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitAPI : RetrofitAPI = retrofit.create(RetrofitAPI::class.java)
        val call : Call<List<Posts>> = retrofitAPI.getAllPosts()

        call.enqueue(object : Callback<List<Posts>>{
            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {

                if(!response.isSuccessful){
                    mainBinding.textViewUseId.text = "ERROR!!!"
                    mainBinding.textViewId.text = "ERROR!!!"
                    mainBinding.textViewTitle.text = "ERROR!!!"
                    mainBinding.textViewBody.text = "ERROR!!!"
                }

                postList = response.body() as ArrayList<Posts>

                mainBinding.textViewUseId.text = postList[0].userId.toString()
                mainBinding.textViewId.text = postList[0].id.toString()
                mainBinding.textViewTitle.text = postList[0].title
                mainBinding.textViewBody.text = postList[0].subTitle

            }

            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                Toast.makeText(applicationContext,t.localizedMessage,Toast.LENGTH_LONG).show()
            }


        })
    }
}