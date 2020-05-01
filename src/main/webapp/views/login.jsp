<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/common/taglib.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
</head>
<body>
	<div class="card card-container">
		
            <p id="profile-name" class="profile-name-card"></p>
            <form class="form-signin" action="<c:url value='/dang-nhap'/>" id="formLogin" method="post">
                
                <input type="text" id="userName" class="form-control" name="userName"
                	placeholder="Tên đăng nhập" required autofocus>
                <input type="password" id="password" class="form-control" name="password"
                	placeholder="Mật khẩu" required>
                	
                <div id="remember" class="checkbox">
                    <label>
                        <input type="checkbox" value="remember-me"> Ghi nhớ
                    </label>
                </div>
                
                <!-- gui action len url  --> 
                <input type="hidden" value="login" id="action" name="action"/>
                
                <button class="btn btn-lg btn-primary btn-block btn-signin" type="submit">Đăng nhập</button>
            	
            </form><!-- /form -->
            
            <c:if test="${not empty message}">
	            <div class="alert alert-${alert}">
	  				${message}
				</div>
			</c:if>
			
            <a href="#" class="forgot-password">
                Quên mật khẩu?
            </a>
        </div><!-- /card-container -->
	  
</body>
</html>