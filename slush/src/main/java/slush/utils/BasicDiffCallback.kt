package slush.utils

open class BasicDiffCallback<ITEM> : SlushDiffCallback<ITEM>() {
    internal var oldItems = listOf<ITEM>()
    internal var newItems = listOf<ITEM>()

    override fun setOldItems(oldItems: List<ITEM>) {
        this.oldItems = oldItems
    }

    override fun setNewItems(newItems: List<ITEM>) {
        this.newItems = newItems
    }

    override fun getOldListSize(): Int {
        return oldItems.size
    }

    override fun getNewListSize(): Int {
        return newItems.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] === newItems[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }
}
