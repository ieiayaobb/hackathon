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
            var company = '${company}';
            var data = $.parseJSON(company);
            console.log(data);

            $("#name").text(data.name);
            $("#web").attr("href",data.web).text(data.web);
            var content = "";

            var i = 0;
            for (var prop in data.alias){
                if(i % 3 == 0){
                    content += '<div class="row">'
                }
                var eachValue = '<div class="col-md-4">'+
                '<h2>' + prop + '</h2>' +
                '<p>' + data.alias[prop] + '</p>' +
                '</div>';
                content += eachValue;
                if( i % 3 == 2){
                    content += '</div>'
                }
                i++;
            }
            $("#content").append(content);
            var description = "";

            for (var prop in data.description){
                if(i % 3 == 0){
                    description += '<div class="row">'
                }
                var eachValue = '<div class="col-md-4">'+
                        '<h2>' + prop + '</h2>' +
                        '<p>' + data.description[prop] + '</p>' +
                        '</div>';
                description += eachValue;
                if( i % 3 == 2){
                    description += '</div>'
                }
                i++;
            }
            $("#description").append(description);
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
        <h1 id="name"></h1>
          <a id="web" href=""></a>
      </div>
    </div>

    <div id="content" class="container"></div>
      <!-- Example row of columns -->
      <hr>
    <div id="description" class="container"></div>
      <hr>
      <footer>
        <p>© PatSnap 2014</p>
      </footer>
    </div> <!-- /container -->
</body></html>