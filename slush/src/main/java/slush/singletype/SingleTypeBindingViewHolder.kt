package slush.singletype

import androidx.databinding.ViewDataBinding
import slush.listeners.OnBindDataListener
import slush.listeners.OnItemClickListener

class SingleTypeBindingViewHolder<ITEM>(
    private val binding: ViewDataBinding,
    private val onBindDataListener: OnBindDataListener<ITEM>?,
    private val onItemClickListener: OnItemClickListener?
) : BaseSingleTypeViewHolder<ITEM>(binding.root) {

    override fun bind(item: ITEM) {
        onBindDataListener?.onBind(binding, item)

        onItemClickListener ?: return
        binding.root.setOnClickListener {
            onItemClickListener.onItemClick(it, layoutPosition)
        }
    }
}
