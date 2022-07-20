<%-- 
    Document   : nav
    Created on : 12-03-2021, 23:03:22
    Author     : dinhd
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <header>
            <nav class="navbar navbar-expand-lg navbar navbar-dark bg-dark">
                <div class="container-fluid">
                    <img class="navbar-brand" src="img/logo4.png" alt="">
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="home">Home</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="showCart2">Cart</a>
                            </li>
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Manager Center
                                </a>
                                <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                    <c:if test="${sessionScope.acc.role == 'admin' or sessionScope.acc.role =='sale'}">
                                        <li><a class="dropdown-item" href="newsControl">News Management</a></li>
                                        </c:if>
                                        <c:if test="${sessionScope.acc.role == 'admin'}">
                                        <!--<li><a class="dropdown-item" href="newsControl">News Management</a></li>-->
                                        <li><a class="dropdown-item" href="accountControl">Account Management</a></li>
                                        <li><a class="dropdown-item" href="purchase">Purchase Management</a></li>
                                        </c:if>
                                        <c:if test="${sessionScope.acc.role == 'admin' or sessionScope.acc.role =='sale' or sessionScope.acc.role =='null'}">
                                        <li><hr class="dropdown-divider"></li>
                                        </c:if>
                                    <li><a class="dropdown-item" href="loadDataToProfile">Profile</a></li>
                                </ul>
                            </li>



                    </div>
                    </ul>
                    <div class="div-on-nav1">
                        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <c:if test="${sessionScope.acc==null}">
                                <li class="nav-item">
                                    <a class="nav-link" href="login" tabindex="-1" aria-disabled="false">Login</a>
                                </li>
                            </c:if>
                            <c:if test="${sessionScope.acc!=null}">
                                <li class="nav-item">
                                    <a class="nav-link" href="" tabindex="-1" aria-disabled="false">WELCOME BACK <span style="font-weight: bolder;color: green">${sessionScope.acc.userName}</span></a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="logout" tabindex="-1" aria-disabled="false">Logout</a>
                                </li>
                            </c:if>   
                        </ul>
                    </div>
                </div>
            </nav>

        </header>
