package slush.listeners

import android.view.View

interface OnItemClickListener {
    fun onItemClick(clickedView: View, position: Int)
}
