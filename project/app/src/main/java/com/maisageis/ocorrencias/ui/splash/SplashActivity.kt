package com.maisageis.ocorrencias.ui.splash

import android.os.Bundle
import android.os.Handler
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat.animate
import androidx.core.view.ViewPropertyAnimatorCompat
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.ui.register.RegisterActivity
import com.maisageis.ocorrencias.ui.login.LoginActivity
import com.maisageis.ocorrencias.ui.slider.SliderActivity
import com.maisageis.ocorrencias.util.SecurityData
import org.koin.android.ext.android.inject


class SplashActivity : AppCompatActivity() {

    private val STARTUP_DELAY = 300
    private val ANIM_ITEM_DURATION = 1000
    private val ITEM_DELAY = 300

    private val animationStarted = false
    private val securityData: SecurityData by inject()

    private lateinit var btnLogin: Button
    private lateinit var btnRegister: Button
    private lateinit var logoImageView: ImageView
    private lateinit var container: ViewGroup
    private lateinit var load: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        initViews()
        initClicks()
    }

    private fun initClicks() {
        btnRegister.setOnClickListener {
            startActivity(RegisterActivity.newInstance(this))
            finish()
        }

        btnLogin.setOnClickListener {
            startActivity(LoginActivity.newInstance(this))
            finish()
        }
    }

    private fun initViews() {
        btnLogin = findViewById(R.id.btn_splash_login)
        btnRegister = findViewById(R.id.btn_splash_cadastro)
        logoImageView = findViewById(R.id.img_logo)
        container = findViewById(R.id.container)
        load = findViewById(R.id.splash_loading)
    }

    override fun onWindowFocusChanged(hasFocus: Boolean) {
        if (!hasFocus || animationStarted)
            return

        animate()
        super.onWindowFocusChanged(hasFocus)
    }

    private fun removeItems() {
        for (i in 0 until container.childCount) {
            val v = container.getChildAt(i)
            var viewAnimator: ViewPropertyAnimatorCompat
            viewAnimator = if (v !is Button)
                animate(v)
                    .translationY(0f).alpha(0f)
                    .setStartDelay(ITEM_DELAY * i + 500.toLong())
                    .setDuration(1000)
            else
                animate(v)
                    .scaleY(1f).scaleX(0f)
                    .setStartDelay(ITEM_DELAY * i + 500.toLong())
                    .setDuration(500)

            viewAnimator.setInterpolator(DecelerateInterpolator()).start()
        }

        animate(logoImageView).scaleY(1f).alpha(0f)
            .setStartDelay(ITEM_DELAY * (container.childCount + 1) + 500.toLong())
            .setDuration(500).start()

        Handler().postDelayed(Runnable {
            startActivity(SliderActivity.newInstance(this))
            finish()
        }, 3000)
    }

    private fun animate() {
        animate(logoImageView)
            .translationY(-250f)
            .setStartDelay(STARTUP_DELAY.toLong())
            .setDuration(ANIM_ITEM_DURATION.toLong()).setInterpolator(
                DecelerateInterpolator(1.2f)
            ).start()

        for (i in 0 until container.childCount) {
            val v = container.getChildAt(i)
            var viewAnimator: ViewPropertyAnimatorCompat
            if (v !is Button) {
                viewAnimator = animate(v)
                    .translationY(50f).alpha(1f)
                    .setStartDelay(ITEM_DELAY * i + 500.toLong())
                    .setDuration(1000)
                viewAnimator.setInterpolator(DecelerateInterpolator()).start()
            }
        }

        Handler().postDelayed(Runnable {
            animate(load).scaleY(1f).alpha(0f)
                .setStartDelay(ITEM_DELAY * 1 + 500.toLong())
                .setDuration(500).start()

            if (securityData.getUer() == null) {
                for (i in 0 until container.childCount) {
                    val v = container.getChildAt(i)
                    var viewAnimator: ViewPropertyAnimatorCompat
                    if (v is Button) {
                        viewAnimator = animate(v)
                            .scaleY(1f).scaleX(1f)
                            .setStartDelay(ITEM_DELAY * i + 500.toLong())
                            .setDuration(500)

                        viewAnimator.setInterpolator(DecelerateInterpolator()).start()
                    }
                }
            } else
                removeItems()
        }, 3000)
    }
}
