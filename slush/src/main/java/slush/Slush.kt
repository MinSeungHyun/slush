package slush

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import slush.listeners.OnBindListener
import slush.listeners.OnItemClickListener
import slush.singletype.SingleTypeAdapter
import slush.utils.SlushException

class Slush private constructor() {
    data class SingleType<ITEM>(
        private var layoutId: Int? = null,
        private var items: List<ITEM>? = null,
        private var layoutManager: RecyclerView.LayoutManager? = null,
        private var onBindListener: OnBindListener<ITEM>? = null,
        private var onItemClickListener: OnItemClickListener? = null
    ) {

        fun setItemLayout(@LayoutRes layoutId: Int) = apply {
            this.layoutId = layoutId
        }

        fun setItems(items: List<ITEM>) = apply {
            this.items = items
        }

        fun setLayoutManager(layoutManager: RecyclerView.LayoutManager) = apply {
            this.layoutManager = layoutManager
        }

        fun onBind(listener: OnBindListener<ITEM>) = apply {
            onBindListener = listener
        }

        fun onBind(listener: (View, ITEM) -> Unit) = onBind(
            object : OnBindListener<ITEM> {
                override fun onBind(view: View, item: ITEM) {
                    listener(view, item)
                }
            })

        fun onItemClick(listener: OnItemClickListener) = apply {
            onItemClickListener = listener
        }

        fun onItemClick(listener: (View, Int) -> Unit) = onItemClick(
            object : OnItemClickListener {
                override fun onItemClick(clickedView: View, position: Int) {
                    listener(clickedView, position)
                }
            })

        fun into(recyclerView: RecyclerView): AdapterAppliedResult<ITEM> {
            recyclerView.layoutManager = layoutManager

            val adapter = SingleTypeAdapter(
                recyclerView.context,
                layoutId ?: throw SlushException.LayoutIdNotFoundException(),
                onBindListener,
                onItemClickListener,
                items ?: listOf()
            )
            recyclerView.adapter = adapter

            return AdapterAppliedResult(adapter)
        }
    }
}
