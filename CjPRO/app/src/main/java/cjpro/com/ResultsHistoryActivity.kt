package cjpro.com

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import cjpro.com.databinding.ActivityResultsHistoryBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ResultsHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultsHistoryBinding
    private lateinit var sharedPrefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultsHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPrefs = getSharedPreferences("QuizResults", MODE_PRIVATE)

        setupRecyclerView()
        loadResults()
    }

    private fun setupRecyclerView() {
        binding.resultsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@ResultsHistoryActivity)
            adapter = ResultsAdapter(mutableListOf())
            setHasFixedSize(true)
        }
    }

    private fun loadResults() {
        val gson = Gson()
        val json = sharedPrefs.getString("saved_results", null)

        if (json == null) {
            showEmptyState()
            return
        }

        val type = object : TypeToken<List<QuizResult>>() {}.type
        val results = gson.fromJson<List<QuizResult>>(json, type)

        if (results.isEmpty()) {
            showEmptyState()
        } else {
            showResults(results.sortedByDescending { it.date })
        }
    }

    private fun showResults(results: List<QuizResult>) {
        binding.emptyStateText.visibility = View.GONE
        binding.resultsRecyclerView.visibility = View.VISIBLE
        (binding.resultsRecyclerView.adapter as ResultsAdapter).updateResults(results)
    }

    private fun showEmptyState() {
        binding.emptyStateText.visibility = View.VISIBLE
        binding.resultsRecyclerView.visibility = View.GONE
    }
}