package com.example.kolokolnikov_laboratory_work

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kolokolnikov_laboratory_work.databinding.VisitItemLwBinding

class VisitAdapter_lw: RecyclerView.Adapter<VisitAdapter_lw.VisitHolder>() {

    val visits = ArrayList<Visit_lw>()

    class VisitHolder(item: View): RecyclerView.ViewHolder(item){
        val binding = VisitItemLwBinding.bind(item)
        fun bind(visit: Visit_lw) = with(binding){
            textDocName.text = visit.docName
            textPatName.text = visit.patName
            textDate.text = visit.date
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VisitHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.visit_item_lw, parent, false)
        return VisitHolder(view)
    }

    override fun onBindViewHolder(holder: VisitHolder, position: Int) {
        holder.bind(visits[position])
    }

    override fun getItemCount(): Int {
        return visits.size
    }

    fun addVisit(visit: Visit_lw){
        visits.add(visit)
        notifyDataSetChanged()
    }
}