package client;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import setup.Product;
import setup.RequestData;

import java.io.IOException;

public class GetMethod {

    private Product product;
    private HttpClient client;
    private HttpResponse response;
    private HttpGet getRequest;

    public void getRequest() throws IOException {
        product = new Product();
        client = HttpClientBuilder.create().build();

        getRequest = new HttpGet(RequestData.PRODUCT_URL + product.getID());
        response = client.execute(getRequest);
        System.out.println("Response is: " + response.getStatusLine());
    }

    public boolean responseIs200OK() {
        return RequestData.RESPONSE_200_OK.equals(response.getStatusLine().toString());
    }

    public boolean responseIs404() {
        return RequestData.RESPONSE_404_NOT_FOUND.equals(response.getStatusLine().toString());
    }
}