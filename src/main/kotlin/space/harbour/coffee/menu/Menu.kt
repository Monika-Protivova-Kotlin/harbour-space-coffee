package space.harbour.coffee.menu

data class Menu(
    val name: String,
    val price: Double,
    val description: String,
    val category: String,
    val sizeOptions: List<String>,
    val instances: Int
)