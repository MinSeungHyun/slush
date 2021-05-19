package slush.utils

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SlushSwipeActions<ITEM>() {
    var swipeDirs: Int = -1

    var propertiesRight: Properties? = null
    var actionRight: SwipeAction<ITEM>? = null

    var propertiesLeft: Properties? = null
    var actionLeft: SwipeAction<ITEM>? = null

    fun setRight(properties: Properties, action: SwipeAction<ITEM>) = apply {
        propertiesRight = properties
        actionRight = action

        addSwipeDir(ItemTouchHelper.RIGHT)
    }

    fun setLeft(properties: Properties, action: SwipeAction<ITEM>) = apply {
        propertiesLeft = properties
        actionLeft = action

        addSwipeDir(ItemTouchHelper.LEFT)
    }

    private fun addSwipeDir(dir: Int) {
        if (swipeDirs < 0) {
            swipeDirs = dir
        } else {
            swipeDirs = swipeDirs or dir
        }
    }

    interface SwipeAction<ITEM> {
        fun onSwipe(position: Int, item: ITEM)
    }

    class Properties (
        val icon: Int? = null,
        val color: Int? = null,
        val text: String? = null,
        val textColor: Int? = null,
        val textSizeDp: Float? = null
    )
}