package setup;

import util.RandomNumbers;

public class ProductFactory {

    public static Product createProduct() {
        Product product = new Product();
        product.setId(RandomNumbers.generateRandomNumber());
        return product;
    }
}