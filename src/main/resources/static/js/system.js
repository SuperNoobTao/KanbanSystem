$(function(){
	tabClose();
	tabCloseRightButtonEvent();

	//关闭修改密码框
	closePwd();

	//初始化首页时钟
	clockon();

	//初始化顶部快捷导航
	initTopQuickNav();

	
	// 导航菜单绑定初始化
	// $("#system_menu").accordion( {
	// 	animate : false
	// });

	initLeftMenu(0);


	//初始化退出功能
	logOut();
	//初始化修改密码弹框
	openPwd();
});

/**
 * [initTopQuickNav 初始化顶部快捷导航]
 * @return {[type]} [description]
 */
function initTopQuickNav(_index){
	if (!_index) { _index = 0;};
	$('.system_nav a:eq('+_index+')').addClass("active");
	$('.system_nav a').click(function() {
		$('.system_nav a').removeClass('active');
		$(this).addClass('active');
		initLeftMenu($(".system_nav a").index($(this)));
	});
}


/**
 * [initLeftMenu 初始化左侧菜单]
 * @return {[type]} [description]
 */
function initLeftMenu(_index){
	//清除左侧菜单
	clearNav();

	if (!_index) { _index = 0;};
	var firstMenuName = $('.system_nav a:eq('+_index+')').attr('name');
	
	//添加菜单
	addNav(_menus[firstMenuName]);
	initLeftMenuEvent();
}

/**
 * @return {[type]}
 */
function initLeftMenuEvent(){
	$('#system_menu li a').unbind('click').bind('click', function() {
		var tabTitle = $(this).children('.nav').text();

		var url = $(this).attr("rel");
		var menuid = $(this).attr("ref");
		var icon = getIcon(menuid, icon);

		addTab(tabTitle, url, icon);
		$('#system_menu li div').removeClass("selected");
		$(this).parent().addClass("selected");
	});
}

/**
 * [getIcon 获取左侧导航菜单的图标]
 * @param  {[type]} menuid [description]
 * @return {[type]}        [description]
 */
function getIcon(menuid) {
	var icon = 'icon ';
	$.each(_menus, function(i, n) {
		$.each(n, function(j, o) {
			$.each(o.menus, function(k, m){
				if (m.menuid == menuid) {
					icon += m.icon;
					return false;
				}
			});
		});
	});
	return icon;
}

/**
 * [addTab 添加内容区域tab页]
 * @param {[type]} subtitle [description]
 * @param {[type]} url      [description]
 * @param {[type]} icon     [description]
 */
function addTab(subtitle, url, icon) {
	if (!$('#tabs').tabs('exists', subtitle)) {
		$('#tabs').tabs('add', {
			title : subtitle,
			content : createFrame(url),
			closable : true,
			icon : icon
		});
	} else {
		$('#tabs').tabs('select', subtitle);
		$('#mm-tabupdate').click();
	}
	tabClose();
}

/**
 * [createFrame 创建并加载内容区域]
 * @param  {[type]} url [description]
 * @return {[type]}     [description]
 */
function createFrame(url) {
	var s = '<iframe scrolling="auto" frameborder="0"  src="' + url + '" style="width:100%;height:100%;"></iframe>';
	return s;
}


/**
 * [clearNav 清除左侧导航菜单]
 * @return {[type]} [description]
 */
function clearNav() {
	var pp = $('#system_menu').accordion('panels');
	for (var i = pp.length - 1; i >= 0; i--) {
		$('#system_menu').accordion('remove', pp[i].panel('options').title);
	};

	/*
	
	$.each 使用出错，最后一个对象为空（O(∩_∩)O~）
	太悲催了，找了一天
	$.each(pp, function(i, n) {
		if (n) {
			var t = n.panel('options').title;
			$('#system_menu').accordion('remove', t);
		}
	});
	pp = $('#system_menu').accordion('getSelected');
	if (pp) {
		var title = pp.panel('options').title;
		$('#system_menu').accordion('remove', title);
	}
	*/
}

/**
 * [addNav 添加菜单]
 * @param {[type]} data [description]
 */
function addNav(data) {
	$.each(data, function(i, sm) {
		var menulist = "";
		menulist += '<ul>';
		$.each(sm.menus, function(j, o) {
			menulist += '<li><div><a ref="' + o.menuid + '" href="javascript:void(0)" rel="'
					+ o.url + '" ><span class="icon ' + o.icon
					+ '" >&nbsp;</span><span class="nav">' + o.menuname
					+ '</span></a></div></li> ';
		});
		menulist += '</ul>';

		$('#system_menu').accordion('add', {
			title : sm.menuname,
			content : menulist,
			iconCls : 'icon ' + sm.icon
		});

	});

	var pp = $('#system_menu').accordion('panels');
	// var t = pp[0].panel('options').title;
	var t = pp[pp.length-1].panel('options').title;
	$('#system_menu').accordion('select', t);
}



