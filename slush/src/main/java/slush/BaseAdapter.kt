package slush

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import slush.listeners.OnBindListener
import slush.listeners.OnItemClickListener

class BaseAdapter<ITEM> internal constructor(
    private val context: Context,
    private val layoutId: Int,
    private val items: List<ITEM>,
    private val onBindListener: OnBindListener<ITEM>?,
    private val onItemClickListener: OnItemClickListener?
) : RecyclerView.Adapter<BaseViewHolder<ITEM>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<ITEM> {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(layoutId, parent, false)
        return BaseViewHolder(view, onBindListener, onItemClickListener)
    }

    override fun onBindViewHolder(holder: BaseViewHolder<ITEM>, position: Int) {
        holder.bind(position, items[position])
    }

    override fun getItemCount(): Int = items.size
}
