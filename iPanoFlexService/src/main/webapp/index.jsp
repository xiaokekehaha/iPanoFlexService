<html>
<head>
<script type="text/javascript" src="js/swfobject.js"></script>
<script type="text/javascript">
	var swfVersionStr = "11.1.0";
	var xiSwfUrlStr = "swf/playerProductInstall.swf";
	var flashvars = {};
	var params = {};
	params.quality = "high";
	params.bgcolor = "#ffffff";
	params.allowscriptaccess = "sameDomain";
	params.allowfullscreen = "true";
	var attributes = {};
	attributes.id = "3d";
	attributes.name = "3d";
	attributes.align = "middle";
	swfobject.embedSWF("swf/3d.swf?conf=http://localhost:8080/iPanoFlexService/getconf?id=flashtest", "flashContent",
			"700", "400", swfVersionStr, xiSwfUrlStr, flashvars, params,
			attributes);
</script>
</head>
<body>
	<h2>Hello World!</h2>
	<div class="rightlist">
		<div width="700" height="400" id="flashContent">浏览器不支持flash
			请更换浏览器</div>
	</div>
</body>
</html>
