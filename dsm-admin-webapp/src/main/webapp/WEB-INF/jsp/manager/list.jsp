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
		  <a href="#" class="current">管理员列表</a> 
	  </div>
	</div>
    
	<div class="container-fluid">
		<div class="row-fluid">
        	<div class="span12">
              
              <div class="widget-box cool-class-box">
                  <div class="widget-content nopadding">
                  	<a class="btn btn-default pull-right" href="${basepath }/manager/toAdd">
                  		<i class="icon-plus"></i> 新增管理员
                  	</a>
                  </div>
                  <div class="widget-content nopadding">
                  	
                    <table id="tableBox" class="table table-bordered table-striped">
                    	 <thead>
	                        <tr>
	                          <th><i class="icon-resize-vertical"></i></th>
	                          <th>No.</th>
	                          <th>账号</th>
	                          <th>联系方式</th>
	                          <th>邮箱</th>
	                          <th>创建时间</th>
	                          <th>状态</th>
	                          <th>操作</th>
	                        </tr>
	                      </thead>
                    </table>
                    
                  </div>
                  
                </div>
                
            </div>
        </div>
	</div>
</div>

<%@ include file="../global/footer.jsp" %>
<script>
var W = window.top;
var $table = null;
$(function(){

	//表格初始化
	initTable();
	
	//启用禁用
	$("td .btn-success, .btn-warning").livequery(function(){
		$(this).click(function(){
			var dataArr = $table.bootstrapTable('getData');
            var _index = $(this).parents("tr").attr("data-index");
            var _status = dataArr[_index].status == 0?1:0;
            var _tips = _status == 0?'是否激活此账号？':'是否禁用此账号?';
            $.changeStatus(dataArr[_index].id, _status, _tips, function(){
            	$table.bootstrapTable('updateRow', {index: _index, row:{status: _status}});
            });
		});
	});
	
	//删除
	$("td .btn-danger").livequery(function(){
		$(this).click(function(){
			var dataArr = $table.bootstrapTable('getData');
            var _index = $(this).parents("tr").attr("data-index");
            $.deleteUser(dataArr[_index].id, function(){
            	$table.bootstrapTable('remove', {field: 'id', values: [dataArr[_index].id]});
            });
		});
	});
});

function initTable() {
	
	$table = $('#tableBox').bootstrapTable({
        method: 'post',
		url:"${basepath}/manager/list",
        contentType:'application/x-www-form-urlencoded',
        sidePagination:'server',
        queryParams:function(params){
                
                return params;
        },
        pagination: true,
        pageSize : 10,
        columns: [{
            field: 'id',
            title: 'NO.',
            formatter : function(data, row, index) {
            	var opts = $table.bootstrapTable('getOptions');
            	return (opts.pageNumber -1) * opts.pageSize + index +1;
            }
        },{
            field: 'username',
            title: '账号',
        },{
            field: 'phoneNo',
            title: '联系方式',
        },{
            field: 'email',
            title: '邮箱'
        },{
            field: 'utc',
            title: '创建时间',
			formatter: function(data, row ,index){
				if (!data) return '';
				return formatUTC(data, 'yyyy-MM-dd hh:mm');
			}
        },{
            field: 'roleName',
            title: '角色'
        },{
            field: 'status',
            title: '状态',
			formatter: function(data, row ,index){
                if(row.status == 0){
                    return '<span class="label label-success">已激活</span>';
                } else {
                    return '<span class="label label-warning">已锁定</span>';
                }
            }
        },{
            field: 'status',
            title: '操作',
            'class' : 'text-center',
            formatter: function(data, row ,index){
                var html = '<div class="btn-group">';
                if(row.roleId != 1){
                	html += ' <a class="btn btn-primary btn-sm btn-mini" href="${basepath}/manager/toEdit?id='+row.id+'">编辑</a>';
                	if(row.status == 0){
                		html += ' <a class="btn btn-warning btn-sm btn-mini">锁定</a>';
                	}else{
                		html += ' <a class="btn btn-success btn-sm btn-mini">激活</a>';
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
				var AjaxURL = "${basepath}/manager/updateStatus"; //API接口
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
	//删除
	deleteUser: function(_id, _fun){
		W.$.confirm(
			"是否要删除此账号？",
			function(){
				var AjaxURL = "${basepath}/manager/delete"; //API接口
				$.ajax({
					type: "POST",
					dataType: "json",
					url: AjaxURL,
					data: { 
						id  : _id
					},
					success: function (json) {
						if(json.code == '000000'){//成功
							W.$.alert('已删除', 1);
							_fun();
						}else {
							alert("出错了");
						}
					},
					error: function(data) {
						alert("错误");
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