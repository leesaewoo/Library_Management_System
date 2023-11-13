package leejaewoo.server.category.service;

import leejaewoo.server.category.entity.Category;
import leejaewoo.server.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    //TODO: 책 하나에 카테고리 여러개 설정
    public Category createCategory(String name) {

        Optional<Category> findCategory = categoryRepository.findByName(name);

        if (findCategory.isEmpty()) {
            Category category =
                    Category.builder()
                            .name(name)
                            .build();

            return categoryRepository.save(category);
        } else {
            return findCategory.get();
        }
    }
}
