package com.example.aroundtheworld.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aroundtheworld.databinding.ActivityBlogDetailsBinding
import com.squareup.picasso.Picasso

class BlogDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBlogDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.blogTitle.text = getIntent().getStringExtra("BlogTitle")
        binding.blogAuthor.text = getIntent().getStringExtra("BlogAuthor")
        binding.blogBody.text = getIntent().getStringExtra("BlogBody")

        val image_url : String? = getIntent().getStringExtra("BlogImageUrl")

        Picasso.get().load(image_url).into(binding.blogImageUrl)
    }
}