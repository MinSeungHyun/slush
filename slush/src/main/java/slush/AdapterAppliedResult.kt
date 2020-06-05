package slush

class AdapterAppliedResult<ITEM>(private val adapter: SingleTypeAdapter<ITEM>) {
    val itemListEditor: ItemListEditor<ITEM>
        get() = adapter.itemListManager
}
