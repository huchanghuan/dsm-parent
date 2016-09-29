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
		  <a href="#" class="current">订单列表</a> 
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
                    
                    
                    <!--表单组-->
                    <div class="control-group" style="width:200px;">
                      <label class="control-label">状态 :</label>
                      <div class="controls">
                        <select style="width:100px;" name="statusStr">
                            <option value="1,2,3,4,10,11,12,13,14,127">--全部--</option>
                            <option value="1">已付款</option>
                            <option value="2,14">已发货</option>
                            <option value="3,4">已签收</option>
                            <option value="127">已取消</option>
                            <option value="10,11,12">退款中</option>
                            <option value="13">已退款</option>
                        </select>
                      </div>
                    </div>

                    
                    
                    <div class="control-group">
                        <div class="controls">
                            <button class="btn btn-info pull-right search">查询</button>
                        </div>
                    </div>
                    
              </div>
              
              
              <div class="widget-box cool-list-box">
                  <!-- 
                  <div class="widget-title">
                  	<span class="icon">
                    	<input type="checkbox" id="title-checkbox" name="title-checkbox">
                    </span>
                    
                    <h5>全选</h5>
                   
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
	
	//下拉
	$("select[name='statusStr']").change(function(){
		$table.bootstrapTable('refresh');
	});
	
	//查询
	$(".search").click(function(){
		$table.bootstrapTable('refresh');
	});
});

function initTable() {
	
	$table = $('#tableBox').bootstrapTable({
        method: 'post',
		url:"${basepath}/order/list",
        contentType:'application/x-www-form-urlencoded',
        sidePagination:'server',
        queryParams:function(params){
               params.statusStr = $("select[name='statusStr']").val();
               params.keyword = $("input[name='keyword']").val();
               return params;
        },
        pagination: true,
        pageSize : 10,
        columns: [/* {
        	field : "id",
        	title : '<i class="icon-resize-vertical"></i>',
        	formatter : function(data, row, index) {
        		return '<input type="checkbox">';
        	}
        }, */{
        	field :'id',
        	title : '订单号'
        },{
            field: 'goodsName',
            title: '商品名称'
        },{
            field: 'dealPrice',
            title: '订单总金额',
            formatter : function(data) {
            	return "￥" + data/100..toPrecision(2);
            }
        },{
        	field : 'receiver',
        	title : '收件人'
        },{
        	field : 'phone',
        	title : '收件人号码'
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
        		var html = '';
        		switch (data) {
				case 1:
					html = '<span class="label label-info">已付款</span>';
					break;
				case 2:
					html = '<span class="label label-warning">已发货</span>';
					break;
				case 14:
					html = '<span class="label label-warning">已发货</span>';
					break;
				case 3:
					html = '<span class="label label-success">已签收</span>';
					break;
				case 4:
					html = '<span class="label label-success">已签收</span>';
					break;
				case 10:
					html = '<span class="label label-danger">退款中</span>';
					break;
				case 11:
					html = '<span class="label label-danger">退款中</span>';
					break;
				case 12:
					html = '<span class="label label-danger">退款中</span>';
					break;
				case 13:
					html = '<span class="label label-success">已退款</span>';
					break;
				case 127:
					html = '<span class="label label-default">已取消</span>';
					break;
				default:
					html = '未知';
					break;
				}
        	return html;
			}
        },{
            field: 'id',
            title: '操作',
            formatter: function(data, row ,index){
                var html = '<div class="btn-group">';
				html += '<a href="${basepath}/order/toDetail?orderId='+row.id+'" class="btn btn-primary btn-mini">详情</a>';            
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
	//下架
	SoldOutProduct: function(_id){
		alert(_id);
	},
	//发布/上架
	soldInProduct: function(_id){
		alert(_id);
	},
	//删除
	deleteProduct: function(_id){
		alert(_id);
	}
});

</script>
</body>
</html>