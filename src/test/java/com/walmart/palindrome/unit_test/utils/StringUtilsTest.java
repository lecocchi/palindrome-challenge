package com.walmart.palindrome.unit_test.utils;

import com.walmart.palindrome.utils.StringUtils;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class StringUtilsTest {

    private StringUtils stringUtils;

    @Before
    public void setUp() {
        stringUtils = new StringUtils();
    }

    @Test
    public void testMethodIsPalindromeIsFalse() {

        boolean isPalindrome = stringUtils.isPalindrome("abi");

        assertFalse(isPalindrome);
    }

    @Test
    public void testMethodIsPalindromeIsTrue() {

        boolean isPalindrome = stringUtils.isPalindrome("aba");

        assertTrue(isPalindrome);
    }

    @Test
    public void testMethodIsPalindromeWhenArgumentContainSpaceAndReturnTrue() {

        boolean isPalindrome = stringUtils.isPalindrome("ab a");

        assertTrue(isPalindrome);
    }
}
