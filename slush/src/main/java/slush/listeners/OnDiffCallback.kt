package slush.listeners

import android.view.View

interface OnDiffCallback<T> {
    fun areItemsTheSame(newItem: T, oldItem: T): Boolean
    fun areContentsTheSame(newItem: T, oldItem: T): Boolean? = null
}
