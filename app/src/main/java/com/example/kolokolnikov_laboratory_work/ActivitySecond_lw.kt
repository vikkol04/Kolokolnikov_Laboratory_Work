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

    override fun onCreate(savedInstanceState: Bundle?)  {
        super.onCreate(savedInstanceState)
        var errorFieldColor = ContextCompat.getColor(this, R.color.red_light)
        var correctFieldColor = ContextCompat.getColor(this, R.color.white)
        bindingS = ActivitySecondLwBinding.inflate(layoutInflater)
        setContentView(bindingS.root)

        bindingS.buttonCreateVisit2.setOnClickListener {
            correct = check(errorFieldColor, correctFieldColor)
            if (correct){
                var dotDate = bindingS.fieldDate.text.toString()
                dotDate = "${dotDate.substring(0,2)}.${dotDate.substring(2,4)}.${dotDate.substring(4,6)}"
                val visitData = Visit_lw(bindingS.fieldDocName.text.toString(), bindingS.fieldPatName.text.toString(), dotDate)
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
                bindingS.fieldDocName.text = "—"
            }
        }
    }

    fun check(errorFieldColor: Int, correctFieldColor: Int): Boolean = with(bindingS){
        var ch = true

        if (fieldPatName.text.toString().isEmpty()){
            ch = false
            fieldPatName.setBackgroundColor(errorFieldColor)
        } else fieldPatName.setBackgroundColor(correctFieldColor)

        if (fieldDate.text.toString().isEmpty() ) {
            ch = false
            fieldDate.setBackgroundColor(errorFieldColor)
        } else fieldDate.setBackgroundColor(correctFieldColor)

        for (i in fieldDate.text.toString()){
            if (i !in "0123456789") {
                ch = false
                fieldDate.setBackgroundColor(errorFieldColor)
                break
            }
        }
        if (fieldDate.text.toString().length < 6){
            ch = false
            fieldDate.setBackgroundColor(errorFieldColor)
        }
        return ch
    }

    fun closeActivitySecondLw(view: View){
        finish()
    }
}