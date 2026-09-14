public class CartItem {

    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void increaseQuantity(int amount) {
        quantity += amount;
    }

    public void decreaseQuantity(int amount) {
        quantity -= amount;
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public void displayCartItem() {
        System.out.println(
            product.getName() +
            " | Quantity: " + quantity +
            " | Price: ₹" + getTotalPrice()
        );
    }
}