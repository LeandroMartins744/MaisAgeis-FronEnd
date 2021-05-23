package com.maisageis.ocorrencias.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.model.response.CategoryResponse

class AdapterCategoryHome(
    private val context: Context,
    private val data: List<CategoryResponse>,
    private var itemSelected: (CategoryResponse) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val mView = inflater.inflate(R.layout.item_card_category, parent, false)

        return DefaultViewHolder(mView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]

        val dfVH = holder as DefaultViewHolder

        dfVH.title.setText(item.name)
        dfVH.total.setText(item.total.toString())
        dfVH.description.setText(item.reference)

    }

    override fun getItemCount(): Int {
        return data.size
    }

    private inner class DefaultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mView = itemView.rootView

        val const: ConstraintLayout = itemView.findViewById(R.id.cardId)
        val title: TextView = itemView.findViewById(R.id.cardTitleText)
        val total: TextView = itemView.findViewById(R.id.cardTotal)
        val description: TextView = itemView.findViewById(R.id.cardDescription)
        init {
            mView.setOnClickListener {
                val position = adapterPosition
                val item = data[position]


               // val item = data[position]

                itemSelected.invoke(item)
            }
        }
    }
}