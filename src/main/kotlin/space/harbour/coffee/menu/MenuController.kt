package space.harbour.coffee.menu

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import kotlin.String


data class MenuItem(
    val id: Long,
    val name: String,
    val description: String,
    val price: Double,
    val category: String
)

data class MenuItemRequest(
    val name: String,
    val description: String,
    val price: Double,
    val category: String
)

@RestController
@RequestMapping("/api/menu")
class MenuController(
    private val menuService: MenuService
){

    @GetMapping
    fun getAllMenus(
        @RequestParam(required = false) category: String?
    ): List<MenuItem> {
        return menuService.getAllMenus(category)
    }

    @GetMapping("/{id}")
    fun getMenuById(
        @PathVariable id: Long
    ): ResponseEntity<MenuItem> {
        val menu = menuService.getMenuById(id)
        return when (menu) {
            null -> ResponseEntity.notFound().build()
            else -> ResponseEntity.ok(menu)
        }
    }

    @PostMapping
    fun createMenu(
        @RequestBody request: MenuItemRequest
    ): ResponseEntity<MenuItem> {
        val newMenu = menuService.createMenu(request)
        return ResponseEntity.status(HttpStatus.CREATED).body(newMenu)
    }

    @PutMapping("/{id}")
    fun updateMenu(
        @PathVariable id: Long,
        @RequestBody request: MenuItemRequest
    ): ResponseEntity<MenuItem>{
        val updatedMenu = menuService.updateMenu(id, request)
        return when (updatedMenu) {
            null -> ResponseEntity.notFound().build()
            else -> ResponseEntity.ok(updatedMenu)
        }
    }

    @DeleteMapping("/{id}")
    fun deleteMenu(
        @PathVariable id: Long
    ): ResponseEntity<Void> {
        val deletedMenu = menuService.deleteMenu(id)
        return when (deletedMenu) {
            true -> ResponseEntity.noContent().build()
            else -> ResponseEntity.notFound().build()
        }
    }
}
