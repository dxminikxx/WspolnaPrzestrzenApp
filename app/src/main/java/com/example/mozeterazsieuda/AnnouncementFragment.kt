import AnnouncementDBHelper
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.mozeterazsieuda.R
import java.util.*

class AnnouncementFragment : Fragment() {

    private lateinit var announcementDBHelper: AnnouncementDBHelper
    private lateinit var etAnnouncementDate: EditText
    private lateinit var selectedDate: Calendar
    private lateinit var etTitle: EditText


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_announcement, container, false)
        val btnAdd = view.findViewById<Button>(R.id.btnAddAnnouncement)
        etAnnouncementDate = view.findViewById(R.id.announcementDate)
        etTitle = view.findViewById(R.id.announcementName)

        announcementDBHelper = AnnouncementDBHelper(requireContext())

        etAnnouncementDate.setOnClickListener {
            showDatePicker()
        }

        btnAdd.setOnClickListener {
            val date = etAnnouncementDate.text.toString()
            val title = etTitle.text.toString()

            if (date.isNotEmpty() && title.isNotEmpty()) {
                val success = announcementDBHelper.addAnnouncement(date, title)

                if (success) {
                    Toast.makeText(requireContext(), "Poprawnie dodano informację", Toast.LENGTH_SHORT).show()
                    clearFields()
                } else {
                    Toast.makeText(requireContext(), "Wystąpił błąd podczas dodawania informacji", Toast.LENGTH_SHORT).show()
                    // Możesz tutaj dodać kod, który zostanie wykonany w przypadku błędu dodawania informacji
                }
            } else {
                Toast.makeText(requireContext(), "Należy wypełnić wszystkie pola", Toast.LENGTH_SHORT).show()
            }
        }

        return view
    }

    private fun showDatePicker() {
        val currentDate = Calendar.getInstance()
        val year = currentDate.get(Calendar.YEAR)
        val month = currentDate.get(Calendar.MONTH)
        val dayOfMonth = currentDate.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, dayOfMonth ->
                selectedDate = Calendar.getInstance()
                selectedDate.set(selectedYear, selectedMonth, dayOfMonth)
                updateAnnouncementDate()
            },
            year,
            month,
            dayOfMonth
        )

        datePickerDialog.datePicker.minDate = currentDate.timeInMillis
        datePickerDialog.show()
    }

    private fun updateAnnouncementDate() {
        val myFormat = "dd-MM-yyyy"
        val sdf = java.text.SimpleDateFormat(myFormat, Locale.getDefault())
        etAnnouncementDate.setText(sdf.format(selectedDate.time))
    }

    private fun clearFields() {
        etAnnouncementDate.text.clear()
        etTitle.text.clear()
    }
}
