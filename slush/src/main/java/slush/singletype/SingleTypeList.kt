package slush.singletype

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import slush.utils.ListWrapper
import slush.utils.MutableListWrapper

sealed class SingleTypeList<ITEM> : ListWrapper<ITEM> {

    class NormalList<ITEM>(private var items: List<ITEM>) : SingleTypeList<ITEM>(), MutableListWrapper<ITEM> {
        override fun getItems(): List<ITEM> = items

        override fun setItems(items: List<ITEM>) {
            this.items = items
        }
    }

    class LiveDataList<ITEM>(
        private val itemsLiveData: LiveData<List<ITEM>>,
        private val lifecycleOwner: LifecycleOwner
    ) : SingleTypeList<ITEM>() {

        internal fun observe(observer: (List<ITEM>) -> Unit) {
            itemsLiveData.observe(lifecycleOwner, Observer(observer))
        }

        override fun getItems(): List<ITEM> = itemsLiveData.value ?: listOf()
    }
}