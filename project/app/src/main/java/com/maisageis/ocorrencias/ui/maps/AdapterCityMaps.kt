package com.maisageis.ocorrencias.ui.maps

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.maisageis.ocorrencias.R
import com.maisageis.ocorrencias.model.response.CategoryResponse
import com.maisageis.ocorrencias.model.response.getSearchStreet
import com.squareup.picasso.Picasso

class AdapterCityMaps(
    private val context: Context,
    private val data: List<getSearchStreet>,
    private var itemSelected: (getSearchStreet) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val mView = inflater.inflate(R.layout.item_card_category, parent, false)

        return DefaultViewHolder(mView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]

        val dfVH = holder as DefaultViewHolder

        dfVH.title.text = item.categoria
        dfVH.total.text = item.total.toString()
        dfVH.description.text = item.descricao
        Picasso.get().load(item.imagem).into(dfVH.image);
        //dfVH.const.background = item.image

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
        val image: ImageView = itemView.findViewById(R.id.cardImage)
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