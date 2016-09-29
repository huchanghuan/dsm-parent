<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../global/header.jsp" %>
</head>

<body>
<div id="content">
	<div id="content-header">
		<div id="breadcrumb"> 
	  	<a href="${basepath }/main/index" target="_top" title="首页" class="tip-bottom"><i class="icon-home"></i> 主页</a> 
		  <a href="#" class="tip-bottom">分类管理</a> 
		  <a href="#" class="current">分类列表</a> 
	  </div>
	</div>
    
	<div class="container-fluid">
		<div class="row-fluid">
        	<div class="span12">
              
              <div class="widget-box cool-class-box" style="border:none;">
                  
                  <div class="widget-content nopadding"  style="border:none;">
                  	<h5 class="span6">一级分类</h5>
                    <h5 class="span6">二级分类<i class="icon-plus"></i></h5>
                    
                    <div class="span6 rank1">
                    	<ul>
                    		<c:forEach items="${columnlist }" var="column" varStatus="status">
                    			<c:if test="${column.id != 0 }">
                    				<c:if test="${column.id == 1 }">
                    					<li data-id="${column.id }" class="active"><a href="javascript:void(0);"><i class="icon iconfont"></i> <span class="name">${column.name }</span></a><i class="icon-pencil"></i></li>
                    				</c:if>
                    				<c:if test="${column.id != 1 }">
                    					<li data-id="${column.id }"><a href="javascript:void(0);"><i class="icon iconfont"></i> <span class="name">${column.name }</span></a><i class="icon-pencil"></i></li>
                    				</c:if>
                    			</c:if>
                    		</c:forEach>
                        </ul>
                    </div>
                    <div class="span6 rank2">
                    	<ul style="overflow: auto;max-height: 500px;">
                        </ul>
                    </div>
                    
                    
                  </div>
                  
                </div>
                
            </div>
        </div>
	</div>
</div>

<div class="popUpCover popduration"></div>
<div class="popEdit1Class popBox">
	<div class="popTitle">
    	分类编辑
    </div>
    <div class="row-fluid clearfix">
        <form>
    	<div class="control-group">
            <label class="control-label span4">分类名称 :</label>
            <div class="controls span8">
                <input id="className" type="text" name="name" placeholder="输入名称" maxlength="10" data-valid="[{rule:'not_empty'}]" />
                <span class="help-inline"></span>
            </div>
        </div>
        
        
    	<div class="control-group">
            <label class="control-label span4"></label>
            <div class="controls span8">
                <button type="button" class="btn btn-success">保存</button>
                <button type="button" class="btn btn-primary">返回</button>
            </div>
        </div>
        </form>
    </div>
</div>

<div class="popEdit2Class popBox">
	<div class="popTitle">
    	分类编辑
    </div>
    <div class="row-fluid clearfix">
        <form>
    	<div class="control-group">
            <label class="control-label span4">分类名称 :</label>
            <div class="controls span8">
                <input id="className" type="text" name="name" placeholder="输入名称" maxlength="10" data-valid="[{rule:'not_empty'}]" />
                <span class="help-inline"></span>
            </div>
        </div>
        
        
    	<div class="control-group">
            <label class="control-label span4"></label>
            <div class="controls span8">
                <button type="button" class="btn btn-success">保存</button>
                <button type="button" class="btn btn-primary">返回</button>
            </div>
        </div>
        </form>
    </div>
</div>

<div class="popAddClass popBox">
	<div class="popTitle">
    	添加二级分类
    </div>
    <div class="row-fluid clearfix">
    	<form>
    	<div class="control-group">
            <label class="control-label span4">二级分类名称 :</label>
            <div class="controls span8">
                <input id="subClassName" type="text" name="name" placeholder="输入名称" maxlength="10" data-valid="[{rule:'not_empty'}]" />
                <span class="help-inline"></span>
            </div>
        </div>

    	<div class="control-group">
            <label class="control-label span4"></label>
            <div class="controls span8">
                <button type="button" class="btn btn-success">创建</button>
                <button type="button" class="btn btn-primary">返回</button>
            </div>
        </div>
        </form>
    </div>
</div>

