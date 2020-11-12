package com.teimour.wordsaver.api.controller;

import com.teimour.wordsaver.modelDTO.CategoryDTO;
import com.teimour.wordsaver.modelDTO.CategoryListDTO;
import com.teimour.wordsaver.api.service.CategoryServiceDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author kebritam
 * Project word-saver
 * Created on 04/11/2020
 */

@RestController
@RequestMapping("/category")
public class CategoryApiController {

    private final CategoryServiceDTO categoryService;

    public CategoryApiController(CategoryServiceDTO categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoryListDTO getCategories(){
        return new CategoryListDTO(categoryService.findAll());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryListDTO createCategory(@RequestBody CategoryDTO categoryDTO){
        categoryService.create(categoryDTO);
        return new CategoryListDTO(categoryService.findAll());
    }

    @GetMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public CategoryDTO getCategory(@PathVariable String name){
        return categoryService.findByName(name);
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public CategoryDTO updateCategory(@RequestBody CategoryDTO categoryDTO, @PathVariable String name){
        return categoryService.update(name, categoryDTO);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCategory(@PathVariable String name){
        categoryService.delete(name);
    }
}
