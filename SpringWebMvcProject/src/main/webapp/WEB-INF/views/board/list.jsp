<%@ page contentType="text/html;charset=UTF-8" language="java" 
		trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>

<head>
	<jsp:include page="../include/static-head.jsp" />
	
<style type="text/css">
	#count-per-page .btn {
		padding: 0 12px;
	}
</style>
</head>

<body class="hold-transition skin-blue sidebar-mini layout-boxed">

<div class="wrapper">

    <!-- Main Header -->
    <jsp:include page="../include/header.jsp" />

    <!-- Left side column. contains the logo and sidebar -->
    <jsp:include page="../include/side-bar.jsp" />

    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                	게시판
                <small>목록페이지</small>
            </h1>
            <ol class="breadcrumb">
                <li><i class="fa fa-edit"></i> article</li>
                <li class="active"><a href="#"> list</a></li>
            </ol>
        </section>

        <!-- Main content -->
        <section class="content container-fluid">

            <div class="col-lg-12">
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">게시글 목록</h3>
                    </div>
                    
                    <span id="count-per-page" style="float: right;">
                        <i class="fa fa-list">목록 보기</i>
	                       <input class="btn" type="button" value="10">  
	                       <input class="btn" type="button" value="20">   
	                       <input class="btn" type="button" value="30">
                    </span>
                    
                    
                    <div class="box-body">
                        <table class="table table-bordered">
                            <tbody>
                            <tr>
                                <th style="width: 30px">#</th>
                                <th>제목</th>
                                <th style="width: 100px">작성자</th>
                                <th style="width: 150px">작성시간</th>
                                <th style="width: 60px">조회</th>
                            </tr>
                            
                            <c:if test="${articles.size() <= 0}">
                               <tr>
	                            <td align="center" colspan="5"><strong>검색 결과가 없습니다!</strong></td>
	                           </tr>                      
                            </c:if>                           
                            
                            <c:if test="${articles.size() > 0}">
	                            <%-- 게시물이 들어갈 공간 --%>
	                            <c:forEach var="article" items="${articles}" >
	                            <tr>
	                                <td>${article.boardNo}</td>
	                                <td>
	                                	<a href="<c:url value='/board/content/${article.boardNo}?page=${pageCreator.criteria.page}&countPerPage=${pageCreator.criteria.countPerPage}&keyword=${param.keyword}&condition=${param.condition}' />">
	                                		${article.title}&nbsp;
	                                	</a>
	                                	<c:if test="${article.newMark}">
	                                		<span class="label label-success">new</span>
	                                	</c:if>
	                                </td>
	                                <td>${article.writer}</td>
	                                <td>
	                                <fmt:formatDate value="${article.regDate}" pattern="yyyy년 MM월 dd일 a hh:mm" />               
	                               
	                                </td>
	                                <td>${article.viewCnt}</td>
	                            </tr>
	                            </c:forEach>
                            </c:if>
                            
                            </tbody>
                        </table>
                    </div>  

					<div class="box-footer">
					      <div class="text-center"> 
					          <ul class="pagination">
					          
					          	  <c:if test="${pageCreator.prev}">                                     
					              	<li><a href="<c:url value='/board/list/${pageCreator.beginPage - 1}${pageCreator.makeSearchURI()}' />">이전</a></li>                                
					              </c:if>
					                            
					              <c:forEach var="num" begin="${pageCreator.beginPage}" end="${pageCreator.endPage}">         
					              	<li <c:out value="${pageCreator.criteria.page == num ? 'class=active' : '' }" />><a href="<c:url value='/board/list/${num}${pageCreao}'/>">${num}</a></li>                                
					              </c:forEach> 
					                     
					              <c:if test="${pageCreator.next}">     
					              	<li><a href="<c:url value='/board/list/${pageCreator.endPage + 1}${pageCreator.makeSearchURI()}' />">다음</a></li>
					         	  </c:if>
					         </ul>
					      </div>
					</div>                    
                    
                    
                    
                    <div class="box-footer">
                    	<div class="col-sm-2"></div>
                        <div class="form-group col-sm-2">
                            <select id="condition" class="form-control" name="condition">
                                <option value="title" <c:out value="${param.condition == 'title' ? 'selected' : ''}"/>>제목</option>
                                <option value="content" <c:out value="${param.condition == 'content' ? 'selected' : ''}"/>>내용</option>
                                <option value="writer" <c:out value="${param.condition == 'writer' ? 'selected' : ''}"/>>작성자</option>
                                <option value="titleContent" <c:out value="${param.condition == 'titleContent' ? 'selected' : ''}"/>>제목+내용</option>
                            </select>
                        </div>
                        <div class="form-group col-sm-6">
                            <div class="input-group">
                                <input type="text" class="form-control" name="keyword" id="keywordInput" value="${param.keyword}" placeholder="검색어">
                                <span class="input-group-btn">
                                    <button type="button" class="btn btn-primary btn-flat" id="searchBtn">
                                        <i class="fa fa-search"></i> 검색
                                    </button>
                                </span>
                            </div>
                        </div>
                        <div class="col-sm-2 pull-right">
                            <button type="button" class="btn btn-success btn-flat" id="writeBtn">
                                <i class="fa fa-pencil"></i> 글쓰기
                            </button>
                        </div>
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
	
	//JQuery라이브러리 사용.
	//JQuery 시작 구문
	$(document).ready(function() {
		
		//글쓰기 완료 시 띄울 알림창 처리
		const result = "${message}";
		
		if(result === "regSuccess") {
			alert("게시글 등록이 완료되었습니다.");
		} else if(result === "delSuccess") {
			alert("게시글 삭제가 완료되었습니다.");
		}
		
		//글쓰기 버튼 클릭 이벤트 처리
		$("#writeBtn").on("click", function() {
			self.location = "/mvc/board/write";
		});
		
		//목록 개수가 변동하는 클릭 이벤트 처리
		$("#count-per-page .btn").on("click", function() {
			//console.log($(this).val());
			let count = $(this).val();
			self.location = "/mvc/board/list/" +
					"${pageCreator.criteria.page}" +
					"?countPerPage="+count;
		});
		
		//검색 버튼 클릭 이벤트 처리
		$("#searchBtn").on("click", function() {
			self.location = "/mvc/board/list/1"
							+ "?keyword=" + $("#keywordInput").val()
							+ "&condition=" + $("select option:selected").val();
							
		});
		
		//엔터키 입력 이벤트
		$("#keywordInput").keydown(function (key) {
			 
	        if(key.keyCode == 13){//키가 13이면 실행 (엔터는 13)
	        	$("#searchBtn").click();
	        }
	 
	    });
		
	});
	
	
</script>

</body>

</html>









