package space.harbour.coffee.menu

data class CreateMenuItemRequest(
    val name: String,
    val description: String,
    val price: Double,
    val category: String
)
