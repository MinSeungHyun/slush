package slush

class AdapterAppliedResult<ITEM>(private val adapter: BaseAdapter<ITEM>) {
    val itemListEditor: ItemListEditor<ITEM>
        get() = adapter.itemListManager
}
