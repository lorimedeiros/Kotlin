package com.example.myapplication

import android.graphics.Rect
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        reproduzirAudio()

        binding.main.setOnClickListener {
            try {
                reproduzirAudio()
                hideKeyboard()
            } catch (e: Exception) {
                Log.e("MainActivity", "Erro", e)
            }
        }
    }

    private fun hideKeyboard() {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        currentFocus?.let { focusView ->
            imm.hideSoftInputFromWindow(focusView.windowToken, 0)
        }
    }

    override fun dispatchTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (v is View) {
                val outRect = Rect()
                v.getGlobalVisibleRect(outRect)
                if (!outRect.contains(event.rawX.toInt(), event.rawY.toInt())) {
                    v.clearFocus()
                    hideKeyboard()
                }
            }
        }
        return super.dispatchTouchEvent(event)
    }

    private fun reproduzirAudio() {
        mediaPlayer?.release() // Libere o MediaPlayer anterior, se existir
        mediaPlayer = MediaPlayer.create(this, R.raw.banhar)
        mediaPlayer?.start()
        mediaPlayer?.setOnCompletionListener {
            it.release()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer?.release()
        mediaPlayer = null
    }
}