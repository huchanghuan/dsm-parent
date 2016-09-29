<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../global/header.jsp" %>
<link rel="stylesheet" href="${basepath }/res/css/metro.css">
<link rel="stylesheet" href="${basepath }/res/fonticon/iconfont.css" />
</head>

<body>
<div id="content">
	<div id="content-header">
		<div id="breadcrumb"> 
	  	<a href="${basepath }/main/index" target="_top" title="首页" class="tip-bottom"><i class="icon-home"></i> 主页</a> 
		  <a href="#" class="tip-bottom">系统设置</a> 
		  <a href="#" class="current">权限设置</a> 
	  </div>
	</div>
    
	<div class="container-fluid">
		<div class="row-fluid">
        	<div class="span12">
              
              <div class="widget-box cool-class-box" style="border:none;">
                  
                  <div class="widget-content nopadding"  style="border:none;">
                  		<h5 class="span6">权限角色<i class="icon-plus"></i></h5>
	                    <h5 class="span6">分配权限<i class="icon-save"></i></h5>
	                    
	                    <div class="span6 rank1">
	                    	<ul>
	                    		<c:forEach items="${roleList }" var="role" varStatus="vs">
	                    			<c:if test="${role.id==1 }">
	                    				<li data-id="${role.id }" class="active"><a href="javascript:void(0);"><i class="icon iconfont"></i> <span class="name">${role.roleName }</span></a></li>
	                    			</c:if>
	                    			<c:if test="${role.id!=1 }">
	                    				<li data-id="${role.id }"><a href="javascript:void(0);"> <span class="name">${role.roleName }</span></a><i class="icon-pencil" style="margin-right:25px;"></i><i class="icon-remove"></i></li>
	                    			</c:if>
	                    		</c:forEach>
	                        </ul>
	                    </div>
	                    <div class="span6 rank2">
	                    	<ul id="tree" class="ztree" style="overflow: auto;max-height: 500px;">
	                        </ul>
	                    </div>
						
                  </div>
                  
                </div>
                
            </div>
        </div>
	</div>
</div>

<div class="popUpCover popduration"></div>

<div class="popAddClass popBox">
	<div class="popTitle">
    	新增权限角色
    </div>
    <div class="row-fluid clearfix">
    	<form>
    	<div class="control-group">
            <label class="control-label span4" for="roleName">角色名称 :</label>
            <div class="controls span8">
                <input id="roleName" name="roleName" type="text" placeholder="输入名称" data-valid="[{rule:'not_empty'}]" maxlength="10" />
                <span class="help-inline"></span>
            </div>
        </div>
        
        
    	<div class="control-group">
            <label class="control-label span4"></label>
            <div class="controls span8">
                <button type="submit" class="btn btn-success">保存</button>
                <button type="button" class="btn btn-primary">返回</button>
            </div>
        </div>
        </form>
    </div>
</div>

<div class="popEditClass popBox">
	<div class="popTitle">
    	编辑权限角色
    </div>
    <div class="row-fluid clearfix">
    <form>
    	<div class="control-group">
            <label class="control-label span4" for="roleName">角色名称 :</label>
            <div class="controls span8">
                <input id="roleName" name="roleName" type="text" placeholder="输入名称" data-valid="[{rule:'not_empty'}]" maxlength="10" />
                <span class="help-inline"></span>
            </div>
        </div>
        
        
    	<div class="control-group">
            <label class="control-label span4"></label>
            <div class="controls span8">
                <button type="submit" class="btn btn-success">保存</button>
                <button type="button" class="btn btn-primary">返回</button>
            </div>
        </div>
      </form>
    </div>
</div>


<%@ include file="../global/footer.jsp" %>

<script src="${basepath }/res/js/jquery.ztree.all-3.5.min.js"></script>
<script src="${basepath }/res/js/inputvalid.js"></script>
<script>
var ztree = null;
var W = window.top;
var form1 = null, form2 = null;
var setting = {
	    check: {
	        enable: true
	    },
	    view: {
	        dblClickExpand: false,
	        showLine: true,
	        selectedMulti: false
	    },
	    data: {
	        simpleData: {
	            enable:true,
	            idKey: "id",
	            pIdKey: "pId",
	            rootPId: ""
	        }
	    },
	    callback: {
	        beforeClick: function(treeId, treeNode) {
	            var zTree = $.fn.zTree.getZTreeObj("tree");
	            if (treeNode.isParent) {
	                zTree.expandNode(treeNode);
	                return false;
	            } else {
	                return true;
	            }
	        },
	        onClick: zTreeOnClick
	    }
	};

