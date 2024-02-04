package com.example.musicify.universal

import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import androidx.appcompat.app.AppCompatActivity
import java.io.DataOutputStream

open class universal_methods : AppCompatActivity() {

    protected fun removeTitleBar(activity: AppCompatActivity) {
        activity.requestWindowFeature(Window.FEATURE_NO_TITLE)
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        activity.supportActionBar?.hide()
    }

    /**
     * Used to fade in views
     * @param view view to be faded in
     * @param dur duration of fade in
     */
    protected fun fadeIn(view: View, dur: Int) {
        view.visibility = View.VISIBLE
        val fadeIn: Animation = AlphaAnimation(0f, 1f)
        fadeIn.duration = dur.toLong()
    }

    /**
     * Used to fade out views
     * @param view view to be faded out
     * @param dur duration of fade out
     */
    protected fun fadeOut(view: View, dur: Int) {
        view.clearAnimation()
        val fadeOut: Animation = AlphaAnimation(1f, 0f)
        fadeOut.duration = dur.toLong()
        view.visibility = View.INVISIBLE
    }

    protected fun flashAnimation(view: View) {
        val flashingAnimation = AlphaAnimation(0.5f, 1.0f)
        flashingAnimation.duration = 1000 // Adjust the duration as needed
        flashingAnimation.repeatMode = Animation.REVERSE
        flashingAnimation.repeatCount = Animation.INFINITE

        // Start the animation
        view.startAnimation(flashingAnimation)
    }

    /**
     * Requests superuser permissions and executes commands
     * @param commands List of commands to be executed with superuser permissions
     */
    protected fun superUser(commands: List<String>) {
        val process: Process
        try {
            process = Runtime.getRuntime().exec("su")
            val outputStream = DataOutputStream(process.outputStream)

            // Execute the provided commands with superuser permissions
            for (command in commands) {
                outputStream.writeBytes("$command\n")
            }

            outputStream.flush()
            outputStream.close()
            process.waitFor()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
