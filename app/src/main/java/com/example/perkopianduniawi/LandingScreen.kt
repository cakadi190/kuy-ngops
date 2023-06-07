package com.example.perkopianduniawi

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.*
import android.view.animation.AccelerateDecelerateInterpolator

class LandingScreen : AppCompatActivity() {
    /**
     * The Splash Delay Time to show before enter MainActivity
     *
     * @author Cak Adi cakadi190@gmail.com
     * @since 1.0.0
     */
    private val splashDelay: Long = 3000
    private lateinit var pulseAnimatorSet: AnimatorSet

    /**
     * Overriding onCreate function
     *
     * @author Cak Adi cakadi190@gmail.com
     * @since 1.0.0
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Start the pulse animation
        startPulseAnimation()

        /* Set the loading time and redirect to MainActivity */
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, splashDelay)
    }

    private fun startPulseAnimation() {
        // Create the scale up and scale down animators
        val scaleUpAnimator = ObjectAnimator.ofPropertyValuesHolder(
            R.id.imageViewLoading,
            PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.2f),
            PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.2f)
        ).apply {
            duration = 500
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
            interpolator = AccelerateDecelerateInterpolator()
        }

        // Create the animator set
        pulseAnimatorSet = AnimatorSet().apply {
            play(scaleUpAnimator)
            start()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        pulseAnimatorSet.cancel() // Cancel the animation to avoid leaks
    }
}