<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="row col-md-12">

	<div class="commentCnt">
 		<label class="control-label">${commentCnt } 개의 댓글이 있습니다.</label>
		<br>
	</div>

	<table class="table">
		<c:forEach items="${commentList }" var="item" varStatus="i">
			<tr>
				<td style="width: 400px;"><c:out value="${item.c_cmt}" /></td>
				<td style="width: 100px;"><c:out value="${item.std_id}" /></td>
				<td style="width: 200px;"><c:out value="${item.c_regdt}" /></td>
				<td style="width: 100px;">
					<c:set value="${studentVo.std_id}" var="loginStd"/>
					<c:if test="${loginStd eq item.std_id}" var="result">
					<form action="/commentDelete" method="post" id="frmDelete">
						<input type="hidden" name="w_id" value="${writeVo.w_id}">
						<input type="hidden" name="b_id" value="${writeVo.b_id}">
						<input type="hidden" name="c_id" value="${item.c_id}">
						<button type="submit" class="btn btn-default btn-xs">삭제</button>
					</form>
					</c:if>
				</td>
			</tr>
		</c:forEach>
	</table>
</div>
