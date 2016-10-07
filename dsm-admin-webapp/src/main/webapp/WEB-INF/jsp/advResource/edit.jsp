<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../global/header.jsp" %>
<link href="${basepath }/res/css/bootstrap-datetimepicker.css" rel="stylesheet">
<link rel="stylesheet" href="${basepath }/res/uploadify-pic/css/zyUpload.css">
<link rel="stylesheet" href="${basepath }/res/css/ladda-themeless.min.css">
</head>

<body>
<div id="content">
	<div id="content-header">
		<div id="breadcrumb"> 
	  	<a href="${basepath }/main/index" target="_top" title="首页" class="tip-bottom"><i class="icon-home"></i> 主页</a> 
		  <a href="#" class="tip-bottom">运营管理</a> 
		  <a href="#" class="tip-bottom">广告位列表</a> 
		  <a href="#" class="current">编辑广告位资源</a> 
	  </div>	  
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
        	<div class="span12">
              <div class="widget-box user-add-box">
                <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
                  <h5>编辑广告位资源</h5>
                </div>
                <div class="widget-content nopadding">
                  <form action="javascript:void(0);" method="post" class="form-horizontal">
                   
                   <input name="locationId" type="hidden" value="${advResource.locationId }">
                   <input name="id" type="hidden" value="${advResource.id }">
                   
                   <div class="control-group">
                      <label class="control-label" for="type">类型:</label>
                      <div class="controls">
                        <select id="type" name="type">
                       			<c:if test="${advResource.type == 0}">
                       				<option value="0" selected="selected">图片</option>
                       				<option value="1">视频</option>
	                        		<option value="2">文字</option>
                       			</c:if>
                       			<c:if test="${advResource.type == 1}">
                       				<option value="0">图片</option>
                       				<option value="1" selected="selected">视频</option>
	                        		<option value="2">文字</option>
                       			</c:if>
                       			<c:if test="${advResource.type == 2}">
                       				<option value="0">图片</option>
                       				<option value="1">视频</option>
	                        		<option value="2" selected="selected">文字</option>
                       			</c:if>
                        </select>
                      </div>
                    </div>
                    
                    <div class="control-group media">
                    	<label class="control-label">上传资源:</label>
	                    <div class="controls">
	                      
	                      		<!-------已有设置-------->
	                      		<div class="oldpic clearfix">
	                                <ul>
	                                   <!-- <li><img picID='21231231' src="http://a.broadin.cn/multimedia/album/default/97/173c548e34bcdcca655f167c93dea7bc.jpg@1e_320w_400h_1c_0i_1o_100Q_1x.jpg"></li>-->
	                                </ul>
	                        	</div>
	                            <!-------已有设置-END------->
	                        	<div id="picload">
	                        	
	                        	</div>
	                      </div>
                  	</div>
                  	
                  	<div class="control-group">
                      <label class="control-label" for="con">内容:</label>
                      <div class="controls">
                        <input id="con" name="content" class="span11" value="${advResource.content }"  data-valid="[{rule:'not_empty'}]" placeholder="必填,图片视频上传后自动获得" readonly="readonly">
                      </div>
                    </div>
                    
                    <div class="control-group">
                      <label class="control-label" for="url">跳转的URL :</label>
                      <div class="controls">
                        <input id="url" name="url" type="text" class="span11" value="${advResource.url }" placeholder="选填，长度不大于255" data-valid="[{rule:'url'}]" maxlength="255">
                      </div>
                    </div>
                    
                    <div class="control-group" style="width:500px;">
                      <label class="control-label" for="startUtc">生效时间 :</label>
                      <div class="controls">
                        <input id="startUtc" name="startUtcStr" type="datetime" class="span11 form_datetime" value="${advResource.startUtc }" placeholder="必填" data-valid="[{rule:'not_empty'}]" maxlength="20">
                      </div>
                    </div>
                    
                    <div class="control-group" style="width:500px;">
                      <label class="control-label" for="endUtc">结束时间 :</label>
                      <div class="controls">
                        <input id="endUtc" name="endUtcStr" type="datetime" class="span11 form_datetime" placeholder="必填" value="${advResource.endUtc }" data-valid="[{rule:'not_empty'}]" maxlength="20">
                      </div>
                    </div>
                    
                    
                    <div class="control-group">
                      <label class="control-label">备注：</label>
                      <div class="controls">
                        <textarea rows="5" class="span11" name="remark" placeholder="选填，长度不大于255">${advResource.remark }</textarea>
                      </div>
                    </div>
                    
                    
                    <div class="form-actions">
                    	<div class="control-label"></div>
                      <button type="submit" class="btn btn-success">保存</button>
                      <button class="btn btn-default" onclick="javascript:history.back();">返回</button>
                    </div>
                    
                  </form>
                </div>
              </div>
            </div>
        </div>
	</div>
