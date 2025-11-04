package space.harbour.coffee.menu

data class MenuItem(
    val id: Int,
    val name: String,
    val description: String,
    val price: Double,
    val category: String,
    val size: String,
    val availability: Boolean = true
)