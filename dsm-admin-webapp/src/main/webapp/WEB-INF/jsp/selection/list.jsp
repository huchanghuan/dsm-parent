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
		  <a href="#" class="current">精选列表</a> 
	  </div>
	</div>
    
	<div class="container-fluid">
   	 	
          <h4>出售中商品</h4>
         
		<div class="row-fluid">
        	
            
        	<div class="span6">
              <form action="o_index_tj.php" method="post">
              <div class="searching-bar">
              		
                  <div class="control-group" style="width:280px;">
                      <label class="control-label">　关键字 :</label>
                      <div class="controls">
                          <input type="text" name="keyword" placeholder="输入关键字" style="width:140px;">
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
              </form>
              
              <div class="widget-box cool-list-box">
                  
                  <div class="widget-title">
                    <h5>商品列表</h5>
                  </div>
                  
                  <div class="widget-content nopadding">
                    <table id="tableBox" class="table table-bordered table-striped"></table>
                  </div>
                  
                </div>
                
            </div>
        
        	<div class="span6 o_index_tj">
            	<h5>当前 首页 推荐</h5>
                <hr>
                <div class="tj_list">
                	<ul>
                    	
                    </ul>
                </div>
            </div>
        	
        </div>
	</div>
</div>

<%@ include file="../global/footer.jsp" %>
<script src="${basepath }/res/js/jqPaginator.js"></script>
<script type="text/javascript">
var W = window.top;
var $table = null;
var limit = 20;
var domain = "http://cdn.duoshoucat.com/";
$(function(){
	
	initTable();
	
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
			} else W.$.alert('加载数据失败', 2);
		},'JSON');
	}
	
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
	
	getSelectionData(0);
	
	//精选
	$("td .btn-primary").livequery(function(){
		$(this).click(function(){
			var dataArr = $table.bootstrapTable("getData");
			var index = $(this).parents("tr").data("index");
			var goodsId = dataArr[index].id;
			var goodsName = dataArr[index].name;
			var images = dataArr[index].images;
			$.select(goodsId, goodsName, images);
		});
	});
	
	//移除
	$("ul > li i.icon-remove").livequery(function(){
		$(this).click(function(){
			var id = $(this).parents("li").data("id");
			$.cancel(id);
		});
	});
});

function getSelectionData(offset) {
	$.ajax({
		type : 'POST',
		dataType : 'json',
		data : {"offset":offset,"limit":limit},
		url : '${basepath}/selection/list',
		success : function (json) {
			if (!json || 0 == json.total)
				return;
			
			//填充内容
			$(".o_index_tj .tj_list > ul").html(callback(json.rows));
			
			//分页
			
		},
		error : function (e) {
			W.$.alert("出错了", 2);
		}
	});
}

function callback(records){
	return records.map(function(ele, index){
		return getHtml(ele.id, ele.goodsName, ele.images);
	}).join("");
}

function getHtml(id, goodsName, images) {
	var html = '<li data-id="'+id+'">';
    html += '<a target="_blank" href="http://iwancool.com/details.php?productID=10001">';
    html += '<img src="'+domain+JSON.parse(images)[0]+'@1e_120w_110h_1c_0i_1o_100Q_1x.jpg">';
    html += '<div class="text-center">'+goodsName+'</div>';
	html += '</a>'
	html += '<i class="icon-remove"></i>';
	html += '</li>';
	return html;
}

function initTable() {
	
	$table = $('#tableBox').bootstrapTable({
        method: 'post',
		url:"${basepath}/goods/list",
        contentType:'application/x-www-form-urlencoded',
        sidePagination:'server',
        queryParams:function(params){
               params.status = 1;
               params.keyword = $("input[name='keyword']").val();
               params.columnId = $("select[name='columnId']").val();
               params.categoryId = $("select[name='categoryId']").val();
               return params;
        },
        pagination: true,
        pageSize : 10,
        columns: [{
        	field : "id",
        	title : 'ID'
        },{
            field: 'images',
            title: '图片',
            formatter : function(data) {
            	var html = '';
            	if (data) {
            		var imgArrays = JSON.parse(data);
            		html = '<img src="'+domain+imgArrays[0]+'" width="70" height="47">';
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
        	field : 'createUtc',
        	title : '分享时间',
        	formatter : function(data) {
        		if (data) {
        			return formatUTC(data, 'yyyy-MM-dd hh');
        		}
        		return '';
        	}
        },{
            field: 'status',
            title: '操作',
            formatter: function(data, row ,index){
                var html = '<div class="btn-group">';
                html += ' <a class="btn btn-info btn-sm btn-mini" href="${basepath}/goods/toDetail?id='+row.id+'">详情</a>';
                html += ' <a class="btn btn-primary btn-sm btn-mini">推荐</a>';
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
	select: function(_id, _goodsName, _images){
		W.$.confirm(
				"确定要推荐吗？",function(){
		var AjaxURL = "${basepath}/selection/saveSelection"; //API接口
		$.ajax({
			type: "POST",
			dataType: "json",
			url: AjaxURL,
			data: { 
				goodsId  : _id
			},
			success: function (json) {
					if (json) {
						if (json.id == 0) {
							W.$.alert("请勿重复添加为精选");
							return;
						}
						
						$(".tj_list ul").append(getHtml(json.id, _goodsName, _images));
						W.$.alert("推荐成功",1);
					}else {
						W.$.alert("出错了");
					}
			},
			error: function(data) {
				W.$.alert("错误");
			}
		});
		},
		1,function(){}
		);
	},

	//取消推荐
	cancel: function(_id){
		W.$.confirm(
				"确定要取消吗？",function(){
		var AjaxURL = "${basepath}/selection/delete"; //API接口
		$.ajax({
			type: "POST",
			dataType: "json",
			url: AjaxURL,
			data: { 
				id  : _id
			},
			success: function (json) {
				if(json.code == '000000'){//成功
					$(".tj_list ul li[data-id='"+_id+"']").remove();
					W.$.alert("取消推荐成功",1);
				}else {
					W.$.alert("出错");
				}
			},
			error: function(data) {
				W.$.alert("错误");
			}
		});
		},
		1,
		function(){}
		);
	} 
});

</script>
</body>
</html>