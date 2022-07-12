<%-- 
    Document   : history
    Created on : 12-03-2021, 23:01:06
    Author     : dinhd
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Required meta tags-->
        <meta charset="UTF-8">

        <!-- Title Page-->
        <title>History Purchase</title>

        <!-- Fontfaces CSS-->
        <link href="css/font-face.css" rel="stylesheet" media="all">
        <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
        <link href="vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
        <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

        <!-- Bootstrap CSS-->
        <link href="vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

        <!-- Vendor CSS-->
        <link href="vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
        <link href="vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
        <link href="vendor/wow/animate.css" rel="stylesheet" media="all">
        <link href="vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
        <link href="vendor/slick/slick.css" rel="stylesheet" media="all">
        <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
        <link href="vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.2/css/all.css" integrity="sha384-vSIIfh2YWi9wW0r9iZe7RJPrKwp6bG+s9QZMoITbCckVJqGCCRhc+ccxNcdpHuYu" crossorigin="anonymous">
        <!-- Main CSS-->
        <link href="css/theme.css" rel="stylesheet" media="all">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
        <script src="js/homeJS.js" type="text/javascript"></script> 
    </head>

    <body class="animsition">
        <div class="page-wrapper">
            <!--nav-bar-->
            <jsp:include page="nav.jsp"/>
            <!--end-nav-->

            <section class="welcome p-t-30">
                <div class="section__content section__content--p30">
                    <div class="row">
                        <div class="col-md-12">
                            <h1 class="title-4">Welcome to Purchase Center
                                <span style="color: green;">${sessionScope.acc1.pName}!
                                    <c:if test="${sessionScope.acc.role == 'admin'}">
                                        <span style="color: #fada5e"> <i class="fas fa-crown"></i></span>
                                    </c:if>
                            </h1>
                            <hr class="line-seprate">
                        </div>
                    </div>
                </div>
            </section>

            <section class="statistic statistic2">
                <div class="section__content section__content--p30">
                    <div class="row">
                        <div class="col-md-6 col-lg-4">
                            <div class="statistic__item statistic__item--green">

                                <h2 class="number">${count}</h2>
                                <span class="desc">members</span>
                                <div class="icon">
                                    <i class="zmdi zmdi-account-o"></i>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-6 col-lg-4">
                            <div class="statistic__item statistic__item--orange">
                                <h2 class="number">${sold}</h2>
                                <span class="desc">Purchase</span>
                                <div class="icon">
                                    <i class="zmdi zmdi-shopping-cart"></i>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6 col-lg-4">
                            <div class="statistic__item statistic__item--red">
                                <h2 class="number">$${total}</h2>
                                <span class="desc">total earnings</span>
                                <div class="icon">
                                    <i class="zmdi zmdi-money"></i>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </section>

            <section class="main-content">
                <div class="section__content section__content--p30">
                    <h3 class="title-5 m-b-35">history purchase</h3>
                    <div class="table-data__tool">
                        <div class="table-data__tool-left">
                            <div class="rs-select2--light rs-select2--md">
                                <select id="list1" class="js-select2" name="property">
                                    <option value="none" selected="selected">None</option>
                                    <option value="asc">Ascending</option>
                                    <option value="desc">Decreasing</option>
                                </select>
                                <div class="dropDownSelect2"></div>
                            </div>
                            <div class="rs-select2--light rs-select2--sm">
                                <select id="list2" class="js-select2" name="time">
                                    <option value="today" >Today</option>
                                    <option value="newest" >Newest</option>
                                    <option  value="lastest">Lastest</option>
                                    <option  value="none" selected="selected">None</option>
                                </select>
                                <div class="dropDownSelect2"></div>
                            </div>
                            <button onclick="filter()" class="au-btn-filter">
                                <i class="zmdi zmdi-filter-list"></i>filters</button>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-8">

                            <div class="table-responsive table-responsive-data2">
                                <table class="table table-data2">
                                    <thead>
                                        <tr>

                                            <th>Account ID</th>
                                            <th>Username</th>
                                            <th>Product ID</th>
                                            <th>Amount</th>
                                            <th>Price</th>
                                            <th>Total</th>
                                            <th>Date</th>
                                            <th>More</th>
                                        </tr>
                                    </thead>
                                    <tbody  id="content3">
                                        <c:forEach items="${vectorH}" var="o">
                                            <tr  class="numberRow " class="tr-shadow">
                                                <td>${o.aID}</td>
                                                <td><span>${o.username}</span></td>
                                                <td class="">${o.pID}</td>
                                                <td>${o.amount}</td>
                                                <td>${o.price}</td>
                                                <td>${o.total}</td>
                                                <td>${o.date}</td>
                                                <td>
                                                    <button value="${o.username}"  onclick="showAllInfo(this)" class="item" data-toggle="tooltip" data-placement="top" title="More">
                                                        <i class="fas fa-eye"></i>
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>

                                </table>
                                <!--                                <div class="user-data__footer">
                                                                    <button  onclick="loadMore1()" class="au-btn au-btn-load">load more</button>
                                                                </div>-->
                                <button style="float: right; margin-top: 20px;" onclick="loadMore1()" type="button" class="btn btn-success">Load More</button>
                            </div>

                            <!-- <div class="user-data__footer">
                                <button class="au-btn au-btn-load">load more</button>
                            </div> -->

                        </div>
                        <div class="col-lg-4">
                            <div class="au-card au-card--bg-blue au-card-top-countries m-b-30">
                                <div class="au-card-inner">
                                    <div class="table-responsive">
                                        <table class="table table-top-countries">
                                            <tbody id="content4">
                                                <tr>
                                                    <td>Account ID</td>
                                                    <td class="text-right"></td>
                                                </tr>
                                                <tr>
                                                    <td>Name</td>
                                                    <td class="text-right"></td>
                                                </tr>
                                                <tr>
                                                    <td>Address</td>
                                                    <td class="text-right"></td>
                                                </tr>
                                                <tr>
                                                    <td>Phone number</td>
                                                    <td class="text-right"></td>
                                                </tr>
                                                <tr>
                                                    <td>Gender</td>
                                                    <td class="text-right"></td>
                                                </tr>
                                                <tr>
                                                    <td>Birth Year</td>
                                                    <td class="text-right"></td>
                                                </tr>

                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
            </section>
        </div>


        <!-- Jquery JS-->
        <script src="vendor/jquery-3.2.1.min.js"></script>
        <!-- Bootstrap JS-->
        <script src="vendor/bootstrap-4.1/popper.min.js"></script>
        <script src="vendor/bootstrap-4.1/bootstrap.min.js"></script>
        <!-- Vendor JS       -->
        <script src="vendor/slick/slick.min.js">
        </script>
        <script src="vendor/wow/wow.min.js"></script>
        <script src="vendor/animsition/animsition.min.js"></script>
        <script src="vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">
        </script>

        <script src="vendor/circle-progress/circle-progress.min.js"></script>
        <script src="vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
        <script src="vendor/chartjs/Chart.bundle.min.js"></script>
        <script src="vendor/select2/select2.min.js">
        </script>

        <!-- Main JS-->
        <script src="js/main.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

        <script>
                                    function loadMore1() {
                                        var num = document.getElementsByClassName("numberRow").length;
                                        var op1 = document.getElementById("list1").value;
                                        var op2 = document.getElementById("list2").value;
                                        $.ajax({
                                            url: "/MyProject/loadMoreHistory",
                                            type: "get", //send it through get method
                                            data: {
                                                numberR: num,
                                                filter2: op2,
                                                filter1: op1
                                            },
                                            success: function (data) {
                                                var row = document.getElementById("content3");
                                                row.innerHTML += data;
                                            },
                                            error: function (xhr) {
                                                //Do Something to handle error
                                            }
                                        });
                                    }
                                    function filter() {
                                        var op1 = document.getElementById("list1").value;
                                        var op2 = document.getElementById("list2").value;
//            var num = 0;
                                        $.ajax({
                                            url: "/MyProject/loadPurchaseByFilter",
                                            type: "get", //send it through get method
                                            data: {
//                    numberR: num,
                                                filter2: op2,
                                                filter1: op1
                                            },
                                            success: function (data) {
                                                var row = document.getElementById("content3");
                                                row.innerHTML = data;
                                            },
                                            error: function (xhr) {
                                                //Do Something to handle error
                                            }
                                        });
                                    }
                                    function  showAllInfo(uname) {
                                        var x = uname.value;
                                        $.ajax({
                                            url: "/MyProject/showInfo",
                                            type: "get", //send it through get method
                                            data: {
                                                userName: x
                                            },
                                            success: function (data) {
                                                var row = document.getElementById("content4");
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
