<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<style type="text/css">
table, th, td {
	margin: auto;
	text-align: center;
}
</style>

<script>

	$(document).ready(function() {

		//게시판 추가 이벤트
		$("#createBtn").on("click", function() {
			//학생 이름, 상세주소
			if ($("#cboardName").val().length < 2) {
				alert("게시판이름을 입력해주세요");
				$("#cboardName").focus();
				return false;
			}
			$("#createFrm").submit();
		});
		
		//게시판 추가 이벤트
		$("#updateBtn").on("click", function() {
			//학생 이름, 상세주소
			if ($("#uboardName").val().length < 2) {
				alert("게시판이름을 입력해주세요");
				$("#uboardName").focus();
				return false;
			}
			$("#updateFrm").submit();
		});
	});
	
</script>

<body>

	<form id="createFrm" action="/board/boardCreate" method="post"
		class="form-horizontal" role="form">

		<h2 class="sub-header">게시판 추가</h2>
		<div class="row-right">
			<div class="col-lg-6">
				<div class="input-group">
					<input id="cboardName" name="boardName" type="text"
						class="form-control" placeholder="게시판 이름을 입력하세요"><span
						class="input-group-btn"> <br>
						<button id="createBtn" class="btn btn-default" type="button">추가</button>
						<br>
					</span>
				</div>
				<!-- /input-group -->
			</div>
		</div><!-- /.row -->
	</form>

	<br>
	<hr>
	<br>

	<h2 class="sub-header">게시판 리스트</h2>

	<table class="table table-striped">
		<thead>
			<tr>
				<th>게시판ID</th>
				<th>게시판이름</th>
				<th>생성일자</th>
				<th>사용여부</th>
				<th>수정</th>
			</tr>
		</thead>
	</table>

	<ul class="list-group">

		<c:forEach items="${boardList}" var="vo">
			<li class="list-group-item">
				<form id="updateFrm" action="/board/boardUpdate" method="post"
					class="form-horizontal" role="form">

					<!--  items 루프할 대상-->
					<input type="hidden" name="b_id" value="${vo.b_id}">
					${vo.b_id} <input type="text" class="form-control col-lg-3"
						name="boardName" id="uboardName" value="${vo.b_name}">
					<fmt:formatDate value="${vo.b_regdt }" pattern="yyyy-MM-dd" />

					<select name="b_useny" class="form-control" id="sel1">
						<option <c:if test="${vo.b_useny eq 'Y'}">selected</c:if>>Y</option>
						<option <c:if test="${vo.b_useny eq 'N'}">selected</c:if>>N</option>
					</select>

					<button id="updateBtn" class="btn btn-default" type="submit">수정</button>
					<br>
				
				</form>
			</li>
		</c:forEach>
	</ul>
			

</body>
</html>