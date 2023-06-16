package com.example.kolokolnikov_laboratory_work

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.GridLayoutManager
import com.example.kolokolnikov_laboratory_work.databinding.ActivityFirstLwBinding
import com.example.kolokolnikov_labotatory_work.constance_lw.ConstanceLw

class ActivityFirst_lw : AppCompatActivity() {
    lateinit var bindingF: ActivityFirstLwBinding
    private val adapter = VisitAdapter_lw()
    private var launcher: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingF = ActivityFirstLwBinding.inflate(layoutInflater)
        setContentView(bindingF.root)
        bindingF.apply {
            recyclerView.layoutManager = GridLayoutManager(this@ActivityFirst_lw, 3)
            recyclerView.adapter = adapter
        }
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == RESULT_OK){
                adapter.addVisit(it.data?.getSerializableExtra(ConstanceLw.INTENT_ANSWER) as Visit_lw)
            }
        }


    }

    fun startActivitySecondLw(view: View) {
        val intentTakeData = Intent(this, ActivitySecond_lw::class.java)
        launcher?.launch(intentTakeData)
    }

    fun closeActivityFirstLw(view: View){
        finish()
    }
}

