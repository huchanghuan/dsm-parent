<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../global/header.jsp" %>
</head>

<body>
<div id="content">
	<div id="content-header">
		<div id="breadcrumb"> 
	  	<a href="${basepath }/main/index" target="_top" title="首页" class="tip-bottom"><i class="icon-home"></i> 主页</a> 
		  <a href="#" class="tip-bottom">用户管理</a> 
		  <a href="#" class="current">用户列表</a> 
	  </div>
	</div>
    
	<div class="container-fluid">
		<div class="row-fluid">
        	<div class="span12">
              
              <div class="widget-box user-list-box">
                  <div class="widget-title">
                  	<span class="icon">
                    	<input type="checkbox" id="title-checkbox" name="title-checkbox" />
                    </span>
                    
                    <h5>全选</h5>
                    
                    
                    <div class="control-group pull-right search">
                      <div class="controls">
                        <div class="input-append">
                          <input id="keyword" name="keyword" type="text" placeholder="手机号/昵称" style="width:120px;">
                          <span class="add-on"><a href="javascript:$.searchUser()">GO</a></span>
                        </div>
                      </div>
                    </div>
                    
                    
                    <!--表单组-->
					<div class="btn_group0 pull-right">
                    	<div class="control-group">
                          <div class="controls">
                            <select id="status" name="status" style="width:150px;">
                                <option value="-1">不限</option>
                                <option value="0">正常</option>
                                <option value="1">禁用</option>
                            </select>
                          </div>
                        </div>
                    </div><!--表单组 END-->
                    
                    <button id="batchRemove" class="btn pull-right">批量删除</button>
                    
                  </div>
                  <div class="widget-content nopadding">
                    <table id="tableBox" class="table table-bordered table-striped with-check"></table>
                  </div>
                  
                </div>
                
            </div>
        </div>
	</div>
</div>


<%@ include file="../global/footer.jsp" %>
<script type="text/javascript">
var W = window.top;
var $table = null;
$(function(){
	
	//初始化表格
	initTable();
	
	$("#title-checkbox").toggle(function(){
		$(".table td input[type=checkbox]").attr("checked","checked");
	},function(){
		$(".table td input[type=checkbox]").removeAttr("checked");
	});
	
	//批量删除
	$("#batchRemove").click(function(){
		var map = [];
		var data = $table.bootstrapTable('getData');
		$("td input:checked").parents("tr").each(function(index, element) {
          map.push(data[$(this).data('index')].id);
        });
		$.deleteUser(map);
	});
	
	//切换状态的
	$("#status").change(function(){
		$.retrieveUser();
	});
	
	//启用禁用
	$("td .btn-success, .btn-warning").livequery(function(){
		$(this).click(function(){
			var dataArr = $table.bootstrapTable('getData');
            var _index = $(this).parents("tr").attr("data-index");
            var _status = dataArr[_index].status == 0?1:0;
            var _tips = _status == 0?'是否激活此账号？':'是否禁用此账号?';
            $.changeStatus(dataArr[_index].id.toString(), _status, _tips, function(){
            	$table.bootstrapTable('updateRow', {index: _index, row:{status: _status}});
            });
		});
	});
	
	//删除
	$("td .btn-danger").livequery(function(){
		$(this).click(function(){
			var dataArr = $table.bootstrapTable('getData');
            var _index = $(this).parents("tr").attr("data-index");
            $.deleteUser(dataArr[_index].id);
		});
	});
});

function initTable() {
	
	$table = $('#tableBox').bootstrapTable({
        method: 'post',
		url:"${basepath}/user/list",
        contentType:'application/x-www-form-urlencoded',
        sidePagination:'server',
        queryParams:function(params){
                params.status = $("#status").val();
                params.keyword = $("#keyword").val();
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
        },{
        	field : "id",
        	title : 'ID'
        },{
            field: 'nickname',
            title: '昵称'
        },{
            field: 'phone',
            title: '手机号'
        },{
            field: 'sex',
            title: '性别',
            formatter : function(data) {
            	var html = "";
            	switch (data) {
				case -1:
					html = "女";
					break;
				case 1:
					html = "男";
					break;
				default:
					html = '未知';
					break;
				}
            	
            	return html;
            }
        },{
            field: 'email',
            title: '邮箱'
        },{
            field: 'status',
            title: '状态',
			formatter: function(data, row ,index){
                if(row.status == 0){
                    return '<span class="label label-success">正常</span>';
                } else {
                    return '<span class="label label-warning">禁用</span>';
                }
            }
        },{
            field: 'status',
            title: '操作',
            formatter: function(data, row ,index){
                var html = '<div class="btn-group">';
                if(row.roleId != 1){
                	html += ' <a class="btn btn-primary btn-sm btn-mini" href="${basepath}/user/toEdit?id='+row.id+'">编辑</a>';
                	if(row.status == 0){
                		html += ' <a class="btn btn-warning btn-sm btn-mini">禁用</a>';
                	}else{
                		html += ' <a class="btn btn-success btn-sm btn-mini">启用</a>';
                	}
                	html += ' <a class="btn btn-danger btn-sm btn-mini">删除</a>';
                }
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
	//启用禁用
	changeStatus: function(_id, _status, _tips, _fun){
		W.$.confirm(
			_tips,
			function(){
				var AjaxURL = "${basepath}/user/updateStatus"; //API接口
				$.ajax({
					type: "POST",
					dataType: "json",
					url: AjaxURL,
					data: { 
						id  : _id,
						status : _status
					},
					success: function (json) {
						if(json.code == '000000'){//成功
							//你也可以重新创新页面
							W.$.alert('更新成功', 1);
							_fun();
						}else {
							W.$.alert("<font color='#f00'>出错了</font>");
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
	},
	
	deleteUser: function(_id){
		var id = _id;
		if($.isArray(_id)){ //数组类型
			id = id.join(",");
		}
		
		W.$.confirm(
			"确定要删除吗？",
			function(){
				var AjaxURL = "${basepath}/user/delete"; //API接口
				$.ajax({
					type: "POST",
					dataType: "json",
					url: AjaxURL,
					data: { 
						ids  : id
					},
					success: function (json) {
						if(json.code == '000000'){//成功
							
							if($.isArray(_id)){ //数组类型
								$table.bootstrapTable('remove', {field: 'id', values: _id});
							}else {
								$table.bootstrapTable('remove', {field: 'id', values: [_id]});
							}
							W.$.alert('已删除', 1);
						}else {
							W.$.alert("出错了");
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
	},
	
	//GO，查询用户
	searchUser : function(){
		$table.bootstrapTable('refresh');
		
	},
	//检索，下拉状态 ： 正常或禁用
	retrieveUser : function(){
		var status = $("#status").val(); //状态
		$table.bootstrapTable('refresh');
	},
	isArray : function(v){
		return toString.apply(v) === '[object Array]';
	}
});

</script>
</body>
>>>>>>> 954d83f 9-5
</html>