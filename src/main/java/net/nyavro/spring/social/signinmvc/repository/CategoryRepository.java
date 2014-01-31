package net.nyavro.spring.social.signinmvc.repository;

import net.nyavro.spring.social.signinmvc.model.Category;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CategoryRepository extends PagingAndSortingRepository<Category, String> {
}
