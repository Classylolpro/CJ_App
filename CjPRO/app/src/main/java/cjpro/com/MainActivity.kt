package cjpro.com

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cjpro.com.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Verificamos si se llegó desde el botón "Volver a Jugar"
        val shouldStartQuiz = intent.getBooleanExtra("start_quiz", false)
        if (shouldStartQuiz) {
            startActivity(Intent(this, QuizActivity::class.java))
        }

        // Botón para iniciar el quiz manualmente
        binding.btnQuiz.setOnClickListener {
            startActivity(Intent(this, QuizActivity::class.java))
        }

        // Botón para ver resultados históricos
        binding.btnResults.setOnClickListener {
            startActivity(Intent(this, ResultsHistoryActivity::class.java))
        }

        // Botón para ver el tutorial
        binding.btnTutorial.setOnClickListener {
            startActivity(Intent(this, TutorialActivity::class.java))
        }
    }
}



