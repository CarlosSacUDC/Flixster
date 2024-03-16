package com.example.flixster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Headers

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movieRv = findViewById<RecyclerView>(R.id.moviesRv)
        movieRv.layoutManager = LinearLayoutManager(this)

        updateAdapter(movieRv)

    }

    private fun updateAdapter(recyclerView: RecyclerView) {

        val client = AsyncHttpClient()
        val params = RequestParams()
        params["api_key"] = "a07e22bc18f5cb106bfe4cc1f83ad8ed"

        client[
            "https://api.themoviedb.org/3/movie/now_playing", params,
            object : JsonHttpResponseHandler() {

                override fun onSuccess(
                    statusCode: Int,
                    headers: Headers,
                    json: JsonHttpResponseHandler.JSON
                ) {
                    val moviesRawJSON : String = json.jsonObject.get("results").toString()
                    val gson = Gson()
                    val arrayMovieType = object :TypeToken<List<Movie>>() {}.type
                    val models : List<Movie> = gson.fromJson(moviesRawJSON, arrayMovieType)
                    recyclerView.adapter = MovieAdapter(models)
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    t: Throwable?
                ){
                    t?.message?.let {
                        Log.e("MainActivity", errorResponse)
                    }
                }
            }
        ]

    }
}