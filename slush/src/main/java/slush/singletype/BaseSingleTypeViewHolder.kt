package slush.singletype

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseSingleTypeViewHolder<ITEM>(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(position: Int, item: ITEM)
}
