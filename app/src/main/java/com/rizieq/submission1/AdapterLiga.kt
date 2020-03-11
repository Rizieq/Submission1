package com.rizieq.submission1

import android.content.ClipData
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.jetbrains.anko.AnkoContext
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class AdapterLiga(private var context: Context, private val data: List<Data>,val listener: (Data) -> Unit) :
    RecyclerView.Adapter<AdapterLiga.ViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterLiga.ViewHolder {
        return ViewHolder(
            MainActivity.ListItemUI().createView(
                AnkoContext.Companion.create(
                    parent.context,
                    parent
                )
            )
        )
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: AdapterLiga.ViewHolder, position: Int) {
        holder.bindItems(data[position], listener)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val tvHome = view.findViewById<TextView>(MainActivity.ListItemUI.idName)
        private val imgHome = view.findViewById<ImageView>(MainActivity.ListItemUI.idImage)
        fun bindItems(data: Data, listener: (Data) -> Unit) {

            tvHome.text = data.name
            Glide.with(itemView.context)
                .load(data.image)
                .into(imgHome)

            itemView.setOnClickListener {
                listener(data)
//                val intent = Intent(itemView.context, DetailActivity::class.java)
//                intent.putExtra(DetailActivity.INTENT_TO_DETAIL,data)
//                itemView.context.startActivity(intent)

            }

        }

    }

}