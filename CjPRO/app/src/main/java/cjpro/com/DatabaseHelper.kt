package cjpro.com

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.util.*

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "CJProQuiz.db"
        private const val DATABASE_VERSION = 2  // Incrementado por cambios en estructura

        // Tabla de preguntas
        private const val TABLE_QUESTIONS = "questions"
        private const val COLUMN_ID = "id"
        private const val COLUMN_QUESTION = "question"
        private const val COLUMN_OPTION1 = "option1"
        private const val COLUMN_OPTION2 = "option2"
        private const val COLUMN_OPTION3 = "option3"
        private const val COLUMN_OPTION4 = "option4"
        private const val COLUMN_CORRECT = "correct_answer"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_QUESTIONS_TABLE = """
            CREATE TABLE $TABLE_QUESTIONS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_QUESTION TEXT NOT NULL,
                $COLUMN_OPTION1 TEXT NOT NULL,
                $COLUMN_OPTION2 TEXT NOT NULL,
                $COLUMN_OPTION3 TEXT NOT NULL,
                $COLUMN_OPTION4 TEXT NOT NULL,
                $COLUMN_CORRECT INTEGER NOT NULL
            )
        """.trimIndent()
        db.execSQL(CREATE_QUESTIONS_TABLE)
        insertInitialQuestions(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_QUESTIONS")
        onCreate(db)
    }

    private fun insertInitialQuestions(db: SQLiteDatabase) {
        insertQuestion(db, "¿Qué es una dirección MAC?", "Dirección de email", "Identificador único de red", "Clave de red", "Puerto físico", 2);
        insertQuestion(db, "¿Qué significa BIOS?", "Basic Input Output System", "Binary Integrated Operating System", "Base Instruction Operating Set", "Bit Input Output Storage", 1);
        insertQuestion(db, "¿Qué protocolo se usa en la web?", "FTP", "SMTP", "HTTP", "SNMP", 3);
        insertQuestion(db, "¿Qué es un backup?", "Actualización de software", "Copia de seguridad", "Reinicio del sistema", "Instalación de programas", 2);
        insertQuestion(db, "¿Qué componente interpreta y ejecuta instrucciones del sistema?", "CPU", "GPU", "RAM", "Disco Duro", 1);
        insertQuestion(db, "¿Cuál es el lenguaje principal usado para scripts en sistemas Unix?", "Python", "Perl", "Shell", "C++", 3);
        insertQuestion(db, "¿Qué protocolo se utiliza para obtener una dirección IP automáticamente?", "HTTP", "FTP", "DHCP", "DNS", 3);
        insertQuestion(db, "¿Cuál es la capa 3 del modelo OSI?", "Transporte", "Red", "Enlace de datos", "Aplicación", 2);
        insertQuestion(db, "¿Qué dispositivo conecta dos redes diferentes?", "Switch", "Router", "Hub", "Firewall", 2);
        insertQuestion(db, "¿Cuál es la principal función de un firewall?", "Enviar correos", "Proteger la red", "Compartir archivos", "Realizar backups", 2);
        insertQuestion(db, "¿Qué sistema operativo es de código abierto?", "Windows", "macOS", "Linux", "iOS", 3);
        insertQuestion(db, "¿Qué tipo de software es Microsoft Word?", "Sistema", "Aplicación", "Utilitario", "Firmware", 2);
        insertQuestion(db, "¿Qué componente almacena temporalmente datos de la CPU?", "ROM", "Disco Duro", "RAM", "GPU", 3);
        insertQuestion(db, "¿Cuál es el sistema de archivos usado por defecto en Windows?", "ext4", "FAT32", "NTFS", "APFS", 3);
        insertQuestion(db, "¿Qué es un SSD?", "Un tipo de memoria RAM", "Un disco duro tradicional", "Disco de estado sólido", "Tarjeta gráfica", 3);
        insertQuestion(db, "¿Qué lenguaje se utiliza para consultas en bases de datos relacionales?", "HTML", "Java", "SQL", "Python", 3);
        insertQuestion(db, "¿Qué es una dirección IP?", "Un tipo de router", "Identificador de red", "Un puerto USB", "Un tipo de memoria", 2);
        insertQuestion(db, "¿Qué es una VPN?", "Tipo de virus", "Red privada virtual", "Sistema de respaldo", "Software de correo", 2);
        insertQuestion(db, "¿Qué tipo de red cubre una ciudad entera?", "LAN", "WAN", "PAN", "MAN", 4);
        insertQuestion(db, "¿Qué hace un servidor DNS?", "Resuelve nombres de dominio", "Cifra datos", "Conecta usuarios", "Detecta virus", 1);

        insertQuestion(db, "¿Qué lenguaje se usa para desarrollar aplicaciones Android?", "Swift", "Kotlin", "JavaScript", "Ruby", 2);
        insertQuestion(db, "¿Cuál es la estructura principal de control condicional?", "for", "if", "while", "switch", 2);
        insertQuestion(db, "¿Qué simboliza '==' en programación?", "Asignación", "Comparación", "Concatenación", "Multiplicación", 2);
        insertQuestion(db, "¿Qué es una variable?", "Función", "Constante", "Contenedor de datos", "Clase", 3);
        insertQuestion(db, "¿Qué es un bucle en programación?", "Tipo de error", "Repetición de código", "Clase abstracta", "Sistema operativo", 2);
        insertQuestion(db, "¿Qué significa HTML?", "Hyperlink and Text Management Language", "HyperText Markup Language", "Hyper Tool Multi Language", "None", 2);
        insertQuestion(db, "¿Qué es un array?", "Un bucle", "Un error", "Estructura de control", "Colección de elementos", 4);
        insertQuestion(db, "¿Qué lenguaje es más usado para páginas web?", "Java", "C", "HTML", "Python", 3);
        insertQuestion(db, "¿Qué hace una función en programación?", "Contiene variables", "Repite código", "Agrupa instrucciones reutilizables", "Compila el código", 3);
        insertQuestion(db, "¿Qué es un IDE?", "Editor de imágenes", "Programa para editar texto", "Entorno de desarrollo", "Base de datos", 3);
        insertQuestion(db, "¿Qué tipo de lenguaje es Python?", "Compilado", "Marcado", "Interpretado", "Ensamblador", 3);
        insertQuestion(db, "¿Para qué sirve Git?", "Crear archivos", "Gestionar versiones", "Editar imágenes", "Navegar en Internet", 2);
        insertQuestion(db, "¿Qué es un 'bug'?", "Un comentario", "Una mejora", "Un error", "Una librería", 3);
        insertQuestion(db, "¿Qué hace 'print' en la mayoría de lenguajes?", "Cierra programa", "Reinicia sistema", "Imprime valores", "Suma números", 3);
        insertQuestion(db, "¿Qué es una clase en POO?", "Tipo de dato", "Grupo de funciones", "Plantilla de objetos", "Variable global", 3);
        insertQuestion(db, "¿Cuál es el operador lógico AND en muchos lenguajes?", "&&", "||", "!", "==", 1);
        insertQuestion(db, "¿Qué extensión tienen los archivos Java?", ".js", ".jav", ".jv", ".java", 4);
        insertQuestion(db, "¿Qué significa 'null'?", "0", "Nada", "Infinito", "Cadena vacía", 2);
        insertQuestion(db, "¿Qué estructura se usa para almacenar pares clave-valor?", "Array", "Lista", "HashMap", "Pila", 3);
        insertQuestion(db, "¿Qué es una API?", "Base de datos", "Sistema operativo", "Interfaz de programación", "Tipo de software", 3);

        insertQuestion(db, "¿Quién escribió '1984'?", "Aldous Huxley", "George Orwell", "Ray Bradbury", "Isaac Asimov", 2);
        insertQuestion(db, "¿Qué científico propuso la teoría de la relatividad?", "Isaac Newton", "Nikola Tesla", "Albert Einstein", "Stephen Hawking", 3);
        insertQuestion(db, "¿Cuál es la capital de Australia?", "Sídney", "Melbourne", "Canberra", "Perth", 3);
        insertQuestion(db, "¿Qué órgano del cuerpo humano filtra la sangre?", "Pulmones", "Corazón", "Riñones", "Estómago", 3);
        insertQuestion(db, "¿Quién escribió 'Cien años de soledad'?", "Mario Vargas Llosa", "Julio Cortázar", "Gabriel García Márquez", "Pablo Neruda", 3);
        insertQuestion(db, "¿Qué país tiene la economía más grande del mundo?", "China", "Japón", "Estados Unidos", "Alemania", 3);
        insertQuestion(db, "¿Cuál es el símbolo químico del oro?", "Au", "Ag", "Fe", "O", 1);
        insertQuestion(db, "¿En qué continente está Kazajistán?", "Europa", "Asia", "África", "Oceanía", 2);
        insertQuestion(db, "¿Qué instrumento se usa para medir la presión atmosférica?", "Termómetro", "Higrómetro", "Barómetro", "Anemómetro", 3);
        insertQuestion(db, "¿Qué filósofo escribió 'La República'?", "Aristóteles", "Sócrates", "Platón", "Descartes", 3);
        insertQuestion(db, "¿Qué elemento tiene el número atómico 1?", "Helio", "Oxígeno", "Hidrógeno", "Nitrógeno", 3);
        insertQuestion(db, "¿Qué país es conocido como el país del sol naciente?", "Corea del Sur", "China", "Japón", "India", 3);
        insertQuestion(db, "¿Cuál es el idioma oficial de Brasil?", "Español", "Portugués", "Francés", "Inglés", 2);
        insertQuestion(db, "¿Quién escribió 'El Principito'?", "Antoine de Saint-Exupéry", "Victor Hugo", "Franz Kafka", "Julio Verne", 1);
        insertQuestion(db, "¿Qué país tiene más Patrimonios de la Humanidad?", "China", "Italia", "España", "México", 2);
        insertQuestion(db, "¿Qué planeta tiene los anillos más visibles?", "Júpiter", "Urano", "Neptuno", "Saturno", 4);
        insertQuestion(db, "¿Cuál es la ciudad más poblada del mundo?", "Tokio", "Delhi", "Shanghái", "Ciudad de México", 1);
        insertQuestion(db, "¿En qué año llegó el hombre a la Luna?", "1965", "1969", "1971", "1973", 2);
        insertQuestion(db, "¿Qué pintor es famoso por cortar parte de su oreja?", "Dalí", "Picasso", "Van Gogh", "Monet", 3);
        insertQuestion(db, "¿Qué país inventó el papel?", "Egipto", "Grecia", "China", "India", 3);
        insertQuestion(db, "¿Cuál es la capital de Sudáfrica?", "Pretoria", "Ciudad del Cabo", "Johannesburgo", "Durban", 1);
        insertQuestion(db, "¿Qué científico formuló las leyes del movimiento?", "Einstein", "Galileo", "Newton", "Kepler", 3);
        insertQuestion(db, "¿Cuál es el océano más profundo?", "Atlántico", "Índico", "Pacífico", "Ártico", 3);
        insertQuestion(db, "¿Qué escritor fue Premio Nobel en 1954?", "Hemingway", "Faulkner", "Sartre", "Camus", 1);
        insertQuestion(db, "¿Qué instrumento musical tiene cuerdas y se toca con arco?", "Guitarra", "Piano", "Violín", "Trompeta", 3);
        insertQuestion(db, "¿Qué país tiene forma de bota?", "España", "Francia", "Italia", "Grecia", 3);
        insertQuestion(db, "¿Qué gas respiramos principalmente?", "Oxígeno", "Dióxido de carbono", "Nitrógeno", "Hidrógeno", 3);
        insertQuestion(db, "¿Cuál es la lengua más hablada del mundo?", "Inglés", "Español", "Chino Mandarín", "Árabe", 3);
        insertQuestion(db, "¿Qué metal es el mejor conductor eléctrico?", "Cobre", "Oro", "Aluminio", "Plata", 4);
    }

    fun getRandomQuestions(): List<Question> {
        val db = readableDatabase
        val questions = mutableListOf<Question>()

        // Consulta que obtiene 10 preguntas aleatorias
        val cursor = db.rawQuery("""
            SELECT * FROM $TABLE_QUESTIONS 
            ORDER BY RANDOM() 
            LIMIT 10
        """.trimIndent(), null)

        while (cursor.moveToNext()) {
            questions.add(
                Question(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5),
                    cursor.getInt(6)
                )
            )
        }
        cursor.close()
        return questions
    }

    private fun insertQuestion(db: SQLiteDatabase, question: String,
                               option1: String, option2: String, option3: String, option4: String,
                               correctAnswer: Int) {
        val values = ContentValues().apply {
            put(COLUMN_QUESTION, question)
            put(COLUMN_OPTION1, option1)
            put(COLUMN_OPTION2, option2)
            put(COLUMN_OPTION3, option3)
            put(COLUMN_OPTION4, option4)
            put(COLUMN_CORRECT, correctAnswer)
        }
        db.insert(TABLE_QUESTIONS, null, values)
    }
}