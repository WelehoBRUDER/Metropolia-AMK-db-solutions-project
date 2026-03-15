# Web Store JPA - REST API

This project implements a REST API for a web store using Java Persistence API (JPA) and Spring Boot. The API provides endpoints for managing products, suppliers, categories, customers, and orders.

⚠️ This repository is a continuation of the disorganized and scattered repository that was only created to store implementations of daily assignments.  
The project has been refactored and moved here in a bulk commit, so the commit history is not preserved.  
 It can be found here: [https://github.com/WelehoBRUDER/Metropolia-AMK-database-solutions](https://github.com/WelehoBRUDER/Metropolia-AMK-database-solutions)

## API Endpoints

### Product

`ProductSimpleDto` is a simplified version of the Product entity, containing only the following fields: `id` (int), `name` (string), `description` (string), `stockQuantity` (int), `price` (double) and `category` (int).  
 This DTO is used when returning product information in contexts where only basic details are needed, such as in order items.

`ProductDto` is otherwise identical to _ProductSimpleDto_, but also includes a list of supplier IDs (int).

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

`SupplierSimpleDto` is a simplified version of the Supplier entity, containing only the following fields: `id` (int), `name` (string), `contactName` (string), `phone` (string) and `email` (string).  
 This DTO is used when returning supplier information in contexts where only basic details are needed, such as in product information.

`SupplierDto` is otherwise identical to _SupplierSimpleDto_, but also includes a list of products (ProductSimpleDto).

- `GET /supplier`: Retrieve a list of all suppliers as SupplierDto objects.
- `GET /supplier/{id}`: Retrieve a specific supplier by its ID as a SupplierDto object.

### Category

`CategoryDto` is a simplified version of the Category entity, containing the following fields: `id` (int), `name` (string), `description` (string) and `products` (ProductDto).  
 This DTO is used when returning category information, including the products that belong to the category.

- `GET /category`: Retrieve a list of all categories as CategoryDto objects.
- `GET /category/{id}`: Retrieve a specific category by its ID as a CategoryDto object.
- `POST /category/add`: Create a new category.
  - JSON request body: `name`: string, `description`: string
- `DELETE /category/{id}`: Delete a category by its ID. This will also delete all products associated with the category.

### Customer

`CustomerDto` contains the following fields: `id` (int), `firstName` (string), `lastName` (string), `email` (string), `phone` (string), and `profile` (CustomerProfileSimpleDto).  
 This DTO is used when returning customer information, including the orders that belong to the customer.

- `GET /customer`: Retrieve a list of all customers as CustomerDto objects.
- `GET /customer/{id}`: Retrieve a specific customer by its ID as a CustomerDto object.

### Order

`OrderDto` contains the following fields: `id` (int), `customerId` (Integer), `orderItems` (OrderItemDto), `deliveryDate` (Date), `orderDate` (Date),`shippingAddressId` (int), and `status` (string).  
 This DTO is used when returning order information.

Items in an order are represented as a JSON array of OrderItemDto objects. Each OrderItemDto contains the following fields: `product` (ProductSimpleDto), `quantity` (int), and `price` (double).

- `GET /order`: Retrieve a list of all orders as OrderDto objects.
- `GET /order/{id}`: Retrieve a specific order by its ID as an OrderDto object.
- `PUT /order/{id}`: Update the status / state of an order.
  - JSON request body: `status`: string ("CANCELLED", "SHIPPED", "NEW")
