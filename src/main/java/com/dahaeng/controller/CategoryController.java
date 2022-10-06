package com.dahaeng.controller;

import com.dahaeng.biz.category.CategoryService;
import com.dahaeng.biz.category.CategoryVO;

import com.dahaeng.biz.line.LineService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private LineService lineService;

    @PostMapping("/insert")
    public ResponseEntity<JSONObject> insert(@RequestBody CategoryVO vo) {
        ResponseEntity<JSONObject> entity = null;
        JSONObject obj = new JSONObject();

        try {
            System.out.println("/insert");
            categoryService.insertCategory(vo);
            obj.put("result", "SUCCESS");
            entity = new ResponseEntity<>(obj, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("result", "FAIL");
            entity = new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @PostMapping("/update")
    public ResponseEntity<JSONObject> update(@RequestBody CategoryVO vo) {
        ResponseEntity<JSONObject> entity = null;
        JSONObject obj = new JSONObject();

        try {
            categoryService.updateCategory(vo);
            obj.put("result", "SUCCESS");
            entity = new ResponseEntity<>(obj, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("result", "FAIL");
            entity = new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
        }
        return entity;
    }


    @PostMapping("/delete")
    public ResponseEntity<JSONObject> delete(@RequestBody Map<String, Object> param) {
        ResponseEntity<JSONObject> entity = null;
        JSONObject obj = new JSONObject();
        int categoryId = (int) param.get("categoryId");

        try {
            categoryService.deleteCategory(categoryId);
            obj.put("result", "SUCCESS");
            entity = new ResponseEntity<>(obj, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("result", "FAIL");
            entity = new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @PostMapping("/get")
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

    @PostMapping("/getwithcell")
    public ResponseEntity<JSONObject> getwithcell(@RequestBody Map<String, Object> param){
        ResponseEntity<JSONObject> entity = null;
        int categoryId = (int) param.get("categoryId");

        JSONObject newjson = new JSONObject();

        newjson.put("categoryId",categoryService.getCategory(categoryId).getCategoryId());
        newjson.put("categoryName", categoryService.getCategory(categoryId).getCategoryName());
        newjson.put("cell",lineService.getLineList(categoryId));

        try {
            entity = new ResponseEntity<JSONObject>(newjson, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }

    @PostMapping("/list")
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