<%-- 
    Document   : newsManager
    Created on : 14-03-2021, 22:20:11
    Author     : dinhd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%--<%@ page contentType="text/html; charset=EUC-JP" %>--%>

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
                            <h1 class="title-4">Welcome to News Manager
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
                                <h3 id="titleTable" class="title-3 m-b-30">
                                    <i class="zmdi zmdi-account-calendar"></i>News Data</h3>
                                <div class="filters m-b-45">
                                    <p>Search By</p>
                                    <div class="rs-select2--dark rs-select2--md m-r-10 rs-select2--border">
                                        <select onchange="filterOption()" id="filterOption1" class="js-select2" name="property">
                                            <option selected="selected" value="News Data">News Data</option>
                                            <option value="Product Data">Product Data</option>
                                            <option value="NOP">News of Product Data</option>
                                        </select>
                                        <div class="dropDownSelect2"></div>
                                    </div>
                                    <div class="rs-select2--dark rs-select3--md m-r-10 rs-select2--border">
                                        <input id="txtS" class="form-control me-2" name="txt" type="search" placeholder="Search" aria-label="Search">
                                    </div>
                                    <div class="rs-select2--dark rs-select2--sm rs-select2--border">
                                        <button onclick="filterNews()" class="au-btn-filter">
                                            <i class="zmdi zmdi-filter-list"></i>filters</button>
                                    </div>
                                    <div id="creatPro" class="rs-select2--dark rs-select2--sm rs-select2--border">
                                        <button onclick="addNew()" type="button" class="btn btn-outline-primary">Add New</button>
                                    </div>
                                </div>
                                <div class="table-responsive table-data">
                                    <table class="table">
                                        <thead>
                                            <tr>
                                                <td>News ID</td>
                                                <td>Title</td>
                                                <td>Img</td>
                                                <td>View</td>
                                            </tr>
                                        </thead>
                                        <tbody id="ftable">
                                            <c:forEach items="${listNews}" var="a">
                                                <tr>
                                                    <td>
                                                        ${a.id}
                                                    </td>
                                                    <td>
                                                        <div class="table-data__info">
                                                            <p>${a.title}</p>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <img width="50%" src="${a.urlImage}">
                                                        <button value="${a.id}" type="button" onclick="editImg(this)" style="margin-left: 20px"><i class="far fa-edit"></i></button>
                                                    </td>
                                                    <td>
                                                        ${a.view}
                                                    </td>
                                                    <td>
                                                        <button  type="button" value="${a.id}" onclick="showMore4(this)">
                                                            <i class="fas fa-eye"></i>
                                                        </button>
                                                       <button id="b-c-1"  type="button" value="${a.id}" onclick="makeProduct(this)">
                                                             <i class="fab fa-product-hunt"></i>
                                                        </button>
                                                            

                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>

                                </div>
                            </div>
                            <!--<h3 class="title-3 m-b-30">Upload Image</h3>-->
                            <!--                            <form id="uploadForm"  method="post" action="uploadImg" enctype="multipart/form-data">
                                                            <label for="sID">News ID</label>
                                                            <input value="" type="text" id="sID" name="sID" placeholder="New id ..">
                                                            <label for="url">Url</label>
                                                            <input value="" type="text" id="urlA" name="urlA" placeholder="New id ..">
                                                            <br>
                                                            <input name="file_upload" type="file" id="file_upload" onchange="previewImg(event)">
                                                            <br>
                                                            <image style="width: 100%" name="imgF" src="img\photo-icon-1.jpg" width="200" height="250" id="image-field3">
                                                            <br>
                                                            <input  type="submit" value="Submit">
                                                        </form>-->
                        </div>
                        <div class="col-lg-6">
                            <!-- TOP CAMPAIGN-->
                            <div class="top-campaign">
                                <h3  class="title-3 m-b-30">Edit </h3>
                                <h6>${success}</h6>
                                <div id="f-2-3" class="container">
                                    <form enctype="multipart/form-data"  name="formNews" method="post" id="formNews" action="uploadImg">
                                        <label for="newID">News ID</label>
                                        <input value="" readonly type="text" id="newID" name="newID" placeholder="New id ..">
                                        <br>
                                        <label for="pName">Product Name</label>
                                        <input value="" type="text" id="pName" name="pName" placeholder="Product Name ..">
                                        <br>
                                        <label for="amount">Amount</label>  <br>
                                        <input type="number" id="amount" name="amount" min="0">
                                        <br>
                                        <label for="price">Price</label>
                                        <input onchange="returns checkInp()" type="text" id="price" name="price" >

                                        <label for="title">Title News</label>
                                        <input value="" type="text" id="title" name="title" placeholder="Title..">
                                        <!--<textarea name="titleA" id="titleA"></textarea>-->
                                        <label for="img">Image</label>

                                        <input name="file_upload" type="file" id="file_upload" onchange="previewImg(event)">
                                        <br>
                                        <image name="imgF" src="img\photo-icon-1.jpg" width="200" height="250" id="image-field3">
                                        <br>
                                        <label for="txtA">Content</label>
                                        <textarea style="height: 300px;" name="txtA" id="txtA"></textarea>
                                        <input type="submit" value="Save">
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>  
        <script type="text/javascript">
            function previewImg(event) {
                var reader = new FileReader();
                var imageField = document.getElementById("image-field");
                reader.onload = function () {
                    if (reader.readyState == 2) {
                        imageField.src = reader.result;
                    }
                }
                reader.readAsDataURL(event.target.files[0]);
            }

