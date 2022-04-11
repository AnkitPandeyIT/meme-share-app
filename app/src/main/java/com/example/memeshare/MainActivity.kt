package com.example.memeshare

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.databinding.DataBindingUtil
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.memeshare.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var curruntImageUrl: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)


loadMeme()
    }

   private fun loadMeme(){
      binding.progressBar.visibility = VISIBLE
       // Instantiate the RequestQueue.
       val queue = Volley.newRequestQueue(this)
       val url = "https://meme-api.herokuapp.com/gimme"

// Request a JsonObjectRequest response from the provided URL.
       val JsonObjectRequest = JsonObjectRequest(
           Request.Method.GET, url,null,
           Response.Listener { response ->
                curruntImageUrl = response.getString("url")
               Glide.with(this).load(curruntImageUrl).listener(object: RequestListener<Drawable> {
                   override fun onLoadFailed(
                       e: GlideException?,
                       model: Any?,
                       target: Target<Drawable>?,
                       isFirstResource: Boolean
                   ): Boolean {
                       binding.progressBar.visibility = GONE
                       return false
                       TODO("Not yet implemented")
                   }

                   override fun onResourceReady(
                       resource: Drawable?,
                       model: Any?,
                       target: Target<Drawable>?,
                       dataSource: DataSource?,
                       isFirstResource: Boolean
                   ): Boolean {
                       binding.progressBar.visibility = GONE
                       return false
                       TODO("Not yet implemented")
                   }
               }

               ).into(binding.memeImage)


           },
           Response.ErrorListener {  })

// Add the request to the RequestQueue.
       queue.add(JsonObjectRequest)
    }

    fun share(view: View) {

        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "Checkout this cool meme from reddit \n $curruntImageUrl")
        val chooser = Intent.createChooser(intent,"share this meme using...")
        startActivity(chooser)
    }
    fun next(view: View) {
        loadMeme()

    }

    fun NextPage(view: View) {
//        val intent = Intent(this,wishingActivity::class.java)
//        intent.putExtra("name", name)
//        startActivity(intent)

        val intent = Intent(this,political::class.java)
        startActivity(intent)
    }
}