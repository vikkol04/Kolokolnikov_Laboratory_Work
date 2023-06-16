package com.example.kolokolnikov_laboratory_work

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import com.example.kolokolnikov_laboratory_work.databinding.ActivitySecondLwBinding
import com.example.kolokolnikov_labotatory_work.constance_lw.ConstanceLw

class ActivitySecond_lw : AppCompatActivity() {
    lateinit var bindingS: ActivitySecondLwBinding
    private var correct = false
    private var red_light = ContextCompat.getColor(this, R.color.red_light)
    private var white = ContextCompat.getColor(this, R.color.white)


    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        bindingS = ActivitySecondLwBinding.inflate(layoutInflater)
        setContentView(bindingS.root)

        bindingS.buttonCreateVisit2.setOnClickListener {
            correct = check()
            if (correct){
                val visitData = Visit_lw(bindingS.fieldDocName.text.toString(), bindingS.fieldPatName.text.toString(), bindingS.fieldDate.text.toString())
                val intentWithData = Intent().putExtra(ConstanceLw.INTENT_ANSWER, visitData)
                setResult(RESULT_OK, intentWithData)
                finish()
            }

        }
        bindingS.spinner.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, ConstanceLw.SPINNER_DOCTORS)
        bindingS.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                bindingS.fieldDocName.text = ConstanceLw.SPINNER_DOCTORS.get(position)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                bindingS.fieldDocName.text = "Выбрать врача"
            }
        }
    }

    fun check(): Boolean = with(bindingS){
        var ch = true

        if (fieldDocName.text.toString() == "Выбрать врача") {
            ch = false
            fieldDocName.setTextColor(red_light)
        } else fieldDocName.setTextColor(white)

        if (fieldPatName.text.toString().isEmpty()){
            ch = false
            fieldPatName.setTextColor(red_light)
        } else fieldPatName.setTextColor(white)

        if (fieldDate.text.toString().isEmpty() ) {
            ch = false
            fieldDate.setTextColor(red_light)
        } else fieldDate.setTextColor(white)

        for (i in fieldDate.text.toString()){
            if (i !in "0123456789"){
                ch = false
                fieldDate.setTextColor(red_light)
            }
        }
        return ch
    }
}