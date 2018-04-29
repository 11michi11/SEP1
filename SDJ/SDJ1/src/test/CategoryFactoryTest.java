package test;

import org.junit.jupiter.api.Test;
import server.domain.model.Category;
import server.domain.model.CategoryFactory;

import java.util.zip.DataFormatException;

import static org.junit.jupiter.api.Assertions.*;

class CategoryFactoryTest {

	@Test
	void getCategory() {
		Category cat1 = CategoryFactory.getCategory(Category.NATURE);
		assertEquals(new Category(Category.NATURE), cat1);
		Category cat2 = CategoryFactory.getCategory(Category.NATURE);
		assertEquals(true, cat1 == cat2);
	}

	@Test
	void getCategory1() {
		Category cat1 = CategoryFactory.getCategory(new Category(Category.NATURE).getNumber());
		assertEquals(new Category(Category.NATURE), cat1);
		Category cat2 = CategoryFactory.getCategory(Category.NATURE);
		assertEquals(true, cat1 == cat2);
	}
}