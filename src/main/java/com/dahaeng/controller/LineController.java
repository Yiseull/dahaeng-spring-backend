package com.dahaeng.controller;

import com.dahaeng.biz.line.LineService;
import com.dahaeng.biz.line.LineVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/line")
public class LineController {
    @Autowired
    private LineService lineService;

    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody LineVO vo) {
        ResponseEntity<String> entity = null;

        try {
            System.out.println("/insert");
            lineService.insertLine(vo);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody LineVO vo) {
        ResponseEntity<String> entity = null;

        try {
            lineService.updateLine(vo);
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
        int lineId = (int) param.get("lineId");

        try {
            lineService.deleteLine(lineId);
            entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    @GetMapping("/get")
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

    @GetMapping("/list")
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