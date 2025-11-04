package space.harbour.coffee.menu

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/menu")
class MenuController {
    private val menuItems = mutableListOf(
        MenuItem(
            id = 1,
            name = "Matcha Latte",
            description = "Smooth Japanese matcha blended with creamy steamed milk for a balanced, earthy sweetness.",
            price = 60.0,
            category = "Tea",
            size = "Medium"
        ),
        MenuItem(
            id = 2,
            name = "Americano",
            description = "Rich espresso diluted with hot water, creating a bold yet smooth coffee flavor.",
            price = 55.0,
            category = "Coffee",
            size = "Large"
        )
    )

    @GetMapping
    fun getAllItems(
        @RequestParam(required = false) category: String?,
        @RequestParam(required = false) maxPrice: Double?
    ): List<MenuItem> {
        return menuItems.filter {
            (category == null || it.category.equals(category, ignoreCase = true)) &&
                    (maxPrice == null || it.price <= maxPrice)
        }
    }

    @GetMapping("/{id}")
    fun getItemById(@PathVariable id: Int): ResponseEntity<MenuItem> {
        val item = menuItems.find { it.id == id }
        return if (item != null) ResponseEntity.ok(item)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }

    @PostMapping
    fun addItem(@RequestBody newItem: MenuItem): ResponseEntity<MenuItem> {
        val newId = (menuItems.maxOfOrNull { it.id } ?: 0) + 1
        val itemWithId = newItem.copy(id = newId)
        menuItems.add(itemWithId)
        return ResponseEntity(itemWithId, HttpStatus.CREATED)
    }

    @PutMapping("/{id}")
    fun updateItem(
        @PathVariable id: Int,
        @RequestBody updatedItem: MenuItem
    ): ResponseEntity<MenuItem> {
        val index = menuItems.indexOfFirst { it.id == id }
        return if (index != -1) {
            menuItems[index] = updatedItem
            ResponseEntity(updatedItem, HttpStatus.OK)
        } else {
            ResponseEntity(HttpStatus.NOT_FOUND)
        }
    }


    @DeleteMapping("/{id}")
    fun deleteItem(@PathVariable id: Int): ResponseEntity<Void> {
        val removedItem = menuItems.removeIf { it.id == id }
        return if (removedItem) ResponseEntity(HttpStatus.NO_CONTENT)
        else ResponseEntity(HttpStatus.NOT_FOUND)
    }
}


