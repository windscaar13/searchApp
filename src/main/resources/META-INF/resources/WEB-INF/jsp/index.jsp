<!DOCTYPE HTML>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta http-equiv="Pragma" content="no-cache">
    <meta http-equiv="Cache-Control" content="no-cache">
    <meta http-equiv="Expires" content="Sat, 01 Dec 2001 00:00:00 GMT">

    <title>Code Lookup | Home</title>

    <link href="static/css/bootstrap.min.css" rel="stylesheet">
     <link href="static/css/style.css" rel="stylesheet">

    <!--[if lt IE 9]>
		<script src="static/js/html5shiv.min.js"></script>
		<script src="static/js/respond.min.js"></script>
	<![endif]-->
</head>

<body>

  <div role="navigation">
    <div class="navbar navbar-inverse">
      <a href="/" class="navbar-brand">[CodeSearch]</a>
      <div class="navbar-collapse collapse">
        <ul class="nav navbar-nav">
          <li><a href="search">Code Search</a></li>
          <li><a href="new-code">Add Code</a></li>
          <li><a href="all-codes">All Codes</a></li>
        </ul>
      </div>
    </div>
  </div>

<c:choose>
  <c:when test="${mode == 'MODE_HOME'}">
    <div class="container" id="homeDiv">
      <div class="jumbotron text-center">
        <h1>Welcome to CODE SEARCH APP</h1>
      </div>
    </div>
  </c:when>
  <c:when test="${mode == 'MODE_CODES'}">
    <div class="container text-center" id="codesDiv">
      <h3>All Codes</h3>
      <hr>
      <div class="table-responsive">
        <table class="table table-striped table-bordered tex-left">
          <thead>
            <th>Id</th>
            <th>Code</th>
            <th>Description</th>
            <th>Edit</th>
            <th>Delete</th>
          </thead>
          <tbody>
            <c:forEach var="code" items="${codes}">
              <tr>
                <td>${code.id}</td>
                <td>${code.code}</td>
                <td>${code.description}</td>
                <td><a href="update-code?id=${code.id}"><span class="glyphicon glyphicon-pencil"></span></a></td>
                <td><a href="delete-code?id=${code.id}"><span class="glyphicon glyphicon-trash"></span></a></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
      </div>
    </div>
  </c:when>
  <c:when test="${mode == 'MODE_NEW' || mode == 'MODE_UPDATE'}">
    <div class="container text-center">
      <h3>Add/Update Codes</h3>
      <hr>
        <form class="form-horizontal" method="POST" action="save-codes">
					<input type="hidden" name="id" value="${code.id}"/>
					<div class="form-group">
						<label class="control-label col-md-3">Code</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="code" value="${code.code}"/>
						</div>
					</div>
					<div class="form-group">
						<label class="control-label col-md-3">Description</label>
						<div class="col-md-7">
							<input type="text" class="form-control" name="description" value="${code.description}"/>
						</div>
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-primary" value="Save/Update"/>
					</div>
				</form>
      </div>
      </c:when>

    <c:when test="${mode == 'MODE_SEARCH'}">
      <div class="container text-center">
        <h3>Search Codes</h3>
        <hr>
          <form class="form-horizontal" method="POST" action="code-search">
  					<input type="hidden" name="id" value="${code.id}"/>
  					<div class="form-group">
  						<label class="control-label col-md-3">Code</label>
  						<div class="col-md-7">
  							<input type="text" class="form-control" name="code" value="${code.code}"/>
  						</div>
  					</div>
  					<div class="form-group">
  						<label class="control-label col-md-3">Description</label>
  						<div class="col-md-7">
  							<input type="text" class="form-control" name="description" value="${code.description}"/>
  						</div>
  					</div>
  					<div class="form-group">
  						<input type="submit" class="btn btn-primary" value="Search"/>
  					</div>
  				</form>


      <c:choose>
        <c:when test="${result == 'MODE_RESULT'}">
          <hr>

            <div class="table-responsive">
              <table class="table table-striped table-bordered tex-left">
                <thead>
                  <th>Id</th>
                  <th>Code</th>
                  <th>Description</th>
                </thead>
                <tbody>
                  <c:forEach var="code" items="${codes}">
                    <tr>
                      <td>${code.id}</td>
                      <td>${code.code}</td>
                      <td>${code.description}</td>
                    </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>

        </c:when>

        <c:when test="${result == 'MODE_SEARCH_FAIL'}">
          <hr>
            <h4><font color="red">Please enter valid entries to Search!</font></h4>
        </c:when>
      </c:choose>

      </div>
    </c:when>

</c:choose>
</body>

<script src="static/js/jquery-1.11.1.min.js"></script>
  <script src="static/js/bootstrap.min.js"></script>
</body>
</html>
