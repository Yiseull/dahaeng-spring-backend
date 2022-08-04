<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>노트 목록</title>
</head>
<body>
    <center>
        <h1>노트 목록</h1>
        <h3>${user.nickname}<a href="/logout">Log-out</a></h3>

        <table border="1" cellpadding="0" cellspacing="0" width="700">
            <tr>
                <td bgcolor="orange" width="100">노트 이름</td>
            </tr>

            <c:forEach items="${noteList}" var="note">
            <tr>
                <td>${note.noteId}</td>
                <td align="left"><a href="getNote.do?noteId=${note.noteId}">${note.noteName}</a></td>
                <td><fmt:formatDate value="${note.startDate}" pattern="yyyy-MM-dd"/></td>
                <td><fmt:formatDate value="${note.endDate}" pattern="yyyy-MM-dd"/></td>
                <td><fmt:formatDate value="${note.lastUpdate}" pattern="yyyy-MM-dd"/></td>
            </tr>
            </c:forEach>
        </table>
        <br>
        <a href="insertNote.jsp">새 노트 등록</a>
    </center>
</body>
</html>
