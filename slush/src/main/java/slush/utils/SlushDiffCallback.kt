package slush.utils

import androidx.recyclerview.widget.DiffUtil

abstract class SlushDiffCallback<ITEM> : DiffUtil.Callback() {
    abstract fun setOldItems(oldItems: List<ITEM>)
    abstract fun setNewItems(newItems: List<ITEM>)
}
