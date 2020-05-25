package slush

import android.view.View

interface OnBindListener<T> {
    fun View.onBind(item: T)
}
