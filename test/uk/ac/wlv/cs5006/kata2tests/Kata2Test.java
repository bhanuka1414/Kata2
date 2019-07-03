package uk.ac.wlv.cs5006.kata2tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import uk.ac.wlv.cs5006.kata2.Kata2;

class Kata2Test {

	@Test
	void testNull() {
		assertEquals(0, Kata2.evaluate(null));
	}
	
	@Test
	void testEmpty() {
		assertEquals(0, Kata2.evaluate(""));
	}
	
	@Test
	void testDivideByZero() {
		assertEquals(0, Kata2.evaluate("7 + 8 / 0"));
	}
	
	@Test
	void testAlphabets() {
		assertEquals(0, Kata2.evaluate("7 + 8f + 1"));
	}
	
	@Test
	void testNotFormat() {
		assertEquals(0, Kata2.evaluate("7 - - 1 / * 5"));
		assertEquals(0, Kata2.evaluate("7 // 1 / * 5"));
	}
	
	@Test
	void testEvaluate() {
		assertEquals(4.5, Kata2.evaluate("10 / 4 + 2"));
		assertEquals(5, Kata2.evaluate("1 + 6 - 2"));
		assertEquals(9, Kata2.evaluate("15 / 5 * 3"));
		assertEquals(8.5, Kata2.evaluate("15 / 2 + 1"));
		assertEquals(11, Kata2.evaluate("10 / 1 + 1"));
		assertEquals(16, Kata2.evaluate("15 / 1 + 1"));
	}

}
