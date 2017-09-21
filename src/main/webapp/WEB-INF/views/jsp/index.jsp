<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
    <head>
        <title>DemoApp</title>
     </head>
        <link rel="stylesheet" type="text/css" href="/resources/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="/resources/bootstrap/css/bootstrap-theme.min.css">
        <script src="/resources/bootstrap/js/bootstrap.min.js"></script>
    <body>
   <div class="container">
        <div class="row">
            <div class="col-lg-3"></div>
            <div class="col-lg-6">
                Logowanie:
                <form class="form-inline" role="form">
                  <div class="form-group">
                    <label class="sr-only" for="exampleInputEmail2">Email</label>
                    <input type="email" class="form-control" id="exampleInputEmail2" placeholder="Podaj email">
                  </div>
                  <div class="form-group">
                    <label class="sr-only" for="exampleInputPassword2">Hasło</label>
                    <input type="password" class="form-control" id="exampleInputPassword2" placeholder=<spring:message code='password'/>>
                  </div>
                  <div class="checkbox">
                    <label>
                      <input type="checkbox"> Zaznacz mnie
                    </label>
                  </div>
                  <button type="submit" class="btn btn-default"><spring:message code='send'/></button>
                </form>
            </div>
            <div class="col-lg-3"></div>
        </div>
   </div>
<%@include file="includes/bottom.jsp" %>