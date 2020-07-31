package slush

import slush.singletype.SingleTypeAdapter
import slush.singletype.SingleTypeList

class AdapterAppliedResult<ITEM>(private val adapter: SingleTypeAdapter<ITEM>) {
    val itemListEditor: ItemListEditor<ITEM> by lazy {
        if (adapter.singleTypeList is SingleTypeList.NormalList) AdapterItemListEditor(adapter)
        else throw Exception()
    }
}
