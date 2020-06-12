package slush

import slush.singletype.SingleTypeAdapter

class AdapterItemListEditor<ITEM>(private val adapter: SingleTypeAdapter<ITEM>)
    : BaseItemListEditor<ITEM>(adapter.items) {

    override fun addItem(item: ITEM) {
        super.addItem(item)
        adapter.notifyItemInserted(adapter.itemCount)
    }

    override fun addItemAt(position: Int, item: ITEM) {
        super.addItemAt(position, item)
        adapter.notifyItemInserted(position)
    }

    override fun addItemRange(items: List<ITEM>) {
        super.addItemRange(items)
        adapter.notifyItemRangeInserted(adapter.itemCount, items.size)
    }

    override fun addItemRangeAt(startPosition: Int, items: List<ITEM>) {
        super.addItemRangeAt(startPosition, items)
        adapter.notifyItemRangeInserted(startPosition, items.size)
    }

    override fun removeItem(item: ITEM): Int {
        val index = super.removeItem(item)
        if (index >= 0) adapter.notifyItemRemoved(index)
        return index
    }

    override fun removeItemAt(position: Int) {
        super.removeItemAt(position)
        adapter.notifyItemRemoved(position)
    }

    override fun removeItemRange(startPosition: Int, itemCount: Int) {
        super.removeItemRange(startPosition, itemCount)
        adapter.notifyItemRangeRemoved(startPosition, itemCount)
    }

    override fun changeItemAt(position: Int, item: ITEM) {
        super.changeItemAt(position, item)
        adapter.notifyItemChanged(position)
    }

    override fun changeItemRange(startPosition: Int, items: List<ITEM>) {
        super.changeItemRange(startPosition, items)
        adapter.notifyItemRangeChanged(startPosition, items.size)
    }

    override fun changeAll(items: List<ITEM>) {
        super.changeAll(items)
        adapter.notifyDataSetChanged()
    }

    override fun moveItem(fromPosition: Int, toPosition: Int) {
        super.moveItem(fromPosition, toPosition)
        adapter.notifyItemMoved(fromPosition, toPosition)
    }
}
