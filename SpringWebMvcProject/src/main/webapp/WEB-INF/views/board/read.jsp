<%@ page language="java" contentType="text/html;charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>

<head>
	<jsp:include page="../include/static-head.jsp" />
</head>

<body class="hold-transition skin-blue sidebar-mini layout-boxed">

<div class="wrapper">

    <!-- Main Header -->
    <jsp:include page="../include/header.jsp" />

    <!-- Left side column. contains the logo and sidebar -->
    <jsp:include page="../include/side-bar.jsp" />

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                게시판
                <small>조회페이지</small>
            </h1>
            <ol class="breadcrumb">
                <li><i class="fa fa-edit"></i> article</li>
                <li class="active"><a href="<c:url value='/board/read'/>"> read</a></li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content container-fluid">

            <div class="col-lg-12">
                <div class="box box-primary">
                
                	
                    <div class="box-header with-border">
                        <h3 class="box-title">글제목 : ${article.title}</h3>
                    </div>
                    <div class="box-body" style="height: 700px">
                        ${article.content}
                    </div>
					<div class="box-footer">
                        <div class="user-block">
                            <img class="img-circle img-bordered-sm" src="<c:url value='/dist/img/user1-128x128.jpg'/>" alt="user image">
                            <span class="username">
                                <a href="#">${article.writer}</a>
                            </span>
                            <span class="description"><fmt:formatDate pattern="yyyy-MM-dd a HH:mm" value="${article.regDate}"/></span>
                        </div>
                    </div>
                    
                    <div class="box-footer">
                        <form id="form-obj" role="form" method="post">
                            <input type="hidden" name="boardNo" value="${article.boardNo}">
                        	<input type="hidden" name="page" value="${criteria.page}">
                        	<input type="hidden" name="countPerPage" value="${criteria.countPerPage}">
                        	<input type="hidden" name="keyword" value="${criteria.keyword}">
                        	<input type="hidden" name="condition" value="${criteria.condition}">
                        </form>
                        <button type="button" id="list-btn" class="btn btn-primary listBtn"><i class="fa fa-list"></i> 목록</button>
                       
                       <c:if test="${login.userId == article.writer}">
                        <div class="pull-right">
                            <button type="button" id="mod-btn" class="btn btn-warning modBtn"><i class="fa fa-edit"></i> 수정</button>
                            <button type="button" id="del-btn" class="btn btn-danger delBtn"><i class="fa fa-trash"></i> 삭제</button>
                        </div>
                       </c:if>
                    </div>
                </div>
            </div>

        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Main Footer -->
    <jsp:include page="../include/footer.jsp" />

</div>
<!-- ./wrapper -->
<jsp:include page="../include/plugin-js.jsp" />

<script type="text/javascript">
$(document).ready(function() {
	
	const msg = "${message}";
	if(msg === "modSuccess") {
		alert("게시물 수정 완료!");
	}
	
	//form태그 객체를 저장할 상수 선언.
	const formObj = $("#form-obj");
	
	//목록으로 돌아가는 버튼 클릭 이벤트 함수
	$("#list-btn").on("click", function() {
		//console.log($(this)); //this는 해당 이벤트가 일어난 DOM객체
		//console.log("버튼이 클릭됨!!");
		self.location = "/mvc/board/list/" 
						+"${criteria.page}" 
						+"?countPerPage=${criteria.countPerPage}"
						+"&keyword=${criteria.keyword}"
						+"&condition=${criteria.condition}";
	});
	
	//수정 화면을 열어주는 요청버튼 클릭 이벤트
	$("#mod-btn").on("click", function() {
		console.log("수정 버튼이 클릭됨!");
		formObj.attr("action", "/mvc/board/modify"); //속성을 제어하는 함수
		formObj.attr("method", "get");
		formObj.submit();
	});
	
	//삭제처리 클릭 이벤트
	$("#del-btn").on("click", function() {
		
		if(!confirm("진짜로 삭제할거??")) {
			return;
		}
		
        formObj.attr("action", "/mvc/board/delete");
        formObj.submit();
     });
	
});
</script>


</body>
</html>




