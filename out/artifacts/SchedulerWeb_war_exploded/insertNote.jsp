<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>새 노트 등록</title>
</head>
<body>
    <center>
        <h1>노트 등록</h1>
        <a href="/logout">Log-out</a>
        <hr>
        <form action="/insertNote" method="post">
            <table border="1" cellpadding="0" cellspacing="0">
                <tr>
                    <td bgcolor="orange" width="70">노트이름</td>
                    <td align="left"><input name="noteName" type="text"></td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value=" 새 노트 등록 ">
                    </td>
                </tr>
            </table>
        </form>
        <hr>
        <a href="/getNoteList">글 목록 가기</a>
    </center>
</body>
</html>
