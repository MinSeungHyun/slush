package slush

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import slush.listeners.OnBindListener
import slush.listeners.OnItemClickListener

class SingleTypeAdapter<ITEM> internal constructor(
    private val context: Context,
    private val layoutId: Int,
    private val onBindListener: OnBindListener<ITEM>?,
    private val onItemClickListener: OnItemClickListener?,
    internal var items: List<ITEM>
) : RecyclerView.Adapter<BaseViewHolder<ITEM>>() {
    internal val itemListManager by lazy { AdapterItemListEditor(this) }

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
