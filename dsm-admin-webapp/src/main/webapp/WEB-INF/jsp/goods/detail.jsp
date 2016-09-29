<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../global/header.jsp" %>
<style>
.p-pic {
	margin:5px;
}
</style>
</head>

<body>
<div id="content">
	<div id="content-header">
		<div id="breadcrumb"> 
	  	<a href="${basepath }/main/index" target="_top" title="首页" class="tip-bottom"><i class="icon-home"></i> 主页</a> 
		  <a href="#" class="tip-bottom">商品管理</a> 
		  <a href="#" class="current">商品详情</a> 
	  </div>
	</div>
    
	<div class="container-fluid">
		<div class="row-fluid">
        	<div class="span12">
            
              
              
              <div class="widget-box cool-list-box">
                  
                  <div class="widget-title">
                  	
                    <h5>产品ID：<b>${goods.id }</b></h5>
                    
                    
                  </div>
                  
                  <div class="widget-content nopadding">
                    <table class="table table-bordered table-striped">
                      
                      <tbody>
                        <!-- <tr>
                          <td width="200">产品ID</td>
                          <td>4578454545</td>
                          <td style="min-width:250px;">&nbsp;</td>
                        </tr> -->
                        <tr>
                          <td>状态</td>
                          <td>${goods.status }</td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td>发布时间</td>
                          <td class="pubUtc">
                          	${goods.pubUtc }
                          </td>
                          <td><!-- <button class="btn btn-info pull-left">产品详情</button> --></td>
                        </tr>
                        <tr>
                          <td>发布者ID</td>
                          <td>${goods.userId }</td>
                          <td><button class="btn btn-info pull-left">用户信息</button></td>
                        </tr>
                        <tr>
                          <td>产品名称</td>
                          <td>${goods.name }</td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td>商品图片</td>
                          <td class="product_img">
                          	${goods.images }
                          </td>
                          <td>&nbsp;</td>
                        </tr>
              
                        <tr>
                          <td>价格</td>
                          <td>55</td>
                          <td>&nbsp;</td>
                        </tr>
                        
                        <tr>
                          <td>详情说明</td>
                          <td>${goods.desp }</td>
                          <td>&nbsp;</td>
                        </tr>
                        
                        <tr>
                          <td>&nbsp;</td>
                          <td>&nbsp;</td>
                          <td>&nbsp;</td>
                        </tr>
                        
                      </tbody>
                    </table>
                    
                    
                  </div>
                  
                </div>
                
            </div>
        </div>
        <button onClick="javascript:history.back()" class="btn btn-danger pull-left">返回</button>
	</div>
</div>

<script src="${basepath }/res/js/jquery.min.js"></script>
<script src="${basepath }/res/js/util.js"></script>
<script>
;(function(){
	$(".pubUtc").html(formatUTC($(".pubUtc").html(), 'yyyy-MM-dd hh:mm'));
	var images = $(".product_img");
	var html = JSON.parse(images.html()).map(function(ele, index){
		return '<img class="p-pic" src="' + ele + '">';
	}).join('');
	images.html(html);
	
})();
</script>
</body>
</html>