/**
 * [tabClose 内容区域tab选项卡“关闭”事件初始化]
 * @return {[type]} [description]
 */
function tabClose() {
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function() {
		var subtitle = $(this).children(".tabs-closable").text();
		$('#tabs').tabs('close', subtitle);
	});
	/* 为选项卡绑定右键 */
	$(".tabs-inner").bind('contextmenu', function(e) {
		$('#mm').menu('show', {
			left : e.pageX,
			top : e.pageY
		});

		var subtitle = $(this).children(".tabs-closable").text();

		$('#mm').data("currtab", subtitle);
		$('#tabs').tabs('select', subtitle);
		return false;
	});
}

/**
 * [tabCloseRightButtonEvent 内容区域tab页面右键菜单]
 * @return {[type]} [description]
 */
function tabCloseRightButtonEvent() {
	// 刷新
	$('#mm-tabupdate').click(function() {
		var currTab = $('#tabs').tabs('getSelected');
		var url = $(currTab.panel('options').content).attr('src');
		$('#tabs').tabs('update', {
			tab : currTab,
			options : {
				content : createFrame(url)
			}
		});
	});
	// 关闭当前
	$('#mm-tabclose').click(function() {
		var currtab_title = $('#mm').data("currtab");
		$('#tabs').tabs('close', currtab_title);
	});
	// 全部关闭
	$('#mm-tabcloseall').click(function() {
		$('.tabs-inner span').each(function(i, n) {
			var t = $(n).text();
			$('#tabs').tabs('close', t);
		});
	});
	// 关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function() {
		$('#mm-tabcloseright').click();
		$('#mm-tabcloseleft').click();
	});
	// 关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function() {
		var nextall = $('.tabs-selected').nextAll();
		if (nextall.length == 0) {
			msgShow('系统提示','后边没有啦~~','error');
			//alert('后边没有啦~~');
			return false;
		}
		nextall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			$('#tabs').tabs('close', t);
		});
		return false;
	});
	// 关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function() {
		var prevall = $('.tabs-selected').prevAll();
		if (prevall.length == 0) {
			alert('到头了，前边没有啦~~');
			return false;
		}
		prevall.each(function(i, n) {
			var t = $('a:eq(0) span', $(n)).text();
			$('#tabs').tabs('close', t);
		});
		return false;
	});

	// 退出
	$("#mm-exit").click(function() {
		$('#mm').menu('hide');
	});
}

/**
 * [msgShow 弹出菜单]
 * @param  {[type]} title     [description]
 * @param  {[type]} msgString [description]
 * @param  {[type]} msgType   [description]
 * @return {[type]}           [description]
 */
function msgShow(title, msgString, msgType) {
	$.messager.alert(title, msgString, msgType);
}

/**
 * [clockon 首页时钟展示]
 * @param  {[type]} obj [description]
 * @return {[type]}     [description]
 */
function clockon() {
	var now = new Date();
	var year = now.getFullYear(); // getFullYear getYear
	var month = now.getMonth();
	var date = now.getDate();
	var day = now.getDay();
	var hour = now.getHours();
	var minu = now.getMinutes();
	var sec = now.getSeconds();
	var week;
	month = month + 1;
	if (month < 10)
		month = "0" + month;
	if (date < 10)
		date = "0" + date;
	if (hour < 10)
		hour = "0" + hour;
	if (minu < 10)
		minu = "0" + minu;
	if (sec < 10)
		sec = "0" + sec;
	var arr_week = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
	week = arr_week[day];
	var time = "";
	time = year + "年" + month + "月" + date + "日" + " " + hour + ":" + minu
			+ ":" + sec + " " + week;

	$("#bgclock").html(time);

	var timer = setTimeout("clockon()", 200);
}

/**
 * [closePwd 关闭登录窗口]
 * @return {[type]} [description]
 */
function closePwd() {
    $('#w').window('close');
}

/**
 * [logOut 退出]
 * @return {[type]} [description]
 */
function logOut(){
	$('.loginOut').click(function() {
	    $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
	        if (r) {
	            
	        }
	    });
	})
}




/**
 * [openPwd 打开修改密码页面]
 * @return {[type]} [description]
 */
function openPwd() {
	$('.editPass').click(function() {
        $('#w').window('open');
    });
    $('#w').window({
        title: '修改密码',
        width: 300,
        modal: true,
        shadow: true,
        closed: true,
        height: 160,
        resizable:false
    });
}
