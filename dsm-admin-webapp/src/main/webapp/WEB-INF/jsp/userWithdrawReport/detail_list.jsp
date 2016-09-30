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
		  <a href="#" class="tip-bottom">用户提现</a> 
		  <a href="#" class="current">详情</a> 
	  </div>
	</div>
    
	<div class="container-fluid">
		<div class="row-fluid">
        	<div class="span12">
            	 <div class="widget-box user-list-box">
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
var batchNo = "${batchNo}";
var date = "${date}";
$(function(){
	
	initTable();
	
});

function initTable() {
	
	$table = $('#tableBox').bootstrapTable({
        method: 'post',
		url:"${basepath}/userWithdrawReport/detail",
        contentType:'application/x-www-form-urlencoded',
        sidePagination:'client',
        queryParams:function(params){
              params.batchNo = batchNo;			//批次
              params.date = date;				//日期
              return params;
        },
        pagination: true,
        pageSize : 10,
        columns: [{
        	field :'idx',
        	title : '编号'
        },{
        	field :'userId',
        	title : '用户ID'
        },{
        	field:'requestAmount',
        	title : '提现金额',
        	formatter : function (data) {
        		return "￥" + (data/100).toFixed(2);
        	}
        },{
        	field:'payAmount',
        	title : '实际付款',
        	formatter : function (data) {
        		return "￥" + (data/100).toFixed(2);
        	}
        },{
        	field :'utc',
        	title : '申请时间',
        	formatter : function (data) {
        		if (data) {
        			return formatUTC(data, "yyyy-MM-dd hh:mm");
        		} else {
        			return '';
        		}
        	}
        },{
            field: 'status',
            title: '结果',
            formatter : function (data, row, index) {
            	var html = '';
            	if (data == 0) {
            		html = '<label class="label label-warning">提现处理中</label>';
            	} else if (data == 1) {
            		html = '<label class="label label-success">提现成功</label>';
            	} else if (data == -1) {
            		html = '<label class="label label-default">提现失败</label>';
            	} else {
            		html = '<label class="label label-danger">未知</label>';
            	}
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