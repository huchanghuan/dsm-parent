<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    
<head>
        <title>剁手猫-OMS后台管理系统</title><meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="${basepath}/res/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${basepath}/res/css/bootstrap-responsive.min.css" />
        <link rel="stylesheet" href="${basepath}/res/css/matrix-login.css" />
        <link href="${basepath}/res/font-awesome/css/font-awesome.css" rel="stylesheet" />
		<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700,800' rel='stylesheet' type='text/css'>

    </head>
    <body>
        <div id="loginbox">   
            <form id="loginform" class="form-vertical" action="javascript:void(0);" method="post">
				<div class="control-group normal_text"> <h3><img src="${basepath }/res/images/logo.png" alt="Logo" /></h3></div>
               <div class="">
	                <div class="alert alert-danger alert-dismissible text-center" role="alert" style="margin:0px 20px;display:none;">
					  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					  <strong>${msg }</strong>
					</div>  
                </div>
                
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lg"><i class="icon-user"></i></span><input type="text" name="username" required placeholder="Username" />
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_ly"><i class="icon-lock"></i></span><input type="password" name="password" required placeholder="Password" />
                        </div>
                    </div>
                </div>
                <div class="form-actions">
                    <!-- <span class="pull-left"><a href="#" class="flip-link btn btn-info" id="to-recover">Lost password?</a></span> -->
                    <span class="pull-left" style="margin-left:30px;"><button type="submit" class="btn btn-success"> Login</button></span>
                </div>
            </form>
            <!-- <form id="recoverform" action="#" class="form-vertical">
				<p class="normal_text">Enter your e-mail address below and we will send you instructions how to recover a password.</p>
				
                    <div class="controls">
                        <div class="main_input_box">
                            <span class="add-on bg_lo"><i class="icon-envelope"></i></span><input type="text" placeholder="E-mail address" />
                        </div>
                    </div>
               
                <div class="form-actions">
                    <span class="pull-left"><a href="#" class="flip-link btn btn-success" id="to-login">&laquo; Back to login</a></span>
                    <span class="pull-right"><a class="btn btn-info">Reecover</a></span>
                </div>
            </form> -->
        </div>
        <script src="${basepath}/res/js/jquery.min.js"></script>  
        <script src="${basepath}/res/js/jquery.validate.js"></script>  
        <script src="${basepath}/res/js/bootstrap.min.js"></script>  
        <script src="${basepath}/res/js/matrix.login.js"></script> 
        <script>
        	$(function(){
        		
        		if (window != top) 
        		top.location.href = location.href; 
        		
        		_alert();
        		
        		$("#loginform button[type='submit']").click(function(){
        			var params = $("#loginform").serialize();
        			$.post("${basepath}/login/login", params, function(resultJson){
        				if (resultJson.code == '000000') {
        					top.location.href="${basepath}/main/index";
        				} else {
        					$("#loginform div.alert strong").html(resultJson.msg);
        					_alert();
        				}
        			},'json');
        		});
        	});
        	
        	function _alert(){
        		var $alert = $("#loginform div.alert");
        	    if ($alert.find("strong").html()) {
        	    	$alert.fadeIn(500);
        	    	setTimeout(function(){
        	    		$alert.fadeOut(500);
        	    	}, 4000);
        	    }
        	}
        </script>
    </body>

</html>
