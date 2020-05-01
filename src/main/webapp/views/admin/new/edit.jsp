<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<c:url var="APIurl" value="/api-admin-new"/>
<c:url var="NewURL" value="/admin-new"/>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Chỉnh sửa bài viết</title>
</head>

<body>
    <div class="main-content">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try { ace.settings.check('breadcrumbs', 'fixed') } catch (e) { }
                </script>
                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="#">Trang chủ</a>
                    </li>
                    <li class="active">Chỉnh sửa bài viết</li>
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
                        <form id="formSubmit">
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Thể loại</label>
                                <div class="col-sm-7">
                                    <select class="form-control" id="categoryCode" name="categoryCode">
                                        
                                        <c:if test="${empty model.categoryCode}">
                                        	<option value="" >Chọn loại bài viết</option>
	                                        <c:forEach var="item" items="${categories}">
	                                        	
	                                        	<option value="${item.code}">
	                                        		${item.name}
	                                        	</option>
	                                        </c:forEach>
                                        </c:if>
                                        <c:if test="${not empty model.categoryCode}">
                                        	<option value="" >Chọn loại bài viết</option>
	                                        <c:forEach var="item" items="${categories}">
	                                        	
	                                        	<option value="${item.code}" <c:if test="${item.code == model.categoryCode}">selected="selected"</c:if>>
	                                        		${item.name}
	                                        	</option>
	                                        </c:forEach>
                                        </c:if>
                                    </select>
                                </div>
                            </div>
                            <br />
                            <br />
                            <br />
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Tiêu đề</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="title" name="title"
                                        value="${model.title}" />
                                </div>
                            </div>
                            <br />
                            <br />
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Hình đại diện</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="thumbnail" name="thumbnail"
                                        value="${model.thumbnail}" />
                                </div>
                            </div>
                            <br />
                            <br />
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Mô tả ngắn</label>
                                <div class="col-sm-7">
                                    <input type="text" class="form-control" id="shortDescription"
                                        name="shortDescription" value="${model.shortDescription}" />
                                </div>
                            </div>
                            <br />
                            <br />
                            <div class="form-group">
                                <label class="col-sm-3 control-label no-padding-right">Nội dung</label>
                                <div class="col-sm-7">
                                    <textarea rows="" cols="" id="content" name="content"
                                        style="width: 610px;height: 175px">${model.content}</textarea>
                                </div>
                            </div>
                            <br />
                            <br />
                            <div class="form-group">
                                <div class="col-sm-12">
                                    <c:if test="${not empty model.id}">
                                        <input type="button" class="btn btn-primary" value="Cập nhật bài viết" id="btnAddOrUpdateNew"/>
                                    </c:if>
                                    <c:if test="${empty model.id}">
                                        <input type="button" class="btn btn-primary" value="Thêm bài viết" id="btnAddOrUpdateNew"/>
                                    </c:if>
                                </div>
                            </div>
                            <input type="hidden" value="${model.id}" id="id" name="id"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script type="text/javascript">

    var editor = "";

    $(document).ready(function(){
        editor = CKEDITOR.replace("content");
    });



	$("#btnAddOrUpdateNew").click(function(e){
        e.preventDefault();
//      tránh khi submit sẽ ko bị gửi nhầm về url đang đứng 
        
        var data = {};
        var formData = $("#formSubmit").serializeArray();
//      lấy tất cả các giá trị trong form có id=formSubmit (các trường có name và value tương ứng)
//      chuyển thành 1 mảng gồm mỗi p.tử có các giá trị name (= name trong form) và value tương ứng

//  VD dũ liệu trong formData
//      {name: "categoryCode", value: "kinh-te"}
//      {name: "title", value: "Bài viết 6"}
//      {name: "thumbnail", value: ""}
//      {name: "shortDescription", value: "education"}
//      {name: "content", value: "Bai viet 6"}

//      i = index
//      v = mỗi giá trị trong formData
        $.each(formData, function(i,v){
//          gán p.tử trong data các name và value tương ứng (kiểu key value )
//  VD      thay vì data[0] để gọi p.tử số 0 thì là data[categoryCode]
            data[""+v.name+""] = v.value;
        });

//      sử dụng CKeditor nên ko dùng được cách lấy data theo name của serializeArray -> phải gán bằng tay
        data["content"] = editor.getData();


        var id = $("#id").val();
        if(id == ""){
            addNew(data);
        }
        else{
            updateNew(data);
        }
    });
    
//      call API sử dụng AJAX
    function addNew(data){
        $.ajax({
            url: '${APIurl}',
            type: 'POST',
            contentType: 'application/json',
//          dinh nghia du lieu client gui len server la json
            data: JSON.stringify(data),
//          chuyen data tu JAVASCRIPTOBJECT sang JSON
            dataType: 'json',
//          dinh nghia du lieu tu server gui lai client la json

            success: function(result){
//			khi them moi thanh cong se tra ve 1 model 
//			model o day = result
//			redirect den trang edit can co id 
            	window.location.href = "${NewURL}?type=edit&id="+result.id+"&message=insert_success&alert=success"
            },
            error: function(error){
            	window.location.href = "${NewURL}?type=edit&message=error_system&alert=danger"
            },
        });
    }

    function updateNew(data){
        $.ajax({
            url: '${APIurl}',
            type: 'PUT',
            contentType: 'application/json',
//          dinh nghia du lieu client gui len server la json
            data: JSON.stringify(data),
//          JSON.stringify(data) du lieu gui len
//          JSON.stringify = chuyen data tu JAVASCRIPTOBJECT sang JSON 
            dataType: 'json',
//          dinh nghia du lieu tu server gui lai client la json

            success: function(result){
            	window.location.href = "${NewURL}?type=edit&id="+result.id+"&message=update_success&alert=success"
            },
            error: function(error){
            	window.location.href = "${NewURL}?type=edit&message=error_system&alert=danger"
            },
        });
    }   

</script>
</body>
</html>