<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../global/header.jsp" %>
<style>
.nav-tabs {
	margin:10px 0px 10px 0px
}
</style>
</head>

<body>
<div id="content">
	<div id="content-header">
	  	<div id="breadcrumb"> 
	  	<a href="${basepath }/main/index" target="_top" title="首页" class="tip-bottom"><i class="icon-home"></i> 主页</a> 
		  <a href="#" class="tip-bottom">类目管理</a> 
		  <a href="#" class="current">编辑品牌</a> 
	  </div>
	</div>
	<div class="container-fluid">
		<div class="row-fluid">
        	<div class="span12">
              <div class="widget-box">
                <div class="widget-title"> <span class="icon"> <i class="icon-align-justify"></i> </span>
                  <h5>编辑品牌</h5>
                </div>
                <div class="widget-content nopadding">
                  <form action="javascript:void(null);" method="post" class="form-horizontal">
                    <input type="hidden" name="visible" value="${brand.visible }">
                    <!-- <div class="control-group">
                      <label class="control-label">上传LOGO :</label>
                      <div class="controls">
                      
                      		-----已有设置------
                      		<div class="oldpic clearfix">
                                <ul>
                                    <li><img picID='21231231' src="http://a.broadin.cn/multimedia/album/default/97/173c548e34bcdcca655f167c93dea7bc.jpg@1e_295h_295h_1c_0i_1o_100Q_1x.jpg"></li>
                                </ul>
                        	</div>
                            -----已有设置-END-----
                            
                        	<div id="picload">
                        	
                        	</div>
                      </div>
                    </div> -->
                    <div class="control-group">
                      <label class="control-label" for="name">名称 :</label>
                      <div class="controls">
                        <input id="name" type="text" name="name" class="span11" value="${brand.name }" data-valid="[{rule:'not_empty'}]" placeholder="必填，长度不大于30" maxlength="30" readonly="readonly">
                      </div>
                    </div>
                    
                    <div class="control-group">
                      <label class="control-label" for="initial">首字母 :</label>
                      <div class="controls">
                        <input id="initial" type="text" name="initial" class="span11" value="${brand.initial }" data-valid="[{rule:'not_empty'},{rule:'upper_letter'}]" placeholder="必填，长度为1的大写字母" maxlength="1">
                      </div>
                    </div>
                    
                    
                    <div class="control-group">
                      <label class="control-label" for="pinyin">全拼 :</label>
                      <div class="controls">
                        <input id="pinyin" type="text" name="pinyin" class="span11" value="${brand.pinyin }" data-valid="[{rule:'not_empty'},{rule:'lower_letter'}]" placeholder="必填,不大于100" maxlength="100">
                      </div>
                    </div>
                    <div class="control-group">
                      <label class="control-label">分类 :</label>
                      <div class="controls columnId" data-checked="${brand.columnName }">
                        <%-- <select style="width:100px;" name="columnId">
                            <c:forEach items="${columnList }" var="column">
                            	<c:if test="${column.id!=0 }">
                            		<option value="${column.id }">${column.name }</option>
                            	</c:if>
                            </c:forEach>
                        </select> --%>
                        <c:forEach items="${columnList }" var="column">
                            	<c:if test="${column.id!=0 }">
                            		
                            		<label class="checkbox-inline span1">
									  <input type="checkbox" name="columnId" value="${column.id }"><span>${column.name }</span>
									</label>
                            	</c:if>
                          </c:forEach>
                      </div>
                    </div>
                    <div class="form-actions">
                    	<label class="control-label"></label>
                    	<div class="control">
                      <button type="button" class="btn btn-success">保存</button>
                      <button type="button" onclick="javascript:history.back();" class="btn btn-default">返回</button>
                   	</div>
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
<script type="text/javascript">
var $form = null;
$(function(){
	//验证
	$form = $("div.widget-content > form").inputValid();
	
	//保存
	$(".form-actions button.btn-success").click(function(){
		if ($form.validate_all()) {
			var columnIds = [];
			$("input[name='columnId']:checked").each(function(index, e){
				columnIds.push($(e).val());
			});
			var columnIdsStr = columnIds.join(',');
			save($("div.widget-content > form").serialize() + "&columnIdsStr=" + columnIdsStr, function(){
				window.location.href="${basepath}/brand/toList";
			});
		}
	});
	
	$("input[name='columnId']").change(function(){
		if ($("input[name='columnId']:checked").size() != 0) {
			$(".form-actions button.btn-success").removeAttr("disabled");
		} else {
			$(".form-actions button.btn-success").attr("disabled","disabled");
		}
	});
	
	$("input[name='columnId']").val($(".columnId").data("checked").toString().split(","));
});

function save($params, $fun) {
	$.ajax({
		type : 'POST',
		dataType : 'JSON',
		data : $params,
		url : '${basepath}/brand/update',
		success : function(json) {
			if (json.code == '000000') {
				W.$.alert('编辑成功',1);
				$fun();
			} else {
				W.$.alert(json.msg);
			}
		},
		error : function(e) {
			W.$.alert('异常');
		}
	});
		
	
}

$.extend({
	
});

</script>
</body>
</html>