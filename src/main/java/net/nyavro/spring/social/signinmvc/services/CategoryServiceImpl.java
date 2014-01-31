package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.Category;
import net.nyavro.spring.social.signinmvc.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    public static final int BATCH = 1024;

    @Autowired
    private CategoryRepository repository;

    @Override
    public List<Category> findAll() {
        return repository.findAll(
            new PageRequest(0, BATCH, new Sort(Sort.Direction.DESC, "published"))
        ).getContent();
    }
}
