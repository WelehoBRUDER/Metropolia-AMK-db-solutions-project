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
- `POST /product/`: Create a new product. The created product is returned as a JSON object of ProductDto.
  - JSON request body: `name`: string, `description`: string, `price`: double, `stockQuantity`: int, `suppliers`: [supplierEntity], `category`: categoryEntity
- `PUT /product/{id}`: Update a product by its ID. The updated product is returned as a JSON object of ProductDto.
  - JSON request body: `name`: string, `description`: string, `price`: double, `stockQuantity`: int, `suppliers`: [supplierEntity], `category`: categoryEntity
- `PUT /product/price-bulk`: Update prices for all products in a given category at once. The factor is applied as a multiplier (eg 1.1 translates to a 10% increase).
  - JSON request body: `cat`: int, `factor`: double
- `DELETE /product/{id}`: Delete a product by its ID.

### Supplier

`SupplierSimpleDto` is a simplified version of the Supplier entity, containing only the following fields: `id` (int), `name` (string), `contactName` (string), `phone` (string) and `email` (string).  
 This DTO is used when returning supplier information in contexts where only basic details are needed, such as in product information.

`SupplierDto` is otherwise identical to _SupplierSimpleDto_, but also includes a list of products (ProductSimpleDto).

- `GET /supplier`: Retrieve a list of all suppliers as SupplierDto objects.
- `GET /supplier/{id}`: Retrieve a specific supplier by its ID as a SupplierDto object.
- `POST /supplier/`: Create a new supplier. The created supplier is returned as a JSON object of SupplierDto.
  - JSON request body: `name`: string, `contactName`: string, `phone`: string, `email`: string
- `PUT /supplier/{id}`: Update a supplier by its ID. The updated supplier is returned as a JSON object of SupplierDto.
  - JSON request body: `name`: string, `contactName`: string, `phone`: string, `email`: string
- `DELETE /supplier/{id}`: Delete a supplier by its ID.

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
 This DTO is used when returning customer information.

`CustomerSimpleDto` is a simplified object for bulk retrieval of customer information, containing only the following fields: `id` (int), `firstName` (string), `lastName` (string), `email` (string) and `phone` (string).

`CustomerDto` also includes all orders associated with the customer, represented as a JSON array of OrderDto objects.  
This is only displayed when retrieving a single customer by ID, and not when retrieving the list of all customers.

- `GET /customer`: Retrieve a list of all customers as CustomerDto objects.
- `GET /customer/{id}`: Retrieve a specific customer by its ID as a CustomerDto object.
- `POST /customer/`: Create a new customer. The created customer is returned as a JSON object of CustomerDto.
  - JSON request body: `firstName`: string, `lastName`: string, `email`: string, `phone`: string,
- `PUT /customer/{id}`: Update a customer by its ID. The updated customer is returned as a JSON object of CustomerDto.
  - JSON request body: `firstName`: string, `lastName`: string, `email`: string, `phone`: string
- `DELETE /customer/{id}`: Delete a customer by its ID.

### Order

`OrderDto` contains the following fields: `id` (int), `customerId` (Integer), `orderItems` (OrderItemDto), `deliveryDate` (Date), `orderDate` (Date),`shippingAddressId` (int), and `status` (string).  
 This DTO is used when returning order information.

Items in an order are represented as a JSON array of OrderItemDto objects. Each OrderItemDto contains the following fields: `product` (ProductSimpleDto), `quantity` (int), and `price` (double).

- `GET /order`: Retrieve a list of all orders as OrderDto objects.
- `GET /order/{id}`: Retrieve a specific order by its ID as an OrderDto object.
- `POST /order/`: Create a new order. The created order is returned as a JSON object of OrderDto.
  - JSON request body: `customerId`: int, `orderItems`: [OrderItemDto], `orderDate`: Date, `deliveryDate`: Date, `shippingAddressId`: int
- `PUT /order/{id}`: Update the status / state of an order.
  - JSON request body: `status`: string ("CANCELLED", "SHIPPED", "NEW"), `deliveryDate`: Date (optional)
- `DELETE /order/{id}`: Delete an order by its ID.

## Database changes

The project's database includes various enhancements not present in the base db.  
These enhancements are not included in the JPA repository but are locally on the database.

### Order logging event

A logging event that is run once per day. It gathers all orders made that day and sums their prices together.  
The log is saved to the *order_logs* table, alongside the current date. It also shows how many orders were made that day.

```SQL
INSERT INTO order_logs (log_date, orders_total, total_price)
	SELECT
		CURDATE() AS log_date,
		COUNT(DISTINCT o.id) AS orders_total,
		COALESCE(SUM(oi.unit_price * oi.quantity), 0) AS total_price
	FROM orders o
	LEFT JOIN orderitems oi
		ON oi.order_id = o.id
	WHERE o.order_date >= CURDATE() AND o.order_date < CURDATE() + INTERVAL 1 DAY
```

### Price change logging

A logging trigger that is run whenever a product's price is changed. It saves the product's id and its old price.  
The log is saved to table *price_changes* alongside the current date.

```SQL
BEGIN
    IF OLD.price <> NEW.price THEN
        INSERT INTO price_changes (productid, prev_price)
        VALUES (OLD.id, OLD.price);
    END IF;
END
```

### Product category view

Displays a view of each product category. The values included in the view are:  
* Category name
* Number of products
* Average price

```SQL
SELECT productcategories.name AS 'Category', COUNT(*) AS 'Product count', AVG(price) AS 'Median price'
FROM productcategories INNER JOIN products
ON products.category_id = productcategories.id
GROUP BY productcategories.name 
```