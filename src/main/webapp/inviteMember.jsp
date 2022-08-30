<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
    <title>멤버 추가</title>
</head>
<body>
<h1>멤버 추가</h1>

<input clase= "email_input" type="text" name="email_input" id="email_input">
<input class ="findmember_button" type="button" value="멤버 찾기" name="findmember_button" onclick="location.href='/findMember.do?email='+document.getElementById('email_input').value">

<table border="1" cellpadding="0" cellspacing="0" width="700">
    <tr>
        <td bgcolor="orange" width="100">찾은 이메일</td>
        <td bgcolor="orange" width="100">초대 버튼</td>
    </tr>



    <c:forEach items="${findMemberList}" var="memberListvar">
        <tr>
            <td>${memberListvar.email}</td>
            <td>
                <button class="btn_addmember">초대하기</button>
            </td>
        </tr>
    </c:forEach>

</table>

<br>
<a href="/getNoteList">노트목록</a>
</body>
</html>