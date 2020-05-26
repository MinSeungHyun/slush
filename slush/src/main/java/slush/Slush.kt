package slush

import android.view.View
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import slush.listeners.OnBindListener
import slush.utils.SlushException

class Slush {
    class SingleTypeAdapter<ITEM> {
        private var layoutId: Int? = null
        private var items: List<ITEM>? = null
        private var layoutManager: RecyclerView.LayoutManager? = null
        private var onBindListener: OnBindListener<ITEM>? = null

        fun setItemLayout(@LayoutRes layoutId: Int) = apply {
            this.layoutId = layoutId
        }

        fun setItems(items: List<ITEM>) = apply {
            this.items = items
        }

        fun setLayoutManager(layoutManager: RecyclerView.LayoutManager) = apply {
            this.layoutManager = layoutManager
        }

        fun setOnBindListener(listener: OnBindListener<ITEM>) {
            onBindListener = listener
        }

        fun setOnBindListener(listener: View.(ITEM) -> Unit) = apply {
            setOnBindListener(object : OnBindListener<ITEM> {
                override fun View.onBind(item: ITEM) {
                    listener(item)
                }
            })
        }

        fun into(recyclerView: RecyclerView) {
            recyclerView.layoutManager = layoutManager

            val adapter = BaseAdapter(
                recyclerView.context,
                layoutId ?: throw SlushException.LayoutIdNotFoundException(),
                items ?: throw SlushException.ItemsNotFoundException(),
                onBindListener
            )
            recyclerView.adapter = adapter
        }
    }
}
