package space.harbour.coffee.menu

import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong
import kotlin.String

@Service
class MenuServiceImpl: MenuService
{
    private val idCounter = AtomicLong(1)
    private val menuItems = mutableListOf<MenuItem>()
    init {
        menuItems.add(
            MenuItem(
                id = idCounter.getAndIncrement(),
                name = "Espresso",
                description = "Strong single-shot coffee",
                price = 2.50,
                category = "Coffee"
            )
        )
    }

    override fun getAllMenus(
        category: String?
    ): List<MenuItem> {
        return when (category){
            null -> menuItems
            else -> {
                menuItems.filter { it.category.equals(category, ignoreCase = true) }
            }
        }
    }

    override fun getMenuById(
        id: Long
    ): MenuItem? {
        return menuItems.find { it.id == id}
    }

    override fun createMenu(
         request: MenuItemRequest
    ): MenuItem {
        val newMenu = MenuItem(
            id = idCounter.getAndIncrement(),
            request.name,
            request.description,
            request.price,
            request.category
        )

        menuItems.add(newMenu)
        return newMenu
    }

    override fun updateMenu(
         id: Long,
         request: MenuItemRequest
    ): MenuItem?{
        val index = menuItems.indexOfFirst { it.id == id }

        return when (index) {
            -1 -> null
            else -> {
                val updatedMenu = MenuItem(
                    id = id,
                    name = request.name,
                    description = request.description,
                    price = request.price,
                    category = request.category
                )

                menuItems[index] = updatedMenu
                updatedMenu
            }
        }
    }

    override fun deleteMenu(
         id: Long
    ): Boolean {
        return menuItems.removeIf { it.id == id }
    }
}
