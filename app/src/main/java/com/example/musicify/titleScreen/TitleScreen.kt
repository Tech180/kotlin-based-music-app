package com.example.musicify.titleScreen

import com.example.musicify.ui.menu.Drawer

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.*
import coil.Coil
import coil.load
import coil.util.CoilUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.load.resource.gif.GifDrawable
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.example.musicify.R
import com.example.musicify.universal.universal_methods
import com.google.android.material.button.MaterialButton
import java.lang.reflect.Modifier


class TitleScreen : universal_methods() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // set layout
        setContentView(R.layout.activity_title_screen)

        // set views
        val music: ImageView = findViewById(R.id.music)
        //val background: ImageView = findViewById(R.id.background)
        val screen: ImageView = findViewById(R.id.screen)

        val signUp: TextView = findViewById(R.id.signUp)
        val title: TextView = findViewById(R.id.title)

        val start: Button = findViewById(R.id.start)
        val guest: Button = findViewById(R.id.guest)
        val login: Button = findViewById(R.id.login)

        val email: EditText = findViewById(R.id.fragUsername)
        val pass: EditText = findViewById(R.id.fragPassword)

        // load in gifs to proper ImageViews
        //Glide.with(this).load(R.drawable.background).into(background)
        //Glide.with(this).load(R.drawable.music).into(music)

        loadGIF(music, R.drawable.musiclight);

        val startButton: MaterialButton = findViewById(R.id.start)

        startButton.setOnClickListener {
            val intent = Intent(this, Drawer::class.java)

            startActivity(intent)
        }

        /*
        val rootButton: Button = findViewById(R.id.root)
        rootButton.setOnClickListener {
            val superuserCommands = listOf("echo Hello from superuser!", "ls /data")

            // Execute superuser commands
            val result = superUser(superuserCommands)

            val resultString = result.toString()

            Toast.makeText(applicationContext, resultString, Toast.LENGTH_SHORT).show()
        }
         */

        // create new views array for Controller constructor
        // 0:music, 1:Screen, 2:signUp, 3:start, 4:guest, 5:login, 6:user, 7:pass, 8: title
        val views = arrayOf(music, screen, signUp, start, guest, login, email, pass, title)
        //val views = arrayOf(music, screen, start, guest, title)

        // create controller constructor --> sets up onClicks and controls animations
        Controller(views, this)
    }

    private fun loadGIF(imageView: ImageView, drawableRes: Int) {
        Glide.with(this).asGif().load(drawableRes)
            .listener(object : RequestListener<GifDrawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

                override fun onResourceReady(
                    resource: GifDrawable?,
                    model: Any?,
                    target: Target<GifDrawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    resource?.setLoopCount(1)
                    return false
                }
            }).into(imageView)
    }
}
