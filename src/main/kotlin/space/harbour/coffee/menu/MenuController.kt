package space.harbour.coffee.menu

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/menu")
class MenuController(
    private val menuService: MenuService
) {

    @GetMapping("")
    fun getMenu(@RequestParam(required = false) category: String?): ResponseEntity<*> {
        return try {
            val items = if (category != null) {
                menuService.getMenuItems(category)
            } else {
                menuService.getAllMenuItems()
            }
            if (items.isEmpty()) {
                ResponseEntity.notFound().build<Void>()
            } else {
                ResponseEntity.ok(items)
            }
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @GetMapping("/{id}")
    fun getMenuItem(@PathVariable id: Int): ResponseEntity<*> {
        return try {
            val item = menuService.getMenuItemById(id)
            ResponseEntity.ok(item)
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        }
    }

    @PostMapping
    fun createMenuItem(@RequestBody newItem: NewMenuItem): ResponseEntity<MenuItem> {
        menuService.createMenuItem(newItem)
        return ResponseEntity.status(HttpStatus.CREATED).build()
    }

    @PutMapping("/{id}")
    fun updateMenuItem(@PathVariable id: Int, @RequestBody updatedItem: NewMenuItem): ResponseEntity<*> {
        return try {
            val item = menuService.updateMenuItem(id, updatedItem)
            ResponseEntity.ok(item)
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        } catch (e: IllegalArgumentException) {
            ResponseEntity.badRequest().body(e.message)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteMenuItem(@PathVariable id: Int): ResponseEntity<*> {
        return try {
            menuService.deleteMenuItem(id)
            ResponseEntity.noContent().build<Void>()
        } catch (e: NoSuchElementException) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.message)
        }
    }
}