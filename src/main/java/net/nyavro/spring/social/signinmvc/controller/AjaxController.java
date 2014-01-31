package net.nyavro.spring.social.signinmvc.controller;

import net.nyavro.spring.social.signinmvc.model.Category;
import net.nyavro.spring.social.signinmvc.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/ajax", produces = MediaType.APPLICATION_JSON_VALUE)
public class AjaxController extends CommonController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/category/list", method = RequestMethod.GET)
    @ResponseBody
    public List<Category> listCategories() throws IOException {
        return categoryService.findAll();
    }
}
