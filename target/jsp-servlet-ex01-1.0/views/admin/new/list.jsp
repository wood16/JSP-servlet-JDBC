<%@ include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách bài viết</title>
</head>
<body>
	<div class="main-content">
		<form action="<c:url value='/admin-new'/>" id="formSubmit" method="get">
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li>
							<i class="ace-icon fa fa-home home-icon"></i>
							<a href="#">Trang chủ</a>
						</li>
					</ul><!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<div class="row" >
						<div class="col-xs-12">
							<div class="table-responsive">
								<table class="table table-bordered">
									<thead>
									<tr>
										<th>Tên bài viết</th>
										<th>Mô tả ngắn</th>
										<th>Ngày tạo</th>
										
									</tr>
									</thead>
									<tbody>
									<!--item la 1 model trong listResult -->
										<c:forEach var="item" items="${model.listResult}">
										<tr>
											<td>${item.title}</td>
											<td>${item.shortDescription}</td>
											<td>${item.createdDate}</td>
										</tr>
										</c:forEach>
									</tbody>
								</table>
								<ul class="pagination" id="pagination"></ul>
								
								<!-- name trong the input la ten trong API khi Submit-->
								<!-- VD: http://localhost:8081/jsp-servlet-ex01/admin-new?page=1&maxPageItem=2 -->
								<input type="hidden" value="" id="page" name="page"/>
								<input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div><!-- /.main-content -->
	<script type="text/javascript">
		var currentPage = ${model.page};
		var totalPages = ${model.totalPage};
		
		// note var limit = 2;
		var limit = ${model.maxPageItem};
		$(function () {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages: totalPages,
				visiblePages: 5,
				startPage: currentPage,
				onPageClick: function (event, page) {
					if(currentPage != page){
						$('#page').val(page);
						// gan value cua page vao the input co id page
						$('#maxPageItem').val(limit);
						$('#formSubmit').submit();
					}
				}
			});
		});
	</script> 
</body>
</html>