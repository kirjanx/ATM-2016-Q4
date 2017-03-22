package test;

import client.DeleteMethod;
import client.GetMethod;
import client.PostMethod;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import setup.Product;

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
        product = new Product();
    }

    @Test(description = "Create product with random ID")
    public void createProduct() throws IOException {
        postMethod.postRequest();
        System.out.println("Product ID for current test is: " + product.getID());

        getMethod.getRequest();
        Assert.assertTrue(getMethod.responseIs200OK());
        System.out.println("Product with ID=" + product.getID() + "" +
                " was posted successfully");
    }

    @Test(description = "Delete product", dependsOnMethods = "createProduct")
    public void deleteProduct() throws IOException {
        deleteMethod.deleteRequest();

        getMethod.getRequest();
        Assert.assertTrue(getMethod.responseIs404());
        System.out.println("Product with ID=" + product.getID() + "" +
                " was deleted successfully");
    }
}