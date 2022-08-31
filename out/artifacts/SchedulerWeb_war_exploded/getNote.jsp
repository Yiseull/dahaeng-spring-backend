<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>노트 상세</title>
</head>
<body>
    <center>
        <h1>노트 상세</h1>
        <a href="/logout">Log-out</a>
        <hr>
        <form action="/updateNote" method="post">
            <input name="noteId" type="hidden" value="${note.noteId}">
            <table border="1" cellpadding="0" cellspacing="0">
                <tr>
                    <td bgcolor="orange" width="70">노트 이름</td>
                    <td align="left"><input name="noteName" type="text" value="${note.noteName}"></td>
                </tr>
                <tr>
                    <td bgcolor="orange">시작일</td>
                    <td align="left">${note.startDate}</td>
                </tr>
                <tr>
                    <td bgcolor="orange">종료일</td>
                    <td align="left">${note.endDate}</td>
                </tr>
                <tr>
                    <td bgcolor="orange">마지막업데이트</td>
                    <td align="left">${note.lastUpdate}</td>
                </tr>
                <tr>
                    <td colspan="2" align="center">
                        <input type="submit" value="노트 수정">
                    </td>
                </tr>
            </table>
        </form>
        <hr>
        <a href="/insertNote.jsp">노트등록</a>&nbsp;&nbsp;&nbsp;
        <a href="/deleteNote?noteId=${note.noteId}">노트삭제</a>&nbsp;&nbsp;&nbsp;
        <a href="/deleteNoteCompletely?noteId=${note.noteId}">노트완전삭제</a>
        <a href="/inviteMember.jsp">멤버 추가 페이지</a>
        <a href="/getNoteList">노트목록</a>
    </center>
</body>
</html>
