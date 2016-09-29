<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../global/header.jsp" %>
</head>

<body>
<div id="content">
	<div id="content-header">
		<div id="breadcrumb"> 
	  	<a href="${basepath }/main/index" target="_top" title="首页" class="tip-bottom"><i class="icon-home"></i> 主页</a> 
		  <a href="#" class="tip-bottom">商品管理</a> 
		  <a href="#" class="current">商品列表</a> 
	  </div>
	</div>
    
	<div class="container-fluid">
		<div class="row-fluid">
        	<div class="span12">
            
              <div class="searching-bar">
              		
              	   
                  <div class="control-group" style="width:280px;">
                      <label class="control-label">　关键字 :</label>
                      <div class="controls">
                          <input type="text" name="keyword" placeholder="输入关键字" style="width:140px;">
                      </div>
                  </div>
                    
                    
                    <!--表单组-->
                    <div class="control-group" style="width:200px;">
                      <label class="control-label">　　状态 :</label>
                      <div class="controls">
                        <select style="width:100px;" id="status" name="status">
                            <option value="-1">不限</option>
                            <option value="0">未上架</option>
                            <option value="1">出售中</option>
                            <option value="2">已下架</option>
                            <option value="3">已拍下</option>
                            <option value="4">已出售</option>
                        </select>
                      </div>
                    </div>

                    
                    <div class="control-group" style="width:400px;">
                        <label class="control-label">所属类别 :</label>
                        <div class="controls">
                            <select class="select_rank1" name="columnId" style="width:100px; float:left;">
                           		<c:forEach items="${columnList }" var="column">
                           			<option value="${column.id }">${column.name }</option>
                           		</c:forEach>
                            </select>
                            <select class="select_rank2" name="categoryId" style="width:100px; float:left;">
                                <option class="defult" selected value="-1">不限</option>
                            </select>
                        </div>
                    </div>
                    
                    <div class="control-group">
                        <div class="controls">
                            <button class="btn btn-info pull-right btn-search">查询</button>
                        </div>
                    </div>
                    
              </div>
              
              
              <div class="widget-box cool-list-box">
                  
                  <div class="widget-title">
                  	<span class="icon">
                    	<input type="checkbox" id="title-checkbox" name="title-checkbox">
                    </span>
                    
                    <h5>全选</h5>
                    
                    
                    
                    <button class="btn pull-right batch-remove">批量删除</button>
                    
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
var W = window.top;
var $table = null;
var domain = "http://cdn.duoshoucat.com/"
$(function(){
	
	//初始化表格
	initTable();
	
	$("#title-checkbox").toggle(function(){
		$(".table td input[type=checkbox]").attr("checked","checked");
	},function(){
		$(".table td input[type=checkbox]").removeAttr("checked");
	});
	
	//批量删除
	$("button.batch-remove").click(function(){
		var map = [];
		var data = $table.bootstrapTable('getData');
		$("td input:checked").parents("tr").each(function(index, element) {
          map.push(data[$(this).data('index')].id);
        });
		$.deleteGoods(map);
	});
	
	//切换状态选择
	$("#status").change(function(){
		$.retrieve();
	});
	
	var columnId = $(".select_rank1").val();
	if (columnId == 0) {
		$(".select_rank2").attr("disabled","disabled");
	
	} else {
		$(".select_rank2").html('<option value="-1">不限</option>');
		$.post('${basepath}/category/findCategoryListByPid', {"columnId":columnId}, function(json){
			if (json) {
				var html = json.map(function(ele, index){
					return '<option value="'+ele.id+'">'+ele.name+'</option>';
				}).join('');
				$(".select_rank2").append(html);
				$(".select_rank2").removeAttr("disabled");
			} else W.$.alert('加载数据失败', 1);
		},'JSON');
	}
	
	//改变状态
	$("td .btn-success, td .btn-warning").livequery(function(){
		$(this).click(function(){
			var dataArr = $table.bootstrapTable('getData');
            var _index = $(this).parents("tr").attr("data-index");
            var temp = null;
            var _status = (temp=dataArr[_index].status) == 0 || temp == 2?1:2;
            var _tips = _status == 1?'是否上架？':'是否下架?';
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
            $.deleteGoods(dataArr[_index].id, function(){
            	$table.bootstrapTable('remove', {field: 'id', values: [dataArr[_index].id]});
            });
		});
	});
	
	$(".select_rank1").change(function(){
		$(".select_rank2").html('<option value="-1">不限</option>');
		$table.bootstrapTable('refresh');
		var columnId = $(this).val();
		if (columnId != 0){
			$.post('${basepath}/category/findCategoryListByPid', {"columnId":columnId}, function(json){
				if (json) {
					var html = json.map(function(ele, index){
						return '<option value="'+ele.id+'">'+ele.name+'</option>';
					}).join('');
					$(".select_rank2").append(html);
					$(".select_rank2").removeAttr("disabled");
				} else W.$.alert('加载数据失败', 2);
			},'JSON');
		} else {
			$(".select_rank2").attr("disabled","disabled");
		}
		
	});
	
	$(".select_rank2").change(function(){
		$table.bootstrapTable('refresh');
	});
	
	//查询
	$("button.btn-search").click(function(){
		$table.bootstrapTable('refresh');
	});
	
});


