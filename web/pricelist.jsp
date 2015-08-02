<%@ page import="models.Product" %>
<%--
  Created by IntelliJ IDEA.
  User: voronov
  Date: 27.07.2015
  Time: 14:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>PriceList1.0</title>

  <!-- Bootstrap core CSS -->
  <link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom styles for this template -->
  <style>
    body {
      padding-top: 50px;
      padding-bottom: 50px;
    }

    .starter-template {
      padding: 40px 15px;
      text-align: center;
    }

    .myfooter {
      background: lightgrey;
      padding: 12px 12px;
      text-align: center;
    }
  </style>

  <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
  <!--[if lt IE 9]>
  <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>

<body>
<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
  <div class="container">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target=".navbar-collapse">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Прайс-лист 1.0</a>
    </div>
    <div class="collapse navbar-collapse">
      <ul class="nav navbar-nav">
        <%--<li class="active"><a href="#">Home</a></li>--%>
      </ul>
    </div>
    <!--/.nav-collapse -->
  </div>
</div>

<div class="container">
  <div class="starter-template">
    <form action="" method="post" class="form-inline" role="form" id="product_search_form">

      <div class="form-group">
        <label class="sr-only" for="category">Категория</label>
        <input type="text" class="form-control" name="category" id="category" placeholder="Категория"
               value="${empty old_category_name?'':old_category_name}">

      </div>
      <div class="form-group">
        <label class="sr-only" for="product_name">Наименование</label>
        <input type="text" class="form-control" name="product_name" id="product_name" placeholder="Наименование"
               value="${empty old_product_name?'':old_product_name}">
      </div>
      <div class="form-group">
        <label class="sr-only" for="price_min">Цена от</label>
        <input type="number" class="form-control" name="price_min" id="price_min" placeholder="Цена от"
               value="${empty old_price_min?'':old_price_min}" step="0.01">
      </div>
      <div class="form-group">
        <label class="sr-only" for="price_max">Цена до</label>
        <input type="number" class="form-control" name="price_max" id="price_max" placeholder="Цена до"
               value="${empty old_price_max?'':old_price_max}" step="0.01">
      </div>

      <button type="submit" class="btn btn-primary">
        <i class="icon-search icon-white"></i>
        Найти
      </button>
    </form>
  </div>

  <div class="container">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <table class="table table-hover table-bordered">
      <thead>
      <tr>
        <th>Категория</th>
        <th>Наименование</th>
        <th>Цена</th>
      </tr>
      </thead>
      <tbody>
      <c:forEach var="prod" items="${prods}">
        <tr>
          <td>${prod.getCategoryByCategoryIdCategory().getName()}</td>
          <td>${prod.getName()}</td>
          <td>${prod.getPrice()}</td>
        </tr>
      </c:forEach>
      </tbody>
    </table>
  </div>
</div>
<!-- /.container -->

<nav class="navbar navbar-default navbar-fixed-bottom myfooter">
  <div class="container">
    &copy; Артём Воронов 2015<br>
  </div>
</nav>
<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</body>
</html>