package goover;

import static org.junit.Assert.*;

import org.junit.Test;

public class PalindromeTest {

	@Test
	public void isPalindromeTest() {
		assertTrue(Palindrome.isPalindrome("Otto"));
	}

	@Test
	public void isNoPalindromeTest() {
		assertFalse(Palindrome.isPalindrome("Test"));
	}
	
	@Test
	public void makeJsonTest() {
		Palindrome.makeJson();
	}

	@Test
	public void makeItTest() throws Exception {
		Palindrome.makeIt();
	}
}
