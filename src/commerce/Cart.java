package commerce;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    private Map<Product,Integer> cartMap = new HashMap<>();

    //생성자
    public Cart() {
        this.cartMap = new HashMap<>();
    }

    //장바구니 물건담기
    public void addProduct(Product product, int quantity){
        cartMap.put(product , cartMap.getOrDefault(product,0) + quantity);
    }

    // 장바구니 갖고와
    public Map<Product, Integer> getCartMap() {
        return this.cartMap;
    }
}

