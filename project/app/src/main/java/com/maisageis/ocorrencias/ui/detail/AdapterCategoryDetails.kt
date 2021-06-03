package com.maisageis.ocorrencias.ui.detail

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
import com.squareup.picasso.Picasso
import java.security.PrivateKey

class AdapterCategoryDetails(
    private val context: Context,
    private val data: List<CategoryResponse>,
    private var itemSelected: (CategoryResponse) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(context)
        val mView = inflater.inflate(R.layout.item_card_category_details, parent, false)

        return DefaultViewHolder(mView)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = data[position]

        val dfVH = holder as DefaultViewHolder

        dfVH.title.text = this.formatText(item.name)
        Picasso.get().load(item.image).into(dfVH.image);
    }

    private fun formatText(str: String): String{
        var result = ""
        for(item in str)
            result += item + "\n"
        return result
    }

    override fun getItemCount(): Int {
        return data.size
    }

    private inner class DefaultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mView = itemView.rootView

        val title: TextView = itemView.findViewById(R.id.detailTitleText)
        val image: ImageView = itemView.findViewById(R.id.detailImage)
        init {
            mView.setOnClickListener {
                val position = adapterPosition
                val item = data[position]

                itemSelected.invoke(item)
            }
        }
    }
}