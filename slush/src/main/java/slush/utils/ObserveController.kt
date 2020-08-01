package slush.utils

import slush.AdapterAppliedResult

class ObserveController(private val result: AdapterAppliedResult<*>) :
    ControllableObserver by result.getObserver()
