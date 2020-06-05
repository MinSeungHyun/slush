package slush.singletype

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import slush.listeners.OnBindListener
import slush.listeners.OnItemClickListener

class SingleTypeViewHolder<ITEM>(
    private val view: View,
    private val onBindListener: OnBindListener<ITEM>?,
    private val onItemClickListener: OnItemClickListener?
) : RecyclerView.ViewHolder(view) {

    fun bind(position: Int, item: ITEM) {
        onBindListener?.onBind(view, item)

        onItemClickListener ?: return
        view.setOnClickListener {
            onItemClickListener.onItemClick(it, position)
        }
    }
}
