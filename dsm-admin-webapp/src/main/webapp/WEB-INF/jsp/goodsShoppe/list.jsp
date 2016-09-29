<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../global/header.jsp" %>
</head>

<body>
<div id="content">
	<div id="content-header">
	  <div id="breadcrumb"> 
		  <a href="${basepath }/main/index" target="_top" title="首页" class="tip-bottom"><i class="icon-home"></i> 主页</a> 
		  <a href="#" class="tip-bottom">运营管理</a> 
		  <a href="#" class="current">专柜列表</a> 
	  </div>
	</div>
    
	<div class="container-fluid">
		<div class="row-fluid">
        	<div class="span12">

              <div class="widget-box cool-list-box">
                  
                  <div class="widget-title" style="height:40px">
                    <a class="btn btn-default pull-right" href="${basepath }/goodsShoppe/toAdd">新建专柜</a>
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
	
	//删除
	$("td .btn-danger").livequery(function(){
		$(this).click(function(){
			var dataArr = $table.bootstrapTable('getData');
            var _index = $(this).parents("tr").attr("data-index");
            $.deleteGoodsShoppe(dataArr[_index].id);
		});
	});
	
	//显示隐藏
	$("td .btn-success, td .btn-warning").livequery(function(){
		$(this).click(function(){
			var dataArr = $table.bootstrapTable('getData');
            var _index = $(this).parents("tr").attr("data-index");
            var _status = dataArr[_index].status == 0?1:0;
            var _tips = _status == 0?'是否隐藏此专柜？':'是否显示此专柜?';
            $.changeStatus(dataArr[_index].id, _status, _tips, function(){
            	$table.bootstrapTable('updateRow', {index: _index, row:{status: _status}});
            });
		});
	});
	
	
});


function initTable() {
	
	$table = $('#tableBox').bootstrapTable({
        method: 'post',
		url:"${basepath}/goodsShoppe/list",
        contentType:'application/x-www-form-urlencoded',
        sidePagination:'server',
        queryParams:function(params){
              
               return params;
        },
        pagination: true,
        pageSize : 10,
        columns: [{
        	field :'id',
        	title : '专柜ID'
        },{
        	field : 'image1',
        	title : '图片',
        	formatter : function (data, row, index) {
        		var html = '';
        		if (data) {
        			html = '<img class="img-responsive img-w-h" src="'+row.image1+'@1e_300w_194h_1c_0i_1o_100q_100sh_1x.jpg" >';
        		} 
        		return html;
        	}
        },{
        	field :'title',
        	title : '名称'
        },{
        	field : 'slogan',
        	title : '专柜广告语'
        },{
            field: 'status',
            title: '状态',
            formatter : function(data){
            	var html = '';
            	if (data == 0){
            		html += '<label class="label label-default">隐藏</label>';
            	} else if (data == 1) {
            		html += '<label class="label label-success">显示</label>';
            	} else {
            		html += '未知';
            	}
            	return html;
            }
        },{
        	field : 'status',
        	title : '操作',
        	formatter : function(data, row, index){
        		var html = '<div class="btn-group">';
        		html += ' <a class="btn btn-info btn-sm btn-mini" href="${basepath}/goodsShoppePublish/toList?shoppeId='+row.id+'">详情</a>';
        		html += ' <a class="btn btn-primary btn-sm btn-mini" href="${basepath}/goodsShoppe/toEdit?id=' + row.id + '">编辑</a>';
            	if(row.status == 0){
            		html += ' <a class="btn btn-success btn-sm btn-mini">显示</a>';
            	}else{
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
	//启用禁用
	changeStatus: function(_id, _status, _tips, _fun){
		W.$.confirm(
			_tips,
			function(){
				var AjaxURL = "${basepath}/goodsShoppe/updateStatus"; //API接口
				$.ajax({
					type: "POST",
					dataType: "json",
					url: AjaxURL,
					data: { 
						"id"  : _id,
						"status" : _status
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
	
	deleteGoodsShoppe: function(_id){
		W.$.confirm(
			"确定要删除吗？",
			function(){
				var AjaxURL = "${basepath}/goodsShoppe/delete"; //API接口
				$.ajax({
					type: "POST",
					dataType: "json",
					url: AjaxURL,
					data: { 
						id  : _id
					},
					success: function (json) {
						if(json.code == '000000'){//成功
							$table.bootstrapTable('remove', {field: 'id', values: [_id]});
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
	}
	
});

</script>
</body>
</html>