function initTable() {
	
	$table = $('#tableBox').bootstrapTable({
        method: 'post',
		url:"${basepath}/goods/list",
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
        },{
        	field : "id",
        	title : 'ID'
        },{
            field: 'images',
            title: '图片',
            formatter : function(data) {
            	var html = '';
            	if (data) {
            		var imgArrays = JSON.parse(data);
            		html = html += '<img class="img-responsive img-w-h" src="'+domain+imgArrays[0]+'@1e_300w_194h_1c_0i_1o_100q_100sh_1x.jpg" >';
            	}
            	return html;
            }
        },{
            field: 'name',
            title: '商品名'
        },{
            field: 'username',
            title: '卖家'
        },{
        	field : 'phone',
        	title : '卖家联系号码'
        },{
        	field : 'columnName',
        	title : '一级分类'
        },{
        	field : 'categoryName',
        	title : '二级分类'
        },{
        	field : 'createUtc',
        	title : '创建时间',
        	formatter : function(data) {
        		if (data) {
        			return formatUTC(data, 'yyyy-MM-dd hh:mm');
        		}
        		return '';
        	}
        },{
            field: 'status',
            title: '状态',
			formatter: function(data, row ,index){
				if (row.status == 0) {
					return '<span class="label label-info">未上架</span>';
				} else if(row.status == 1){
                    return '<span class="label label-primary">出售中</span>';
                } else if (row.status == 2) {
                    return '<span class="label label-default">已下架</span>';
                } else if (row.status == 3) {
                    return '<span class="label label-warning">已拍下</span>';
                } else if (row.status == 4) {
                    return '<span class="label label-success">已售出</span>';
                }
                
                return '';
            }
        },{
            field: 'status',
            title: '操作',
            formatter: function(data, row ,index){
                var html = '<div class="btn-group">';
               
                html += ' <a class="btn btn-primary btn-sm btn-mini" href="${basepath}/goods/toDetail?id='+row.id+'">详情</a>';
                if(row.status == 0 || row.status == 2){
                	html += ' <a class="btn btn-success btn-sm btn-mini">上架</a>';
                }else if (row.status == 1) {
                	html += ' <a class="btn btn-warning btn-sm btn-mini">下架</a>';
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
	//
	changeStatus: function(_id, _status, _tips, _fun){
		W.$.confirm(
			_tips,
			function(){
				var AjaxURL = "${basepath}/goods/updateStatus"; //API接口
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
	
	deleteGoods: function(_id, _fun){
		var id = _id;
		if($.isArray(_id)){ //数组类型
			id = id.join(",");
		}
		
		W.$.confirm(
			"确定要删除吗？",
			function(){
				var AjaxURL = "${basepath}/goods/delete"; //API接口
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
	search : function(){
		$table.bootstrapTable('refresh');
	},
	//检索，下拉状态 ： 正常或禁用
	retrieve : function(){
		$table.bootstrapTable('refresh');
	},
	isArray : function(v){
		return toString.apply(v) === '[object Array]';
	}
});

</script>
</body>
</html>