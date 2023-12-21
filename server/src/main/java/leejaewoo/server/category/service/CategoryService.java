package leejaewoo.server.category.service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import leejaewoo.server.category.entity.Category;
import leejaewoo.server.category.entity.QCategory;
import leejaewoo.server.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    private final JPAQueryFactory jpaQueryFactory;

    public Category createCategory(String name) {

//        JPA 쿼리 메서드 -> queryDSL 변경
//        Optional<Category> findCategory = categoryRepository.findByName(name);
        Optional<Category> findCategory = Optional.ofNullable(
                jpaQueryFactory
                        .selectFrom(QCategory.category)
                        .where(QCategory.category.name.eq(name))
                        .fetchOne()
        );

        if (findCategory.isEmpty()) {
            Category category =
                    Category.builder()
                            .name(name)
                            .build();

            Category result = categoryRepository.save(category);
            log.info("카테고리 생성, categoryId: " + result.getCategoryId());

            return result;
        } else {
            Category result = findCategory.get();
            log.info("기존 카테고리 사용, categoryId: " + result.getCategoryId());

            return result;
        }
    }
}
