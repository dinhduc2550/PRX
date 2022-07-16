<%-- 
    Document   : header
    Created on : 14-03-2021, 16:55:38
    Author     : dinhd
--%>
<%@page import="entity.News"%>
<%@page import="DAO.DAONews"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <div class="section__content section__content--p30">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-3">
                        <div class="div1">
                            <p class="intro">Today Weather</p>
                            <div>
                                <script src="https://apps.elfsight.com/p/platform.js" defer></script>
                                <div class="elfsight-app-44fa9f8d-4d84-4070-b594-795165a20866"></div>
                            </div>
                        </div>
                    </div>
                    <div style="margin: 9px 100px;max-width: 58vw;" class="col-lg-9">
                        <div style="padding-left: 19vh;" class="div1_1">
                            <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
                                <ol class="carousel-indicators">
                                    <c:forEach items="${productss}" var="pro" varStatus="theCount">
                                        <li data-bs-target="#carouselExampleCaptions" data-bs-slide-to="${theCount.index}" class="active"></li>
                                    </c:forEach>
                                </ol>
                                <div class="carousel-inner">
                                    <c:forEach items="${productss}" var="pro1">
                                        <div class="carousel-item ${pro1.pID==lastID?"active":""}">
                                            <a  href="detailControlForProduct?pID=${pro1.pID}">
                                                <img  style="border-radius: 10px" src="${pro1.urlImage}" class="d-block w-100" alt="...">
                                            </a>
                                            <div class="carousel-caption d-none d-md-block">
                                                <h5>${pro1.pName}</h5>
                                                <p></p>
                                            </div>
                                        </div>
                                    </c:forEach>
                                </div>
                                <a class="carousel-control-prev" href="#carouselExampleCaptions" role="button" data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Previous</span>
                                </a>
                                <a class="carousel-control-next" href="#carouselExampleCaptions" role="button" data-bs-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Next</span>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
