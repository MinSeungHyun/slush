package slush.listeners

import android.view.View

interface OnDiffCallback<T> {
    fun areSameItems(newItem: T, oldItem: T): Boolean
}
