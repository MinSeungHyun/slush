package slush

import slush.utils.ListWrapper

open class BaseItemListEditor<ITEM>(private val list: ListWrapper<ITEM>) : ItemListEditor<ITEM> {
    override fun addItem(item: ITEM) = editList {
        add(item)
    }

    override fun addItem(position: Int, item: ITEM) = editList {
        add(position, item)
    }

    override fun addItems(items: List<ITEM>) = editList {
        addAll(items)
    }

    override fun addItems(startPosition: Int, items: List<ITEM>) = editList {
        addAll(startPosition, items)
    }

    override fun removeItem(item: ITEM): Int {
        val index = list.items.indexOf(item)
        if (index >= 0) editList { removeAt(index) }
        return index
    }

    override fun removeItem(position: Int) = editList {
        removeAt(position)
    }

    override fun removeItems(startPosition: Int, itemCount: Int) = editList {
        subList(startPosition, startPosition + itemCount).clear()
    }

    override fun changeItem(position: Int, item: ITEM) = editList {
        set(position, item)
    }

    override fun changeItems(startPosition: Int, items: List<ITEM>) = editList {
        subList(startPosition, startPosition + items.size).clear()
        addAll(startPosition, items)
    }

    override fun changeAll(items: List<ITEM>) = editList {
        clear()
        addAll(items)
    }

    override fun moveItem(fromPosition: Int, toPosition: Int) = editList {
        val removedItem = removeAt(fromPosition)
        add(toPosition, removedItem)
    }

    private inline fun editList(block: MutableList<ITEM>.() -> Unit) {
        list.items = list.items.toMutableList().apply(block)
    }
}
