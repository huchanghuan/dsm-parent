<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../global/header.jsp" %>
<link rel="stylesheet" href="${basepath }/res/uploadify-pic/css/zyUpload.css">
<link rel="stylesheet" href="${basepath }/res/css/ladda-themeless.min.css">
</head>
<body>
<div id="content">
	<div id="content-header">
		<div id="breadcrumb"> 
	  	<a href="${basepath }/main/index" target="_top" title="首页" class="tip-bottom"><i class="icon-home"></i> 主页</a> 
		  <a href="#" class="tip-bottom">运营管理</a> 
		  <a href="#" class="current">编辑专柜</a> 
	  </div>	  
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
        	<div class="span12">
              <div class="widget-box user-add-box">
                <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
                  <h5>添加专柜</h5>
                </div>
                <div class="widget-content nopadding">
                  <form action="javascript:void(0);" method="post" class="form-horizontal">
                   
                   <input type="hidden" name="id" value="${goodsShoppe.id }">
                   <input type="hidden" name="status" value="${goodsShoppe.status }">
                   <input type="hidden" name="image2" value="${goodsShoppe.image2 }">
                   <input type="hidden" name="ordinal" value="${goodsShoppe.ordinal }">
                   
                    <div class="control-group media">
                    	<label class="control-label">上传资源:</label>
	                    <div class="controls">
	                      
	                      		<!-------已有设置-------->
	                      		<div class="oldpic clearfix">
	                                <ul>
	                                   <!-- <li><img picID='21231231' src="http://a.broadin.cn/multimedia/album/default/97/173c548e34bcdcca655f167c93dea7bc.jpg@1e_320w_400h_1c_0i_1o_100Q_1x.jpg"></li>-->
	                               		<%-- <c:if test="${goodsShoppe.image1 != null && goodsShoppe.image1 != '' }">
	                               			<li><img picID='21231231' src="http://a.broadin.cn/multimedia/album/default/97/173c548e34bcdcca655f167c93dea7bc.jpg@1e_320w_400h_1c_0i_1o_100Q_1x.jpg"></li>
	                               		</c:if> --%>
	                                </ul>
	                        	</div>
	                            <!-------已有设置-END------->
	                        	<div id="picload">
	                        	
	                        	</div>
	                      </div>
                  	</div>
                  	
                  	<div class="control-group">
                      <label class="control-label" for="con">图片:</label>
                      <div class="controls">
                        <input id="con" name="image1" class="span11" data-valid="[{rule:'not_empty'}]" placeholder="必填,图片视频上传后自动获得" readonly="readonly" value="${goodsShoppe.image1 }">
                      </div>
                    </div>
                  
                    <div class="control-group" style="width:500px;">
                      <label class="control-label" for="name">专柜名:</label>
                      <div class="controls">
                        <input id="name" name="title" type="datetime" class="span11" placeholder="必填,不大于20" data-valid="[{rule:'not_empty'}]" maxlength="20" value="${goodsShoppe.title }">
                      </div>
                    </div>
                    
                     <div class="control-group">
                      <label class="control-label">专柜广告语：</label>
                      <div class="controls">
                        <textarea rows="5" class="span11" name="slogan" placeholder="选填，长度不大于128">${goodsShoppe.slogan }</textarea>
                      </div>
                    </div>
                    
                    <div class="control-group">
                      <label class="control-label">描述语：</label>
                      <div class="controls">
                        <textarea rows="5" class="span11" name="description" placeholder="选填，长度不大于255">${goodsShoppe.description }</textarea>
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
<script src="${basepath }/res/uploadify-pic/js/zyFile.js"></script>
<script src="${basepath }/res/uploadify-pic/js/zyUpload.js"></script>

<script type="text/javascript">
var $form = null;
$(function(){
	//验证
	$form = $("div.widget-content > form").inputValid();
	
	$("div.form-actions > button[type='submit']").click(function(){
		if ($form.validate_all()) {
			var params = $("div.widget-content > form").serialize();
			add(params, function(){
				history.back();
			});
		}
	});
	
	
	initUpload();
	
});

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
				$("input[name='image1']").val(json.url);
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
		url : '${basepath}/goodsShoppe/saveOrUpdate',
		success : function(json) {
			if (json.code == '000000') {
				W.$.alert('添加成功',1);
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