package slush

interface ItemListEditor<ITEM> {
    fun addItem(item: ITEM)
    fun addItemAt(position: Int, item: ITEM)
    fun addItemRange(startPosition: Int, items: List<ITEM>)

    fun removeItem(item: ITEM)
    fun removeItemAt(position: Int)
    fun removeItemRange(startPosition: Int, itemCount: Int)

    fun changeItemAt(position: Int, item: ITEM)
    fun changeAll(items: List<ITEM>)
    fun changeItemRange(startPosition: Int, items: List<ITEM>)

    fun moveItem(fromPosition: Int, toPosition: Int)
}
