<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title><dec:title default="Trang chu" /></title>

	<link href="<c:url value='/template/web/bootstrap/css/bootstrap.min.css' />" rel="stylesheet" type="text/css" media="all"/>
    <link href="${pageContext.request.contextPath}/template/web/css/style.css" rel="stylesheet" type="text/css" media="all"/>
	
	<!-- 2 cach href nhu nhau -->

</head>
<body>
	<!-- Header -->
	<%@ include  file = "/common/web/header.jsp"%>
	<!-- Header -->
	
	<!-- Container -->
	<div class="container">
		<!-- dat ten prefix = "dec" trong file taglib.jsp-->
		<dec:body/>
	</div>

	<!-- Footer -->
	<%@ include  file = "/common/web/footer.jsp"%>
	<!-- Footer -->
	
	
	<!-- Bootstrap core JavaScript -->
	<script type="text/javascript" src="<c:url value='/template/web/jquery/jquery.min.js' />" ></script>
	<script type="text/javascript" src="<c:url value='/template/web/bootstrap/js/bootstrap.bundle.min.js' />"></script>

</body>
</html>