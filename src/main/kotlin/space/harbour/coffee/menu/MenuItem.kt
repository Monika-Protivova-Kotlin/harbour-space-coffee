package space.harbour.coffee.menu

enum class Category(val displayName: String) {
    MAIN("main"),
    DESSERT("dessert"),
    DRINK("drink");

    companion object {
        private val map = Category.entries.associateBy(Category::displayName)

        fun fromString(value: String): Category? {
            return map[value]
        }
    }
}

data class MenuItem(
    val id: Int,
    val name: String,
    val category: Category,
    val price: Double,
)

data class NewMenuItem(
    val name: String,
    val category: Category,
    val price: Double
)


