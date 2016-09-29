/*---------分类选择三级联动------------*/
$(function(){
	initClasSelect();
	function initClasSelect(_m,_s){
		$(".select_rank1 option[class!=defult],.select_rank2 option[class!=defult],.select_rank3 option[class!=defult]").remove();
		
		var m = _m || 0;
		var s = _s || 0;
		for(var i=0; i<classArr.mainClass.length; i++){
			var selected = i==m ? 'selected="selected"' : '';
			if(typeof(_m)=="undefined") selected = "";
			var mainClassli = '<option '+selected+' value="'+classArr.mainClass[i].id+'">'+classArr.mainClass[i].name+'</option>';
			$(".select_rank1").append(mainClassli);
		}
		
		for(var j=0; j<classArr.mainClass[m].subClass.length; j++){
			var selected = j==s ? 'selected="selected"' : '';
			if(typeof(_s)=="undefined") selected = "";
			var subClassli = '<option '+selected+' value="'+classArr.mainClass[m].subClass[j].id+'">'+classArr.mainClass[m].subClass[j].name+'</option>';
			$(".select_rank2").append(subClassli);
		}
		
	}
	$(".select_rank1").livequery(function(){
		$(this).change(function(){
			var val = $(this).val();
			var m = $(".select_rank1 option:selected").index(".select_rank1 option")-1;
			if(val == -1){
				$(".select_rank2 option").removeAttr("selected").eq(0).attr("selected","selected"); 
				$(".select_rank2").attr("disabled" , "disabled");
			}else {
				$(".select_rank2").removeAttr("disabled" , "disabled");
				initClasSelect(m);
			}
		});
	});
	
});