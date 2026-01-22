package commerce;

public class Customer {
    String name;
    String email;
    Rating rating;
    double totalOrderAmount;

    public Customer(String name, String email, double totalOrderAmount) {
        this.name = name;
        this.email = email;
        this.totalOrderAmount = totalOrderAmount;
        this.rating = rating(totalOrderAmount);
    }

    Rating rating(double totalOrderAmount){
        if (totalOrderAmount < 500_000) return Rating.BRONZE;
        if (totalOrderAmount < 1_000_000) return  Rating.SILVER;
        if (totalOrderAmount < 1_500_000) return  Rating.GOLD;
        return Rating.PLATINUM;
    }

}
