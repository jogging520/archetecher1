<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<input type="text" class="" style="color">
<div id="mydiv"></div>
<input type="button" value="LoadJS" onclick="loadJs();">
</body>
</html>

<script language="javascript" src="jquery-1.11.0.js"></script>
<script language="javascript">
	function loadJs(){
		/*
		jQuery.getScript("t.js",function(){
			t1();
		});
		*/
		
		jQuery.ajax({
		      url: "t.js",
		      dataType: "script",
		      cache: true
		}).done(function() {
		  t1();
		});
	}
</script>