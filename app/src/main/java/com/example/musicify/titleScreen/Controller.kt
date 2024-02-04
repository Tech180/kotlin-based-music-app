package com.example.musicify.titleScreen
import com.example.musicify.universal.universal_methods

import android.content.Intent
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.musicify.R

class Controller(private val views: Array<View>, private val activity: AppCompatActivity) : universal_methods() {

    private val handler = Handler()
    //private val db = ArcZoneDatabase()
    //private val auth = ArcZoneAuth(activity)

    init {
        // Set onClick Listeners for each field (visible and invisible)
        start()

        //flashAnimation(views[3])

        //login()
        guest()
        //signUp()
        //buttonSwapOnInput()
    }

    /**
     * Sets up the onClick for the start button
     * This fades out the start button, brings up the screen image,
     * fades in the user EditText, pass EditText, Guest button,
     * and sign up Text button
     */
    private fun start() {
        views[2].setOnClickListener {
            // fade out start button
            fadeOut(views[2], 1000)

            // animate the screen image to appear
            val scale: Animation = AnimationUtils.loadAnimation(activity, R.anim.scale_up)
            views[1].visibility = View.VISIBLE
            views[1].startAnimation(scale)

            // hide the start button
            fadeOut(views[2], 500)

            // delay the fade in of the user, pass, guest, and signup views
            // until the screen image animation has finished + 100 ms
            /*
            handler.postDelayed({
                // fade in user edit text
                fadeIn(views[6], 1000)
                // fade in pass edit text
                fadeIn(views[7], 1000)
                // fade in the guest sign in button
                fadeIn(views[4], 1000)
                // fade in the sign up text
                fadeIn(views[2], 1000)
            }, 1100)
             */
        }
    }

    /*
    private fun login() {
        views[5].setOnClickListener {
            val credential = (views[6] as EditText).text.toString()
            val pass = (views[7] as EditText).text.toString()

            //if the credential is not null
            if (credential.contains("@") && pass != null) {
                val loadingDialog = LoadingDialogFragment()
                auth.loginUser(credential, pass, activity, loadingDialog, db)
                //if the loading frag is hidden and we are still in this activity...
                if (!loadingDialog.isVisible) {
                    // Clear password
                    (views[7] as EditText).text = null
                }
            } else {
                if (!credential.contains("@")) {
                    (views[6] as EditText).text = null
                    Toast.makeText(activity, "Please enter a valid email", Toast.LENGTH_LONG).show()
                } else {
                    // Clear password
                    (views[7] as EditText).text = null
                    Toast.makeText(activity, "Login failed", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
     */

    private fun guest() {
        views[4].setOnClickListener {
            //val arcZoneUser = ArcZoneUser.getInstance("Guest", null, null, null)

            //val intent = Intent(activity, GameSelectionScreen::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.startActivity(intent)
        }
    }

    /*
    private fun signUp() {
        views[2].setOnClickListener { showRegisterPopUp() }
    }
     */

    /*
    private fun buttonSwapOnInput() {
        (views[6] as EditText).addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                // hide Guest login and show login button
                if (s.length > 0) {
                    views[4].visibility = View.INVISIBLE
                    views[5].visibility = View.VISIBLE
                }
                // else if no input, show guest login and hide login button
                else {
                    views[4].visibility = View.VISIBLE
                    views[5].visibility = View.INVISIBLE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                TODO("Not yet implemented")
            }

            override fun afterTextChanged(s: Editable?) {
                TODO("Not yet implemented")
            }
        })
    }

     */

    /*
    private fun showRegisterPopUp() {
        val signup = SignUpFragment()
        signup.show((activity as TitleScreen).supportFragmentManager, "SignUpFragment")
    }
     */
}
