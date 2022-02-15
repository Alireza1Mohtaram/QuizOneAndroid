package com.alireza.quizone

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.alireza.quizone.databinding.EnqueueLayoutBinding
import okhttp3.*
import java.io.IOException
import java.util.concurrent.Executors

class Enqueue : Fragment(R.layout.enqueue_layout) {

    private val clinet by lazy { OkHttpClient() }
    lateinit var binding: EnqueueLayoutBinding
    private val requestUrl:String ="https://picsum.photos/v2/list?limit=20"
    lateinit var request: Request

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.enqueue_layout, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.enqueueText.text = load()
    }
    private fun load(): String {
        Log.d("Data","load is running")
        request = Request.Builder().url(requestUrl).build()
        var responseBody:String=""

        try {
                val call = clinet.newCall(request)
            call.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                  responseBody = "fail"
                }

                override fun onResponse(call: Call, response: Response) {
                  responseBody = response.body.toString()
                }
            })
        }catch (e:Exception){
            println(e.message)
        }
        Log.d("Data","load is done")
        Log.d("Res",responseBody)
        return responseBody
    }

}