<%-- 
    Document   : detailNews
    Created on : 14-03-2021, 16:53:05
    Author     : dinhd
--%>

<%@page import="DAO.DAONews"%>
<%@page import="java.io.FileReader"%>
<%@page import="java.io.BufferedReader"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vn">

    <head>
        <meta charset="UTF-8">
        <title>Tech Hot</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="icon" type="png"
              href="https://scontent.fhan5-1.fna.fbcdn.net/v/t1.0-9/91711721_843932326017872_4935418229175615488_n.jpg?_nc_cat=109&_nc_sid=09cbfe&_nc_ohc=IiXZdv-H1L8AX-20QC3&_nc_ht=scontent.fhan5-1.fna&oh=335c44bb50a3fba6760ce6c271541fa4&oe=5F00BA0E">
        <link rel="stylesheet" href="css/MyProject.css">
        <script src="https://kit.fontawesome.com/65c1b2b78c.js" crossorigin="anonymous"></script>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
        <script src="js/homeJS.js" type="text/javascript"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
    </head>
    <body>
        <jsp:include page="nav.jsp"/>
        <jsp:include page="header.jsp"/>
        <div class="section__content section__content--p30">
            <div class="container-fluid">

                <div class="row">
                    <div class="col-lg-12">
                        <c:if test="${type eq 1}">
                        <div class="card-body text-secondary"><p class="h1">${o1.title}</p></div>
                        </c:if>
                        <c:if test="${type eq 0}">
                        <div class="card-body text-secondary"><p class="h1">${o1.pName}</p></div>
                        </c:if>
                    </div>
                </div>
            </div>
            <div style="float: left;width: 40%">
                <div class="card-body text-secondary">
                    <c:if test="${type eq 1}">
                    <img width="100%" height="100%" src="${o1.urlImage}" class="imgLeft" alt="...">
                    </c:if>
                    <c:if test="${type eq 0}">
                         <img width="100%" height="100%" src="${o1.urlImage}" class="imgLeft" alt="...">
                        <!--<a onclick="add()"  class="btn btn-primary">Buy</a>-->
                        <button style="margin-bottom: 20px;margin-top: 20px;float: right"  class="btn btn-primary" onclick="add(${id})">Add to Cart</button>
                    </c:if>
                </div>
            </div>
            <div style="width: 50%; float: left">
                <p>${string}</p>
            </div>                 
        </div>
    </body>
    <footer>
        <script src="https://apps.elfsight.com/p/platform.js" defer></script>
        <div class="elfsight-app-959efa1d-6a33-411f-8e32-959a0864ffa9"></div>
        <div>
            <p>Contact Me</p>
            <a class="a1" href="https://www.facebook.com/" aria-label="facebook"><i
                    class="fab fa-facebook-square fa-5x"></i></a>
            <a class="a2" href="https://www.facebook.com/" aria-label="facebook"><i
                    class="fab fa-instagram-square fa-5x"></i></a>
            <a class="a3" href="https://www.facebook.com/" aria-label="facebook"><i
                    class="fab fa-youtube-square fa-5x"></i></a>
        </div>
    </footer>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>

                    
                    function add(x) {
//                        alert("clicked");
                            var id =x;
                        $.ajax({
                            url: "/MyProject/addToCart",
                            type: "get", //send it through get method
                            data: {
                                id1: id
                            },
                            success: function (data) {
//                                alert("Add to cart successfully!    ");
                              swal("Thanks so much!", "Add to cart successfully!", "success");
                            },
                            error: function (xhr) {
                                //Do Something to handle error
                            }
                        });
                    }
                    
        </script>
</html>
