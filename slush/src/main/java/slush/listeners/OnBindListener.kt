package slush.listeners

import android.view.View

interface OnBindListener<T> {
    fun onBind(view: View, item: T)
}