//            function previewImg1(event) {
//                var reader = new FileReader();
//                var imageField = document.getElementById("image-field1");
//                reader.onload = function () {
//                    if (reader.readyState == 2) {
//                        imageField.src = reader.result;
//                    }
//                }
//                reader.readAsDataURL(event.target.files[0]);
//            }
        </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script>
            function checkInp()
            {
                var x = document.forms["formNews"]["price"].value;
                if (isNaN(x))
                {
                    alert("Price must be numbers");
                    return false;
                }
            }
            var textarea = document.querySelector('textarea');
            textarea.addEventListener('keydown', autosize);
            function filterOption() {
                var op = document.getElementById("filterOption1").value;
                document.getElementById("titleTable").innerHTML = ' <i class="zmdi zmdi-account-calendar"></i>' + op;
            }
            function makeNews(x){
                var pID1 = x.value;
                $.ajax({
                    url: "/MyProject/showFormAddNewsByProduct",
                    type: "get", //send it through get method
                    data: {
                        productID: pID1
                    },
                    success: function (data) {
                        var row = document.getElementById("f-2-3");
                        row.innerHTML = data;
                    },
                    error: function (xhr) {
                    }
                });
            }
            function makeProduct(x) {
                var newID1 = x.value;
//                document.getElementById("m-b-30").textContent ="Make to Product";
                $.ajax({
                    url: "/MyProject/showFormNewsToProduct",
                    type: "get", //send it through get method
                    data: {
                        nID: newID1
                    },
                    success: function (data) {
                        var row = document.getElementById("f-2-3");
                        row.innerHTML = data;
                    },
                    error: function (xhr) {
                    }
                });
            }
            function addNew() {
                var op1 = document.getElementById("filterOption1").value;
                $.ajax({
                    url: "/MyProject/addNew",
                    type: "get", //send it through get method
                    data: {
                        optionB: op1
//                        txtA: txt
                    },
                    success: function (data) {
                        var row = document.getElementById("f-2-3");
                        row.innerHTML = data;
                    },
                    error: function (xhr) {
                        //Do Something to handle error
                    }
                });
            }
            function filterNews() {
                var op = document.getElementById("filterOption1").value;
                var txt = document.getElementById('txtS').value;
                $.ajax({
                    url: "/MyProject/filterNews",
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
            function editImg(x) {
                var id = x.value;
                $.ajax({
                    url: "/MyProject/formUploadImage",
                    type: "get", //send it through get method
                    data: {
                        nID1_1: id
                    },
                    success: function (data) {
                        var row = document.getElementById("f-2-3");
                        row.innerHTML = data;
                    },
                    error: function (xhr) {
                        //Do Something to handle error
                    }
                });
            }
            function showMore4(x) {
                var newID1 = x.value;
                $.ajax({
                    url: "/MyProject/showInfoNews",
                    type: "get", //send it through get method
                    data: {
                        nID: newID1
                    },
                    success: function (data) {
                        var row = document.getElementById("f-2-3");
                        row.innerHTML = data;
                    },
                    error: function (xhr) {
                    }
                });
            }
             function showMoreInfoProduct(x) {
                var newID1 = x.value;
                $.ajax({
                    url: "/MyProject/showInfoProduct",
                    type: "get", //send it through get method
                    data: {
                        nID2: newID1
                    },
                    success: function (data) {
                        var row = document.getElementById("f-2-3");
                        row.innerHTML = data;
                    },
                    error: function (xhr) {
                    }
                });
            }
        </script>
    </body>
</html>
