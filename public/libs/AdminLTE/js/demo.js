$(function() {
	/* For demo purposes */
	var demo = $("<div />").html("<i class='fa fa-gear'></i>").addClass(
			"no-print global-display-purposes");

	var demo_settings = $("<div />").addClass(
			"no-print global-display-settings");
	demo_settings
			.append("<h4 style='margin: 0 0 5px 0; border-bottom: 1px dashed #ddd; padding-bottom: 3px;'>表示設定</h4>"
					+ "<div class='form-group no-margin'>"
					+ "<div class='.checkbox'>"
					+ "<label>"
					+ "<input type='checkbox' onchange='change_layout();'/> "
					+ "メニューを固定する" + "</label>" + "</div>" + "</div>");
	demo_settings
			.append("<h4 style='margin: 0 0 5px 0; border-bottom: 1px dashed #ddd; padding-bottom: 3px;'>テーマ</h4>"
					+ "<div class='form-group no-margin'>"
					+ "<div class='.radio'>"
					+ "<label>"
					+ "<input name='skins' type='radio' onchange='change_skin(\"skin-black\");' checked='checked'/> "
					+ "ブラック"
					+ "</label>"
					+ "</div>"
					+ "</div>"
					+ "<div class='form-group no-margin'>"
					+ "<div class='.radio'>"
					+ "<label>"
					+ "<input name='skins' type='radio' onchange='change_skin(\"skin-blue\");'/> "
					+ "ブルー" + "</label>" + "</div>" + "</div>");

	demo.click(function() {
		if (!$(this).hasClass("open")) {
			$(this).css("right", "200px");
			demo_settings.css("right", "0");
			$(this).addClass("open");
		} else {
			$(this).css("right", "0");
			demo_settings.css("right", "-200px");
			$(this).removeClass("open")
		}
	});

	var page_top = $("<div />", {
		"class" : "no-print global-page-top",
	}).append($("<a />", {
		"href" : "#",
	}).prepend($("<i />", {
		"class" : "fa fa-arrow-up"
	})));
	page_top.hide();
	// スクロールが100に達したらボタン表示
	$(window).scroll(function() {
		if ($(this).scrollTop() > 100) {
			if (page_top.is(":hidden")) {
				page_top.show();
			}
		} else {
			page_top.hide();
		}
	});
	// スクロールしてトップ
	page_top.click(function() {
		$('body,html').animate({
			scrollTop : 0
		}, 500);
		return false;
	});

	$("body").append(demo);
	$("body").append(demo_settings);
	$("body").append(page_top);
});

function change_layout() {
	$("body").toggleClass("fixed");
	fix_sidebar();
}
function change_skin(cls) {
	$("body").removeClass("skin-blue skin-black");
	$("body").addClass(cls);
}
