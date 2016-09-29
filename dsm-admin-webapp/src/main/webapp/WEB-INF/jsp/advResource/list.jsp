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
		  <a href="#" class="tip-bottom">广告位列表</a> 
		  <a href="#" class="current">广告位资源</a> 
	  </div>
	</div>
    
	<div class="container-fluid">
		<div class="row-fluid">
        	<div class="span12">

              <div class="widget-box cool-list-box">
                  
                  <div class="widget-title" style="height:40px">
                    <div class="btn-group pull-right">
	                    <a class="btn btn-default" href="${basepath }/advResource/toAdd?locationId=${locationId}">新建广告</a>
	                    <button onclick="javascript:history.back();" class="btn btn-info">返回</button>
             		</div>
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
var $form = null;
var locationId = ${locationId};
$(function(){
	
	initTable();
	
	//删除
	$("td .btn-danger").livequery(function(){
		$(this).click(function(){
			var dataArr = $table.bootstrapTable('getData');
            var _index = $(this).parents("tr").attr("data-index");
            $.deleteAdvResource(dataArr[_index].id);
		});
	});
	
});

function initTable() {
	
	$table = $('#tableBox').bootstrapTable({
        method: 'post',
		url:"${basepath}/advResource/list",
        contentType:'application/x-www-form-urlencoded',
        sidePagination:'server',
        queryParams:function(params){
              params.locationId = locationId;
               return params;
        },
        pagination: true,
        pageSize : 10,
        columns: [{
        	field :'id',
        	title : 'ID'
        },{
        	field :'content',
        	title : '内容',
        	width : 160,
        	formatter : function (data, row, index) {
        		var html = '';
        		if (row.type == 0) {
        			html += '<img class="img-responsive img-w-h" src="'+row.content+'@1e_300w_194h_1c_0i_1o_100q_100sh_1x.jpg" >';
        		} else if (row.type == 1) {
        			html += '<video src="'+row.content+'" controls="controls" width="300" height="194"></video>';
        		} else if (row.type = 2) {
        			html += '<p>'+row.content+'</p>';
        		}
        			
        		return html;
        	}
        },{
        	field : 'type',
        	title : '类型',
        	formatter : function (data) {
        		var html = '';
        		switch (data) {
				case 0:
					html +='图片'
					break;
				case 1:
					html +='视频'
					break;
				case 2:
					html +='文本'
					break;	
				default:
					break;
				}
        		
        		return html;
        	}
        },{
        	field:'startUtc',
        	title : '生效时间',
        	formatter : function (data) {
        		if (data){
        			return formatUTC(data, 'yyyy-MM-dd hh:mm');
        		}
        		return '';
        	}
        },{
        	field:'endUtc',
        	title : '结束时间',
        	formatter : function (data) {
        		if (data){
        			return formatUTC(data, 'yyyy-MM-dd hh:mm');
        		}
        		return '';
        	}
        },{
            field: 'status',
            title: '状态',
            formatter : function(data, row, index){
            	var html = '';
            	var currentDate = new Date();
            	if (currentDate < row.startUtc) {
            		html = '<label class="label label-default">未生效</label>';
            	} else if (row.startUtc <= currentDate && currentDate < row.endUtc) {
            		html = '<label class="label label-success">已生效</label>';
            	} else if (row.endUtc <= currentDate) {
            		html = '<label class="label label-danger">已过期</label>';
            	} else {
            		html = '<label class="label label-warning">异常</label>';
            	} 
            		
            	return html;
            }
        },{
        	field : 'id',
        	title : '操作',
        	formatter : function(data, row, index){
        		var html = '<div class="btn-group">';
        		html += ' <a class="btn btn-primary btn-sm btn-mini" href="${basepath}/advResource/toEdit?id='+row.id+'">编辑</a>';
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
	deleteAdvResource: function(_id){
		W.$.confirm(
			"确定要删除吗？",
			function(){
				var AjaxURL = "${basepath}/advResource/delete"; //API接口
				$.ajax({
					type: "POST",
					dataType: "json",
					url: AjaxURL,
					data: { 
						"id"  : _id
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