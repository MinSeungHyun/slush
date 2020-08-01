package slush.singletype

import androidx.databinding.ObservableList
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import slush.utils.ControllableObserver
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

    class ObservableItemsList<ITEM>(private val observableList: ObservableList<ITEM>) :
        SingleTypeList<ITEM>(), ControllableObserver {

        private var observer: ObservableList.OnListChangedCallback<ObservableList<ITEM>>? = null
        private var isObserving = false

        override fun getItems(): List<ITEM> = observableList

        internal fun initializeObserver(observer: ObservableList.OnListChangedCallback<ObservableList<ITEM>>) {
            this.observer = observer
        }

        override fun startObserving() {
            if (!isObserving) {
                observableList.addOnListChangedCallback(observer)
                isObserving = true
            }
        }

        override fun stopObserving() {
            observableList.removeOnListChangedCallback(observer)
            isObserving = false
        }
    }
}

