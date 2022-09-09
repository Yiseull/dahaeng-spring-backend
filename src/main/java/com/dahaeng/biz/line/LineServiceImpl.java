package com.dahaeng.biz.line;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LineServiceImpl implements LineService {
    @Autowired
    private LineDAO lineDAO;

    @Transactional
    @Override
    public void insertLine(LineVO vo) {
        lineDAO.insertLine(vo);
    }

    @Transactional
    @Override
    public void updateLine(LineVO vo) {
        lineDAO.updateLine(vo);
    }

    @Transactional
    @Override
    public void deleteLine(int lineId) {
        lineDAO.deleteLine(lineId);
    }

    @Transactional
    @Override
    public LineVO getLine(int lineId) {
        return lineDAO.getLine(lineId);
    }

    @Transactional
    @Override
    public List<LineVO> getLineList(int categoryId) {
        return lineDAO.getLineList(categoryId);
    }
}