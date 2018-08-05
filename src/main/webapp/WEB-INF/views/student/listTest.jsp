<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	listTest 
	
	<c:forEach items="${studentVo}" var="vo">
		<!--  items 루프할 대상-->
		
			<%--tr태그 클릭시 , 상세페이지로 이동 --%>
			<label>${vo.std_id}</label>
			<label>${vo.name}</label>
			<label><fmt:formatDate value="${vo.reg_dt }" pattern="yyyy-MM-dd" /></label>
	
	</c:forEach>
</body>
</html>