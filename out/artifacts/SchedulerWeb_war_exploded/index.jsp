<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <meta name="google-signin-scope" content="profile email">
    <meta name="google-signin-client_id" content="337820958103-d43avd5b12sbr020j8q26jpflvsq53ng.apps.googleusercontent.com">
    <script src="https://apis.google.com/js/platform.js" async defer></script>

    <title>Hi</title>
  </head>
  <body>
  Hi, ${user.nickname}
  <br>
  <a href="/logout" onclick="signOut();">Log-out</a>

  <script>
    function signOut() {
      var auth2 = gapi.auth2.getAuthInstance();
      auth2.signOut().then(function () {
        console.log('User signed out.');
      });
    }
  </script>

  <a href="/logout" onclick="GoogleAuth.signOut();">Sign out</a>

  <a href="/withdrawal" onclick="GoogleAuth.disconnect();">회원탈퇴</a>
  <a href="/login.jsp" onclick="GoogleAuth.disconnect();">구글회원탈퇴</a>

  <br>
  <a href="/getNoteList">노트 목록 바로가기</a>
  </body>
</html>
