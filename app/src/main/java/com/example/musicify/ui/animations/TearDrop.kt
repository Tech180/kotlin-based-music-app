package com.example.musicify.ui.animations

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.example.musicify.R
import com.google.android.material.button.MaterialButton
import com.google.android.material.shape.CornerFamily
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.shape.ShapeAppearanceModel

class TearDrop : AppCompatActivity() {

    private lateinit var teardropButton: MaterialButton
    private lateinit var loginView: View // Replace with your login menu view

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        teardropButton = findViewById(R.id.start)
        loginView = findViewById(R.id.login)

        teardropButton.setOnClickListener {
            animateToTeardrop()
        }
    }

    private fun animateToTeardrop() {
        val shapeModel = ShapeAppearanceModel.builder()
            .setAllCorners(CornerFamily.ROUNDED, resources.getDimension(R.dimen.teardrop_corner_radius))
            .build()

        val initialShapeDrawable = MaterialShapeDrawable(shapeModel)
        initialShapeDrawable.setTint(resources.getColor(R.color.primary))

        val finalShapeModel = ShapeAppearanceModel.builder()
            .setAllCorners(CornerFamily.ROUNDED, 0f)
            .build()

        val finalShapeDrawable = MaterialShapeDrawable(finalShapeModel)
        finalShapeDrawable.setTint(resources.getColor(R.color.primary))

        val initialRadiusAnimator = ObjectAnimator.ofFloat(
            initialShapeDrawable,
            "interpolation",
            1f
        )

        val finalRadiusAnimator = ObjectAnimator.ofFloat(
            finalShapeDrawable,
            "interpolation",
            0f
        )

        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(initialRadiusAnimator, finalRadiusAnimator)
        animatorSet.interpolator = AccelerateDecelerateInterpolator()
        animatorSet.duration = resources.getInteger(R.integer.teardrop_animation_duration).toLong()

        initialRadiusAnimator.addUpdateListener { valueAnimator ->
            teardropButton.background = initialShapeDrawable
            initialShapeDrawable.interpolation = valueAnimator.animatedValue as Float
        }

        // Show your login menu view
        loginView.visibility = View.VISIBLE

        animatorSet.start()
    }
}
