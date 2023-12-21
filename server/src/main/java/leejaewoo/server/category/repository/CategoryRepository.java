package leejaewoo.server.category.repository;

import leejaewoo.server.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
//    queryDSL 사용으로 인한 주석처리
//    Optional<Category> findByName(String name);
}
