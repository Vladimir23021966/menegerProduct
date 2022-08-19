package ru.netology.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.manager.ProductManager;
import ru.netology.repository.Repository;

public class ProductManagerTest {
    Repository repository = new Repository();
    ProductManager productManager = new ProductManager(repository);
    Product book1 = new Book(1, "Война миров", 200, "Герберт Уэльс");
    Product book2 = new Book(2, "Война и мир", 300, "Лев Толстой");
    Product book3 = new Book(33, "Пища богов", 100, "Герберт Уэльс");
    Product book4 = new Book(441, "Спящий пробуждается", 250, "Герберт Уэльс");
    Product smartphone1 = new Smartphone(555, "SamsungA51", 300, "Samsung");
    Product smartphone2 = new Smartphone(556, "SamsungGNote", 600, "Samsung");
    Product smartphone3 = new Smartphone(7777, "SamsungA53", 800, "Samsung");
    Product smartphone4 = new Smartphone(88888, "SamsungOneN0te", 900, "Samsung");


    @Test
    public void testShouldSearchName() {
        productManager.add(book1);
        productManager.add(book2);
        productManager.add(book3);
        productManager.add(book4);
        productManager.add(smartphone1);
        productManager.add(smartphone2);
        productManager.add(smartphone3);
        productManager.add(smartphone4);

        Product[] actual = productManager.searchBy("Пища богов");
        Product[] expected = {book3};
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void testShouldSearchTextShort() {
        productManager.add(book1);
        productManager.add(book2);
        productManager.add(book3);
        productManager.add(book4);
        productManager.add(smartphone1);
        productManager.add(smartphone2);
        productManager.add(smartphone3);
        productManager.add(smartphone4);

        Product[] actual = productManager.searchBy("мир");
        Product[] expected = {book1, book2};
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void testShouldSearchIncorrect() {
        productManager.add(book1);
        productManager.add(book2);
        productManager.add(book3);
        productManager.add(book4);
        productManager.add(smartphone1);
        productManager.add(smartphone2);
        productManager.add(smartphone3);
        productManager.add(smartphone4);

        Product[] actual = productManager.searchBy("ZZZZZZZZZZZZZ");
        Product[] expected = {};
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void testShouldSearchSymbol() {
        productManager.add(book1);
        productManager.add(book2);
        productManager.add(book3);
        productManager.add(book4);
        productManager.add(smartphone1);
        productManager.add(smartphone2);
        productManager.add(smartphone3);
        productManager.add(smartphone4);

        Product[] actual = productManager.searchBy("@$&*(&&&&");
        Product[] expected = {};
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void testShouldSearchText() {
        productManager.add(book1);
        productManager.add(book2);
        productManager.add(book3);
        productManager.add(book4);
        productManager.add(smartphone1);
        productManager.add(smartphone2);
        productManager.add(smartphone3);
        productManager.add(smartphone4);

        Product[] actual = productManager.searchBy("A51");
        Product[] expected = {smartphone1};
        Assertions.assertArrayEquals(expected, actual);

    }

    @Test
    public void testShouldСompareTextIn() {
        productManager.add(book1);
        productManager.add(book2);
        productManager.add(book3);
        productManager.add(book4);
        productManager.add(smartphone1);
        productManager.add(smartphone2);
        productManager.add(smartphone3);
        productManager.add(smartphone4);

        boolean actual = productManager.matches(smartphone1, "SamsungA51");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);

    }

    @Test
    public void testShouldСomparePartTextIn() {
        productManager.add(book1);
        productManager.add(book2);
        productManager.add(book3);
        productManager.add(book4);
        productManager.add(smartphone1);
        productManager.add(smartphone2);
        productManager.add(smartphone3);
        productManager.add(smartphone4);

        boolean actual = productManager.matches(smartphone1, "A51");
        boolean expected = true;
        Assertions.assertEquals(expected, actual);

    }


    @Test
    public void testShouldNoIdSearch() {
        repository.add(book1);
        repository.add(book2);
        repository.add(book3);
        repository.add(book4);
        repository.add(smartphone1);
        repository.add(smartphone2);
        repository.add(smartphone3);
        repository.add(smartphone4);
        Assertions.assertThrows(RuntimeException.class, () -> repository.remove(888888));


    }

    @Test
    public void testShouldRemoveId() {
        repository.add(book1);
        repository.add(book2);
        repository.add(book3);
        repository.add(book4);
        repository.add(smartphone1);
        repository.add(smartphone2);
        repository.add(smartphone3);
        repository.add(smartphone4);
        Product[] actual = repository.remove(1);
        Product[] expected = {book2, book3, book4, smartphone1, smartphone2, smartphone3, smartphone4};

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void testShouldRemoveIdLast() {
        repository.add(book1);
        repository.add(book2);
        repository.add(book3);
        repository.add(book4);
        repository.add(smartphone1);
        repository.add(smartphone2);
        repository.add(smartphone3);
        repository.add(smartphone4);
        Product[] actual = repository.remove(88888);
        Product[] expected = {book1, book2, book3, book4, smartphone1, smartphone2, smartphone3};

        Assertions.assertArrayEquals(expected, actual);
    }

}


