package setup;

import util.RandomNumbers;

public class Product {

    private static int ID = RandomNumbers.generateRandomNumber();

    public int getID() {
        return ID;
    }
}