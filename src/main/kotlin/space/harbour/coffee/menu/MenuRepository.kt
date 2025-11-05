package space.harbour.coffee.menu

import org.springframework.stereotype.Repository


@Repository
class MenuRepository {

    private var id = 0

    private val menu = mutableListOf<MenuItem>(
        MenuItem(id++, "Coffee", Category.DRINK, 1.0),
        MenuItem(id++, "IceCream", Category.DESSERT,2.0),
        MenuItem(id++, "Egg", Category.MAIN, 3.0)
    )

    fun findAll(): List<MenuItem> {
        return menu
    }

    fun findByCategory(category: Category): List<MenuItem> {
        return menu.filter { it.category == category }
    }

    fun findById(id: Int): MenuItem? {
        return menu.find { it.id == id }
    }

    fun addItem(item: NewMenuItem) {
        menu.add(MenuItem(id++, item.name, item.category, item.price))
    }

    fun updateItem(item: MenuItem) {
        val index = menu.indexOfFirst { it.id == item.id }
        if (index >= 0) {
            menu[index] = item
        } else {
            println("Warning: Item with ID ${item.id} not found for update.")
        }
    }

    fun deleteItem(item: MenuItem): Boolean {
        return menu.removeIf { it.id == item.id }
    }

}