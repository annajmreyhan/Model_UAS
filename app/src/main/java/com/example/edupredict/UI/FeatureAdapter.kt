package com.example.edupredict.ui

import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.edupredict.R
import com.example.edupredict.model.FeatureItem

class FeatureAdapter(private val features: List<FeatureItem>) :
    RecyclerView.Adapter<FeatureAdapter.FeatureViewHolder>() {

    class FeatureViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val txtTitle: TextView = view.findViewById(R.id.txtFeatureTitle)
        val txtDesc: TextView = view.findViewById(R.id.txtFeatureDesc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeatureViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_feature, parent, false)
        return FeatureViewHolder(view)
    }

    override fun onBindViewHolder(holder: FeatureViewHolder, position: Int) {
        val item = features[position]
        holder.txtTitle.text = item.title
        holder.txtDesc.text = item.description
    }

    override fun getItemCount(): Int = features.size
}
