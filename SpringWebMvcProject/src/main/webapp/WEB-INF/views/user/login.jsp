<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../include/static-head.jsp" />
</head>
<body class="hold-transition login-page">
<div class="login-box">
    <div class="login-logo">
        <a href="#">
            <b>USER</b>&nbsp LOGIN
        </a>
    </div>

    <div class="login-box-body">
        <p class="login-box-msg">로그인 페이지</p>

        <form action="#" method="post">
           <table style="cellpadding: 0; cellspacing: 0; margin: 0 auto; width: 100%">
						<tr>
							<td style="text-align: left">
								<p><strong>아이디를 입력해주세요.</strong>&nbsp;&nbsp;&nbsp;<span id="idCheck"></span></p>
							</td>
						</tr>
						<tr>
							<td><input type="text" name="userId" id="signInId"
								class="form-control tooltipstered" maxlength="14"
								required="required" aria-required="true"
								style="margin-bottom: 25px; width: 100%; height: 40px; border: 1px solid #d9d9de"
								placeholder="최대 14자"></td>
						</tr>
						<tr>
							<td style="text-align: left">
								<p><strong>비밀번호를 입력해주세요.</strong>&nbsp;&nbsp;&nbsp;<span id="pwCheck"></span></p>
							</td>
						</tr>
						<tr>
							<td><input type="password" size="17" maxlength="20" id="signInPw"
								name="userPw" class="form-control tooltipstered" 
								maxlength="20" required="required" aria-required="true"
								style="ime-mode: inactive; margin-bottom: 25px; height: 40px; border: 1px solid #d9d9de"
								placeholder="최소 8자"></td>
						</tr>
						<tr>
							<td class="col-xs-4">
			                    <label>
			                        <input type="checkbox" id="auto-login" name="autoLogin" checked="checked"> 자동로그인
			                    </label>
			                </td>
						</tr>
						
						<tr>
							<td style="width: 100%; text-align: center; colspan: 2;"><input
								type="button" value="로그인" class="btn form-control tooltipstered" id="signIn-btn"
								style="background-color: #ff52a0; margin-top: 0; height: 40px; color: white; border: 0px solid #f78f24; opacity: 0.8">
							</td>
						</tr>
						<tr>
							<td
								style="width: 100%; text-align: center; colspan: 2; margin-top: 24px; padding-top: 12px; border-top: 1px solid #ececec">

								<a class="btn form-control tooltipstered" href="<c:url value='/user/join'/>"
								style="cursor: pointer; margin-top: 0; height: 40px; color: white; background-color: orange; border: 0px solid #388E3C; opacity: 0.8">
									회원가입</a>
							</td>
						</tr>
					</table>
        </form>

        
    </div>
    <!-- /.form-box -->
</div>
<!-- /.register-box -->

<jsp:include page="../include/plugin-js.jsp" />
<script>
$(function() {
	
	const getIdCheck= RegExp(/^[a-zA-Z0-9]{4,14}$/);
	const getPwCheck= RegExp(/([a-zA-Z0-9].*[!,@,#,$,%,^,&,*,?,_,~])|([!,@,#,$,%,^,&,*,?,_,~].*[a-zA-Z0-9])/);
	let chk1 = false, chk2 = false;
	
	//로그인 검증~~
	//ID 입력값 검증.
	$('#signInId').on('keyup', function() {
		if($("#signInId").val() == ""){
			$('#signInId').css("background-color", "pink");
			$('#idCheck').html('<b style="font-size:14px;color:red;">[아이디는 필수!]</b>');
			chk1 = false;
		}		
		
		//아이디 유효성검사
		else if(!getIdCheck.test($("#signInId").val())){
			$('#signInId').css("background-color", "pink");
			$('#idCheck').html('<b style="font-size:14px;color:red;">[영문자,숫자 4-14자~]</b>');	  
			chk1 = false;
		} else {
			$('#signInId').css("background-color", "aqua");
			$('#idCheck').html('<b style="font-size:14px;color:green;">[참 잘했어요]</b>');
			chk1 = true;
		}
	});
	
	//패스워드 입력값 검증.
	$('#signInPw').on('keyup', function() {
		//비밀번호 공백 확인
		if($("#signInPw").val() === ""){
		    $('#signInPw').css("background-color", "pink");
			$('#pwCheck').html('<b style="font-size:14px;color:red;">[패스워드는 필수!]</b>');
			chk2 = false;
		}		         
		//비밀번호 유효성검사
		else if(!getPwCheck.test($("#signInPw").val()) || $("#signInPw").val().length < 8){
		    $('#signInPw').css("background-color", "pink");
			$('#pwCheck').html('<b style="font-size:14px;color:red;">[특수문자 포함 8자이상]</b>');
			chk2 = false;
		} else {
			$('#signInPw').css("background-color", "aqua");
			$('#pwCheck').html('<b style="font-size:14px;color:green;">[참 잘했어요]</b>');
			chk2 = true;
		}
		
	});
	
	//로그인 버튼 클릭 이벤트
	$("#signIn-btn").click(function() {
		if(chk1 && chk2) {
			//ajax통신으로 서버에서 값 받아오기
			const id = $('#signInId').val();
			const pw = $('#signInPw').val();
			//is()함수는 상태여부를 판단하여 논리값을 리턴합니다.
			let autoLogin = $("input[name=autoLogin]").is(":checked");
			
			console.log("id: " + id);
			console.log("pw: " + pw);
			console.log("auto: " + autoLogin);
			
			const userInfo = {
					userId : id,
					userPw : pw,
					autoLogin : autoLogin
			};
			
			$.ajax({
				type: "POST",
				url: "/mvc/user/loginCheck",
				headers: {
	                "Content-Type": "application/json"
	            },
				data: JSON.stringify(userInfo),
				dataType : "text",
				success: function(data) {
					console.log("result: " + data);	
					
					if(data === "idFail") {
						$('#signInId').css("background-color", "pink");
						$('#idCheck').html('<b style="font-size:14px;color:red;">[회원가입 먼저~~]</b>');
						$('#signInPw').val("");
						$('#signInId').focus();
						chk2 = false;
				    } else if(data === "pwFail") {
						$('#signInPw').css("background-color", "pink");
						$('#signInPw').val("");
						$('#signInPw').focus();
						$('#pwCheck').html('<b style="font-size:14px;color:red;">[비밀번호가 틀렸어요!]</b>');
						chk2 = false;
					} else if(data === "loginSuccess") {
						self.location="/mvc";
					}
					
				}
			});
			
		} else {
			alert("입력정보를 다시 확인하세요!");
		}
	});
});
</script>

</body>
</html>
