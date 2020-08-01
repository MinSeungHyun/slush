package slush.singletype

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import slush.listeners.OnBindDataListener
import slush.listeners.OnBindListener
import slush.listeners.OnItemClickListener
import slush.utils.SlushDiffCallback

class SingleTypeAdapter<ITEM> internal constructor(
    private val context: Context,
    private val layoutId: Int,
    private val onBindListener: OnBindListener<ITEM>?,
    private val onBindDataListener: OnBindDataListener<ITEM>?,
    private val onItemClickListener: OnItemClickListener?,
    private val diffCallback: SlushDiffCallback<ITEM>?,
    internal val singleTypeList: SingleTypeList<ITEM>
) : RecyclerView.Adapter<BaseSingleTypeViewHolder<ITEM>>() {
    private var oldItems = singleTypeList.getItems()

    init {
        if (singleTypeList is SingleTypeList.LiveDataList) {
            singleTypeList.observe { setItems(it) }
        } else if (singleTypeList is SingleTypeList.ObservableItemsList) {
            singleTypeList.initializeObserver(getObserver())
            singleTypeList.startObserving()
        }
    }

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
        holder.bind(position, singleTypeList.getItems()[position])
    }

    override fun getItemCount(): Int = singleTypeList.getItems().size

    internal fun setItems(items: List<ITEM>) {
        if (diffCallback != null) {
            diffCallback.setOldItems(oldItems)
            diffCallback.setNewItems(items)
            DiffUtil.calculateDiff(diffCallback).dispatchUpdatesTo(this)
        } else {
            notifyDataSetChanged()
        }

        oldItems = items

        if (singleTypeList is SingleTypeList.NormalList<ITEM>)
            singleTypeList.setItems(items)
    }

    private fun getObserver() = object : ObservableList.OnListChangedCallback<ObservableList<ITEM>>() {
        override fun onChanged(sender: ObservableList<ITEM>?) {
            notifyDataSetChanged()
        }

        override fun onItemRangeChanged(sender: ObservableList<ITEM>?, positionStart: Int, itemCount: Int) {
            notifyItemRangeChanged(positionStart, itemCount)
        }

        override fun onItemRangeInserted(sender: ObservableList<ITEM>?, positionStart: Int, itemCount: Int) {
            notifyItemRangeInserted(positionStart, itemCount)
        }

        override fun onItemRangeMoved(
            sender: ObservableList<ITEM>?,
            fromPosition: Int,
            toPosition: Int,
            itemCount: Int
        ) {
            notifyDataSetChanged()
        }

        override fun onItemRangeRemoved(sender: ObservableList<ITEM>?, positionStart: Int, itemCount: Int) {
            notifyItemRangeRemoved(positionStart, itemCount)
        }
    }
}
