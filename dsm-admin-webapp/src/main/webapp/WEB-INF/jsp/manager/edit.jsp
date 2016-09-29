<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../global/header.jsp" %>
</head>

<body>
<div id="content">
	<div id="content-header">
		<div id="breadcrumb"> 
	  	<a href="${basepath }/main/index" target="_top" title="首页" class="tip-bottom"><i class="icon-home"></i> 主页</a> 
		  <a href="#" class="tip-bottom">系统设置</a> 
		  <a href="#" class="tip-bottom">管理员列表</a> 
		  <a href="#" class="current">编辑管理员</a> 
	  </div>	  
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
        	<div class="span12">
              <div class="widget-box user-add-box">
                <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
                  <h5>新增管理员</h5>
                </div>
                <div class="widget-content nopadding">
                  <form id="addForm" action="javascript:void(0);" method="post" class="form-horizontal">
                    <input name="id" value="${manager.id }" type="hidden">
                    <div class="control-group">
                      <label class="control-label" for="username">账号 :</label>
                      <div class="controls">
                        <input id="username" name="username" class="span7" value="${manager.username }" placeholder="必填，长度不大于20" data-valid="[{rule:'not_empty'}]" maxlength="20">
                      </div>
                    </div>
                    
                    <div class="control-group">
                      <label class="control-label" for="password">密码 :</label>
                      <div class="controls">
                        <input id="password" name="password" type="password" class="span7" value="${manager.password }" placeholder="必填，长度不大于20" data-valid="[{rule:'not_empty'}]" maxlength="20">
                      </div>
                    </div>
                    
                    <div class="control-group">
                      <label class="control-label" for="password">权限角色 :</label>
                      <div class="controls">
						<select name="roleId" class="span4">
							<c:forEach items="${roleList }" var="role">
								<c:if test="${manager.roleId == role.id }">
									<option value="${role.id }" selected="selected">${role.roleName }</option>
								</c:if>
								<c:if test="${manager.roleId != role.id }">
									<option value="${role.id }">${role.roleName }</option>
								</c:if>
							</c:forEach>
						</select>                         
                      </div>
                    </div>
                    
                    <div class="control-group">
                      <label class="control-label" for="phone">手机号码:</label>
                      <div class="controls" >
                        <input id="phone" name="phoneNo" class="span7" value="${manager.phoneNo }"  placeholder="选填，长度不大于16" data-valid="[{rule:'mobile'}]" maxlength="16">
                      </div>
                    </div>
                    
                    <div class="control-group">
                      <label class="control-label" for="email">邮箱:</label>
                      <div class="controls">
                        <input id="email" name="email" type="email" class="span7" value="${manager.email }" placeholder="选填，长度不大于50" data-valid="[{rule:'email'}]" maxlength="50">
                      </div>
                    </div>
                    
                    <div class="form-actions">
                    	<label class="control-label"></label>
                      <button type="submit" class="btn btn-success">保存</button>
                   		<a class="btn btn-default" href="${basepath }/manager/toList">返回</a>
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
var W = window.top;
var $table = null;
var $form = null;
$(function(){
	$form = $("#addForm").inputValid();
	
	$("#addForm .form-actions button.btn-success").click(function(){
		if ($form.validate_all()) {
			$.ajax({
				type : "POST",
				dateType : "json",
				data : $("#addForm").serialize(),
				url : "${basepath}/manager/saveOrUpdate",
				success : function(json){
					if(JSON.parse(json).code == '000000'){
						W.$.alert("保存成功！", 1);
						window.location.href = "${basepath}/manager/toList";
					}else{
						W.$.alert(json.msg);
					}
				},
				
				error : function(){
					W.$.alert("保存失败");
				}
			});
		}
	});
});


$.extend({
	
	
});
</script>
</body>
</html>