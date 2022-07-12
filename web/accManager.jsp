<%-- 
    Document   : accManager
    Created on : 13-03-2021, 15:24:58
    Author     : dinhd
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

   <jsp:include page="setUpPage2.jsp"/>

    <body>

        <jsp:include page="nav.jsp"/>

        <div class="main-content">
            <section class="welcome p-t-10">
                <div class="section__content section__content--p30">
                    <div class="row">
                        <div class="col-md-12">
                            <h1 class="title-4">Welcome to Account Center
                                <span style="color: green;">${sessionScope.acc1.pName}!</span>
                                    <c:if test="${sessionScope.acc.role == 'admin'}">
                                        <span style="color: #fada5e"> <i class="fas fa-crown"></i></span>
                                        </c:if>
                            </h1>
                            <hr class="line-seprate">
                        </div>
                    </div>
                </div>
            </section>
            <div class="section__content section__content--p30">
                <div class="container-fluid">

                    <div class="row">
                        <div class="col-lg-6">
                            <!-- USER DATA-->
                            <div class="user-data m-b-30">
                                <h3 class="title-3 m-b-30">
                                    <i class="zmdi zmdi-account-calendar"></i>user data</h3>
                                <div class="filters m-b-45">
                                    <p>Search By</p>
                                    <div class="rs-select2--dark rs-select2--md m-r-10 rs-select2--border">
                                        <select id="filterOption1" class="js-select2" name="property">
                                            <option selected="selected" value="none">None</option>
                                            <option  value="id">ID</option>
                                            <option value="uname">Username</option>
                                            <option value="fname">Full Name</option>
                                            <option value="role">Role</option>
                                            <option value="status">Status</option>
                                        </select>
                                        <div class="dropDownSelect2"></div>
                                    </div>
                                    <div class="rs-select2--dark rs-select3--md m-r-10 rs-select2--border">
                                        <input id="txtS" class="form-control me-2" name="txt" type="search" placeholder="Search" aria-label="Search">
                                    </div>
                                    <div class="rs-select2--dark rs-select2--sm rs-select2--border">
                                        <button onclick="filterAcc()" class="au-btn-filter">
                                            <i class="zmdi zmdi-filter-list"></i>filters</button>
                                    </div>
                                </div>
                                <div class="table-responsive table-data">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <td>account id</td>
                                                <td>User name</td>
                                                <td>Role</td>
                                                <td>status</td>
                                            </tr>
                                        </thead>
                                        <tbody id="ftable">
                                            <c:forEach items="${listAcc}" var="a">
                                                <tr>
                                                    <td>
                                                        ${a.id}
                                                    </td>
                                                    <td>
                                                        <div class="table-data__info">
                                                            <h6>${a.pName}</h6>
                                                            <span>
                                                                <a href="#">${a.name}</a>
                                                            </span>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <c:if test="${a.role == 'admin'}">
                                                            <span class="role admin">Admin</span>
                                                        </c:if>
                                                        <c:if test="${a.role == 'sale'}">
                                                            <span class="role member">Saler</span>
                                                        </c:if>
                                                        <c:if test="${a.role == 'cus'}">
                                                            <span class="role user">User</span>
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <c:if test="${a.active == '1'}">
                                                            <p class="text-success">Activated</p>
                                                        </c:if>
                                                        <c:if test="${a.active == '0'}">
                                                            <p class="text-danger">Banned</p>
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <button  type="button" value="${a.name}" onclick="showMore2(this)">
                                                            <i class="fas fa-eye"></i>
                                                        </button>
                                                        </span>
                                                    </td>
                                                </tr>
                                            </c:forEach>


                                        </tbody>
                                    </table>

                                </div>
                            </div>
                            <h3 class="title-3 m-b-30">History Purchase</h3>
                            <div class="table-responsive table-responsive-data2">
                                <table class="table table-data2">
                                    <thead>
                                        <tr>

