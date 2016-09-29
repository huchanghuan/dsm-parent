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
		  <a href="#" class="current">广告位列表</a> 
	  </div>
	</div>
    
	<div class="container-fluid">
		<div class="row-fluid">
        	<div class="span12">

              <div class="widget-box cool-list-box">
                  
                  <div class="widget-title" style="height:40px">
                  	<!-- <span class="icon">
                    	<input type="checkbox" id="title-checkbox" name="title-checkbox">
                    </span> -->
                    <button class="btn btn-default pull-right">新建广告位</button>
                  </div>
                  
                  <div class="widget-content nopadding">
                    <table id="tableBox" class="table table-bordered table-striped"></table>   
                </div>
                  
                </div>
                
            </div>
        </div>
	</div>
</div>

<div class="popUpCover popduration"></div>
<div class="popClass popBox">
	<div class="popTitle">
    	分类编辑
    </div>
    <div class="row-fluid clearfix">
        <form>
    	
    	<input type="hidden" name="id" value="0">
    	
    	<div class="control-group">
            <label class="control-label span4">广告位名称 :</label>
            <div class="controls span8">
                <input id="className" type="text" name="name" placeholder="必填，不大于16" maxlength="16" data-valid="[{rule:'not_empty'}]" />
                <span class="help-inline"></span>
            </div>
        </div>
        
        
    	<div class="control-group">
            <label class="control-label span4"></label>
            <div class="controls span8">
                <button type="button" class="btn btn-success">保存</button>
                <button type="button" class="btn btn-primary">返回</button>
            </div>
        </div>
        </form>
    </div>
</div>

<%@ include file="../global/footer.jsp" %> 
<script src="${basepath }/res/js/inputvalid.js"></script>
<script type="text/javascript">
var $table = null;
var $form = null;
$(function(){
	
	$form = $(".popClass form").inputValid();
	
	initTable();
	
	//删除
	$("td .btn-danger").livequery(function(){
		$(this).click(function(){
			var dataArr = $table.bootstrapTable('getData');
            var _index = $(this).parents("tr").attr("data-index");
            $.deleteAdvLocation(dataArr[_index].id);
		});
	});
	
	//显示隐藏
	$("td .btn-success, td .btn-warning").livequery(function(){
		$(this).click(function(){
			var dataArr = $table.bootstrapTable('getData');
            var _index = $(this).parents("tr").attr("data-index");
            var _status = dataArr[_index].status == 0?1:0;
            var _tips = _status == 0?'是否显示此广告位？':'是否隐藏此广告位?';
            $.changeStatus(dataArr[_index].id, _status, _tips, function(){
            	$table.bootstrapTable('updateRow', {index: _index, row:{status: _status}});
            });
		});
	});
	
	//新增显示
	$(".widget-title button.btn-default").click(function(){
		$(".popClass form")[0].reset();
		showModal(0, '', '新增广告位');
	});
	
	//编辑显示
	$("td .btn-primary").livequery(function(){
		$(this).click(function(){
			var index = $(this).parents("tr").data("index");
			var data = $table.bootstrapTable("getData")[index];
			$(".popClass form")[0].reset();
			//console.log(data.id);
			showModal(data.id, data.name, '编辑广告位');
		});
	});
	
	
	//提交
	$(".popClass button.btn-success").click(function(){
		if ($form.validate_all()) {
			var params = $(".popClass form").serialize();
			$.sendSubmit(params, function(){
				$table.bootstrapTable("refresh");
				$(".popClass, .popUpCover").hide();
			});
		}
	});
	

	//关闭modal
	$(".popBox .btn-primary,.popUpCover").livequery(function(){
		$(this).click(function(){
			$(".popBox, .popUpCover").hide();
		});
	});
});


function showModal(id, name, title) {
	
	var form = $(".popClass form");
	$(".popTitle").html(title);
	$("[input[name='id']").val(id);
	$("input[name='name']").val(name);
	$(".popClass, .popUpCover").show();
}

function initTable() {
	
	$table = $('#tableBox').bootstrapTable({
        method: 'post',
		url:"${basepath}/advLocation/list",
        contentType:'application/x-www-form-urlencoded',
        sidePagination:'server',
        queryParams:function(params){
              
               return params;
        },
        pagination: true,
        pageSize : 10,
        columns: [{
        	field :'id',
        	title : '广告位ID'
        },{
        	field :'name',
        	title : '名称'
        },{
        	field:'createUtc',
        	title : '创建时间',
        	formatter : function (data) {
        		if (data){
        			return formatUTC(data, 'yyyy-MM-dd hh:mm');
        		}
        		return '';
        	}
        },{
            field: 'status',
            title: '状态',
            formatter : function(data){
            	var html = '';
            	if (data == 0){
            		html += '<label class="label label-success">显示</label>';
            	} else if (data == 1) {
            		html += '<label class="label label-default">隐藏</label>';
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
        		html += ' <a class="btn btn-info btn-sm btn-mini" href="${basepath}/advResource/toList?locationId='+row.id+'">详情</a>';
        		html += ' <button class="btn btn-primary btn-sm btn-mini">编辑</button>';
            	if(row.status == 0){
            		html += ' <a class="btn btn-warning btn-sm btn-mini">隐藏</a>';
            	}else{
            		html += ' <a class="btn btn-success btn-sm btn-mini">显示</a>';
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
				var AjaxURL = "${basepath}/advLocation/updateStatus"; //API接口
				$.ajax({
					type: "POST",
					dataType: "json",
					url: AjaxURL,
					data: { 
						"locationId"  : _id,
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
	
	deleteAdvLocation: function(_id){
		W.$.confirm(
			"确定要删除吗？",
			function(){
				var AjaxURL = "${basepath}/advLocation/delete"; //API接口
				$.ajax({
					type: "POST",
					dataType: "json",
					url: AjaxURL,
					data: { 
						locationId  : _id
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
	},
	
	sendSubmit : function(_params, _fun){
		$.ajax({
			type : 'post',
			dataType : 'json',
			data : _params,
			url : '${basepath}/advLocation/saveOrUpdate',
			success : function (json) {
				if (json.code == '000000') {
					_fun();
					W.$.alert("成功", 1);
				} else {
					W.$.alert("出错了", 2);
				}
			},
			error : function (e) {
				W.$.alert("错误", 2);
			}
		});
	}
});

</script>
</body>
</html>