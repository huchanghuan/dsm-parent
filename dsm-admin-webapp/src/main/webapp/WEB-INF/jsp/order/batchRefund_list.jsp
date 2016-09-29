<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../global/header.jsp" %>
</head>

<body>
<div id="content">
	<div id="content-header">
		<div id="breadcrumb"> 
	  	<a href="${basepath }/main/index" target="_top" title="首页" class="tip-bottom"><i class="icon-home"></i> 主页</a> 
		  <a href="#" class="tip-bottom">订单管理</a> 
		  <a href="#" class="current">退款处理</a> 
	  </div>
	</div>
    
	<div class="container-fluid">
		<div class="row-fluid">
        	<div class="span12">
            
              <div class="searching-bar">
              		
              	   
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
                    
              </div>
              
              
              <div class="widget-box cool-list-box">
                  
                  <div class="widget-title" style="height:50px">
                  	<span class="icon">
                    	<input type="checkbox" id="title-checkbox" name="title-checkbox">
                    </span>
                    
                    <h5>全选</h5>
                    
                    
                    
                     <button id="batchPay" class="btn btn-warning pull-right btn-large">批量退款</button>
                    
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
	
	$("#title-checkbox").toggle(function(){
		$("#batchPay").removeAttr("disabled");
		$(".table td input[type=checkbox]").attr("checked","checked");
	},function(){
		$("#batchPay").attr("disabled","disabled");
		$(".table td input[type=checkbox]").removeAttr("checked");
	});
	
	$(".table td input[type=checkbox]").livequery(function(){
		$(this).change(function(){
			if ($(".table td input[type=checkbox]:checked").size() > 0){
				$("#batchPay").removeAttr("disabled");
			} else 
				$("#batchPay").attr("disabled","disabled");
		});
	});
	
	
	initTable();
	
	//查询
	$(".search").click(function(){
		$table.bootstrapTable('refresh');
	});
	
	$("#batchPay").click(function(){
		var orderMap = [];
		var userMap = [];
		var balanceMap = [];
		var data = $table.bootstrapTable('getData');
		$("td input:checked").parents("tr").each(function(index, element) {
			var order = data[$(this).data('index')];
			orderMap.push(order.id);
			userMap.push(order.buyerId);
			balanceMap.push(order.dealPrice);
        });
		$.batchPay(orderMap, userMap, balanceMap);
	});
	
	$("td .btn-warning").livequery(function(){
		$(this).click(function(){
			var dataArr = $table.bootstrapTable('getData');
            var _index = $(this).parents("tr").attr("data-index");
            var data = dataArr[_index];
            console.log(data);
            $.batchPay(data.id, data.buyerId, data.dealPrice);
		});
	});
});

function initTable() {
	
	$table = $('#tableBox').bootstrapTable({
        method: 'post',
		url:"${basepath}/order/list",
        contentType:'application/x-www-form-urlencoded',
        sidePagination:'server',
        queryParams:function(params){
        	   params.statusStr = '10,11,12,13,14';   //
               params.keyword = $("input[name='keyword']").val();
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
        	field :'id',
        	title : '订单号'
        },{
        	field :'receiptUtc',
        	title : '签收时间'
        },{
        	field :'payType',
        	title : '支付方式',
        	formatter : function(data) {
        		var html = '';
        		switch (data) {
				case 1:
					html = '支付宝';
					break;
				case 2:
					html = '微信';
					break;
				case 3:
					html = '银行卡';
					break;
				case 4:
					html = 'Apple-pay';
					break;
				default:
					html = '未知';
					break;
				}
        		return html;
        	}
        },{
            field: 'dealPrice',
            title: '订单总金额',
            formatter : function(data) {
            	return "￥" + data/100..toPrecision(2);
            }
        },{
            field: 'goodsName',
            title: '商品名称'
        },{
        	field : 'createUtc',
        	title : '下单时间',
        	formatter : function(data) {
        		if (data) {
        			return formatUTC(data, 'yyyy-MM-dd hh:mm');
        		}
        		return '';
        	}
        },{
        	field : 'status',
        	title : '状态',
        	formatter : function(data, row, index) {
        		var html = '<span class="label label-success">已退款</span>';
        		
        	return html;
			}
        },{
            field: 'id',
            title: '操作',
            formatter: function(data, row ,index){
                var html = '<div class="btn-group">';
				html += '<a href="${basepath}/order/toDetail?orderId='+row.id+'" class="btn btn-primary btn-mini">详情</a>';            
				html += '<button class="btn btn-warning btn-mini">退款</a>';            
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
		
		var userIds = _userIds;
		if($.isArray(_userIds)){ //数组类型
			userIds = userIds.join(",");
		}
		
		var balances = _balances;
		if($.isArray(_balances)){ //数组类型
			balances = balances.join(",");
		}
		
		W.$.confirm(
			"确定要退款给买家吗？",
			function(){
				var AjaxURL = "${basepath}/userAccount/updateBatchPay"; //API接口
				$.ajax({
					type: "POST",
					dataType: "json",
					url: AjaxURL,
					data: { 
						"orderIdsStr"  : id,
						"userIdsStr" : userIds,
						"balancesStr" : balances
					},
					success: function (json) {
						if(json.code == '000000'){//成功
							//移除
							if($.isArray(_id)){ //数组类型
								$table.bootstrapTable('remove', {field: 'id', values: _id});
							}else {
								$table.bootstrapTable('remove', {field: 'id', values: [_id]});
							}
							
							W.$.alert('已更新用户钱包', 1);
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