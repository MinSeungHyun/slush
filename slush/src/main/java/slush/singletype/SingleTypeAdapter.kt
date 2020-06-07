package slush.singletype

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import slush.AdapterItemListEditor
import slush.listeners.OnBindDataListener
import slush.listeners.OnBindListener
import slush.listeners.OnItemClickListener

class SingleTypeAdapter<ITEM> internal constructor(
    private val context: Context,
    private val layoutId: Int,
    private val onBindListener: OnBindListener<ITEM>?,
    private val onBindDataListener: OnBindDataListener<ITEM>?,
    private val onItemClickListener: OnItemClickListener?,
    internal var items: List<ITEM>
) : RecyclerView.Adapter<BaseSingleTypeViewHolder<ITEM>>() {
    internal val itemListManager by lazy { AdapterItemListEditor(this) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseSingleTypeViewHolder<ITEM> {
        val inflater = LayoutInflater.from(context)

        return if (onBindDataListener != null) {
            val binding = DataBindingUtil.inflate<ViewDataBinding>(inflater, layoutId, parent, false)
            SingleTypeBindingViewHolder(binding, onBindDataListener, onItemClickListener)
        } else {
            val view = inflater.inflate(layoutId, parent, false)
            SingleTypeViewHolder(view, onBindListener, onItemClickListener)
        }
    }

    override fun onBindViewHolder(holder: BaseSingleTypeViewHolder<ITEM>, position: Int) {
        holder.bind(position, items[position])
    }

    override fun getItemCount(): Int = items.size
}
