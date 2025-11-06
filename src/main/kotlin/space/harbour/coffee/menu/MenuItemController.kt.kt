package space.harbour.coffee.menu

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/menu")
class MenuController(private val menuService: MenuService) {

    @GetMapping
    fun getAllItems(
        @RequestParam(required = false) minPrice: Double?,
        @RequestParam(required = false) maxPrice: Double?
    ): ResponseEntity<List<MenuItem>> {
        val items = menuService.findAll(minPrice, maxPrice)
        return ResponseEntity.ok(items)
    }

    @GetMapping("/{id}")
    fun getItemById(@PathVariable id: Long): ResponseEntity<MenuItem> {
        return menuService.findById(id)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

    @PostMapping
    fun createItem(@RequestBody request: CreateMenuItemRequest): ResponseEntity<MenuItem> {
        val createdItem = menuService.create(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(createdItem)
    }

    @PutMapping("/{id}")
    fun updateItem(
        @PathVariable id: Long,
        @RequestBody request: UpdateMenuItemRequest
    ): ResponseEntity<MenuItem> {
        return menuService.update(id, request)
            ?.let { ResponseEntity.ok(it) }
            ?: ResponseEntity.status(HttpStatus.NOT_FOUND).build()
    }

    @DeleteMapping("/{id}")
    fun deleteItem(@PathVariable id: Long): ResponseEntity<Void> {
        return if (menuService.delete(id)) {
            ResponseEntity.status(HttpStatus.NO_CONTENT).build()
        } else {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}