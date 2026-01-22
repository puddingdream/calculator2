package commerce;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product,Integer> cartMap = new HashMap<>();

    public Cart() {
        this.cartMap = new HashMap<>();
    }

    public void addProduct(Product product, int quantity){
        cartMap.put(product , cartMap.getOrDefault(product,0) + quantity);
    }

    public Map<Product, Integer> getCartMap() {
        return cartMap;
    }
}

