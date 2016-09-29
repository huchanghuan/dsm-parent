<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../global/header.jsp" %>
</head>
<body>

<!--Header-part-->
<div id="header">
  <img alt="剁手猫LOGO" src="${basepath }/res/images/logo.png">
</div>
<!--close-Header-part--> 


<!--top-Header-menu-->
<div id="user-nav" class="navbar navbar-inverse pull-right">
  <ul class="nav">
    <li class=""><a title="" href="javascript:void(null);"><i class="icon icon-share-alt"></i> <span class="text">退出</span></a></li>
  </ul>
</div>
<!--close-top-Header-menu-->
<!--start-top-serch-->
<!-- <div id="search">
  <input type="text" placeholder="Search here..."/>
  <button type="submit" class="tip-bottom" title="Search"><i class="icon-search icon-white"></i></button>
</div> -->
<!--close-top-serch-->


<div class="left_menu clearfix">
	
	<!--sidebar-menu-->
	<div id="sidebar" class="menu_nav">
    	<a href="#" class="visible-phone"><i class="icon icon-home"></i> 首页</a>
	  <ul>
	  	<c:forEach items="${sessionScope.ADMIN_PERMISSION_SHOW_KEY }" var="menu" varStatus="vs">
		  	<li class="submenu">
		    	<a href="javascript:void(0);"><i class="icon icon-user"></i><span>${menu.groupName }</span></a>
		    	<ul>
		    		<c:forEach items="${menu.permissionList}" var="submenu">
		    			<li><a href="${basepath }${submenu.url}">${submenu.name }</a></li>
		    		</c:forEach>
		    	</ul>
		    </li>
	    </c:forEach>
	  </ul>
	</div>
	<!--sidebar-menu-->
</div>
<!--start-main-container-part-->
<div class="right_container">
	<iframe src="global_info.html" name="win" id="win" width="100%" style="overflow:auto;" frameborder="0"></iframe>
</div>
<!--end-main-container-part-->
<%@ include file="../global/footer.jsp" %>

<script type="text/javascript">
;!function(){
	layer.use('extend/layer.ext.js', function(){});
}();

$(function(){
	$(".submenu > ul > li > a").attr("target","win");
	
	//注销登录
	$("#user-nav li > a").click(function(){
		$.confirm("确定退出系统?",function(){
			top.location.href = "${basepath}/login/logout";
		},0);
	});
	
});

$.extend({
	prompt : function(_str , _fun , _type){
		var callback = _fun || function(){};
		layer.prompt({title: _str, type: _type}, function(name,index){
			callback(name);
			layer.close(index);
		});
	},
	confirm : function(_str , _fun , _style , _nofun){
		 $.layer({
			shade: [0.5, '#000'],
			area: ['400',"auto"],
			dialog: {
				msg: _str || "请确认此操作！",
				btns: 2,                    
				type: _style || 0,
				btn: ['确定','取消'],
				yes: function(index){
					_fun && _fun();
					layer.close(index);
					return true;
				}, no: function(index){
					_nofun && _nofun();
					layer.close(index);
					return false;
				}
			}
		});
	},
	alert : function(_str , _style){
		 layer.msg( _str, 1, _style );
	}
});
</script>
</body>
</html>
