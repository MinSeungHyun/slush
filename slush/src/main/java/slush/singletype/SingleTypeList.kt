package slush.singletype

import androidx.databinding.ObservableList
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

    class ObservableItemsList<ITEM>(private val observableList: ObservableList<ITEM>) : SingleTypeList<ITEM>() {
        override fun getItems(): List<ITEM> = observableList

        internal fun observe(adapter: SingleTypeAdapter<ITEM>) = observableList.addOnListChangedCallback(object :
            ObservableList.OnListChangedCallback<ObservableList<ITEM>>() {

            override fun onChanged(sender: ObservableList<ITEM>?) {
                adapter.notifyDataSetChanged()
            }

            override fun onItemRangeChanged(sender: ObservableList<ITEM>?, positionStart: Int, itemCount: Int) {
                adapter.notifyItemRangeChanged(positionStart, itemCount)
            }

            override fun onItemRangeInserted(sender: ObservableList<ITEM>?, positionStart: Int, itemCount: Int) {
                adapter.notifyItemRangeInserted(positionStart, itemCount)
            }

            override fun onItemRangeMoved(
                sender: ObservableList<ITEM>?,
                fromPosition: Int,
                toPosition: Int,
                itemCount: Int
            ) {
                adapter.notifyDataSetChanged()
            }

            override fun onItemRangeRemoved(sender: ObservableList<ITEM>?, positionStart: Int, itemCount: Int) {
                adapter.notifyItemRangeRemoved(positionStart, itemCount)
            }
        })
    }
}