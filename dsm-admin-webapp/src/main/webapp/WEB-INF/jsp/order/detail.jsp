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
		  <a href="#" class="current">订单详情</a> 
	  </div>
	</div>
    
	<div class="container-fluid">
		<div class="row-fluid">
        	<div class="span12">
            
              
              
              <div class="widget-box cool-list-box">
                  
                  <div class="widget-title">
                  	
                    <h5>订单号：<b>7897547985647484</b></h5>
                    
                    
                  </div>
                  
                  <div class="widget-content nopadding">
                    <table class="table table-bordered table-striped">
                      
                      <tbody>
                      <tr>
                          <td width="200">订单状态</td>
                          <td>
                          		<c:if test="${order.status==1 }">
                          			<span class="label label-info">已付款</span>
                          		</c:if>
                          		<c:if test="${order.status==2 || order.status==14 }">
                          			<span class="label label-warning">已发货</span>
                          		</c:if>
                          		<c:if test="${order.status==3 || order.status==4 }">
                          			<span class="label label-success">已签收</span>
                          		</c:if>
                          		<c:if test="${order.status==10 || order.status==11 ||order.status==12 }">
                          			<span class="label label-danger">退款中</span>
                          		</c:if>
                          		<c:if test="${order.status==13 }">
                          			<span class="label label-success">已退款</span>
                          		</c:if>
                          		<c:if test="${order.status==127 }">
                          			<span class="label label-default">已取消</span>
                          		</c:if>
                          </td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td>下单时间</td>
                          <td class="fmtDate">${order.createUtc }</td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td>付款时间</td>
                          <td class="fmtDate">${order.payUtc }</td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td>发货时间</td>
                          <td class="fmtDate">${order.deliverUtc }</td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td>收货时间</td>
                          <td class="fmtDate">${order.receiptUtc}</td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td>商品ID</td>
                          <td>${order.goodsId }</td>
                          <td><button class="btn btn-info pull-left" onclick="location.href='${basepath}/goods/toDetail?id=${order.goodsId }'">产品详情</button></td>
                        </tr>
                        <tr>
                          <td>卖家ID</td>
                          <td>${order.sellerId }</td>
                          <td><button class="btn btn-info pull-left">用户信息</button></td>
                        </tr>
                        <tr>
                          <td>商品名称</td>
                          <td>${order.goodsName }</td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td>商品价格</td>
                          <td>
                          	<fmt:formatNumber value="${order.dealPrice/100 }" type="currency" currencySymbol="￥" maxFractionDigits="2"></fmt:formatNumber>
                          </td>
                          <td>&nbsp;</td>
                        </tr>
                        <tr>
                          <td>商品图片</td>
                          <td>
                          <img picID='2131' src="http://a.broadin.cn/multimedia/album/default/97/1423cf0e0eec23df6115ec2564aa57e9.jpg@1e_100w_100h_1c_0i_1o_100Q_1x.jpg">
                          </td>
                          <td>&nbsp;</td>
                        </tr>
              
                        <tr>
                          <td>收件人</td>
                          <td>${order.receiver }</td>
                          <td>&nbsp;</td>
                        </tr>
                        
                        <tr>
                          <td>收件人电话</td>
                          <td>${order.phone }</td>
                          <td>&nbsp;</td>
                        </tr>
                        
                        <tr>
                          <td>收件人地址</td>
                          <td>${order.expressAddr }</td>
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
<%@ include file="../global/footer.jsp" %>
<script type="text/javascript">

$(function(){
	
	$(".fmtDate").livequery(function(){
		$(this).html(formatUTC($(this).html(), 'yyyy-MM-dd hh:mm'));
	});
	
});
</script>
</body>
</html>