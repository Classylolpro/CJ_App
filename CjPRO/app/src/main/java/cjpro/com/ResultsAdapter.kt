package cjpro.com

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ResultsAdapter(private var results: List<QuizResult>) :
    RecyclerView.Adapter<ResultsAdapter.ResultViewHolder>() {

    // Clase ViewHolder actualizada con los IDs del nuevo layout
    inner class ResultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val date: TextView = itemView.findViewById(R.id.resultDate)
        val scoreDisplay: TextView = itemView.findViewById(R.id.resultScoreDisplay)
        val percentage: TextView = itemView.findViewById(R.id.resultPercentage)

        // Opcional: Puedes agregar un click listener aquí si necesitas
        init {
            itemView.setOnClickListener {
                // Lógica para manejar clicks en items si es necesario
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val result = results[position]
                    // Puedes hacer algo con el resultado clickeado
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResultViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_result, parent, false)
        return ResultViewHolder(view)
    }

    override fun onBindViewHolder(holder: ResultViewHolder, position: Int) {
        val result = results[position]

        // Formateamos la fecha para mejor legibilidad (opcional)
        val displayDate = if (result.date.length > 16) {
            result.date.substring(0, 16) // Mostrar solo fecha y hora sin segundos
        } else {
            result.date
        }

        holder.date.text = displayDate
        holder.scoreDisplay.text = "${result.score}/${result.totalQuestions}"
        holder.percentage.text = "%.1f%%".format(result.percentage)

        // Opcional: Cambiar color según el porcentaje
        when {
            result.percentage >= 80 -> holder.percentage.setTextColor(
                holder.itemView.context.getColor(android.R.color.holo_green_dark)
            )
            result.percentage >= 50 -> holder.percentage.setTextColor(
                holder.itemView.context.getColor(android.R.color.holo_orange_dark)
            )
            else -> holder.percentage.setTextColor(
                holder.itemView.context.getColor(android.R.color.holo_red_dark)
            )
        }
    }

    override fun getItemCount(): Int = results.size

    fun updateResults(newResults: List<QuizResult>) {
        // Usamos toList() para crear una nueva referencia y forzar la actualización
        results = newResults.toList()
        notifyDataSetChanged()
    }

    // Función adicional para limpiar los resultados
    fun clearResults() {
        results = emptyList()
        notifyDataSetChanged()
    }
}