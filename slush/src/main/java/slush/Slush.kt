package slush

import android.graphics.Canvas
import android.util.TypedValue
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.databinding.ObservableList
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import it.xabaras.android.recyclerview.swipedecorator.RecyclerViewSwipeDecorator
import slush.listeners.OnBindDataListener
import slush.listeners.OnBindListener
import slush.listeners.OnItemClickListener
import slush.singletype.SingleTypeAdapter
import slush.singletype.SingleTypeList
import slush.utils.BasicDiffCallback
import slush.utils.SlushDiffCallback
import slush.utils.SlushException
import slush.utils.SlushSwipeActions
import java.time.format.TextStyle

sealed class Slush {
    data class SingleType<ITEM>(
        private var layoutId: Int? = null,
        private var singleTypeList: SingleTypeList<ITEM>? = null,
        private var layoutManager: RecyclerView.LayoutManager? = null,
        private var onBindListener: OnBindListener<ITEM>? = null,
        private var onBindDataListener: OnBindDataListener<ITEM>? = null,
        private var onItemClickListener: OnItemClickListener? = null,
        private var diffCallback: SlushDiffCallback<ITEM>? = null,
        private var itemTouchHelper: ItemTouchHelper? = null
    ) : Slush() {

        fun setItemLayout(@LayoutRes layoutId: Int) = apply {
            this.layoutId = layoutId
        }

        fun setItems(items: List<ITEM>) = apply {
            singleTypeList = SingleTypeList.NormalList(items)
        }

        fun setItems(
            items: LiveData<List<ITEM>>,
            lifecycleOwner: LifecycleOwner,
            useBasicDiffCallback: Boolean = true
        ) = apply {
            if (useBasicDiffCallback) setDiffCallback(BasicDiffCallback())
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

        @JvmName("onItemClickWithItem")
        fun onItemClick(listener: (View, ITEM) -> Unit) = onItemClick(
            object : OnItemClickListener {
                override fun onItemClick(clickedView: View, position: Int) {
                    singleTypeList?.getItems()?.get(position)?.let {
                        listener(clickedView, it)
                    }
                }
            })

        fun setDiffCallback(diffCallback: SlushDiffCallback<ITEM>) = apply {
            this.diffCallback = diffCallback
        }

        fun setItemTouchHelper(itemTouchHelper: ItemTouchHelper) = apply {
            this.itemTouchHelper = itemTouchHelper
        }

        fun setSwipeActions(actions: SlushSwipeActions<ITEM>) = apply {
            val simpleCallback = object : ItemTouchHelper.SimpleCallback(0, actions.swipeDirs) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.bindingAdapterPosition;
                        singleTypeList?.getItems()?.get(position)?.let {
                            when (direction) {
                                ItemTouchHelper.RIGHT -> actions.actionRight?.onSwipe(position, it)
                                ItemTouchHelper.LEFT -> actions.actionLeft?.onSwipe(position, it)
                                else -> return
                            }
                        }
                }

                override fun onChildDraw(
                    c: Canvas,
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    dX: Float,
                    dY: Float,
                    actionState: Int,
                    isCurrentlyActive: Boolean
                ) {
                    val decorator = RecyclerViewSwipeDecorator.Builder(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )

                    actions.propertiesRight?.color?.let {
                        decorator.addSwipeRightBackgroundColor(it)
                    }
                    actions.propertiesRight?.icon?.let {
                        decorator.addSwipeRightActionIcon(it)
                    }
                    actions.propertiesRight?.text?.let {
                        decorator.addSwipeRightLabel(it)
                    }
                    actions.propertiesRight?.textColor?.let {
                        decorator.setSwipeRightLabelColor(it)
                    }
                    actions.propertiesRight?.textSizeDp?.let {
                        decorator.setSwipeRightLabelTextSize(TypedValue.COMPLEX_UNIT_DIP, it)
                    }

                    actions.propertiesLeft?.color?.let {
                        decorator.addSwipeLeftBackgroundColor(it)
                    }
                    actions.propertiesRight?.icon?.let {
                        decorator.addSwipeLeftActionIcon(it)
                    }
                    actions.propertiesRight?.text?.let {
                        decorator.addSwipeLeftLabel(it)
                    }
                    actions.propertiesRight?.textColor?.let {
                        decorator.setSwipeLeftLabelColor(it)
                    }
                    actions.propertiesRight?.textSizeDp?.let {
                        decorator.setSwipeLeftLabelTextSize(TypedValue.COMPLEX_UNIT_DIP, it)
                    }

                    decorator.create().decorate()

                    super.onChildDraw(
                        c,
                        recyclerView,
                        viewHolder,
                        dX,
                        dY,
                        actionState,
                        isCurrentlyActive
                    )
                }

            }
            this.itemTouchHelper = ItemTouchHelper(simpleCallback)
        }

        fun into(recyclerView: RecyclerView): AdapterAppliedResult<ITEM> {
            recyclerView.layoutManager = layoutManager
            itemTouchHelper?.attachToRecyclerView(recyclerView)

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
