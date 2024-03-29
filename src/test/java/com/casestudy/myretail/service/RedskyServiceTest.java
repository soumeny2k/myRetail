package com.casestudy.myretail.service;

import com.casestudy.myretail.MyRetailSpringConfigTest;
import com.casestudy.myretail.config.MyRetailConfig;
import com.casestudy.myretail.valueobject.Product;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@Import(MyRetailSpringConfigTest.class)
public class RedskyServiceTest {

    private long productId = 16696652L;

    @Autowired
    private RedskyService redskyService;

    @MockBean
    private RestTemplate restTemplate;

    @MockBean
    private MyRetailConfig productConfig;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testGetProduct() throws IOException {
        final String json = "{\n" +
                "\t\"product\": {\n" +
                "\t\t\"item\": {\n" +
                "\t\t\t\"tcin\": \"16696652\",\n" +
                "\t\t\t\"bundle_components\": {},\n" +
                "\t\t\t\"dpci\": \"058-34-0436\",\n" +
                "\t\t\t\"upc\": \"025192110306\",\n" +
                "\t\t\t\"product_description\": {\n" +
                "\t\t\t\t\"title\": \"The Big Lebowski (Blu-ray)\",\n" +
                "\t\t\t\t\"bullet_description\": [\"<B>Movie Studio:</B> Universal Studios\", \"<B>Movie Genre:</B> Comedy\", \"<B>Software Format:</B> Blu-ray\"]\n" +
                "\t\t\t}" +
                "\t\t}" +
                "\t}" +
                "}";
        when(restTemplate.getForEntity(anyString(), any(Class.class)))
                .thenReturn(new ResponseEntity<>(json, HttpStatus.OK));
        when(productConfig.getUrl(anyLong())).thenReturn("http://redsky.com");

        final Product product = redskyService.getProduct(productId);
        assertEquals("Product id match", productId, product.getId());
        assertEquals("Product name match", "The Big Lebowski (Blu-ray)", product.getName());
    }

    @Test
    public void testGetProductNoItemJson() throws IOException {
        final String json = "{\n" +
                "\t\"product\": {\n" +
                "\t\t\"item\": {\n" +
                "\t\t}" +
                "\t}" +
                "}";
        when(restTemplate.getForEntity(anyString(), any(Class.class)))
                .thenReturn(new ResponseEntity<>(json, HttpStatus.OK));
        when(productConfig.getUrl(anyLong())).thenReturn("http://redsky.com");

        final Product product = redskyService.getProduct(productId);
        assertEquals("Null product details", null, product);
    }

    @Test
    public void testGetProductBlankProduct() throws IOException {
        final String json = "{\n" +
                "\t\"product\": {\n" +
                "\t}" +
                "}";
        when(restTemplate.getForEntity(anyString(), any(Class.class)))
                .thenReturn(new ResponseEntity<>(json, HttpStatus.OK));
        when(productConfig.getUrl(anyLong())).thenReturn("http://redsky.com");

        final Product product = redskyService.getProduct(productId);
        assertEquals("Null product details", null, product);
    }
}
