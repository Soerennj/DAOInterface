package org.example;

import java.util.HashMap;
import java.util.Map;

interface ProductRepository
{
    void addProduct(Product product);
    Product getProductById(int productId);
    Map<Integer, Product> getAllProducts();
}
class Product
{
    private int productId;
    private String name;
    private double price;

    public Product(int productId, String name, double price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {

        return price;
    }

    @Override
    public String toString() {
        return String.format("Product ID: %d, Name: %s, Price: %.2f", productId, name, price);
    }
}

class ProductDAO implements ProductRepository
{
    private Map<Integer, Product> products;

    public ProductDAO(Map<Integer, Product> products)
    {
        this.products = products;
    }

    @Override
    public void addProduct(Product product)
    {
        if (!products.containsKey(product.getProductId()))
        {
            products.put(product.getProductId(), product);
            System.out.println("Product added successfully: " + product);
        }
        else
        {
            System.out.println("Product with ID " + product.getProductId() + " already exists!");
        }
    }

    @Override
    public Product getProductById(int productId)
    {
        return products.get(productId);
    }

    @Override
    public Map<Integer, Product> getAllProducts()
    {
        return products;
    }
}

public class Main
{
    public static void main(String[] args)
    {
        // Creating instances of products
        Product product1 = new Product(1, "Laptop", 12000.0);
        Product product2 = new Product(2, "Smartphone", 5000.0);
        Product product3 = new Product(3, "Snus", 50.0);

        // Creating an instance of ProductDAO using HashMap
        ProductRepository productDAO = new ProductDAO(new HashMap<>());

        // Adding products to the DAO
        productDAO.addProduct(product1);
        productDAO.addProduct(product2);
        productDAO.addProduct(product3);

        // Retrieving a product by ID
        int productIdToSearch = 3;
        Product foundProduct = productDAO.getProductById(productIdToSearch);
        if (foundProduct != null)
        {
            System.out.println("Product found: " + foundProduct);
        }
        else
        {
            System.out.println("Product not found with ID: " + productIdToSearch);
        }
    }
}

