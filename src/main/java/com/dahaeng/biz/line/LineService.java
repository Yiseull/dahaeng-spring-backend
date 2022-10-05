package com.dahaeng.biz.line;

import java.util.List;

public interface LineService {
    void insertLine(LineVO vo);

    void updateLine(LineVO vo);

    void deleteLine(int lineId);

    LineVO getLine(int lineId);

    List<LineVO> getLineList(int categoryId);

}