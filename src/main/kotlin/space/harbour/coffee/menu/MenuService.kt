package space.harbour.coffee.menu

import space.harbour.coffee.menu.Category
import space.harbour.coffee.menu.CreateMenuItemRequest
import space.harbour.coffee.menu.MenuItem
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.util.concurrent.atomic.AtomicLong

@Service
class MenuItemService {

    private val menuItems = mutableListOf<MenuItem>()
    private val idGenerator = AtomicLong(1)

    init {
        addSampleItems()
    }

    fun getAllItems(category: Category? = null, minPrice: BigDecimal? = null, maxPrice: BigDecimal? = null): List<MenuItem> {
        var items = menuItems.toList()

        category?.let { cat ->
            items = items.filter { it.category == cat }
        }

        minPrice?.let { min ->
            items = items.filter { it.price >= min }
        }

        maxPrice?.let { max ->
            items = items.filter { it.price <= max }
        }

        return items
    }

    fun getItemById(id: Long): MenuItem? {
        return menuItems.find { it.id == id }
    }

    fun createItem(request: CreateMenuItemRequest): MenuItem {
        val newItem = MenuItem(
            id = idGenerator.getAndIncrement(),
            name = request.name,
            description = request.description,
            price = request.price,
            category = request.category,
            size = request.size,
            isAvailable = request.isAvailable,
            imageUrl = request.imageUrl
        )
        menuItems.add(newItem)
        return newItem
    }

    fun updateItem(id: Long, request: CreateMenuItemRequest): MenuItem? {
        val index = menuItems.indexOfFirst { it.id == id }
        if (index == -1) return null

        val updatedItem = MenuItem(
            id = id,
            name = request.name,
            description = request.description,
            price = request.price,
            category = request.category,
            size = request.size,
            isAvailable = request.isAvailable,
            imageUrl = request.imageUrl
        )
        menuItems[index] = updatedItem
        return updatedItem
    }

    fun deleteItem(id: Long): Boolean {
        return menuItems.removeIf { it.id == id }
    }

    private fun addSampleItems() {
        menuItems.addAll(listOf(
            MenuItem(
                id = idGenerator.getAndIncrement(),
                name = "Espresso",
                description = "Strong and bold Italian coffee",
                price = BigDecimal("2.50"),
                category = Category.COFFEE,
                size = space.harbour.coffee.menu.Size.SMALL
            ),
            MenuItem(
                id = idGenerator.getAndIncrement(),
                name = "Cappuccino",
                description = "Espresso with steamed milk and foam",
                price = BigDecimal("3.50"),
                category = Category.COFFEE,
                size = space.harbour.coffee.menu.Size.MEDIUM
            ),
            MenuItem(
                id = idGenerator.getAndIncrement(),
                name = "Croissant",
                description = "Buttery, flaky French pastry",
                price = BigDecimal("2.00"),
                category = Category.PASTRY
            ),
            MenuItem(
                id = idGenerator.getAndIncrement(),
                name = "Green Tea",
                description = "Light and refreshing Japanese green tea",
                price = BigDecimal("2.00"),
                category = Category.TEA
            )
        ))
    }
}