package com.alireza.quizone

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.alireza.quizone.databinding.GlideLayoutBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class Glide : Fragment(R.layout.glide_layout) {

    lateinit var binding: GlideLayoutBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(layoutInflater, R.layout.executer_layout, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadImage(binding.imageView1,"https://picsum.photos/seed/200/300")
        loadImage(binding.imageView2,"https://picsum.photos/seed/200/300")
        loadImage(binding.imageView3,"https://picsum.photos/seed/200/300")

        binding.btnShare1.setOnClickListener {

            sendMovie(
                binding.titleSoonFragment1.text.toString(),
                binding.descSoonFragment1.text.toString()
            )
        }

        binding.btnShare2.setOnClickListener {
            sendMovie(
                binding.titleSoonFragment2.text.toString(),
                binding.descSoonFragment2.text.toString()
            )
        }

        binding.btnShare3.setOnClickListener {
            sendMovie(
                binding.titleSoonFragment3.text.toString(),
                binding.descSoonFragment3.text.toString()
            )
        }
    }
    private fun sendMovie(title: String, desc: String) {

        val i = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, "$title \n $desc")
            type = "text/plain"
        }
        startActivity(Intent.createChooser(i,"movie"))
    }

    private fun loadImage(imageView: ImageView, imageURL: String?) {
        if (!imageURL.isNullOrBlank()) {
            Glide.with(imageView.context)
                .setDefaultRequestOptions(RequestOptions.fitCenterTransform())
                .load(imageURL)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(imageView);
        }
    }



}