<%@ include file="/common/taglib.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:url var="APIurl" value="/api-admin-new"/>
<c:url var="NewURL" value="/admin-new"/>
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
					<div class="row">
						<div class="col-xs-12">
							<c:if test="${not empty messageResponse}">
	                            <div class="alert alert-${alert}">
	                                ${messageResponse}
	                            </div>
	                        </c:if>
							<div class="widget-box table-filter">
								<div class="table-btn-controls">
									<div class="pull-right tableTools-container">
										<div class="dt-buttons btn-overlap btn-group">
											<a flag="info"
												class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
												title='Thêm bài viết' href='<c:url value="/admin-new?type=edit"/>'>
													<span>
														<i class="fa fa-plus-circle bigger-110 green"></i>
													</span>
											</a>
											<button id="btnDelete" type="button"
													class="dt-button buttons-html5 btn btn-white btn-primary btn-bold" data-toggle="tooltip" title='Xóa bài viết'>
														<span>
															<i class="fa fa-trash-o bigger-110 red"></i>
														</span>
											</button>
										</div>
									</div>
								</div>
							</div>
							<div class="row" >
								<div class="col-xs-12">
									<div class="table-responsive">
										<table class="table table-bordered">
											<thead>
											<tr>
												<th><input type="checkbox" id="checkAll"></th>
												<th>Tên bài viết</th>
												<th>Mô tả ngắn</th>
												<th>Ngày tạo</th>
												<th>Thao tác</th>
											</tr>
											</thead>
											<tbody>
											<!--item la 1 model trong listResult -->
												<c:forEach var="item" items="${model.listResult}">
												
												<tr>
													<td><input type="checkbox" id="checkbox_${item.id}"value="${item.id}"></td>
													<td>${item.title}</td>
													<td>${item.shortDescription}</td>
													<td>${item.createdDate}</td>
													<td>
													
														<c:url var="editURL" value="/admin-new">
															<c:param name="type" value="edit"/>
															<c:param name="id" value="${item.id}"/>
															
														</c:url>
													
														<a class="btn btn-sm btn-primary btn-edit" data-toggle="tooltip"
																   title="Cập nhật bài viết" href='${editURL}'><i class="fa fa-pencil-square-o" aria-hidden="true"></i>
														</a>
													</td>
												</tr>
												</c:forEach>
											</tbody>
										</table>
										<ul class="pagination" id="pagination"></ul>
										
										<!-- name trong the input la ten trong API khi Submit-->
										<!-- VD: http://localhost:8081/admin-new?page=1&maxPageItem=2&.... -->
										<input type="hidden" value="" id="page" name="page"/>
										<input type="hidden" value="" id="maxPageItem" name="maxPageItem"/>
										<input type="hidden" value="" id="sortName" name="sortName"/>
										<input type="hidden" value="" id="sortBy" name="sortBy"/>
										<input type="hidden" value="" id="type" name="type"/>
									</div>
								</div>
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
				
//		click submit
				onPageClick: function (event, page) {
					if(currentPage != page){
						$('#page').val(page);
						// gan value cua page vao the input co id page = #page
						$('#sortName').val('title');
						$('#sortBy').val('desc');
						$('#type').val('list');
						$('#maxPageItem').val(limit);
						$('#formSubmit').submit();
						
//			khi submit se gui den /admin-new cung voi cac para(name) va g.tri cua the input
//		VD: /admin-new?page=1&maxPageItem=2&sortName=title&sortBy=desc
					}
				}
			});
		});

		$("#btnDelete").click(function(){
			var data={};
//			lấy giá trị của các check box đang được checked  
			var ids = $('tbody input[type=checkbox]:checked').map(function () {
		            return $(this).val();
			}).get();

// 			dữ liệu trong data để dạng key value 
			data['ids']=ids;
			deleteNew(data);
		});

//		call API su dung AJAX	 
		function deleteNew(data){
        $.ajax({
            url: '${APIurl}',
            type: 'DELETE',
            contentType: 'application/json',
//          dinh nghia du lieu client gui len server la json
            data: JSON.stringify(data),
//          chuyen data tu JAVASCRIPTOBJECT sang JSON

            success: function(result){
                window.location.href = "${NewURL}?type=list&page=1&maxPageItem=2&message=delete_success&alert=success"
            },
            error: function(error){
            	window.location.href = "${NewURL}?type=list&page=1&maxPageItem=2&message=error_system&alert=danger"
            },
        });
    }
	</script> 
</body>
</html>