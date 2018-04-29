package test;

import org.junit.jupiter.api.Test;
import server.domain.model.Category;
import server.domain.model.CategoryFactory;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

	@Test
	void getName() {
		Category cat1 = new Category(Category.NATURE);
		assertEquals("Nature", cat1.getName());
	}

	@Test
	void getNumber() {
		Category cat2 = new Category(Category.NATURE);
		assertEquals(5, cat2.getNumber());
	}

	@Test
	void equals() {
		Category cat1 = new Category(Category.NATURE);
		Category cat2 = new Category(Category.HEALTH);
		assertEquals(false, cat1.equals(cat2));
		Category cat3 = new Category(Category.NATURE);
		assertEquals(true, cat3.equals(cat1));
	}
}