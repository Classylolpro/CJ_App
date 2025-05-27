package cjpro.com

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import cjpro.com.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var questions: List<Question>
    private lateinit var prefs: SharedPreferences
    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DatabaseHelper(this)
        prefs = PreferenceManager.getDefaultSharedPreferences(this)
        questions = dbHelper.getRandomQuestions()

        if (questions.isEmpty()) {
            Toast.makeText(this, "No hay preguntas disponibles", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        showQuestion(currentQuestionIndex)

        binding.nextButton.setOnClickListener {
            handleAnswerSelection()
        }
    }

    private fun handleAnswerSelection() {
        val selectedId = binding.optionsGroup.checkedRadioButtonId
        if (selectedId == -1) {
            Toast.makeText(this, "Por favor selecciona una respuesta", Toast.LENGTH_SHORT).show()
            return
        }

        val selectedRadioButton = findViewById<RadioButton>(selectedId)
        val answerNr = binding.optionsGroup.indexOfChild(selectedRadioButton) + 1

        if (answerNr == questions[currentQuestionIndex].correctAnswer) {
            score++
        }

        currentQuestionIndex++
        if (currentQuestionIndex < questions.size) {
            showQuestion(currentQuestionIndex)
            binding.optionsGroup.clearCheck()
        } else {
            showResults()
        }
    }

    private fun showQuestion(index: Int) {
        val q = questions[index]
        binding.questionText.text = q.question
        binding.option1.text = q.option1
        binding.option2.text = q.option2
        binding.option3.text = q.option3
        binding.option4.text = q.option4

        binding.nextButton.text = if (index == questions.size - 1) "Finalizar" else "Siguiente (${index + 1}/${questions.size})"
    }

    private fun showResults() {
        saveSessionStats()

        startActivity(Intent(this, ResultActivity::class.java).apply {
            putExtra("score", score)
            putExtra("total", questions.size)
        })
        finish()
    }

    private fun saveSessionStats() {
        prefs.edit {
            val bestScore = prefs.getInt("best_score", 0)
            if (score > bestScore) {
                putInt("best_score", score)
            }

            val usedQuestions = questions.map { it.id.toString() }.toSet()
            putStringSet("last_used_questions", usedQuestions)

            val totalQuizzes = prefs.getInt("total_quizzes", 0) + 1
            putInt("total_quizzes", totalQuizzes)
        }
    }
}
