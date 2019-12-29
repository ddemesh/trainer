package by.dima.nutrition.repository;

import by.dima.nutrition.model.ConsumedProduct;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.Map;

public interface ConsumedProductRepository extends CrudRepository<ConsumedProduct, Integer> {
    @Query(value = "select sum(TRUNCATE(cp.size * p.carbohydrate / 100, 1)) as carbohydrate, sum(TRUNCATE(cp.size * p.protein / 100, 1)) as protein, sum(TRUNCATE(cp.size * p.fat / 100, 1)) as fat from consumed_product as cp left join product as p on cp.product_id = p.id where cp.date >= ?1 and cp.date <= ?2", nativeQuery = true)
    Map<String, Float> getNutrientsFromDateRange(Date from, Date to);
}
