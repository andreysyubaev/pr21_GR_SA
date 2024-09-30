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
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.floatingactionbutton.FloatingActionButton

data class Crime(var title: String = "", var date: String = "", var isSolved: Boolean = false)

class CrimeFragment : Fragment() {

    private lateinit var crime: Crime
    private lateinit var titleField: EditText
    private lateinit var fab: FloatingActionButton
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

        val view = inflater.inflate(R.layout.activity_crime, container, false)

        titleField = view.findViewById(R.id.crime_title) as EditText
        fab = view.findViewById(R.id.crime_fab) as FloatingActionButton
        solvedCheckBox = view.findViewById(R.id.crime_solved) as CheckBox

        /*dateButton.apply {
            text = crime.date.ifBlank { "Select date" }
            isEnabled = true
            setOnClickListener {

                Toast.makeText(activity, "Date button clicked", Toast.LENGTH_SHORT).show()
            }
        }
        Log.d("CrimeFragment", "onCreateView called")*/

        fab.setOnClickListener {
            // Логика для выбора даты, можно добавить DatePickerDialog
            Snackbar.make(requireView(), "FAB clicked - choose a date", Snackbar.LENGTH_SHORT).show()
        }
        return view
    }

    override fun onStart() {
        super.onStart()

        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(sequence: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(sequence: CharSequence?, start: Int, before: Int, count: Int) {
                crime.title = sequence.toString()
            }
            override fun afterTextChanged(sequence: Editable?) {}
        }
        titleField.addTextChangedListener(titleWatcher)

        solvedCheckBox.setOnCheckedChangeListener { _, isChecked ->
            crime.isSolved = isChecked
            val message = if (isChecked) "Crime solved!" else "Crime not solved"
            Snackbar.make(requireView(), message, Snackbar.LENGTH_SHORT).show()
        }
    }
}

