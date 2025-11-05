package space.harbour.coffee.menu

import org.springframework.stereotype.Service

@Service
class MenuService(
    private val menuRepository: MenuRepository
) {

    fun getAllMenuItems(): List<MenuItem> {
        return menuRepository.findAll()
    }

    fun getMenuItems(categoryString: String): List<MenuItem> {
        val category = Category.fromString(categoryString)
            ?: throw IllegalArgumentException("Invalid category: $categoryString")

        return menuRepository.findByCategory(category)
    }


    fun getMenuItemById(id: Int): MenuItem {
        return menuRepository.findById(id)
            ?: throw NoSuchElementException("Menu item with ID $id not found.")
    }

    fun createMenuItem(newItem: NewMenuItem) {
        menuRepository.addItem(newItem)
    }

    fun updateMenuItem(id: Int, updatedItem: NewMenuItem): MenuItem {
        val category = Category.fromString(updatedItem.category.displayName)
            ?: throw IllegalArgumentException("Invalid category: ${updatedItem.category.displayName}")


        val itemToUpdate = MenuItem(
            id = id,
            name = updatedItem.name,
            category = category,
            price = updatedItem.price
        )

        menuRepository.updateItem(itemToUpdate)

        return itemToUpdate
    }

    fun deleteMenuItem(id: Int) {
        val itemToDelete = menuRepository.findById(id)
            ?: throw NoSuchElementException("Cannot delete: Menu item with ID $id not found.")
        menuRepository.deleteItem(itemToDelete)
    }
}