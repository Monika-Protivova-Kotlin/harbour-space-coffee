package space.harbour.coffee.menu

class MenuController {

    private val menuList = mutableListOf<Menu>()

    private val menuListHashed = mutableListOf<String>()

    fun findMenu(name: String): Menu? {
        return menuList.find { it.name.equals(name, ignoreCase = true) }
    }

    fun addMenu(menu: Menu): Boolean {
        if (menuList.any { it.name.equals(menu.name, ignoreCase = true) }) {
            return false
        }
        menuList.add(menu)
        menuListHashed.add(menu.name.hashCode().toString())
        return true
    }

    fun removeMenu(name: String): Boolean {
        val item = findMenu(name)
        return if (item != null) {
            menuList.remove(item)
            menuListHashed.remove(item.name.hashCode().toString())
            true
        } else {
            false
        }
    }

    fun getAllMenus(): List<Menu> = menuList

    fun clearAll() {
        menuList.clear()
        menuListHashed.clear()
    }

    fun filter(
        category: String? = null,
        minPrice: Double? = null,
        maxPrice: Double? = null,
        size: String? = null
    ): List<Menu> {
        return menuList.filter { menu ->
            (category == null || menu.category.equals(category, ignoreCase = true)) &&
                    (minPrice == null || menu.price >= minPrice) &&
                    (maxPrice == null || menu.price <= maxPrice) &&
                    (size == null || menu.sizeOptions.any { it.equals(size, ignoreCase = true) })
        }
    }
}
