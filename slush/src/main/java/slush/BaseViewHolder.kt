package slush

import android.view.View
import androidx.recyclerview.widget.RecyclerView

class BaseViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
    fun <ITEM> bind(onBindListener: OnBindListener<ITEM>?, item: ITEM) {
        onBindListener?.run {
            view.onBind(item)
        }
    }
}
