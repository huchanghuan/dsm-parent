var W = window.top;


$(function(){
	
	$("a").each(function(index, element) {
        var href = $(element).attr("href");
        if (href == "#" || href == "") {
            $(element).click(function() {
                return false;
            });
        }
    });
	
	
	$('.left_menu .logo h1').click(function(){
		window.location.href = $(this).attr('data-original');
	});
	
	$('.submenu > a').click(function(e){
		e.preventDefault();
		var submenu = $(this).siblings('ul');
		var li = $(this).parents('li');
		var submenus = $('#sidebar li.submenu ul');
		var submenus_parents = $('#sidebar li.submenu');
		if(li.hasClass('open')){
			if(($(window).width() > 768) || ($(window).width() < 479)) {
				submenu.slideUp();
			} else {
				submenu.fadeOut(250);
			}
			li.removeClass('open');
		} else {
			if(($(window).width() > 768) || ($(window).width() < 479)) {
				submenus.slideUp();			
				submenu.slideDown();
			} else {
				submenus.fadeOut(250);			
				submenu.fadeIn(250);
			}
			submenus_parents.removeClass('open');		
			li.addClass('open');	
		}
	});
	
	$(".submenu > ul > li").click(function(){
		$(".submenu > ul > li").removeClass("active");
		$(this).addClass("active");
		
		$("#sidebar .submenu").removeClass("active");
		$(this).parents(".submenu").addClass("active");
	});
	
	// === Resize window related === //
	$(window).resize(function()
	{
		initIframeHeight();
	});
	
	
	
	initIframeHeight();
	function initIframeHeight(){
		
		var winH = $(window).height();
		var headH = $("#header").height();
		
		$("#win").css({
			height : winH - headH + "px"
		});
		
	}
	
	$('select.screen').select2 && $('select.screen').select2();
	
	//$(".right_container").load("news_list.php");
});