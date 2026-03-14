# Metropolia-AMK-db-solutions-project

## API Endpoints

### Product
- `GET /product`: Retrieve a list of all products. The products are returned as a JSON array of ProductDto objects.
- `GET /product/{id}`: Retrieve a specific product by its ID. The product is returned as a JSON object of ProductDto.
- `GET /product/category/{id}`: Retrieve list of products by category ID. The products are returned as a JSON array of ProductDto objects.
- `GET /product/search`: Search for products by supplier and/or maximum price. The products are returned as a JSON array of ProductDto objects.
  - JSON request body: `supplier`: int (optional), `maxPrice`: double (optional)
- `POST /product/add`: Create a new product. The created product is returned as a JSON object of ProductDto.
  - JSON request body: `name`: string, `description`: string, `price`: double, `stockQuantity`: int, `suppliers`: [supplierEntity], `category`: categoryEntity
- `PUT /product/price-bulk`: Update prices for all products in a given category at once. The factor is applied as a multiplier (eg 1.1 translates to a 10% increase).
  - JSON request body: `cat`: int, `factor`: double

### Supplier
- `GET /supplier`: Retrieve a list of all suppliers.
- `GET /supplier/{id}`: Retrieve a specific supplier by its ID.

### Category
- `GET /category`: Retrieve a list of all categories.
- `GET /category/{id}`: Retrieve a specific category by its ID.
- `POST /category/add`: Create a new category.
  - JSON request body: `name`: string, `description`: string
- `DELETE /category/{id}`: Delete a category by its ID. This will also delete all products associated with the category.

### Customer
- `GET /customer`: Retrieve a list of all customers.
- `GET /customer/{id}`: Retrieve a specific customer by its ID.

### Order
- `GET /order`: Retrieve a list of all orders.
- `GET /order/{id}`: Retrieve a specific order by its ID.
- `PUT /order/{id}`: Update the status / state of an order.
  - JSON request body: `status`: string ("CANCELLED", "SHIPPED", "NEW")