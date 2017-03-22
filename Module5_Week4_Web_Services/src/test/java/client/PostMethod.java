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
    private HttpPost post;
    private HttpClient client;
    private HttpResponse response;

    public void postRequest() throws IOException {
        product = new Product();
        client = HttpClientBuilder.create().build();

        String xml = "<PRODUCT>\n" +
                "<ID>%s</ID>\n" +
                "</PRODUCT>";
        entity = new StringEntity(String.format(xml, product.getID()));

        post = new HttpPost(RequestData.INITIAL_URL);
        post.setEntity(entity);

        response = client.execute(post);
        }
}