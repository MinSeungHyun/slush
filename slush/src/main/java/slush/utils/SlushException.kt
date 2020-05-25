package slush.utils

sealed class SlushException(message: String) : Exception(message) {
    class ItemsNotFoundException : SlushException("Method setItems() not called")
    class LayoutIdNotFoundException : SlushException("Method setItemLayout() not called")
}
