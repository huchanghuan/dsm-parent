<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../global/header.jsp" %>
<style>
.nav-tabs {
	margin:10px 0px 10px 0px
}
</style>
</head>

<body>
<div id="content">
	<div id="content-header">
		<div id="breadcrumb"> 
	  	<a href="${basepath }/main/index" target="_top" title="首页" class="tip-bottom"><i class="icon-home"></i> 主页</a> 
		  <a href="#" class="tip-bottom">类目管理</a> 
		  <a href="#" class="current">品牌管理</a> 
	  </div>
	</div>
    
	<div class="container-fluid">
		<div class="row-fluid">
        	<div class="span12">
            
              <ul class="nav nav-tabs">
                  <li role="presentation" class="active"><a href="${basepath }/brand/toList">品牌列表</a></li>
                  <li role="presentation"><a href="${basepath }/brand/toAdd">新建品牌</a></li>
                </ul>
                
              <div class="searching-bar">
              		
                    
                    <!--表单组-->
                    <div class="control-group" style="width:200px;">
                      <label class="control-label">　　分类 :</label>
                      <div class="controls">
                        <select style="width:100px;" name="columnId">
                            <c:forEach items="${columnList }" var="column">
                            	<option value="${column.id }">${column.name }</option>
                            </c:forEach>
                        </select>
                      </div>
                    </div>

                    <div class="control-group" style="width:280px;">
                      <label class="control-label">　关键字 :</label>
                      <div class="controls">
                          <input type="text" name="keyword"  placeholder="输入关键字" style="width:140px;">
                      </div>
                  </div>
                    
                    <div class="control-group">
                        <div class="controls">
                            <button class="btn btn-info pull-right search">查询</button>
                        </div>
                    </div>
                    
              </div>
              
              
              <div class="widget-box cool-list-box">
                  
                  <div class="widget-title">
                  	<span class="icon">
                    	<input type="checkbox" id="title-checkbox" name="title-checkbox">
                    </span>
                    
                    <h5>全选</h5>
                    
                    
                    
                    <button class="btn pull-right batchDelete">批量删除</button>
                    
                  </div>
                  
                  <div class="widget-content nopadding">
                    <table id="tableBox" class="table table-bordered table-striped"></table>
                    
                   
                  
                </div>
                
            </div>
        </div>
	</div>
</div>
</div>
<%@ include file="../global/footer.jsp" %>
<script type="text/javascript">
var $table = null;
$(function(){
	
	initTable();
	
	$("#title-checkbox").toggle(function(){
		$(".table td input[type=checkbox]").attr("checked","checked");
	},function(){
		$(".table td input[type=checkbox]").removeAttr("checked");
	});
	
	//cumnId改变
	$("select[name='columnId']").change(function(){
		$table.bootstrapTable('refresh');
	}); 
	
	//关键词
	$(".search").click(function(){
		$table.bootstrapTable('refresh');
	});
	
	//改变状态
	$("td .btn-success, td .btn-warning").livequery(function(){
		$(this).click(function(){
			var dataArr = $table.bootstrapTable('getData');
            var _index = $(this).parents("tr").attr("data-index");
            var _status = temp=dataArr[_index].visible == 0?1:0;
            var _tips = _status == 1?'是否显示？':'是否隐藏?';
            $.changeStatus(dataArr[_index].name.toString(), _status, _tips, function(){
            	$table.bootstrapTable('updateRow', {index: _index, row:{visible: _status}});
            });
		});
	});
	
	//删除
	$("td .btn-danger").livequery(function(){
		$(this).click(function(){
			var dataArr = $table.bootstrapTable('getData');
            var _index = $(this).parents("tr").attr("data-index");
            $.deleteBrand(dataArr[_index].name);
		});
	});
	
	$(".batchDelete").click(function(){
		var map = [];
		var data = $table.bootstrapTable('getData');
		$("td input:checked").parents("tr").each(function(index, element) {
          map.push(data[$(this).data('index')].name);
        });
		$.deleteBrand(map);
	});
});

