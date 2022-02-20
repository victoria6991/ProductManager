package ru.netology.repo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.NotFoundException;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    Product firstProduct = new Product(1, "Umbrella", 1_000);
    Product secondProduct = new Product(2, "Table", 10_000);
    Book firstBook = new Book(3, "Гарри Поттер и философский камень", 850, "Джоан Роулинг");
    Book secondBook = new Book(4, "Вино из одуванчиков", 550, "Рей Бредберри");
    Book thirdBook = new Book(5, "Гарри Поттер и тайная комната", 850, "Джоан Роулинг");
    Smartphone firstPhone = new Smartphone(6, "13 pro", 140_000, "Apple");
    Smartphone secondPhone = new Smartphone(7, "galaxy", 50_000, "Samsung");

    ProductRepository repo = new ProductRepository();

    @BeforeEach
    void save() {
        repo.save(firstProduct);
        repo.save(secondProduct);
        repo.save(firstBook);
        repo.save(secondBook);
        repo.save(thirdBook);
        repo.save(firstPhone);
        repo.save(secondPhone);
    }

    @Test
    void shouldRemoveById() {
        repo.findById(1);
        Product[] expected = {secondProduct, firstBook, secondBook, thirdBook, firstPhone, secondPhone};
        Product[] actual = repo.removeById(1);
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldThrowNotFoundException() {
        Assertions.assertThrows(NotFoundException.class, ()->{repo.removeById(10);});
    }
}