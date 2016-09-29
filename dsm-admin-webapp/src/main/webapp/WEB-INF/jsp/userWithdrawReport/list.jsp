<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../global/header.jsp" %>
</head>

<body>
<div id="content">
	<div id="content-header">
		<div id="breadcrumb"> 
	  	<a href="${basepath }/main/index" target="_top" title="首页" class="tip-bottom"><i class="icon-home"></i> 主页</a> 
		  <a href="#" class="tip-bottom">订单处理</a> 
		  <a href="#" class="current">用户提现</a> 
	  </div>
	</div>
    
	<div class="container-fluid">
		<div class="row-fluid">
        	<div class="span12">
            
              <!-- <div class="searching-bar">
              		
              	   
                  <div class="control-group" style="width:330px;">
                      <label class="control-label">关键字：</label>
                      <div class="controls">
                          <input type="text" name="keyword" placeholder="订单号/收件人手机号" style="width:240px;">
                      </div>
                  </div>
                    
                    <div class="control-group">
                        <div class="controls">
                            <button class="btn btn-info pull-right search">查询</button>
                        </div>
                    </div>
                    
              </div> -->
              
              
              <div class="widget-box cool-list-box">
                  
                  <!-- <div class="widget-title" style="height:50px">
                  	<span class="icon">
                    	<input type="checkbox" id="title-checkbox" name="title-checkbox">
                    </span>
                    
                    <h5>全选</h5>
                    
                    
                    
                     <button id="export" class="btn btn-warning pull-right btn-large" disabled="disabled">导出提现Excel</button>
                    
                  </div> -->
                  
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
	
	$("tr button.btn-danger").livequery(function(){
		$(this).click(function(){
			var index = $(this).parents("tr").data("index");
			var dataArray = $table.bootstrapTable("getData");
			console.log(dataArray.batchNo);
			var batchNo = dataArray[index].batchNo;
			var date = dataArray[index].date;
			location.href = "${basepath}/userWithdrawReport/exportAliPayExcel?date="+date+"&batchNo="+batchNo;
		});
	});
	
});

function initTable() {
	
	$table = $('#tableBox').bootstrapTable({
        method: 'post',
		url:"${basepath}/userWithdrawReport/list",
        contentType:'application/x-www-form-urlencoded',
        sidePagination:'server',
        queryParams:function(params){
              
               return params;
        },
        pagination: true,
        pageSize : 10,
        columns: [{
        	field :'date',
        	title : '日期'
        },{
        	field :'batchNo',
        	title : '批次号'
        },{
        	field :'userNum',
        	title : '提现用户数'
        },{
        	field:'withdrawAmount',
        	title : '提现总金额',
        	formatter : function (data) {
        		return "￥" + (data/100).toFixed(2);
        	}
        },{
            field: 'statuc',
            title: '状态',
            formatter : function (data, row, index) {
            	var html = '';
            	if (data == 0) {
            		html = '<label class="label label-default">未导出</label>';
            	} else if (data == 1) {
            		html = '<label class="label label-warning">处理中</label>';
            	} else if (data == 2) {
            		html = '<label class="label label-info">已完成</label>';
            	} else {
            		html = '<label class="label label-danger">未知</label>';
            	}
            	return html;
            }
        },{
        	field : 'statuc',
        	title : '操作',
        	formatter : function (data, row, index) {
        		var html = '<div class="btn-group">';
            	if (data == 0) { 
            		html += '<button class="btn btn-danger btn-mini">导出提现报表</button>';
            	} else if (data == 1) {
            		html += '<button class="btn btn-warning btn-mini">导入提现结果</button>';
            	} else if (data == 2) {
            		html += '<button class="btn btn-info btn-mini">详情</button>';
            	} else {
            		html += '<button class="btn btn-default btn-mini">未知</button>';
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
	batchPay: function(_id, _userIds, _balances){
		var id = _id;
		if($.isArray(_id)){ //数组类型
			id = id.join(",");
		}
		
		W.$.confirm(
			"确定要提现吗？",
			function(){
				var AjaxURL = "${basepath}/userWithdraw/exportExcel"; //API接口
				$.ajax({
					type: "POST",
					dataType: "json",
					url: AjaxURL,
					data: { 
						"idsStr"  : id,
					},
					success: function (json) {
						if(json.code == '000000'){//成功
							$table.bootstrapTable('refresh');
							W.$.alert('已导出提现excel', 1);
						}else {
							W.$.alert("出错了：" + jon.msg);
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
	isArray : function(v){
		return toString.apply(v) === '[object Array]';
	}
});

</script>
</body>
</html>