package com.dahaeng.controller;

import com.dahaeng.biz.line.LineService;
import com.dahaeng.biz.line.LineVO;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/line")
public class LineController {
    @Autowired
    private LineService lineService;

    @PostMapping("/insert")
    public ResponseEntity<JSONObject> insert(@RequestBody LineVO vo) {
        ResponseEntity<JSONObject> entity = null;
        JSONObject obj = new JSONObject();

        try {
            System.out.println("/insert");
            lineService.insertLine(vo);
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
    public ResponseEntity<JSONObject> update(@RequestBody LineVO vo) {
        ResponseEntity<JSONObject> entity = null;
        JSONObject obj = new JSONObject();

        try {
            lineService.updateLine(vo);
            obj.put("result", "SUCCESS");
            entity = new ResponseEntity<>(obj, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            obj.put("result", "FAIL");
            entity = new ResponseEntity<>(obj, HttpStatus.BAD_REQUEST);
        }
        return entity;
    }


    @DeleteMapping("/delete")
    public ResponseEntity<JSONObject> delete(@RequestBody Map<String, Object> param) {
        ResponseEntity<JSONObject> entity = null;
        JSONObject obj = new JSONObject();

        int lineId = (int) param.get("lineId");

        try {
            lineService.deleteLine(lineId);
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
    public ResponseEntity<LineVO> get(@RequestBody Map<String, Object> param) {
        ResponseEntity<LineVO> entity = null;
        int lineId = (int) param.get("lineId");

        try {
            entity = new ResponseEntity<LineVO>(lineService.getLine(lineId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @PostMapping("/list")
    public ResponseEntity<List<LineVO>> list(@RequestBody Map<String, Object> param) {
        ResponseEntity<List<LineVO>> entity = null;
        int categoryId = (int) param.get("categoryId");

        try {
            entity = new ResponseEntity<>(
                    lineService.getLineList(categoryId), HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
}