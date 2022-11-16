package com.dahaeng.controller;

import com.dahaeng.biz.category.CategoryService;
import com.dahaeng.biz.category.CategoryVO;

import com.dahaeng.biz.line.LineService;
import com.dahaeng.biz.line.LineVO;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        LineVO lineVO = new LineVO();
        JSONObject obj = new JSONObject();

        try {
            System.out.println("/insert");
            categoryService.insertCategory(vo);
            int categoryId = categoryService.getCategoryId();
            lineVO.setCategoryId(categoryId);
            lineService.insertLine(lineVO);
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

    @PostMapping("/list2")
    public ResponseEntity<JSONObject> list2(@RequestBody Map<String, Object> param) {
        ResponseEntity<JSONObject> entity = null;
        int noteId = (int) param.get("noteId");

        JSONObject total = new JSONObject();
        List<JSONObject> ctwithcell = new ArrayList<>();
        total.put("noteId",noteId);

        List<CategoryVO> ctlist = categoryService.getCategoryList(noteId);

        for (int i=0; i<ctlist.size();i++) {
            CategoryVO ct = ctlist.get(i);

            JSONObject newjson = new JSONObject();
            newjson.put("categoryId", ct.getCategoryId());
            newjson.put("cell", lineService.getLineList(ct.getCategoryId()));
            ctwithcell.add(newjson);
        }

        total.put("document",ctwithcell);

        try {
            entity = new ResponseEntity<JSONObject>(
                    total, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @PostMapping("/list3")
    public ResponseEntity<JSONObject> list3(@RequestBody Map<String, Object> param) {
        ResponseEntity<JSONObject> entity = null;
        int noteId = (int) param.get("noteId");

        JSONObject total = new JSONObject();
        List<JSONObject> ctwithcell = new ArrayList<>();
        total.put("noteId",noteId);

        List<CategoryVO> ctlist = categoryService.getCategoryList(noteId);

        CategoryVO ct = ctlist.get(0);
        JSONObject newjson = new JSONObject();
        newjson.put("categoryId", ct.getCategoryId());
        newjson.put("categoryName",ct.getCategoryName());
        newjson.put("cell", lineService.getLineList(ct.getCategoryId()));
        ctwithcell.add(newjson);


        for (int i=1; i<ctlist.size();i++) {
            CategoryVO ct2 = ctlist.get(i);

            JSONObject newjson2 = new JSONObject();
            newjson2.put("categoryId", ct2.getCategoryId());
            newjson2.put("categoryName",ct2.getCategoryName());
            //newjson.put("cell", lineService.getLineList(ct.getCategoryId()));
            ctwithcell.add(newjson2);
        }

        total.put("document",ctwithcell);

        try {
            entity = new ResponseEntity<JSONObject>(
                    total, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
}