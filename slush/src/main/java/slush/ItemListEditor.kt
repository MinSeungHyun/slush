package slush

interface ItemListEditor<ITEM> {
    fun addItem(item: ITEM)
    fun addItem(position: Int, item: ITEM)
    fun addItems(items: List<ITEM>)
    fun addItems(startPosition: Int, items: List<ITEM>)

    fun removeItem(item: ITEM): Int
    fun removeItem(position: Int)
    fun removeItems(startPosition: Int, itemCount: Int)

    fun changeItem(position: Int, item: ITEM)
    fun changeItems(startPosition: Int, items: List<ITEM>)
    fun changeAll(items: List<ITEM>)

    fun moveItem(fromPosition: Int, toPosition: Int)
}
