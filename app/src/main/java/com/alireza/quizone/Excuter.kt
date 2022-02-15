package com.alireza.quizone

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.alireza.quizone.databinding.ExecuterLayoutBinding
import okhttp3.OkHttpClient
import okhttp3.Request
import java.util.concurrent.Callable
import java.util.concurrent.Executors
import java.util.concurrent.Future

class Excuter : Fragment(R.layout.activity_main) {

    private val clinet by lazy { OkHttpClient() }
    lateinit var binding : ExecuterLayoutBinding
    private val executer by lazy { Executors.newSingleThreadExecutor()}
    private val requestUrl:String ="https://picsum.photos/v2/list?limit=20"
    lateinit var request: Request

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.executerText.text = load()
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.executer_layout, container, false)
        return binding.root
    }


    private fun load(): String {
        Log.d("Data","load is running")
        request = Request.Builder().url(requestUrl).build()
        var responseBody:String=""
        val future : Future<String>
        try {
            future = executer.submit(Callable<String> {
                val call = clinet.newCall(request)
                val response = call.execute()
                response.body?.let { Log.d("Res", it.string()) }
                response.body.toString()
            })
            responseBody = future.get()
        }catch (e:Exception){
            println(e.message)
        }
        Log.d("Data","load is done")
        Log.d("Res",responseBody)
        return responseBody
    }



}