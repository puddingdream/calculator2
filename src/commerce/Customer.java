package commerce;

public class Customer {
    String name;
    String email;
    Rating rating;
    int totalOrderAmount;

    public Customer(String name, String email, int totalOrderAmount) {
        this.name = name;
        this.email = email;
        this.totalOrderAmount = totalOrderAmount;
        this.rating = rating(totalOrderAmount);
    }

    Rating rating(int totalOrderAmount){
        if (totalOrderAmount < 500_000) return Rating.BRONZE;
        if (totalOrderAmount < 1_000_000) return  Rating.SILVER;
        if (totalOrderAmount < 1_500_000) return  Rating.GOLD;
        return Rating.PLATINUM;
    }

}
