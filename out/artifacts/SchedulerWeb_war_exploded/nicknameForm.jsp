<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>구글 연동 회원가입시 닉네임 입력폼</title>
</head>
<body>
    <form action="/googleSignUp" method="post">
        <input name="email" type="hidden" value="${email}">

        <input type="text" name="nickname">
        <input type="submit" value="회원가입">
    </form>

</body>
</html>
