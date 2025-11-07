package space.harbour.coffee.menu

import space.harbour.coffee.menu.Category
import space.harbour.coffee.menu.CreateMenuItemRequest
import space.harbour.coffee.menu.MenuItem
import space.harbour.coffee.menu.MenuItemService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.math.BigDecimal

@RestController
@RequestMapping("/api/menu")
class MenuItemController(private val menuItemService: MenuItemService) {

    @GetMapping
    fun getAllMenuItems(
        @RequestParam(required = false) category: Category?,
        @RequestParam(required = false) minPrice: BigDecimal?,
        @RequestParam(required = false) maxPrice: BigDecimal?
    ): ResponseEntity<List<MenuItem>> {
        val items = menuItemService.getAllItems(category, minPrice, maxPrice)
        return ResponseEntity.ok(items)
    }


    @GetMapping("/{id}")
    fun getMenuItemById(@PathVariable id: Long): ResponseEntity<MenuItem> {
        val item = menuItemService.getItemById(id)
        return if (item != null) {
            ResponseEntity.ok(item)
        } else {
            ResponseEntity.notFound().build()
        }
    }


    @PostMapping
    fun createMenuItem(@RequestBody request: CreateMenuItemRequest): ResponseEntity<MenuItem> {
        val newItem = menuItemService.createItem(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(newItem)
    }


    @PutMapping("/{id}")
    fun updateMenuItem(
        @PathVariable id: Long,
        @RequestBody request: CreateMenuItemRequest
    ): ResponseEntity<MenuItem> {
        val updatedItem = menuItemService.updateItem(id, request)
        return if (updatedItem != null) {
            ResponseEntity.ok(updatedItem)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @DeleteMapping("/{id}")
    fun deleteMenuItem(@PathVariable id: Long): ResponseEntity<Void> {
        val deleted = menuItemService.deleteItem(id)
        return if (deleted) {
            ResponseEntity.noContent().build()
        } else {
            ResponseEntity.notFound().build()
        }
    }
}