<%@ include file="../global/footer.jsp" %>
<script src="${basepath }/res/js/inputvalid.js"></script>
<script>
var $edit1Form = null;
var $edit2form = null;
var $addform = null;
$(function(){
	
	$edit1Form = $(".popEdit1Class form").inputValid();
	$edit2Form = $(".popEdit2Class form").inputValid();
	$addForm = $(".popAddClass form").inputValid();
	
	/*---------初始化------------*/
	initClas();
	function initClas(){
		$(".rank2 ul").html('');
		var columnId = $(".rank1 ul li.active").data("id");
		$.post("${basepath}/category/findCategoryListByPid", {"columnId":columnId}, function(json){
			if (json) {
				var html = json.map(function(ele, index){
					return '<li data-id="'+ele.id+'"><a href="javascript:void(0);"> <span class="name">'+ele.name+'</span></a><i class="icon-remove"></i><i class="icon-pencil"></i>';
				}).join('');
				
				$(".rank2 ul").html(html);
			}
		}, 'JSON');
		
	}
	
	
	//点击一级菜单
	$(".rank1 li a").livequery(function(){
		$(this).click(function(){
			var $li = $(this).parent("li");
			$li.siblings().removeClass("active");
			$li.addClass("active");
			initClas();
		});
	});
	
	//1级分类编辑
	$(".rank1 li .icon-pencil").livequery(function(){
		$(this).click(function(){
			var $li = $(this).parents("li");
			var name = $li.find('.name').html();
			var $div = $li.parent("ul").parent("div.span6")
			$(".popEdit1Class input[name='name']").val(name);
			$(".popEdit1Class, .popUpCover").show();
			//保存
			$(".popEdit1Class button.btn-success").click(function(){
				var id = $li.data("id");
				name = $(".popEdit1Class input[name='name']").val();
				var params = {"id":id,"name":name};
				if ($edit1Form.validate_all()) {
					var url = '${basepath}/column/updateName';
					$.updateName(params,function(){
						$li.find(".name").html(name);
						$(".popEdit1Class, .popUpCover").hide();
					},url);
				}
			});
			
			//关闭
			$(".popEdit1Class button.btn-primary").click(function(){
				$(".popEdit1Class, .popUpCover").hide();
				$(".popEdit1Class button.btn-success").unbind();
			});
			
		});
	});
	
	//2级分类编辑
	$(".rank2 li .icon-pencil").livequery(function(){
		$(this).click(function(){
			var $li = $(this).parents("li");
			var name = $li.find('.name').html();
			$(".popEdit2Class input[name='name']").val(name);
			$(".popEdit2Class, .popUpCover").show();
			//保存
			$(".popEdit2Class button.btn-success").click(function(){
				var id = $li.data("id");
				name = $(".popEdit2Class input[name='name']").val();
				var params = {"id":id,"name":name};
				if ($edit2Form.validate_all()) {
					var url = '${basepath}/category/updateName';
					$.updateName(params,function(){
						$li.find(".name").html(name);
						$(".popEdit2Class, .popUpCover").hide();
					},url);
				}
			});
			
			//关闭
			$(".popEdit2Class button.btn-primary").click(function(){
				$(".popEdit2Class, .popUpCover").hide();
				$(".popEdit2Class button.btn-success").unbind();
			});
			
		});
	});
	
	//二级分类删除
	$(".rank2 li .icon-remove").livequery(function(){
		$(this).click(function(){
			var $li = $(this).parents('li');
			var id = $li.data("id");
			$.deleteClass(id, function() {
				$li.remove();
			});
		});
	});
	
	
	//添加二级
	$(".widget-content > h5").eq(1).children("i").click(function(){
		var pid = $(".rank1 li.active").data('id');
		$(".popAddClass, .popUpCover").show();
		
		$(".popAddClass button.btn-success").click(function(){
			if ($addForm.validate_all()) {
				var params = {"pid" : pid, "name" : $(".popAddClass input[name='name']").val()};
				$.add(params, function(){
					//$(".rank2 ul").append('<li data-id="'+json.id+'"><a href="javascript:void(0);"> <span class="name">'+json.name+'</span></a><i class="icon-remove"></i><i class="icon-pencil"></i>');
					window.location.reload();
					$(".popAddClass, .popUpCover").hide();
				});
			}
		});
		
		//关闭
		$(".popAddClass button.btn-primary").click(function(){
			$(".popAddClass, .popUpCover").hide();
			$(".popAddClass button.btn-success").unbind();
		});
		
	});
	
	
	$(".popBox .btn-primary,.popUpCover").livequery(function(){
		$(this).click(function(){
			$(".popBox, .popUpCover").hide();
		});
	});
	
});

$.extend({
	
	//删除
	deleteClass: function(_id, _fun){
		W.$.confirm(
			"是否要删除此分类？",
			function(){
				//ajax
				var AjaxURL = "${basepath}/category/delete"; //API接口
				$.ajax({
					type: "POST",
					dataType: "json",
					url: AjaxURL,
					data: {
						"categoryId" : _id
					},
					success: function (json) {
						if(json.code == '000000'){//成功
							_fun();
							W.$.alert("删除成功",1);
						}else {
							W.$.alert("出错了",2)
						}
					},
					error: function(data) {
						W.$.alert("删除失败");
					}
				});
			}
		);
		
	},
	
	add : function(_params, _fun) {
		$.ajax({
			type: "POST",
			dataType: "json",
			url: '${basepath}/category/saveOrUpdate',
			data: _params,
			success: function (json) {
				if(json.code == '000000'){//成功
					_fun();
					W.$.alert("添加成功",1);
				}else {
					W.$.alert("错误")
				}
			}
		});
	},
	
	//编辑
	updateName : function(_params, _fun, _url) {
		$.ajax({
			type: "POST",
			dataType: "json",
			url: _url,
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