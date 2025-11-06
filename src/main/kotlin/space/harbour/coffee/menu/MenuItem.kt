package space.harbour.coffee.menu

data class MenuItem(
    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    val category: MenuCategory,
    val size: Size,
    val isAvailable: Boolean = true,
    val ingredients: List<String> = emptyList()
)

enum class MenuCategory {
    COFFEE, TEA, PASTRY, BEVERAGE, SNACK
}

enum class Size {
    SMALL, MEDIUM, LARGE
}

data class CreateMenuItemRequest(
    val name: String,
    val description: String,
    val price: Double,
    val category: MenuCategory,
    val size: Size,
    val isAvailable: Boolean = true,
    val ingredients: List<String> = emptyList()
)

data class UpdateMenuItemRequest(
    val name: String?,
    val description: String?,
    val price: Double?,
    val category: MenuCategory?,
    val size: Size?,
    val isAvailable: Boolean?,
    val ingredients: List<String>?
)