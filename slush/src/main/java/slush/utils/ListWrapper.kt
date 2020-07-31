package slush.utils

interface ListWrapper<ITEM> {
    fun getItems(): List<ITEM>
}

interface MutableListWrapper<ITEM> : ListWrapper<ITEM> {
    fun setItems(items: List<ITEM>)
}
