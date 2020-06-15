package slush

import slush.singletype.SingleTypeAdapter

class AdapterAppliedResult<ITEM>(private val adapter: SingleTypeAdapter<ITEM>) {
    val itemListEditor: ItemListEditor<ITEM>
        get() = adapter.itemListEditor
}
