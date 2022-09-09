package com.dahaeng.controller;

import com.dahaeng.biz.category.CategoryService;
import com.dahaeng.biz.category.CategoryVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody CategoryVO vo) {
        ResponseEntity<String> entity = null;

        try {
            System.out.println("/insert");
            categoryService.insertCategory(vo);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody CategoryVO vo) {
        ResponseEntity<String> entity = null;

        try {
            categoryService.updateCategory(vo);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        return entity;
    }


    @DeleteMapping("/delete")
    public ResponseEntity<String> delete(@RequestBody Map<String, Object> param) {
        ResponseEntity<String> entity = null;
        int categoryId = (int) param.get("categoryId");

        try {
            categoryService.deleteCategory(categoryId);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @GetMapping("/get")
    public ResponseEntity<CategoryVO> get(@RequestBody Map<String, Object> param) {
        ResponseEntity<CategoryVO> entity = null;
        int categoryId = (int) param.get("categoryId");

        try {
            entity = new ResponseEntity<CategoryVO>(categoryService.getCategory(categoryId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @GetMapping("/list")
    public ResponseEntity<List<CategoryVO>> list(@RequestBody Map<String, Object> param) {
        ResponseEntity<List<CategoryVO>> entity = null;
        int noteId = (int) param.get("noteId");

        try {
            entity = new ResponseEntity<>(
                    categoryService.getCategoryList(noteId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
}