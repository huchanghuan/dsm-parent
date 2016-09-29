<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../global/header.jsp" %>
</head>

<body>
<div id="content">
	<div id="content-header">
	  	<div id="breadcrumb"> 
	  	<a href="${basepath }/main/index" target="_top" title="首页" class="tip-bottom"><i class="icon-home"></i> 主页</a> 
		  <a href="#" class="tip-bottom">商品管理</a> 
		  <a href="#" class="current">添加商品</a> 
	  </div>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
        	<div class="span12">
              <div class="widget-box user-add-box">
                <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
                  <h5>新增用户</h5>
                </div>
                <div class="widget-content nopadding">
                  <form action="javascript:void(0);" method="post" class="form-horizontal">
                    <div class="control-group">
                      <label class="control-label" for="phone">用户手机号码 :</label>
                      <div class="controls">
                        <input id="phone" name="phone" type="tel" class="span11" data-valid="[{rule:'not_empty'},{rule:'mobile'}]" placeholder="必填">
                      </div>
                    </div>
                    <div class="control-group">
                      <label class="control-label" for="nickname">用户昵称 :</label>
                      <div class="controls">
                        <input id="nickname" name="nickname" type="text" class="span11" placeholder="选填，长度不大于20" maxlength="20">
                      </div>
                    </div>
                    <div class="control-group">
                      <label class="control-label" for="gender">用户性别 :</label>
                      <div class="controls">
                        <select id="gender" name="sex" style="width:100px;">
                        	<option value="-1">女</option>
                        	<option value="1">男</option>
                        	<option value="0">未知</option>
                        </select>
                      </div>
                    </div>
                    <div class="control-group">
                      <label class="control-label">用户密码</label>
                      <div class="controls">
                        <input id="psw" name="passwd" type="password" class="span11" placeholder="必填，长度不大于20" data-valid="[{rule:'not_empty'}]" maxlength="20">
                      </div>
                    </div>
                    <div class="control-group">
                      <label class="control-label">再次确认密码</label>
                      <div class="controls">
                        <input id="psw1" name="password1" type="password" class="span11" placeholder="再次确认密码" data-valid="[{rule:'not_empty'},{rule:'confirmPassword',value:'#psw'}]" maxlength="20">
                      </div>
                    </div>
                    
                    
                    <div class="form-actions">
                    	<div class="control-label"></div>
                      <button type="submit" class="btn btn-success">保存</button>
                    </div>
                    
                  </form>
                </div>
              </div>
            </div>
        </div>
	</div>
</div>


<%@ include file="../global/footer.jsp" %>
<script src="${basepath }/res/js/inputvalid.js"></script>
<script type="text/javascript">
var $form = null;
$(function(){
	//验证
	$form = $("div.widget-content > form").inputValid();
	
	$("div.form-actions > button[type='submit']").click(function(){
		if ($form.validate_all()) {
			var params = $("div.widget-content > form").serialize();
			userAdd(params, function(){
				location.href = "${basepath}/user/toList";
			});
		}
	});
});

function userAdd($params, $fun) {
	$.ajax({
		type : 'POST',
		dataType : 'JSON',
		data : $params,
		url : '${basepath}/user/saveOrUpdate',
		success : function(json) {
			if (json.code == '000000') {
				W.$.alert('添加成功',1);
				$fun();
			} else {
				W.$.alert(json.msg);
			}
		},
		error : function(e) {
			W.$.alert('异常');
		}
	});
		
	
}
</script>
</body>
</html>