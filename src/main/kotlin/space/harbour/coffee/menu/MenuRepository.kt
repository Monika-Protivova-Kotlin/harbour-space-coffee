package space.harbour.coffee.menu

import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class MenuRepository {
    private val menuItems = ConcurrentHashMap<String, MenuItem>()

    init {
        // Seed some initial data
        val item1 = MenuItem("1", "Espresso", "Strong black coffee", 2.50, "Coffee")
        val item2 = MenuItem("2", "Latte", "Coffee with steamed milk", 4.00, "Coffee")
        val item3 = MenuItem("3", "Croissant", "Flaky pastry", 3.00, "Pastry")
        menuItems[item1.id] = item1
        menuItems[item2.id] = item2
        menuItems[item3.id] = item3
    }

    fun findAll(): List<MenuItem> = menuItems.values.toList()

    fun findById(id: String): MenuItem? = menuItems[id]

    fun save(menuItem: MenuItem): MenuItem {
        menuItems[menuItem.id] = menuItem
        return menuItem
    }

    fun deleteById(id: String): Boolean = menuItems.remove(id) != null
}
