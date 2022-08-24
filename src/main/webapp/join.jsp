<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link rel="stylesheet" href="join.css">
    <script
            src="https://code.jquery.com/jquery-3.4.1.js"
            integrity="sha256-WpOohJOqMqqyKL9FccASB9O0KwACQJpFTUBLTYOVvVU="
            crossorigin="anonymous"></script>
</head>
<body>
<form action="" method="post" name="join_form" id="join_form">
    <div class="wrapper">
        <div class="wrap">
            <div class="subjecet">
                <span>회원가입</span>
            </div>
            <div class="mail_wrap">
                <div class="mail_name">이메일</div>
                <div class="mail_input_box">
                    <input class="mail_input" type="email" name="email">
                </div>
                <span id="mail_input_box_warn"></span>
                <span class="final_mail_ck">이메일을 입력해주세요.</span>
                <div class="mail_check_wrap">
                    <div class="mail_check_button">
                        <input type="button" value="인증번호 전송">
                    </div>
                    <div class="mail_check_name">인증번호 입력</div>
                    <div class="mail_check_input_box">
                        <input class="mail_check_input" type="text" name="checknumber" disabled="disabled">
                    </div>
                    <div class="clearfix"></div>
                    <span id="mail_check_input_box_warn"></span>
                </div>
            </div>
            <div class="pw_wrap">
                <div class="pw_name">비밀번호</div>
                <div class="pw_input_box">
                    <input class="pw_input" type="password" name="password">
                </div>
                <span class="final_pw_ck">비밀번호를 입력해주세요.</span>
            </div>
            <div class="pwck_wrap">
                <div class="pwck_name">비밀번호 확인</div>
                <div class="pwck_input_box">
                    <input class="pwck_input" type="password">
                </div>
                <span class="final_pwck_ck">비밀번호 확인을 입력해주세요.</span>
                <span class="pwck_input_re_1">비밀번호가 일치합니다.</span>
                <span class="pwck_input_re_2">비밀번호가 일치하지 않습니다.</span>
            </div>


            <div class="user_wrap">
                <div class="user_name">닉네임</div>
                <div class="user_input_box">
                    <input class="user_input" type="text" name="nickname">
                </div>
                <span class="final_name_ck">닉네임을 입력해주세요.</span>
            </div>
            <div class="join_button_wrap">
                <input type="button" class="join_button" value="가입하기">
            </div>
        </div>

    </div>

</form>