<!--                                            <th>Account ID</th>
                                            <th>Username</th>-->
                                            <th>Product ID</th>
                                            <th>Amount</th>
                                            <th>Price</th>
                                            <th>Total</th>
                                            <th>Date</th>
                                            <!--<th>More</th>-->
                                        </tr>
                                    </thead>
                                    <tbody  id="content3-1">
                                            <tr  class="numberRow " class="tr-shadow">
                                                <td class=""></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                                <td></td>
                                            </tr>
                                    </tbody>

                                </table>
                                <button style="float: right; margin-top: 20px;" onclick="loadMore1()" type="button" class="btn btn-success">Load More</button>
                            </div>
                            <!-- END USER DATA-->
                        </div>
                        <div class="col-lg-6">
                            <!-- TOP CAMPAIGN-->
                            <div class="top-campaign">
                                <h3 class="title-3 m-b-30">Edit Account </h3>
                                <h6>${success}</h6>
                                <div class="container">
                                    <form method="post" id="formAcc" action="saveInfo1">
                                        <label for="fname">Account ID</label>
                                        <input value="" readonly type="text" id="aid" name="aIdF" placeholder="">

                                        <label for="uname">Username</label>
                                        <input readonly value="" type="text" id="unameF" name="unameF" placeholder="Your username..">

                                        <label for="pname">Full Name</label>
                                        <input value="" type="text" id="lname" name="fNameF" placeholder="Your full name..">

                                        <label for="address">Address</label>
                                        <input value="" type="text" id="lname" name="addressF" placeholder="Your address..">

                                        <label for="phone">Phone number</label>
                                        <input type="text" id="lname" name="phoneF" placeholder="Your phone number name..">

                                        <label for="gender">Gender</label>
                                        <select id="gender" name="gender">
                                            <option value="Male">Male</option>
                                            <option value="Famale">Female</option>
                                            <option selected value="None">None</option>
                                        </select>
                                        <label for="status">Status</label>
                                        <select id="status" name="status">
                                            <option class="text-success" value="Active">Active</option>
                                            <option class="text-danger" value="Ban">Ban</option>
                                            <option selected="" class="text-danger" value="none">None</option>
                                        </select>
                                        <label for="subject">Date of Birth</label>
                                        <br>
                                        <input type="date" name="dob" id="">
                                        <input disabled="disabled" type="submit" value="Save">
                                    </form>
                                </div>
                            </div>
                            <!--  END TOP CAMPAIGN-->
                        </div>
                    </div>


                    
                </div>
            </div>
        </div>
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
        <script src="vendor/counter-up/jquery.waypoints.min.js"></script>
        <script src="vendor/counter-up/jquery.counterup.min.js">
        </script>
        <script src="vendor/circle-progress/circle-progress.min.js"></script>
        <script src="vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
        <script src="vendor/chartjs/Chart.bundle.min.js"></script>
        <script src="vendor/select2/select2.min.js">
        </script>

        <!-- Main JS-->
        <script src="js/main.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>            var uname1;
                                    function showMore2(x) {
                                         uname1 = x.value;
                                        $.ajax({
                                            url: "/MyProject/showInfo2",
                                            type: "get", //send it through get method
                                            data: {
                                                userName: uname1
                                            },
                                            success: function (data) {
                                                var row = document.getElementById("formAcc");
                                                row.innerHTML = data;
                                            },
                                            error: function (xhr) {
                                                //Do Something to handle error
                                            }
                                        });
                                         $.ajax({
                                            url: "/MyProject/loadPurchaseByUserName1",
                                            type: "get", //send it through get method
                                            data: {
                                               userName: uname1
                                            },
                                            success: function (data) {
                                                var row = document.getElementById("content3-1");
                                                row.innerHTML = data;
                                            },
                                            error: function (xhr) {
                                                //Do Something to handle error
                                            }
                                        });
                                    }
                                    function filterAcc() {
                                        var op = document.getElementById("filterOption1").value;
                                        var txt = document.getElementById('txtS').value;
//                                        var uname12 = x.value;
                                        $.ajax({
                                            url: "/MyProject/filterAccount",
                                            type: "get", //send it through get method
                                            data: {
                                                optionA: op,
                                                txtA: txt
                                            },
                                            success: function (data) {
                                                var row = document.getElementById("ftable");
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
