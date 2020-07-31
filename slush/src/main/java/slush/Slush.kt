package slush

import android.view.View
import androidx.annotation.LayoutRes
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import slush.listeners.OnBindDataListener
import slush.listeners.OnBindListener
import slush.listeners.OnItemClickListener
import slush.singletype.SingleTypeAdapter
import slush.singletype.SingleTypeList
import slush.utils.SlushDiffCallback
import slush.utils.SlushException

sealed class Slush {
    data class SingleType<ITEM>(
        private var layoutId: Int? = null,
        private var singleTypeList: SingleTypeList<ITEM>? = null,
        private var layoutManager: RecyclerView.LayoutManager? = null,
        private var onBindListener: OnBindListener<ITEM>? = null,
        private var onBindDataListener: OnBindDataListener<ITEM>? = null,
        private var onItemClickListener: OnItemClickListener? = null,
        private var diffCallback: SlushDiffCallback<ITEM>? = null
    ) : Slush() {

        fun setItemLayout(@LayoutRes layoutId: Int) = apply {
            this.layoutId = layoutId
        }

        fun setItems(items: List<ITEM>) = apply {
            singleTypeList = SingleTypeList.NormalList(items)
        }

        fun setItems(items: LiveData<List<ITEM>>, lifecycleOwner: LifecycleOwner) = apply {
            singleTypeList = SingleTypeList.LiveDataList(items, lifecycleOwner)
        }

        fun setItems(items: ObservableList<ITEM>) = apply {
            singleTypeList = SingleTypeList.ObservableItemsList(items)
        }

        fun setLayoutManager(layoutManager: RecyclerView.LayoutManager) = apply {
            this.layoutManager = layoutManager
        }

        fun onBind(listener: OnBindListener<ITEM>) = apply {
            if (onBindDataListener != null) throw SlushException.BothBindMethodsCalledException()
            onBindListener = listener
        }

        fun onBind(listener: (View, ITEM) -> Unit) = onBind(
            object : OnBindListener<ITEM> {
                override fun onBind(view: View, item: ITEM) {
                    listener(view, item)
                }
            })

        fun onBindData(dataListener: OnBindDataListener<ITEM>) = apply {
            if (onBindListener != null) throw SlushException.BothBindMethodsCalledException()
            onBindDataListener = dataListener
        }

        fun <T : ViewDataBinding> onBindData(listener: (binding: T, ITEM) -> Unit) = onBindData(
            object : OnBindDataListener<ITEM> {
                override fun onBind(binding: ViewDataBinding, item: ITEM) {
                    @Suppress("UNCHECKED_CAST")
                    listener(binding as T, item)
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

        fun setDiffCallback(diffCallback: SlushDiffCallback<ITEM>) = apply {
            this.diffCallback = diffCallback
        }

        fun into(recyclerView: RecyclerView): AdapterAppliedResult<ITEM> {
            recyclerView.layoutManager = layoutManager

            val adapter = SingleTypeAdapter(
                recyclerView.context,
                layoutId ?: throw SlushException.LayoutIdNotFoundException(),
                onBindListener,
                onBindDataListener,
                onItemClickListener,
                diffCallback,
                singleTypeList ?: SingleTypeList.NormalList(listOf())
            )
            recyclerView.adapter = adapter

            return AdapterAppliedResult(adapter)
        }
    }
}
