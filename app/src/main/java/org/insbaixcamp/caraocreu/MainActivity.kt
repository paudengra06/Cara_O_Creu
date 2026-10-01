package org.insbaixcamp.caraocreu

import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        alPulsar()
    }

    private fun alPulsar() {
        val ivCoin = findViewById<ImageView>(R.id.imageView)
        ivCoin.setOnClickListener {
            val resultat = (1..2).random()
            if (resultat == 1){
                animacioGirar(R.drawable.cara, "Cara")
            }else {
                animacioGirar(R.drawable.creu, "Creu")
            }
        }
    }

    private fun animacioGirar(resultImage: Int, resultText: String){

        val ImageView = findViewById<ImageView>(R.id.imageView)
        ImageView.animate().apply {
            duration = 1000
            rotationYBy(1800f)
            ImageView.isClickable = false
        }.withEndAction {
            ImageView.setImageResource(resultImage)
            mostrarResultat(resultText)
            ImageView.isClickable = true
        }

    }

    private fun mostrarResultat(resultText: String) {
        val tvResultat = findViewById<TextView>(R.id.tvResultat)
        tvResultat.animate().cancel()
        tvResultat.text = resultText
        tvResultat.alpha = 0f
        tvResultat.visibility = View.VISIBLE
        tvResultat.animate().alpha(1f).setStartDelay(0).setDuration(200).withEndAction {
            tvResultat.animate().alpha(0f).setStartDelay(2000).setDuration(200).withEndAction {
                tvResultat.visibility = View.GONE
            }
        }
    }

}