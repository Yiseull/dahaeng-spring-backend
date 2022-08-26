<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script src="https://accounts.google.com/gsi/client" async defer></script>

    <title>Login</title>
</head>
<body>
    <h1>Login</h1>
    <form action="/login" method="post">
        <table border="1" cellpadding="0" cellspacing="0">
            <tr>
                <td bgcolor="orange">이메일</td>
                <td><input type="email" name="email" value="${user.email}"></td>
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
    <a href="join.jsp">Join</a>

    <div id="g_id_onload"
         data-client_id="337820958103-d43avd5b12sbr020j8q26jpflvsq53ng.apps.googleusercontent.com"
         data-ux_mode="popup"
         data-auto_select="true"
         data-login_uri="http://localhost:8080/index">
    </div>
    <div class="g_id_signin"
         data-type="standard"
         data-size="large"
         data-theme="outline"
         data-text="sign_in_with"
         data-shape="rectangular"
         data-logo_alignment="left">
    </div>

</body>
</html>
