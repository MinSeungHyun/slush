package slush

interface ItemListEditor<ITEM> {
    fun addItem(item: ITEM)
    fun addItemAt(position: Int, item: ITEM)
    fun addItemRange(items: List<ITEM>)
    fun addItemRangeAt(startPosition: Int, items: List<ITEM>)

    fun removeItem(item: ITEM): Boolean
    fun removeItemAt(position: Int)
    fun removeItemRange(startPosition: Int, itemCount: Int)

    fun changeItemAt(position: Int, item: ITEM)
    fun changeItemRange(startPosition: Int, items: List<ITEM>)
    fun changeAll(items: List<ITEM>)

    fun moveItem(fromPosition: Int, toPosition: Int)
}
