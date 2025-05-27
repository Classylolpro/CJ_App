package cjpro.com

data class QuizResult(
    val date: String,         // Fecha/hora del quiz (ej: "10/05/2023 15:30")
    val score: Int,          // Puntos obtenidos (ej: 8)
    val totalQuestions: Int, // Total de preguntas (ej: 10)
    val percentage: Float = (score.toFloat() / totalQuestions) * 100 // % calculado automáticamente
)