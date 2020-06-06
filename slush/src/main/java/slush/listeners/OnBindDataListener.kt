package slush.listeners

import androidx.databinding.ViewDataBinding

interface OnBindDataListener<ITEM> {
    fun onBind(binding: ViewDataBinding, item: ITEM)
}
