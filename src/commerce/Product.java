package commerce;

public class Product {
    public String engName; // 영어 이름
    public String korName; // 한글 이름
    public int price;
    public String description;
    public int stock;

    public Product(String engName, String korName, int price, String description, int stock) {
        this.engName = engName;
        this.korName = korName;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    @Override
    public String toString() {
        // 출력 시 영어와 한글을 동시에 보여주도록 수정했습니다.
        return String.format("%-15s (%-10s) | %,10d원 | %s (재고: %d)",
                engName, korName, price, description, stock);
    }
}