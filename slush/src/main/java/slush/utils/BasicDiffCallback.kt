package slush.utils

class BasicDiffCallback<ITEM> : SlushDiffCallback<ITEM>() {
    private var oldItems = listOf<ITEM>()
    private var newItems = listOf<ITEM>()

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
