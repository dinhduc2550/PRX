<%@page import="entity.News"%>
<%@page import="DAO.DAONews"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vn">

    <jsp:include page="setUpPage.jsp"/>
    <body>
        <header>
            <style>
               
                .img-fluid {
                    max-width: 100%;
                    height: 200px !important;
                }
            </style>
            <jsp:include page="nav.jsp"/>
        </header>
        <jsp:include page="header.jsp"/>
        <section class="sectionFilter">
            <!--<form id="myForm" action="" method="get">-->
            <label class="filter">Filter:</label>
            <input onclick="clickRadioFilter()" class="inputF" type="radio" name="filter" id="news" value="NewsF">
            <label class="inputF1" for="news">News</label>
            <input onclick="clickRadioFilter()" class="inputF" type="radio" name="filter" id="product" value="ProductsF">
            <label class="inputF1" for="product">Product</label>
            <input onclick="clickRadioFilter()" class="inputF" type="radio" name="filter" id="show" value="showF" checked="">
            <label class="inputF1" for="show">Show All</label>
            <!--</form>-->
            <input id="txtS" oninput="autoSearch(this)" class="form-control me-2" name="txt" type="search" placeholder="Search" aria-label="Search">
        </section>

        <section class="containerLast">
            <div class="left-half">
                <!--<div class="div-left">-->
                <article>
                    <h1 class="most"><i class="fas fa-fire"></i>Most View<i class="fas fa-fire"></i></h1>
                </article>
                <div class="row rowleft row-cols-1">
                    <c:forEach var="o1" items="${news}">
                        <section class="container1 containerLast-Left">
                            <div class="left-half1 divLeft">
                                 <a  href="detailControl?newID=${o3.id}">
                                <img width="100%" height="100%" src="${o1.urlImage}" class="imgLeft" alt="">
                                 </a>
                            </div>
                            <div class="right-half1 divLeft">
                                <a class="text" href="detailControl?newID=${o1.id}">
                                    ${o1.title}
                                </a> 
                            </div>
                        </section>
                    </c:forEach>
                    <a href=""></a>
                </div>
                <!--</div>-->
            </div>
            <div class="right-half">
                <div id="content2"  class="row row-cols-1 row-cols-sm-2 row-cols-md-3">
                    <c:forEach items="${newsRight}" var="o3">
                        <div class="numNews text" class="col">
                            <a  href="detailControl?newID=${o3.id}">
                                 <img src="${o3.urlImage}" class="img-fluid newsImg" alt="...">
                            
                            </a>
                           <div class="text-text">
                                <a class=" text"
                                   href="detailControl?newID=${o3.id}">
                                    ${o3.title}
                                </a> 

                            </div>

                        </div>
                    </c:forEach>
                </div>

                <button style="float: right; margin-top: 20px;" onclick="loadMore()" type="button" class="btn btn-success">Load More</button>
            </div>

        </section>

        <footer>
            <div>
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
        <!--<script src="js/homeJS.js"></script>-->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>

                    function loadMore() {
                        var num = document.getElementsByClassName("numNews").length;
//                        var check = document.getElementsByClassName("pro").innerHTML;
                        var x = document.querySelector('input[name="filter"]:checked').value;
                        var txt = document.getElementById("txtS").value;
                        $.ajax({
                            url: "/MyProject/loadNews",
                            type: "get", //send it through get method
                            data: {
                                exists: num,
                                typeF1: x,
                                searchT: txt
                            },
                            success: function (data) {
                                var row = document.getElementById("content2");
                                row.innerHTML += data;
                            },
                            error: function (xhr) {
                                //Do Something to handle error
                            }
                        });
                    }
                    function clickRadioFilter() {
                        var x = document.querySelector('input[name="filter"]:checked').value;
                        $.ajax({
                            url: "/MyProject/loadByFilter",
                            type: "get", //send it through get method
                            data: {
                                filterType: x
                            },
                            success: function (data) {
                                var row = document.getElementById("content2");
                                row.innerHTML = data;
                               
                            },
                            error: function (xhr) {
                                //Do Something to handle error
                            }
                        });
                        document.getElementById("txtS").value = "";

                    }
                    function autoSearch(text) {
                        var txtSearch = text.value;
                        var x = document.querySelector('input[name="filter"]:checked').value;
                        $.ajax({
                            url: "/MyProject/search",
                            type: "get", //send it through get method
                            data: {
                                txt: txtSearch,
                                typeF1: x
                            },
                            success: function (data) {
                                var row = document.getElementById("content2");
                                row.innerHTML = data;
                                
                                
                            },
                            error: function (xhr) {
                                //Do Something to handle error
                            }
                        });
                    }
        </script>
    </body>

</html>
