package com.example.myapplication

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import android.util.Log
import android.widget.Toast

data class Crime(var title: String = "", var date: String = "", var isSolved: Boolean = false)

class CrimeFragment : Fragment() {

    private lateinit var crime: Crime
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crime = Crime()
        Log.d("CrimeFragment", "onCreate called")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Правильное использование макета фрагмента
        val view = inflater.inflate(R.layout.activity_crime, container, false)

        titleField = view.findViewById(R.id.crime_title) as EditText
        dateButton = view.findViewById(R.id.crime_date) as Button
        solvedCheckBox = view.findViewById(R.id.crime_solved) as CheckBox

        dateButton.apply {
            text = crime.date.ifBlank { "Select date" } // Заполните текст по умолчанию
            isEnabled = true // Делаем кнопку активной
            setOnClickListener {
                // Логика для выбора даты, можно добавить DatePickerDialog
                Toast.makeText(activity, "Date button clicked", Toast.LENGTH_SHORT).show()
            }
        }
        Log.d("CrimeFragment", "onCreateView called")
        return view
    }

    override fun onStart() {
        super.onStart()
        Log.d("CrimeFragment", "onStart called")

        // Обработчик изменения текста
        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(sequence: CharSequence?, start: Int, count: Int, after: Int) {
                // ничего
            }

            override fun onTextChanged(sequence: CharSequence?, start: Int, before: Int, count: Int) {
                crime.title = sequence.toString()
            }

            override fun afterTextChanged(sequence: Editable?) {
                // ничего
            }
        }
        titleField.addTextChangedListener(titleWatcher)

        // Обработчик чекбокса
        solvedCheckBox.setOnCheckedChangeListener { _, isChecked ->
            crime.isSolved = isChecked
        }
    }
}