</div>


<%@ include file="../global/footer.jsp" %>
<script src="${basepath }/res/js/inputvalid.js"></script>
<script src="${basepath }/res/js/bootstrap-datetimepicker.min.js"></script>
<script src="${basepath }/res/uploadify-pic/js/zyFile.js"></script>
<script src="${basepath }/res/uploadify-pic/js/zyUpload.js"></script>
<script type="text/javascript">
var $form = null;
$(function(){
	//验证
	$form = $("div.widget-content > form").inputValid();
	$("input[type='datetime']").each(function(i){
		$(this).val(formatUTC($(this).val(), 'yyyy-MM-dd hh:mm'));
	});
	
	$("div.form-actions > button[type='submit']").click(function(){
		if ($form.validate_all()) {
			var params = $("div.widget-content > form").serialize();
			add(params, function(){
				history.back();
			});
		}
	});
	
	$(".form_datetime").datetimepicker({format: 'yyyy-mm-dd hh:ii'});

	initUpload();
	change($("select[name='type']").val());
	console.log($("select[name='type']").val());
	
	$("select[name='type']").change(function (){
		var $this = $(this);
		change($this.val());
	});
});

function change(val) {
	if (val == 2) {
		$(".media").addClass("hide");
		//$("input[name='content']").val("");
		$("input[name='content']").removeAttr("readonly");
		
	} else {
		$(".media").removeClass("hide");
		//$("input[name='content']").val("");
		$("input[name='content']").attr("readonly","readonly");
	}
}

function initUpload () {
	$("#picload").zyUpload({
		width            :   "100%",                  // 宽度
		height           :   "auto",                  // 宽度
		itemWidth        :   "120px",                 // 文件项的宽度
		itemHeight       :   "100px",                 // 文件项的高度
		url              :   "${basepath}/public/upload",   	 // 上传文件的路径
		multiple         :   false,                    // 是否可以多个文件上传
		dragDrop         :   false,                   // 是否可以拖动上传文件
		del              :   true,                    // 是否可以删除文件
		finishDel        :   false,  				  // 是否在上传文件完成后删除预览
		/* 外部获得的回调接口 */
		onSelect: function(files, allFiles){// 选择文件的回调方法
			console.info("当前选择了以下文件：");
			console.info(files);
			console.info("之前没上传的文件：");
			console.info(allFiles);
			
			$(".upload_append_list").find(".file_del").click();
			$(".upload_append_list").remove();
			$("#status_info").html("");
		},
		onDelete: function(file, surplusFiles){// 删除一个文件的回调方法
			console.info("当前删除了此文件：");
			console.info(file);
			console.info("当前剩余的文件：");
			console.info(surplusFiles);
		},
		onSuccess: function(file,response){// 文件上传成功的回调方法
			//document.write(response)
			/* var response = eval("(" + response + ")");
			if(response.result == "00000000"){
				
				$("#uploadList_" + file.index).attr("data-url",response.picinfo.fileUrl);
			}else if(response.result == "00000001"){
				alert(response.error);
			} */
			var json = JSON.parse(response);
			if (json.code == '000000') {
				$("input[name='content']").val(json.url);
			} else {
				W.$.alert("上传失败", 1);
			}
		},
		onFailure: function(file){// 文件上传失败的回调方法
			console.info("此文件上传失败：");
			console.info(file);
		},
		onComplete: function(responseInfo){// 上传完成的回调方法
			//alert(responseInfo)
			console.info("文件上传完成");
			console.info(responseInfo);
		}
	});
}

function add($params, $fun) {
	$.ajax({
		type : 'POST',
		dataType : 'JSON',
		data : $params,
		url : '${basepath}/advResource/saveOrUpdate',
		success : function(json) {
			if (json.code == '000000') {
				W.$.alert('更新成功',1);
				$fun();
			} else {
				W.$.alert(json.msg, 2);
			}
		},
		error : function(e) {
			W.$.alert('异常');
		}
	});
		
	
}
</script>
</body>
</html>