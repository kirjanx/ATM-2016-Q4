package client;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.HttpClientBuilder;
import setup.Product;
import setup.RequestData;

import java.io.IOException;

public class DeleteMethod {

    private Product product;
    private HttpClient client;
    private HttpResponse response;
    private HttpDelete deleteRequest;

    public void deleteRequest() throws IOException {
        product = new Product();
        client = HttpClientBuilder.create().build();

        deleteRequest = new HttpDelete(RequestData.PRODUCT_URL + product.getID());
        response = client.execute(deleteRequest);
    }
}