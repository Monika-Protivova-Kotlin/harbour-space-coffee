package space.harbour.coffee.menu

import java.math.BigDecimal

data class MenuItem(
    val id: Long,
    val name: String,
    val description: String,
    val price: BigDecimal,
    val category: Category,
    val size: Size = Size.MEDIUM,
    val isAvailable: Boolean = true,
    val imageUrl: String? = null
)

enum class Category {
    COFFEE,
    TEA,
    PASTRY,
    SANDWICH,
    DESSERT,
    OTHER
}

enum class Size {
    SMALL,
    MEDIUM,
    LARGE
}

// DTO for creating/updating menu items (without ID)
data class CreateMenuItemRequest(
    val name: String,
    val description: String,
    val price: BigDecimal,
    val category: Category,
    val size: Size = Size.MEDIUM,
    val isAvailable: Boolean = true,
    val imageUrl: String? = null
)