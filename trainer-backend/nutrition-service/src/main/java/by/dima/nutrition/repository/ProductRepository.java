package by.dima.nutrition.repository;

import by.dima.nutrition.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
