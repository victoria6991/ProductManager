package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    Product firstProduct = new Product(1, "Umbrella", 1_000);
    Product secondProduct = new Product(2, "Table", 10_000);
    Book firstBook = new Book(3, "Гарри Поттер и философский камень", 850, "Джоан Роулинг");
    Book secondBook = new Book(4, "Вино из одуванчиков", 550, "Рей Бредберри");
    Book thirdBook = new Book(5, "Гарри Поттер и тайная комната", 850, "Джоан Роулинг");
    Smartphone firstPhone = new Smartphone(6, "13 pro", 140_000, "Apple");
    Smartphone secondPhone = new Smartphone(7, "galaxy", 50_000, "Samsung");

    ProductManager manager = new ProductManager(new ProductRepository());

    @BeforeEach
    void save() {
        manager.add(firstProduct);
        manager.add(secondProduct);
        manager.add(firstBook);
        manager.add(secondBook);
        manager.add(thirdBook);
        manager.add(firstPhone);
        manager.add(secondPhone);
    }

    @Test
    void shouldReturnTwoProducts() {
        Product[] expected = {firstBook, thirdBook};
        Product[] actual = manager.searchBy("Джоан");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnBookByAuthor() {
        Product[] expected = {secondBook};
        Product[] actual = manager.searchBy("Бредберри");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnBookByName() {
        Product[] expected = {secondBook};
        Product[] actual = manager.searchBy("Вино из одуванчиков");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnPhoneByProducer() {
        Product[] expected = {firstPhone};
        Product[] actual = manager.searchBy("Apple");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnPhoneByName() {
        Product[] expected = {secondPhone};
        Product[] actual = manager.searchBy("galaxy");
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnEmpty() {
        Product[] expected = {};
        Product[] actual = manager.searchBy("книга");
        assertArrayEquals(expected, actual);
    }

}