package space.harbour.coffee.menu

interface MenuService {
    fun getAllMenus(category: String?): List<MenuItem>
    fun getMenuById(id: Long): MenuItem?
    fun createMenu(request: MenuItemRequest): MenuItem
    fun updateMenu(id: Long, request: MenuItemRequest): MenuItem?
    fun deleteMenu(id: Long): Boolean
}