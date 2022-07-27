<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Join</title>
</head>
<body>
    <h1>Join</h1>
    <form action="/join" method="post">
        <table>
            <tr>
                <td>이메일</td>
                <td><input type="text" name="email" value="${user.email}"></td>
            </tr>
            <tr>
                <td>패스워드</td>
                <td><input type="password" name="password" value="${user.password}"></td>
            </tr>
            <tr>
                <td>닉네임</td>
                <td><input type="text" name="nickname" value="${user.nickname}"></td>
            </tr>
            <tr>
                <td colspan="2">
                    <input type="submit" value="회원가입">
                </td>
            </tr>
        </table>
    </form>

</body>
</html>
