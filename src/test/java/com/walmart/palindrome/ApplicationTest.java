package com.walmart.palindrome;

import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class ApplicationTest {
    @Test
    public void main() {
        Application.main(new String[]{});
    }
}