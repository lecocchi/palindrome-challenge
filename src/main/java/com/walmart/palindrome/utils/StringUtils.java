package com.walmart.palindrome.utils;

import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
public class StringUtils {

    public boolean isPalindrome(String originalString) {

        String tempString = originalString.replaceAll("\\s+", "").toLowerCase();

        return IntStream.range(0, tempString.length() / 2)
                .noneMatch(i -> tempString.charAt(i) != tempString.charAt(tempString.length() - i - 1));

    }
}
