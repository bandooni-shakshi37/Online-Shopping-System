import java.util.ArrayList;
import java.util.Scanner;

public class ShoppingSystem {

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<CartItem> cart = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public ShoppingSystem() {

        products.add(new Product(1, "Wireless Mouse", 599, "Electronics"));
        products.add(new Product(2, "Keyboard", 899, "Electronics"));
        products.add(new Product(3, "Water Bottle", 399, "Home"));
        products.add(new Product(4, "Backpack", 999, "Accessories"));
        products.add(new Product(5, "Notebook", 199, "Stationery"));
    }

    public void viewProducts() {

        System.out.println("\n===== AVAILABLE PRODUCTS =====");

        for (Product product : products) {
            product.displayProduct();
        }
    }

    public void searchProduct() {

        scanner.nextLine();

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        boolean found = false;

        for (Product product : products) {

            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                product.displayProduct();
                found = true;
            }
        }

        if (!found) {
            System.out.println("Product not found.");
        }
    }

    public Product findProduct(int id) {

        for (Product product : products) {

            if (product.getId() == id) {
                return product;
            }
        }

        return null;
    }

    public CartItem findCartItem(int productId) {

        for (CartItem item : cart) {

            if (item.getProduct().getId() == productId) {
                return item;
            }
        }

        return null;
    }

    public void addToCart() {

        System.out.print("Enter Product ID: ");
        int id = scanner.nextInt();

        Product product = findProduct(id);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        if (quantity <= 0) {
            System.out.println("Quantity must be greater than 0.");
            return;
        }

        CartItem existingItem = findCartItem(id);

        if (existingItem != null) {
            existingItem.increaseQuantity(quantity);
        } else {
            cart.add(new CartItem(product, quantity));
        }

        System.out.println("Product added to cart successfully!");
    }

    public void viewCart() {

        if (cart.isEmpty()) {
            System.out.println("\nYour cart is empty.");
            return;
        }

        System.out.println("\n===== YOUR CART =====");

        for (CartItem item : cart) {
            item.displayCartItem();
        }

        System.out.println("-------------------------");
        System.out.println("Subtotal: ₹" + calculateTotal());
    }

    public double calculateTotal() {

        double total = 0;

        for (CartItem item : cart) {
            total += item.getTotalPrice();
        }

        return total;
    }

    public void removeFromCart() {

        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.print("Enter Product ID to remove: ");
        int id = scanner.nextInt();

        CartItem item = findCartItem(id);

        if (item != null) {
            cart.remove(item);
            System.out.println("Product removed from cart.");
        } else {
            System.out.println("Product is not in the cart.");
        }
    }

    public void checkout() {

        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        double subtotal = calculateTotal();
        double discount = 0;

        if (subtotal >= 2000) {
            discount = subtotal * 0.10;
        }

        double finalAmount = subtotal - discount;

        System.out.println("\n===== CHECKOUT =====");
        System.out.println("Subtotal: ₹" + subtotal);
        System.out.println("Discount: ₹" + discount);
        System.out.println("Final Amount: ₹" + finalAmount);

        scanner.nextLine();

        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();

        System.out.println("\nOrder placed successfully!");
        System.out.println("Thank you, " + customerName + "!");
        System.out.println("Your order total is ₹" + finalAmount);

        cart.clear();
    }

    public void start() {

        while (true) {

            System.out.println("\n===== ONLINE SHOPPING SYSTEM =====");
            System.out.println("1. View Products");
            System.out.println("2. Search Product");
            System.out.println("3. Add to Cart");
            System.out.println("4. View Cart");
            System.out.println("5. Remove from Cart");
            System.out.println("6. Checkout");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    viewProducts();
                    break;

                case 2:
                    searchProduct();
                    break;

                case 3:
                    addToCart();
                    break;

                case 4:
                    viewCart();
                    break;

                case 5:
                    removeFromCart();
                    break;

                case 6:
                    checkout();
                    break;

                case 7:
                    System.out.println("Thank you for using the Online Shopping System!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}