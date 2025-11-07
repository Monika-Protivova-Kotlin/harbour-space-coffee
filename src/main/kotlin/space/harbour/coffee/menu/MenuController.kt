package space.harbour.coffee.menu

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/menu")
class MenuController(private val menuService: MenuService) {

    @GetMapping
    fun getAllMenuItems(): List<MenuItem> = menuService.getAllMenuItems()

    @GetMapping("/{id}")
    fun getMenuItemById(@PathVariable id: String): ResponseEntity<MenuItem> {
        val menuItem = menuService.getMenuItemById(id)
        return if (menuItem != null) {
            ResponseEntity(menuItem, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @PostMapping
    fun addMenuItem(@RequestBody request: CreateMenuItemRequest): ResponseEntity<MenuItem> {
        val newItem = menuService.addMenuItem(request)
        return ResponseEntity(newItem, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateMenuItem(@PathVariable id: String, @RequestBody menuItem: MenuItem): ResponseEntity<MenuItem> {
        val updatedItem = menuService.updateMenuItem(id, menuItem)
        return if (updatedItem != null) {
            ResponseEntity(updatedItem, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteMenuItem(@PathVariable id: String): ResponseEntity<Void> {
        return if (menuService.deleteMenuItem(id)) {
            ResponseEntity(HttpStatus.NO_CONTENT)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }
}
