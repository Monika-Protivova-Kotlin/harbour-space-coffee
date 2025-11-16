package space.harbour.coffee.menu

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/menu")
class MenuRestController {

    private val controller = MenuController()

    @GetMapping
    fun getAll() = controller.getAllMenus()

    @GetMapping("/{name}")
    fun getByName(@PathVariable name: String) =
        controller.findMenu(name) ?: "Menu item not found"

    @PostMapping
    fun addMenu(@RequestBody menu: Menu): String =
        if (controller.addMenu(menu)) "Menu added successfully!"
        else "Menu item already exists!"

    @DeleteMapping("/{name}")
    fun deleteMenu(@PathVariable name: String): String =
        if (controller.removeMenu(name)) "Menu deleted!"
        else "Menu not found!"

    @GetMapping("/filter")
    fun filterMenus(
        @RequestParam(required = false) category: String?,
        @RequestParam(required = false) minPrice: Double?,
        @RequestParam(required = false) maxPrice: Double?,
        @RequestParam(required = false) size: String?
    ): List<Menu> = controller.filter(category, minPrice, maxPrice, size)
}
