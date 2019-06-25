package com.me.ats;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountTransactionServiceApplicationTests {

    @Test
public void testAccountTransactions()
  throws ClientProtocolException, IOException, URISyntaxException {

    // Given
    URI URI = new java.net.URI("http://localhost:8080/checkBalance?accountId=ACC334455&fromDate=20-10-2018&toDate=20-10-2018" );
        HttpUriRequest request = new HttpGet(URI);

    // When
    HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );

    // Then
        Assert.assertEquals(
      httpResponse.getStatusLine().getStatusCode(),
      200);
}

}

