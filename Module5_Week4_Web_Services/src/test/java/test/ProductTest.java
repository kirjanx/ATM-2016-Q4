package test;

import client.DeleteMethod;
import client.GetMethod;
import client.PostMethod;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import setup.Product;
import setup.ProductFactory;

import java.io.IOException;

public class ProductTest {

    private GetMethod getMethod;
    private PostMethod postMethod;
    private DeleteMethod deleteMethod;
    private Product product;

    @BeforeClass
    public void setUpBefore() {
        getMethod = new GetMethod();
        postMethod = new PostMethod();
        deleteMethod = new DeleteMethod();
        product = ProductFactory.createProduct();
    }

    @Test(description = "Create product with random ID")
    public void createProduct() throws IOException {
        System.out.println("Product ID for current test is: " + product.getId());
        postMethod.sendRequest();

        getMethod.sendRequest();
        Assert.assertTrue(getMethod.isResponse200OK(), "Response is \"NOT 200\"");
        System.out.println("Product with ID=" + product.getId() + "" +
                " was posted successfully");
    }

    @Test(description = "Delete product", dependsOnMethods = "createProduct")
    public void deleteProduct() throws IOException {
        System.out.println("Deleting Product with ID=" + product.getId());
        deleteMethod.sendRequest();

        getMethod.sendRequest();
        Assert.assertTrue(getMethod.isResponse404(), "Response is \"NOT 404\"");
        System.out.println("Product with ID=" + product.getId() + "" +
                " was deleted successfully");
    }
}