function initTable() {
	
	$table = $('#tableBox').bootstrapTable({
        method: 'post',
		url:"${basepath}/brand/list",
        contentType:'application/x-www-form-urlencoded',
        sidePagination:'server',
        queryParams:function(params){
               params.status = $("select[name='status']").val();
               params.keyword = $("input[name='keyword']").val();
               params.columnId = $("select[name='columnId']").val();
               params.categoryId = $("select[name='categoryId']").val();
               return params;
        },
        pagination: true,
        pageSize : 10,
        columns: [{
        	field : "id",
        	title : '<i class="icon-resize-vertical"></i>',
        	formatter : function(data, row, index) {
        		return '<input type="checkbox">';
        	}
        }/* ,{
        	field : "id",
        	title : '品牌ID'
        },{
            field: 'images',
            title: 'LOGO',
            formatter : function(data) {
            	var html = '';
            	if (data) {
            		var imgArrays = JSON.parse(data);
            		html = imgArrays[0];
            	}
            	return html;
            }
        } */,{
            field: 'name',
            title: '品牌名称'
        },{
            field: 'initial',
            title: '首字母'
        },{
        	field : 'pinyin',
        	title : '拼音'
        },{
        	field : 'columnName',
        	title : '分类名'
        },{
        	field : 'visible',
        	title : '状态',
        	formatter : function(data, row, index) {
        		if (row.visible == 0) {
					return '<span class="label label-primary">隐藏</span>';
				} else if(row.visible == 1){
                    return '<span class="label label-success">显示</span>';
				}
        	return '未知';
			}
        },{
            field: 'visible',
            title: '操作',
            formatter: function(data, row ,index){
                var html = '<div class="btn-group">';
               
                html += ' <a class="btn btn-primary btn-sm btn-mini" href="${basepath}/brand/toEdit?name='+row.name+'">编辑</a>';
                if(row.visible == 0){
                	html += ' <a class="btn btn-success btn-sm btn-mini">显示</a>';
                }else if (row.visible == 1) {
                	html += ' <a class="btn btn-warning btn-sm btn-mini">隐藏</a>';
                }
                html += ' <a class="btn btn-danger btn-sm btn-mini">删除</a>';
              
                html += '</div>';
                return html;
            }
        }],
        onPostBody:function(row){
         	 $("[data-toggle='popover']").popover({
           		trigger:'hover'
           	}); 
         }
     });

	//调整大小
     $(window).resize(function () {
         $table = $('#tableBox').bootstrapTable('resetView');
     }); 
}

$.extend({
	changeStatus: function(_name, _status, _tips, _fun){
		W.$.confirm(
			_tips,
			function(){
				var AjaxURL = "${basepath}/brand/updateStatus"; //API接口
				$.ajax({
					type: "POST",
					dataType: "json",
					url: AjaxURL,
					data: { 
						name  : _name,
						visible: _status
					},
					success: function (json) {
						if(json.code == '000000'){//成功
							//你也可以重新创新页面
							W.$.alert('更新成功', 1);
							_fun();
						}else {
							W.$.alert("<font color='#f00'>出错了</font>",2);
						}
					},
					error: function(data) {
						W.$.alert("错误",2);
					}
				});
			} , 
			1 ,
			function(){}
		);
	},
	deleteBrand: function(_id){
		var id = _id;
		if($.isArray(_id)){ //数组类型
			id = id.join(",");
		}
		
		W.$.confirm(
			"确定要删除吗？",
			function(){
				var AjaxURL = "${basepath}/brand/delete"; //API接口
				$.ajax({
					type: "POST",
					dataType: "json",
					url: AjaxURL,
					data: { 
						idsStr  : id
					},
					success: function (json) {
						if(json.code == '000000'){//成功
							
							if($.isArray(_id)){ //数组类型
								$table.bootstrapTable('remove', {field: 'name', values: _id});
							}else {
								$table.bootstrapTable('remove', {field: 'name', values: [_id]});
							}
							W.$.alert('已删除', 1);
						}else {
							W.$.alert("出错了:" + json.msg);
						}
					},
					error: function(data) {
						W.$.alert("错误");
					}
				});
			} , 
			1 ,
			function(){}
		);
	}
});

</script>
</body>
</html>