$(function(){
	
	form1 = $(".popAddClass form").inputValid();
	form2 = $(".popEditClass form").inputValid();
	
	//初始化树
	initZTree(1);
	
	//动态切换
	changeRole();
	
	//保存权限
	savePermission();
	
	//添加角色
	addRole();
	
	//删除角色
	delRole();
	
	//编辑角色
	editRole();
});

function editRole() {
	$(".rank1 li i.icon-pencil").click(function(){
		var $li = $(this).parents("li");
		var roleId = $li.data("id");
		var roleName = $li.find(".name").html();
		$(".popEditClass input[name='roleName']").val(roleName);
		$(".popEditClass, .popUpCover").show();
		
		//保存
		$(".popEditClass button.btn-success").click(function(){
			roleName = $(".popEditClass input[name='roleName']").val();
			var params = {"roleId":roleId,"roleName":roleName};
			if (form2.validate_all()) {
				$.editRole(params,function(){
					$li.find(".name").html(roleName);
					$(".popEditClass, .popUpCover").hide();
				});
			}
		});
		
		//关闭
		$(".popEditClass button.btn-primary").click(function(){
			$(".popEditClass, .popUpCover").hide();
		});
	});
}

function delRole() {
	$(".rank1 li i.icon-remove").click(function(){
		var $li = $(this).parents("li");
		var roleId = $li.data("id");
		var params = {"roleId":roleId};
		W.$.confirm("确定删除当前角色吗?",function(){
			$.get('${basepath}/role/delete',params,function(result){
				if (result.code == '000000') {
					$li.remove();
					W.$.alert("已删除", 1);
				} else {
					W.$.alert("删除失败："+result.msg);
				}
			},'JSON');
		},1, function(){});
	});
}

function addRole() {
	$(".widget-content > h5 > i.icon-plus").click(function(){
		$(".popAddClass input[name='roleName']").val("");
		$(".popAddClass, .popUpCover").show();
		
		//保存
		$(".popAddClass button.btn-success").click(function(){
			var params = {"roleName":$(".popAddClass input[name='roleName']").val()};
			if (form1.validate_all()) {
				$.addRole(params);
			}
		});
		
		//关闭
		$(".popAddClass button.btn-primary").click(function(){
			$(".popAddClass, .popUpCover").hide();
		});
	});
}

function savePermission() {
	$(".widget-content > h5 >i.icon-save").click(function(){
		W.$.confirm("确定保存当前角色的权限吗?",function(){
			var permissionStr = ztree.getCheckedNodes(true).map(function(item, index){
				return item.id;
			}).join("/");
			var roleId = $(".rank1 ul > li.active").data("id");
			var params = {"roleId":roleId,"permissionStr":permissionStr};
			$.post('${basepath}/role/savePermission',params,function(result){
				if (result.code == '000000') {
					W.$.alert("已保存", 1);
				} else {
					W.$.alert("保存失败："+result.msg);
				}
			},'JSON');
		},1, function(){});
	});
}

function changeRole(){
	$(".rank1 ul > li > a").click(function(){
		
		var $li = $(this).parent("li");
		$li.siblings().removeClass("active");
		$li.addClass("active");
		initZTree($li.data("id"));
	});
}

function initZTree(roleId) {
	$.ajax({
		type:"POST",
		dataType:"json",
		data:{
			roleId:roleId
		},
		url:"${basepath}/role/findPermissionList",
		success:function(responseData){
			if(responseData){
			    ztree = $.fn.zTree.init($("#tree"), setting, responseData);
			}
		}
	});

}

function zTreeOnClick () {
	
}

$.extend({
	saveRole : function(_params) {
		var AjaxURL = "${basepath}/role/saveOrUpdate"; //API接口
		$.ajax({
			type: "POST",
			dataType: "json",
			url: AjaxURL,
			data: _params,
			success: function (json) {
				if(json.code == '000000'){//成功
					//修改成功重新刷新页面
					W.$.alert("成功",1);
					window.location.reload();
				}else {
					W.$.alert("错误")
				}
			},
			error: function(e) {
				W.$.alert("异常");
			}
		});
	},
	editRole : function(_params, _fun) {
		var AjaxURL = "${basepath}/role/updateRoleName"; //API接口
		$.ajax({
			type: "POST",
			dataType: "json",
			url: AjaxURL,
			data: _params,
			success: function (json) {
				if(json.code == '000000'){//成功
					_fun();
					W.$.alert("修改成功",1);
				}else {
					W.$.alert("错误")
				}
			}
		});
	}
});

</script>
</body>
</html>