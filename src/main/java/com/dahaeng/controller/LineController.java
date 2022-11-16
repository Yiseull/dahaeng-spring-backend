package com.dahaeng.controller;

import com.dahaeng.biz.line.LineService;
import com.dahaeng.biz.line.LineVO;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
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
    public ResponseEntity<JSONObject> insert(@RequestBody Map<String, Object> param) {
        ResponseEntity<JSONObject> entity = null;
        int categoryId = (int) param.get("categoryId");
        JSONObject obj = new JSONObject();
        LineVO vo = new LineVO();

        try {
            vo.setlineVO("입력해주세요", "h3", "black", "basicbg","basic", categoryId);
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

    @PostMapping("/parseinsert")
    public ResponseEntity<JSONObject> parseinsert(@RequestBody String param) throws Exception{
        ResponseEntity<JSONObject> entity = null;
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObj = (JSONObject)jsonParser.parse(param);

        JSONArray jsonArr = (JSONArray) jsonObj.get("cell");

        for(int i=0;i<jsonArr.size();i++){
            JSONObject jsonObj2 = (JSONObject)jsonArr.get(i);
            LineVO vo = new LineVO();
            vo.setText(String.valueOf(jsonObj2.get("text")));
            vo.setType(String.valueOf(jsonObj2.get("type")));
            vo.setColor(String.valueOf(jsonObj2.get("color")));
            vo.setBgcolor(String.valueOf(jsonObj2.get("bgcolor")));
            vo.setFont(String.valueOf(jsonObj2.get("font")));
            vo.setCategoryId(Integer.parseInt((String.valueOf(jsonObj2.get("categoryId")))));

            if (jsonObj2.get("lineId")==null){
                lineService.insertLine(vo);
            }
            else{
                vo.setLineId(Integer.parseInt((String.valueOf(jsonObj2.get("lineId")))));
                lineService.updateLine(vo);
            }
        }

        JSONObject obj = new JSONObject();

        try {
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

    @PostMapping("/changetext")
    public ResponseEntity<LineVO> changetext(@RequestBody Map<String, Object> param){
        ResponseEntity<LineVO> entity = null;
        int lineId = (int) param.get("lineId");
        String text = (String) param.get("text");

        LineVO vo = lineService.getLine(lineId);
        try {
            vo.setText(text);
            lineService.updateLine(vo);
            entity = new ResponseEntity<>(lineService.getLine(lineId), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            entity = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return entity;
    }


    @PostMapping("/delete")
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