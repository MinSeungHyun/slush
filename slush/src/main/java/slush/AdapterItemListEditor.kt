package slush

class AdapterItemListEditor<ITEM>(private val adapter: SingleTypeAdapter<ITEM>) : ItemListEditor<ITEM> {
    override fun addItem(item: ITEM) {
        editList {
            add(item)
        }
        adapter.notifyItemInserted(adapter.itemCount)
    }

    override fun addItemAt(position: Int, item: ITEM) {
        editList {
            add(position, item)
        }
        adapter.notifyItemInserted(position)
    }

    override fun addItemRange(items: List<ITEM>) {
        editList {
            addAll(items)
        }
        adapter.notifyItemRangeInserted(adapter.itemCount, items.size)
    }

    override fun addItemRangeAt(startPosition: Int, items: List<ITEM>) {
        editList {
            addAll(startPosition, items)
        }
        adapter.notifyItemRangeInserted(startPosition, items.size)
    }

    override fun removeItem(item: ITEM): Boolean {
        val index = adapter.items.indexOf(item)
        return if (index >= 0) {
            editList { removeAt(index) }
            adapter.notifyItemRemoved(index)
            true
        } else false
    }

    override fun removeItemAt(position: Int) {
        editList {
            removeAt(position)
        }
        adapter.notifyItemRemoved(position)
    }

    override fun removeItemRange(startPosition: Int, itemCount: Int) {
        editList {
            subList(startPosition, startPosition + itemCount).clear()
        }
        adapter.notifyItemRangeRemoved(startPosition, itemCount)
    }

    override fun changeItemAt(position: Int, item: ITEM) {
        editList {
            set(position, item)
        }
        adapter.notifyItemChanged(position)
    }

    override fun changeItemRange(startPosition: Int, items: List<ITEM>) {
        editList {
            subList(startPosition, startPosition + items.size).clear()
            addAll(startPosition, items)
        }
        adapter.notifyItemRangeChanged(startPosition, items.size)
    }

    override fun changeAll(items: List<ITEM>) {
        adapter.items = items
        adapter.notifyDataSetChanged()
    }

    override fun moveItem(fromPosition: Int, toPosition: Int) {
        editList {
            val removedItem = removeAt(fromPosition)
            add(toPosition, removedItem)
        }
        adapter.notifyItemMoved(fromPosition, toPosition)
    }

    private fun editList(block: MutableList<ITEM>.() -> Unit) {
        adapter.items = adapter.items.toMutableList().apply(block)
    }
}
