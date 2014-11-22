<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/layout/taglib.jsp" %>
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="http://v3.bootcss.com/favicon.ico">

    <title>Detail</title>

    <!-- Bootstrap core CSS -->
    <link href="${ctx}/static/css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <script src="${ctx}/static/js/jquery.min.js"></script>
    <script src="${ctx}/static/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            var data = {
                name : "Apple",
                detail : "Detail information of APPLE.",
                alias : [
                    {
                        "language" : "CN",
                        "value" : "中国"
                    },
                    {
                        "language" : "EN",
                        "value" : "APPLE INC."
                    },
                    {
                        "language" : "CN",
                        "value" : "中国"
                    },
                    {
                        "language" : "EN",
                        "value" : "APPLE INC."
                    },
                    {
                        "language" : "CN",
                        "value" : "中国"
                    },
                    {
                        "language" : "EN",
                        "value" : "APPLE INC."
                    },
                    {
                        "language" : "CN",
                        "value" : "中国"
                    },
                    {
                        "language" : "EN",
                        "value" : "APPLE INC."
                    }
                ]
            }

            $("#name").text(data.name);
            $("#detail").text(data.detail);

            var content = "";

            for(var i = 0 ;i < data.alias.length;i++){
                if(i % 3 == 0){
                    content += '<div class="row">'
                }
                var eachValue = '<div class="col-md-4">'+
                '<h2>' + data.alias[i].language + '</h2>' +
                '<p>' + data.alias[i].value + '</p>' +
                '</div>';
                content += eachValue;
                if( i % 3 == 2){
                    content += '</div>'
                }
            }
            $("#content").prepend(content);
        })
    </script>

  </head>

  <body>

    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation" style="background:#8cc63e;">
      <div class="container" >
        <div class="navbar-header">
          <a class="navbar-brand" style="color:#FFF;">Detail Information</a>
        </div>
      </div>
    </nav>

    <!-- Main jumbotron for a primary marketing message or call to action -->
    <div class="jumbotron">
      <div class="container">
        <h1 id="name">Apple</h1>
        <p id="detail">Detail information of APPLE.</p>
      </div>
    </div>

    <div id="content" class="container">
      <!-- Example row of columns -->

      <hr>
      <footer>
        <p>© PatSnap 2014</p>
      </footer>
    </div> <!-- /container -->
</body></html>