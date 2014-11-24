<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/layout/taglib.jsp" %>
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>ForceDirected - Force Directed Static Graph</title>

<!-- CSS Files -->
<link type="text/css" href="${ctx}/static/css/base.css" rel="stylesheet" />
<link type="text/css" href="${ctx}/static/css/ForceDirected.css" rel="stylesheet" />

<!-- JIT Library File -->
<script language="javascript" type="text/javascript" src="${ctx}/static/js/jit.js"></script>

<!-- Example File -->
<script language="javascript" type="text/javascript" src="${ctx}/static/js/fd.js"></script>
<script language="javascript" type="text/javascript" src="${ctx}/static/js/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
        var data = $.parseJSON('${document}');
        init(data);
    })
</script>
</head>

<body>
<div id="container">

<div id="center-container">
    <div id="infovis"></div>    
</div>
</div>
</body>
</html>
