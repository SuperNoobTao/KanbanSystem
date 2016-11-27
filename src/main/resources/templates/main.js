(function($) {
	$(function() {
		
		//加载一级菜单
		var url = "MODELFindLgoinFirstMenu.action";
		$.ajax( {
			type : "post",
			url : url,					
			contentType : "text/html",
			error : function(event,request, settings) {
				$.messager.alert("提示消息", "请求失败!", "info");
			},
			success : function(data) {
				
				$("#topmenu").empty();
				$("#topmenu").append("&nbsp;&nbsp;&nbsp;&nbsp;<a href=\"javascript:addTab('首页','welcome.htm')\" >首&nbsp;&nbsp;页</a>");
				if(data.length>0){
					//循环加载第一级别菜单
					for ( var i = 0; i < data.length; i++) {
						$("#topmenu").append("<a icode='"+data[i].id+"'>"+data[i].text+"</a>");	
					}	
					//自动加载第一个一级菜单下面的二级菜单
					
					$("#topmenu > a").get(1).click();	
				}
			}
		});
		
		//切换一级菜单 加载二级和三级菜单
		$("#topmenu > a").live('click',function(){
			
			//设置样式
			$("#topmenu > a").removeClass("active");
			$(this).addClass("active");
			//加载 二级菜单和三级菜单
			var menu1icode = $(this).attr("icode");
			if(!JUDGE.isNull(menu1icode)){
				
				$('#tt1').tree({   
				    url:"MODELFindLgoinSecondMenu.action?maindatauuid="+menu1icode,
					onClick: function(node) {
						if(node.attributes){
							addTab(node.text,node.attributes.href);	
						}
				    }
				});
				
			}
		});					
	});
})(window.jQuery);

function view(url){
	
	$('#iframe').attr('src',url);
}

/*
*添加选项卡方法
*/
function addTab(title,url){
	
	//先判断是否存在标题为title的选项卡
	var tab=$('#tt').tabs('exists',title);
	if(tab){
		//若存在则直接打开
		$('#tt').tabs('select',title);
	}else{
		//否则创建
		$('#tt').tabs('add',{
			title:title,
			content:"<iframe width='100%' height='100%'  id='iframe' frameborder='0' scrolling='auto'  src='"+url+"'></iframe>",
			closable:true
		});
	}
}

/*
*根据title 选中Accordion对应的面板
*/
function selectAccordion(title){
	
	$('#accordionPanel').accordion('select',title);
}

/*
*刷新时间
*/
function showTime(){
	var date=new Date();
	$('#timeInfo').html();
	$('#timeInfo').html('&nbsp;&nbsp;&nbsp;&nbsp;'+date.toLocaleString()+"&nbsp;&nbsp;");
}
setInterval(showTime,1000);

/*
*检测浏览器窗口大小改变 来改变页面layout大小
*/
$(function(){
	
	$(window).resize(function(){
		$('#cc').layout('resize');
	});
});