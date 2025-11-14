Menu API – cURL Usage Guide
This document shows how to interact with the Menu REST API using curl from the command line.
Base URL:
```
http://localhost:8080/api/menu
```

1. GET All Menu Items
```
curl -X GET http://localhost:8080/api/menu
```
   Returns the full list of menu items.

2. GET Menu Item by Name
```
curl -X GET http://localhost:8080/api/menu/Espresso
```
   Returns the menu item with name Espresso (case-insensitive).

3. FILTER Menu Items (Query Parameters)
   Filter by Category\
```
curl "http://localhost:8080/api/menu/filter?category=Drinks"
```
   Filter by Price Range\
   ```
   curl "http://localhost:8080/api/menu/filter?minPrice=2.5&maxPrice=6.0"
   ```
   Filter by Size Option\
   ```
   curl "http://localhost:8080/api/menu/filter?size=Large"
   ```
   Combine Multiple Filters\
   ```
   curl "http://localhost:8080/api/menu/filter?category=Food&size=Small&minPrice=1.0&maxPrice=5.0"
   ```
   All parameters are optional and case-insensitive.

4. ADD a New Menu Item (POST)
   ```
   curl -X POST http://localhost:8080/api/menu \
   -H "Content-Type: application/json" \
   -d '{
   "name": "Latte",
   "price": 4.5,
   "description": "Creamy espresso with steamed milk",
   "category": "Drinks",
   "sizeOptions": ["Small", "Medium", "Large"],
   "instances": 1
   }'
   ```
   Response:
   "Menu added successfully!" → success
   "Menu item already exists!" → duplicate name


5. DELETE a Menu Item
```
curl -X DELETE http://localhost:8080/api/menu/Latte
```
   Response:
   "Menu deleted!" → removed
   "Menu not found!" → not found
