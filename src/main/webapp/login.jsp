<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <center>
        <h1>Login</h1>
        <form action="login.do" method="post">
            <table border="1" cellpadding="0" cellspacing="0">
                <tr>
                    <td bgcolor="orange">이메일</td>
                    <td><input type="text" name="id" value="${user.email}"></td>
                </tr>
                <tr>
                    <td bgcolor="orange">패스워드</td>

                    <td><input type="password" name="password" value="${user.password}"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="로그인">
                    </td>
                </tr>
            </table>
        </form>
    </center>
</body>
</html>
