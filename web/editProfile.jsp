<%@page import="entity.News"%>
<%@page import="DAO.DAONews"%>
<%@page import="java.util.Vector"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vn">
    <head>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"></script>
    </head>
    <%--<jsp:include page="setUpPage.jsp"/>--%>
    <body>
        <header>

            <jsp:include page="nav.jsp"/>
            <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
            <link rel="icon" type="png"
                  href="https://scontent.fhan5-1.fna.fbcdn.net/v/t1.0-9/91711721_843932326017872_4935418229175615488_n.jpg?_nc_cat=109&_nc_sid=09cbfe&_nc_ohc=IiXZdv-H1L8AX-20QC3&_nc_ht=scontent.fhan5-1.fna&oh=335c44bb50a3fba6760ce6c271541fa4&oe=5F00BA0E">
            <link href="css/editProfile.css" rel="stylesheet" type="text/css"/>
        </header>
        <div class="container rounded bg-white mt-5 mb-5">
            <div class="row">
                <div class="col-md-3 border-right">
                    <div class="d-flex flex-column align-items-center text-center p-3 py-5">
                        <img width="100%" class="rounded-circle mt-5" src="https://png.pngtree.com/png-vector/20190307/ourlarge/pngtree-vector-edit-profile-icon-png-image_760869.jpg">
                        <span class="font-weight-bold">${sessionScope.acc.userName}</span>
                        </span></div>
                </div>
                <div class="col-md-5 border-right">

                    <div class="p-3 py-5">

                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h4 class="text-right">Profile Settings</h4>
                        </div>
                        <form id="formAcc" method="post" action="saveProfile">
                            <div class="row mt-3">
                                <div class="row mt-2">
                                    <div class="col-md-12">
                                        ${success}
                                    </div>
                                </div>
                                <div class="col-md-12">
                                    <label class="labels">Full Name</label>
                                    <input name="fNameF1" type="text" class="form-control" placeholder="first name" value="${b.pName}">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-12">
                                    <label class="labels">PhoneNumber</label>
                                    <input name="phoneF1" type="text" class="form-control" placeholder="enter phone number" value="${b.phone}">
                                </div>
                                <div class="col-md-12">
                                    <label class="labels">Address</label>
                                    <input name="addressF1" type="text" class="form-control" placeholder="enter address" value="${b.address}">
                                </div>
                            </div>
                            <div class="row mt-3">
                                <div class="col-md-12">
                                    <label class="labels" style="margin-right: 10px" for="gender">Gender</label>
                                    <select id="gender" name="gender1">
                                        <option  <c:if test="${b.gender eq 'Male'}"> selected="selected"  </c:if>value="Male">
                                                Male
                                            </option>
                                            <option  <c:if test="${b.gender eq 'Female'}"> selected="selected" </c:if> value="Famale">
                                                Female
                                            </option>
                                        </select>
                                    </div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-md-12">
                                        <label class="labels" for="subject">Date of Birth</label>
                                        <br>
                                        <input value="${b.year}" onclose="check()" type="date" name="dob1" id="dob">
                                    <span id="span1"></span>
                                </div>
                            </div>
                            <div class="mt-3">
                                <input  class="btn btn-outline-info" id="btn" type="submit" value="Save" />
                            </div>
                        </form>
                    </div>

                </div>
                <div class="col-md-4">
                    <div class="p-3 py-5">
                        <form method="post" id="savePass" action="savePass?uid1=${b.id}">
                            <div class="d-flex justify-content-between align-items-center experience">
                                <span>Change Password</span>
                            </div>
                            <c:if test="${not empty mess1}">
                                 <div class="alert alert-danger" role="alert">
                                ${mess1}
                            </div>
                            </c:if>
                            <c:if test="${not empty mess2}">
                                 <div class="alert alert-success" role="alert">
                                ${mess2}
                            </div>
                            </c:if>
                            <br>
                            <div class="col-md-12">
                                <label class="labels">Current Password</label>
                                <input name="pass2-0" type="text" class="form-control" placeholder="" value="">
                            </div> <br>
                            <div class="col-md-12">
                                <label class="labels">New Password</label>
                                <input name="pass2" id="password" type="password" class="form-control">
                            </div> <br>
                            <div class="col-md-12">
                                <label class="labels">Confirm New Password</label>
                                <input name="pass2-1" id="confirm_password" type="password" class="form-control">
                                <!--<span id='message'></span>-->
                            </div>
                            <div style="margin-top: 20px" class="col-md-12"> <span id='message'></span></div>
                            <div style="margin-top: 20px;" class="col-md-12">

                                <input  class="btn btn-outline-info" id="btn1" type="submit" value="Save" />

                            </div>

                        </form>
                    </div>
                </div>
            </div>

            <footer>
                <div>
                    <script src="https://apps.elfsight.com/p/platform.js" defer></script>
                    <div class="elfsight-app-959efa1d-6a33-411f-8e32-959a0864ffa9"></div>
                    <div>
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
            <link href='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css'>
            <script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js'></script>
            <script src='https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js'></script>
            <!--<script src="js/script.js"></script>-->

            <script>
                dob.max = new Date().toISOString().split("T")[0];

                $('#password, #confirm_password').on('keyup', function () {
                    if ($('#password').val() == $('#confirm_password').val()) {
                        $('#message').html('Matching').css('color', 'green');
                    } else
                        $('#message').html('Not Matching').css('color', 'red');
                });
            </script>
    </body>

</html>
