package space.harbour.coffee.menu

import org.springframework.stereotype.Service
import java.util.concurrent.atomic.AtomicLong

@Service
class MenuService {

    private val menuItems = mutableListOf<MenuItem>()
    private val idGenerator = AtomicLong(0)

    init {
        // Pre-populate with sample items
        addInitialItems()
    }

    private fun addInitialItems() {
        menuItems.addAll(
            listOf(
                MenuItem(
                    id = generateId(),
                    name = "Cappuccino",
                    description = "Espresso with steamed milk and foam",
                    price = 4.50,
                    category = MenuCategory.COFFEE,
                    size = Size.MEDIUM,
                    isAvailable = true,
                    ingredients = listOf("Espresso", "Milk", "Foam")
                ),
                MenuItem(
                    id = generateId(),
                    name = "Croissant",
                    description = "Buttery French pastry",
                    price = 3.00,
                    category = MenuCategory.PASTRY,
                    size = Size.MEDIUM,
                    isAvailable = true,
                    ingredients = listOf("Flour", "Butter", "Yeast")
                )
            )
        )
    }

    fun findAll(minPrice: Double? = null, maxPrice: Double? = null): List<MenuItem> {
        var result = menuItems.toList()

        minPrice?.let { min ->
            result = result.filter { it.price >= min }
        }

        maxPrice?.let { max ->
            result = result.filter { it.price <= max }
        }

        return result
    }

    fun findById(id: Long): MenuItem? = menuItems.firstOrNull { it.id == id }

    fun create(request: CreateMenuItemRequest): MenuItem {
        val newItem = MenuItem(
            id = generateId(),
            name = request.name,
            description = request.description,
            price = request.price,
            category = request.category,
            size = request.size,
            isAvailable = request.isAvailable,
            ingredients = request.ingredients
        )

        menuItems.add(newItem)
        return newItem
    }

    fun update(id: Long, request: UpdateMenuItemRequest): MenuItem? {
        val existingItem = findById(id) ?: return null

        val updatedItem = existingItem.copy(
            name = request.name ?: existingItem.name,
            description = request.description ?: existingItem.description,
            price = request.price ?: existingItem.price,
            category = request.category ?: existingItem.category,
            size = request.size ?: existingItem.size,
            isAvailable = request.isAvailable ?: existingItem.isAvailable,
            ingredients = request.ingredients ?: existingItem.ingredients
        )

        val index = menuItems.indexOfFirst { it.id == id }
        menuItems[index] = updatedItem

        return updatedItem
    }

    fun delete(id: Long): Boolean = menuItems.removeIf { it.id == id }

    private fun generateId(): Long = idGenerator.incrementAndGet()
}