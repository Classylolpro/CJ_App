package cjpro.com

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import cjpro.com.databinding.ActivityTutorialBinding
import com.google.android.material.tabs.TabLayoutMediator

class TutorialActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTutorialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTutorialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupSkipButton()
    }

    private fun setupViewPager() {
        val tutorialPages = listOf(
            TutorialPage(
                "Bienvenido a CJ Quiz",
                "Esta aplicación te permite poner a prueba tus conocimientos con quizzes de diferentes temas.",
                R.drawable.tutorial_welcome
            ),
            TutorialPage(
                "Cómo jugar",
                "Responde las preguntas seleccionando una de las opciones y presiona 'Siguiente' para continuar.",
                R.drawable.tutorial_how_to_play
            ),
            TutorialPage(
                "Consulta tus resultados",
                "Puedes ver tu progreso y resultados anteriores en la sección de Historial.",
                R.drawable.tutorial_results
            )
        )

        val adapter = TutorialPagerAdapter(tutorialPages)
        binding.tutorialViewPager.adapter = adapter

        // ✅ Conexión correcta entre ViewPager2 y TabLayout
        TabLayoutMediator(binding.tutorialTabLayout, binding.tutorialViewPager) { _, _ -> }.attach()
    }

    private fun setupSkipButton() {
        binding.btnSkipTutorial.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}

// Modelo para páginas del tutorial
data class TutorialPage(
    val title: String,
    val description: String,
    val imageRes: Int
)

// Adapter para ViewPager2
class TutorialPagerAdapter(private val pages: List<TutorialPage>) :
    RecyclerView.Adapter<TutorialPagerAdapter.TutorialPageViewHolder>() {

    class TutorialPageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.tutorialImage)
        val title: TextView = itemView.findViewById(R.id.tutorialTitle)
        val description: TextView = itemView.findViewById(R.id.tutorialDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TutorialPageViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_tutorial_page, parent, false)
        return TutorialPageViewHolder(view)
    }

    override fun onBindViewHolder(holder: TutorialPageViewHolder, position: Int) {
        val page = pages[position]
        holder.image.setImageResource(page.imageRes)
        holder.title.text = page.title
        holder.description.text = page.description
    }

    override fun getItemCount() = pages.size
}

