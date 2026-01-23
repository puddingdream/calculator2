package commerce;

import java.util.ArrayList;
import java.util.List;

public class Category {

    private String categoryName;
    private List<Product> products;

    // [생성자] 카테고리 이름을 받아서 나만의 빈 리스트를 준비함
    public Category(String categoryName) {
        this.categoryName = categoryName;
        this.products = new ArrayList<>(); // 새로운 독립 바구니 생성 카테고리별로 바구니를 따로씀
    }

    // [기능 1] 내 바구니에 상품 하나를 쏙 넣기
    // 창고안에 카테고리 안에 상품을 추가함
    public void addProduct(Product product) {
        this.products.add(product); // 내 리스트(this.products)에 상품 추가
    }

    // [기능 2] 외부에서 "안에 뭐 들었어?" 물어볼 때 리스트 통째로 보여주기
    public List<Product> getProducts() {
        return this.products;
    }

    // [기능 3] 내 이름이 뭔지 알려주기
    public String getName() {
        return this.categoryName;
    }
}



