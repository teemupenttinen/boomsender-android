package com.example.boomsenderandroid.listviewadapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.boomsenderandroid.R


class ListViewAdapter(
    private val dataSet: List<String>,
    private val ctx: Context
) :
    RecyclerView.Adapter<ListViewAdapter.ViewHolder>() {

    var onDeleteListItem: ((Int) -> Unit)? = null
    var onEditListItem: ((Int) -> Unit)? = null

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textView: TextView = view.findViewById(R.id.listViewItemText)
        val button: ImageButton = view.findViewById(R.id.openListItemContextMenu)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.listview_item, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
       viewHolder.textView.text = dataSet[position]
        viewHolder.button.setOnClickListener {
            val popup = PopupMenu(ctx, viewHolder.button)
            popup.inflate(R.menu.listview_menu)
            popup.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item: MenuItem? ->

                when (item!!.itemId) {
                    R.id.delete -> {
                        onDeleteListItem?.invoke(position)
                    }
                    R.id.edit -> {
                        onEditListItem?.invoke(position)
                    }

                }

                true
            })
            popup.show()
        }
    }

    override fun getItemCount() = dataSet.size

}
