package client;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import setup.Product;
import setup.RequestData;

import java.io.IOException;

public class PostMethod {

    private Product product;
    private StringEntity entity;
    private HttpPost postRequest;
    private HttpClient client;
    private HttpResponse response;

    public void sendRequest() throws IOException {
        product = new Product();
        client = HttpClientBuilder.create().build();

        String xml = "<PRODUCT>\n" +
                "<ID>%s</ID>\n" +
                "</PRODUCT>";
        entity = new StringEntity(String.format(xml, product.getId()));

        postRequest = new HttpPost(RequestData.URL);
        postRequest.setEntity(entity);

        response = client.execute(postRequest);
        System.out.println("Response is: " + response.getStatusLine());
    }
}