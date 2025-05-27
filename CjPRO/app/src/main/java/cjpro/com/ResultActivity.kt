package cjpro.com

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cjpro.com.databinding.ActivityResultBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = getSharedPreferences("QuizResults", MODE_PRIVATE)

        val score = intent.getIntExtra("score", 0)
        val total = intent.getIntExtra("total", 1)
        val percentage = (score * 100) / total.toFloat()

        binding.scoreText.text = "Respondiste correctamente $score de $total preguntas"
        binding.percentageText.text = "Porcentaje: ${percentage.toInt()}%"

        // Guardar el resultado
        saveQuizResult(score, total)

        binding.restartButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("start_quiz", true)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish()
        }
    }

    private fun saveQuizResult(score: Int, total: Int) {
        // Obtener resultados existentes
        val gson = Gson()
        val json = sharedPrefs.getString("saved_results", null)
        val type = object : TypeToken<MutableList<QuizResult>>() {}.type
        val results = json?.let { gson.fromJson<MutableList<QuizResult>>(it, type) } ?: mutableListOf()

        // Crear nuevo resultado
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
        val newResult = QuizResult(
            date = dateFormat.format(Date()),
            score = score,
            totalQuestions = total
        )

        // Agregar y guardar
        results.add(newResult)
        sharedPrefs.edit()
            .putString("saved_results", gson.toJson(results))
            .apply()
    }
}