<script>
    var code = ""; //이메일 인증번호 저장을 위한 코드

    /* 유효성 검사 통과유무 변수 */
    var mailCheck = false;            // 이메일
    var mailchCheck = false;            //이메일 중복 검사
    var mailnumCheck = false;        // 이메일 인증번호 확인
    var pwCheck = false;            // 비번
    var pwckCheck = false;            // 비번 확인
    var pwckcorCheck = false;        // 비번 확인 일치 확인
    var nicknameCheck = false;            // 닉네임
    var nicknameckCheck = false;            // 닉네임 중복 검사


    /* 인증번호 이메일 전송 */
    $(".mail_check_button").click(function(){

        var email = $(".mail_input").val();        // 입력한 이메일
        var checkBox = $(".mail_check_input");        // 인증번호 입력란
        var boxWrap = $(".mail_check_input_box");    // 인증번호 입력란 박스

        $.ajax({

            type:"GET",
            url:"mailCheck.do?email=" + email,
            success:function(data){
                //console.log("data : " + data);
                checkBox.attr("disabled",false);
                code = data;
            }

        });

    });

    /* 인증번호 비교 */
    $(".mail_check_input").blur(function(){
        var inputCode = $(".mail_check_input").val();        // 입력코드
        var checkResult = $("#mail_check_input_box_warn");    // 비교 결과

        if(inputCode == code){                            // 일치할 경우
            checkResult.html("인증번호가 일치합니다.");
            checkResult.attr("class", "correct");
            mailnumCheck=true;
        } else {                                            // 일치하지 않을 경우
            checkResult.html("인증번호를 다시 확인해주세요.");
            checkResult.attr("class", "incorrect");
            mailnumCheck = false;
            console.log("mailnumcheck false");
        }

    });

    /*메일 중복 자동 검사*/
    $('.mail_input').on("propertychange change keyup paste input", function(){

        //console.log("keyup 테스트");
        var memberMail = $('.mail_input').val();			// .mail_input에 입력되는 값
        var data = {memberMail : memberMail}				// '컨트롤에 넘길 데이터 이름' : '데이터(.mail_input에 입력되는 값)'

        $.ajax({
            type : "post",
            url : "/memberMailChk.do",
            data : data,
            success : function(result){
                console.log("성공 여부" + result);
                if (result != 'fail'){
                    mailchCheck=true;
                }else{
                    mailchCheck=false;
                    console.log("mailchcheck false");
                }
            }
        });

    });

    /*닉네임 중복 자동 검사*/
    $('.user_input').on("propertychange change keyup paste input", function(){

        //console.log("keyup 테스트");
        var nickName = $('.user_input').val();			// .user_input에 입력되는 값
        var data = {nickName : nickName}				// '컨트롤에 넘길 데이터 이름' : '데이터(.mail_input에 입력되는 값)'

        $.ajax({
            type : "post",
            url : "/nickNameChk.do",
            data : data,
            success : function(result){
                console.log("성공 여부" + result);
                if (result != 'fail'){
                    nicknameckCheck=true;
                }else{
                    nicknameckCheck=false;
                    console.log("nicknameckcheck false");
                }
            }
        });

    });

    $(document).ready(function(){
        $(".join_button").click(function(){
            var mail = $('.mail_input').val();            // 이메일 입력란
            var pw = $('.pw_input').val();                // 비밀번호 입력란
            var pwck = $('.pwck_input').val();            // 비밀번호 확인 입력란
            var name = $('.user_input').val();            // 닉네임 입력란

            /*메일 유효성 검사*/
            if(mail == ""){
                $('.final_mail_ck').css('display','block');
                mailCheck = false;
                console.log("mailcheck false");
            }else{
                $('.final_mail_ck').css('display', 'none');
                mailCheck = true;
            }

            /* 비밀번호 유효성 검사 */
            if(pw == ""){
                $('.final_pw_ck').css('display','block');
                pwCheck = false;
                console.log("pwcheck false");
            }else{
                $('.final_pw_ck').css('display', 'none');
                pwCheck = true;
            }

            /* 비밀번호 확인 유효성 검사 */
            if(pwck == ""){
                $('.final_pwck_ck').css('display','block');
                pwckCheck = false;
                console.log("pwchcheck false");
            }else{
                $('.final_pwck_ck').css('display', 'none');
                pwckCheck = true;
            }

            /* 닉네임 유효성검사 */
            if(name == ""){
                $('.final_name_ck').css('display','block');
                nicknameCheck = false;
                console.log("nicknamecheck false");
            }else{
                $('.final_name_ck').css('display', 'none');
                nicknameCheck = true;
            }

            /* 최종 유효성 검사 */
            if(mailCheck&&mailchCheck&&mailnumCheck&&pwCheck&&pwckCheck&&pwckcorCheck&&nicknameCheck&&nicknameckCheck){
                console.log("확인");
                $("#join_form").attr("action", "/join.do");
                $("#join_form").submit();
            }
            return false;
        });
    });

    /* 비밀번호 확인 일치 유효성 검사 */
    $('.pwck_input').on("propertychange change keyup paste input", function(){
        var pw = $('.pw_input').val();
        var pwck = $('.pwck_input').val();
        $('.final_pwck_ck').css('display', 'none');

        if(pw == pwck){
            $('.pwck_input_re_1').css('display','block');
            $('.pwck_input_re_2').css('display','none');
            pwckcorCheck = true;
        }else{
            $('.pwck_input_re_1').css('display','none');
            $('.pwck_input_re_2').css('display','block');
            pwckcorCheck = false;
            console.log("pwcorcheck false");
        }
    });
</script>


</body>
</html>