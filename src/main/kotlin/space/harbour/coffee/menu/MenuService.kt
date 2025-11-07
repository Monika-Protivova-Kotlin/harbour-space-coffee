package space.harbour.coffee.menu

import org.springframework.stereotype.Service
import java.util.UUID

@Service
class MenuService(private val menuRepository: MenuRepository) {

    fun getAllMenuItems(): List<MenuItem> = menuRepository.findAll()

    fun getMenuItemById(id: String): MenuItem? = menuRepository.findById(id)

    fun addMenuItem(request: CreateMenuItemRequest): MenuItem {
        val newId = UUID.randomUUID().toString()
        val newItem = MenuItem(
            id = newId,
            name = request.name,
            description = request.description,
            price = request.price,
            category = request.category
        )
        return menuRepository.save(newItem)
    }

    fun updateMenuItem(id: String, updatedItem: MenuItem): MenuItem? {
        return if (menuRepository.findById(id) != null) {
            val itemToSave = updatedItem.copy(id = id)
            menuRepository.save(itemToSave)
        } else {
            null
        }
    }

    fun deleteMenuItem(id: String): Boolean = menuRepository.deleteById(id)
}
