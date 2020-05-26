package slush

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import slush.listeners.OnBindListener

class BaseAdapter<ITEM> internal constructor(
    private val context: Context,
    private val layoutId: Int,
    private val items: List<ITEM>,
    private val onBindListener: OnBindListener<ITEM>?
) : RecyclerView.Adapter<BaseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(layoutId, parent, false)
        return BaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(onBindListener, items[position])
    }

    override fun getItemCount(): Int = items.size
}
