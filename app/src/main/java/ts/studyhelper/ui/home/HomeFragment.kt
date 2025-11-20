package ts.studyhelper.ui.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.util.*
import android.widget.Button
import android.widget.TextView
import ts.studyhelper.R

class HomeFragment : Fragment(R.layout.fragment_home) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Элементы интерфейса
        val dateText: TextView = view.findViewById(R.id.todayDate)
        val greetingText: TextView = view.findViewById(R.id.greetingText)
        val nextTaskText: TextView = view.findViewById(R.id.nextTaskText)


        val quoteText : TextView = view.findViewById(R.id.quoteTextLinear)
        val quoteAut : TextView = view.findViewById(R.id.quoteAuthor)

        // Установка текущей даты
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale("ru"))
        val currentDate = sdf.format(Date())
        dateText.text = currentDate

        // Приветствие
        greetingText.text = getGreeting()

        // Пока нет задач — заглушка
        nextTaskText.text = "Пока задач нет"

        quoteText.text = quoteTextMSG()
        quoteAut.text = quoteAuthor()
    }

    // Определяем приветствие по времени суток
    private fun getGreeting(): String {
        val name = "Радмир"
        return "Добро Пожаловать $name!"
    }

    // Функция переключения вкладок через BottomNavigation
    private fun navigateTo(menuId: Int) {
        activity?.findViewById<com.google.android.material.bottomnavigation.BottomNavigationView>(R.id.bottomNav)
            ?.selectedItemId = menuId
    }

    private fun quoteTextMSG() : String{
        val text = "«Ученье — свет, а неученье — тьма»"
        return text
    }

    private fun quoteAuthor() : String{
        val author = "(Александр Суворов)"
        return author
    }
}