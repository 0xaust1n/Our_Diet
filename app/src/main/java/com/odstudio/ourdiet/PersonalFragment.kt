package com.odstudio.ourdiet

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat
import java.time.Month
import java.util.*


class PersonalFragment : Fragment() {
    var Personal = getActivity()?.getApplicationContext();

    companion object {
        fun newInstance(): PersonalFragment = PersonalFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_personal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var birthday = getView()?.findViewById(R.id.et_birthday) as TextView
        birthday.text = SimpleDateFormat("yyyy-MM-dd").format(System.currentTimeMillis())
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        birthday.setOnClickListener {
            val dpd = DatePickerDialog(
                activity,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in textbox
                    birthday.text=("" + year + "-" + (month + 1) + "-" + dayOfMonth)
                },
                year,
                month,
                day
            )
            dpd.show()
        }
    }
}