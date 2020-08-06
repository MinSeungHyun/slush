package slush.singletype

import android.view.View
import slush.listeners.OnBindListener
import slush.listeners.OnItemClickListener

class SingleTypeViewHolder<ITEM>(
    private val view: View,
    private val onBindListener: OnBindListener<ITEM>?,
    private val onItemClickListener: OnItemClickListener?
) : BaseSingleTypeViewHolder<ITEM>(view) {

    override fun bind(item: ITEM) {
        onBindListener?.onBind(view, item)

        onItemClickListener ?: return
        view.setOnClickListener {
            onItemClickListener.onItemClick(it, layoutPosition)
        }
    }
}
