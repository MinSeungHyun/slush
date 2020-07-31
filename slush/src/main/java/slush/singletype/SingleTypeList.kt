package slush.singletype

import slush.utils.ListWrapper

sealed class SingleTypeList<ITEM> : ListWrapper<ITEM> {
    class NormalList<ITEM>(override var items: List<ITEM>) : SingleTypeList<ITEM>()
}