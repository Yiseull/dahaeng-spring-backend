<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>개인 정보 수정</title>
</head>
<body>
    닉네임: ${user.nickname}
    이메일: ${user.email}
    비밀번호: ${user.password}
    <br>
    <form action="/editNickname" method="post">
        <input name="email" type="hidden" value="${user.email}">

        <input type="text" name="nickname">
        <input type="submit" value="닉네임변경">
    </form>
    <br>
    <form action="/editPassword" method="post">
        <input name="email" type="hidden" value="${user.email}">

        <input type="text" name="nickname">
        <input type="submit" value="비밀번호 변경">
    </form>

</body>
</html>


