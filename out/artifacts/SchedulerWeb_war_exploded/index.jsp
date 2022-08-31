<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <script src="https://accounts.google.com/gsi/client" async defer></script>

    <title>Hi</title>
  </head>
  <body>
  Hi, ${user.nickname}
  <br>
  <a href="/logout" onclick="google.accounts.id.disableAutoSelect();">Sign out</a>
  <%--  <div class="g_id_signout">Sign Out</div>--%>
  <%--  <script>--%>
  <%--    function deleteAccount() {--%>
  <%--      const button = document.getElementById(‘signout_button’);--%>
  <%--      button.onclick = () => {--%>
  <%--        google.accounts.id.disableAutoSelect();--%>
  <%--      }--%>
  <%--    }--%>
  <%--  </script>--%>

  <a href="/withdrawal" onclick="GoogleAuth.disconnect();">회원탈퇴</a>

<%--  <a href="/withdrawal" onclick="deleteAccount();">deleteAccount</a>--%>
<%--  <script>--%>
<%--    var googleUserInfo = googleAuth.userinfo.get();--%>
<%--    var email = googleUserInfo.data.email;--%>
<%--    --%>
<%--    function deleteAccount() {--%>
<%--      google.accounts.id.revoke(email, done => {--%>
<%--        console.log(email + 'consent revoked');--%>
<%--      });--%>
<%--    }--%>
<%--  </script>--%>

  <br>
  <a href="/getNoteList">노트 목록 바로가기</a>
  <a href="/editUser.jsp">개인 정보 수정</a>

  </body>
</html>
