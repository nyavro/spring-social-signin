package net.nyavro.spring.social.signinmvc.services;

import net.nyavro.spring.social.signinmvc.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> findAll();
}