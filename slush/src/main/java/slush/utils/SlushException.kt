package slush.utils

sealed class SlushException(message: String) : Exception(message) {
    class LayoutIdNotFoundException : SlushException("Method setItemLayout() not called")
}
