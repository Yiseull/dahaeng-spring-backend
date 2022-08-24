<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title> google Login</title>
</head>
<body>
<script src="https://accounts.google.com/gsi/client" async defer></script>
<div id="g_id_onload"
     data-client_id="337820958103-d43avd5b12sbr020j8q26jpflvsq53ng.apps.googleusercontent.com"
     data-ux_mode="popup"
     data-login_uri="http://localhost:8080/index"
     data-auto_prompt="false">
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