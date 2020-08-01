package slush

import slush.singletype.SingleTypeAdapter
import slush.singletype.SingleTypeList
import slush.utils.ControllableObserver
import slush.utils.SlushException

class AdapterAppliedResult<ITEM>(private val adapter: SingleTypeAdapter<ITEM>) {
    val itemListEditor: ItemListEditor<ITEM> by lazy {
        if (adapter.singleTypeList is SingleTypeList.NormalList) AdapterItemListEditor(adapter)
        else throw SlushException.ItemListEditorUnavailableException()
    }

    internal fun getObserver(): ControllableObserver {
        if (adapter.singleTypeList is SingleTypeList.ObservableItemsList) return adapter.singleTypeList
        else throw SlushException.ObserveControllerUnavailableException()
    }
}
