package slush

import slush.singletype.SingleTypeAdapter

class AdapterItemListEditor<ITEM>(private val adapter: SingleTypeAdapter<ITEM>)
    : BaseItemListEditor<ITEM>(adapter.items) {

    override fun addItem(item: ITEM) {
        super.addItem(item)
        adapter.notifyItemInserted(adapter.itemCount)
    }

    override fun addItem(position: Int, item: ITEM) {
        super.addItem(position, item)
        adapter.notifyItemInserted(position)
    }

    override fun addItems(items: List<ITEM>) {
        super.addItems(items)
        adapter.notifyItemRangeInserted(adapter.itemCount, items.size)
    }

    override fun addItems(startPosition: Int, items: List<ITEM>) {
        super.addItems(startPosition, items)
        adapter.notifyItemRangeInserted(startPosition, items.size)
    }

    override fun removeItem(item: ITEM): Int {
        val index = super.removeItem(item)
        if (index >= 0) adapter.notifyItemRemoved(index)
        return index
    }

    override fun removeItem(position: Int) {
        super.removeItem(position)
        adapter.notifyItemRemoved(position)
    }

    override fun removeItems(startPosition: Int, itemCount: Int) {
        super.removeItems(startPosition, itemCount)
        adapter.notifyItemRangeRemoved(startPosition, itemCount)
    }

    override fun changeItem(position: Int, item: ITEM) {
        super.changeItem(position, item)
        adapter.notifyItemChanged(position)
    }

    override fun changeItems(startPosition: Int, items: List<ITEM>) {
        super.changeItems(startPosition, items)
        adapter.notifyItemRangeChanged(startPosition, items.size)
    }

    override fun changeAll(items: List<ITEM>) {
        adapter.setItems(items)
    }

    override fun moveItem(fromPosition: Int, toPosition: Int) {
        super.moveItem(fromPosition, toPosition)
        adapter.notifyItemMoved(fromPosition, toPosition)
    }
}
