
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <%--<jsp:include page="setUpPage2.jsp"/>--%>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="au theme template">
        <meta name="keywords" content="au theme template">

        <title>Account Control Center</title>

        <link href="css/font-face.css" rel="stylesheet" media="all">
        <link href="vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
        <link href="vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
        <link href="vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

        <link href="vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

        <link href="vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
        <link href="vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
        <link href="vendor/wow/animate.css" rel="stylesheet" media="all">
        <link href="vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
        <link href="vendor/slick/slick.css" rel="stylesheet" media="all">
        <link href="vendor/select2/select2.min.css" rel="stylesheet" media="all">
        <link href="vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.15.2/css/all.css" integrity="sha384-vSIIfh2YWi9wW0r9iZe7RJPrKwp6bG+s9QZMoITbCckVJqGCCRhc+ccxNcdpHuYu" crossorigin="anonymous">
        <link href="css/theme.css" rel="stylesheet" media="all">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW" crossorigin="anonymous"></script>
        <script src="js/homeJS.js" type="text/javascript"></script>
        <style>
            body {font-family: Arial, Helvetica, sans-serif;}
            * {box-sizing: border-box;}

            input[type=text], select, textarea {
                width: 100%;
                padding: 12px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
                margin-top: 6px;
                margin-bottom: 16px;
                resize: vertical;
            }

            input[type=submit] {
                background-color: #4CAF50;
                color: white;
                padding: 12px 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
            }

            input[type=submit]:hover {
                background-color: #45a049;
            }

            .container {
                border-radius: 5px;
                background-color: #f2f2f2;
                padding: 20px;
            }
        </style>
    </head>
    <body>
        <header>
            <jsp:include page="nav.jsp"/>
        </header>
        <section>

            <div class="row">

                <div class="col-lg-8">

                    <div class="card wish-list mb-3">
                        <div class="card-body">

                            <h5 class="mb-4">Cart (<span>${list.size()}</span> items)</h5>
                            <c:forEach items="${list}" var="o">
                                <div class="row mb-4">
                                    <div class="col-md-5 col-lg-3 col-xl-3">
                                        <div class="view zoom overlay z-depth-1 rounded mb-3 mb-md-0">
                                            <img class="img-fluid w-100"
                                                 src="${o.urlImage}" alt="Sample">
                                        </div>
                                    </div>
                                    <div class="col-md-7 col-lg-9 col-xl-9">
                                        <div>
                                            <div class="d-flex justify-content-between">
                                                <div class="col-lg-8">
                                                    <h4  >${o.pName}</h4>
                                                    <p class="mb-3 text-muted text-uppercase small">Hot</p>
                                                    <p class="mb-2 text-muted text-uppercase small">Limmited Product</p>
                                                    <!--<p class="mb-3 text-muted text-uppercase small">Size: M</p>-->
                                                </div>
                                                <div>
                                                    <div class="def-number-input number-input safari_only mb-0 w-100">
                                                        <!--<button type="button" class="btn btn-outline-success">Add</button>-->
                                                        <strong style="margin-right: 10px">Amount:</strong>
                                                       
                                                        <div style="display: inline-block" >
                                                            <a  href="addToCart?id1=${o.pID}"> <button style="margin-top: -10px" type="button" class="btn btn-outline-primary">Increase</button></a> 
                                                        <!--<input style="margin-left: 10px;width: 30px" readonly="" class="quantity" min="0" name="quantity" value="${o.amount}" type="number">-->
                                                        <span style="font-size: 25px;"><strong>${o.amount}</strong></span>
                                                        <a href="subToCart?id=${o.pID}"><button style="margin-top: -10px" type="button" class="btn btn-outline-warning">Decrease</button></a>
                                                        </div>
                                                         <!--<button onclick="div(${o.pID})">Div</button>-->
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="d-flex justify-content-between align-items-center">
                                                <div>
                                                    <a href="#!" type="button" class="card-link-secondary small text-uppercase mr-3"><i
                                                            class="fas fa-trash-alt mr-1"></i> Remove item </a>
                                                </div>
                                                <p class="mb-0"><span><strong>Total: $${o.amount * o.price}</strong>
                                                    </span><br>
                                                    <span><strong>$${o.price}/ 1 Product</strong><span></p>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <hr class="mb-4">
                            </c:forEach>
                        </div>
                    </div>
                </div>

                <div class="col-lg-4">

                    <div class="card mb-3">
                        <div class="card-body">

                            <h5 class="mb-3">The total amount of</h5>

                            <ul class="list-group list-group-flush">
                                <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 pb-0">
                                    Temporary amount
                                    <span>total</span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between align-items-center px-0">
                                    Shipping
                                    <span>Free</span>
                                </li>
                                <li class="list-group-item d-flex justify-content-between align-items-center border-0 px-0 mb-3">
                                    <div>
                                        <strong>The total amount of</strong>
                                        <strong>
                                            <p class="mb-0">(including VAT)</p>
                                        </strong>
                                    </div>
                                    <span><strong>${totalVat}</strong></span>
                                    
                                </li>
                                <div class="container">
                                    <form method="post" id="formAcc" action="checkout">
                                        <label for="pname">Full Name</label>
                                        <input value="${infoCheckOut.pName}" type="text" id="lname" name="fNameF" placeholder="Your full name..">

                                        <label for="address">Address</label>
                                        <input value="${infoCheckOut.address}" type="text" id="lname" name="addressF" placeholder="Your address..">

                                        <label for="phone">Phone number</label>
                                        <input value="${infoCheckOut.phone}" type="text" id="lname" name="phoneF" placeholder="Your phone number name..">

                                        <input class="btn btn-primary btn-block waves-effect waves-light" type="submit" value="Payment orders">
                                    </form>
                                </div>
                                 <!--<a href="checkout"><button style="float: right" type="button" class="btn btn-primary btn-block waves-effect waves-light">go to checkout</button></a>-->
                            </ul>
                        </div>
                    </div>



                </div>

            </div>
        </section>

<!--    <script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="js/popper.min.js"></script>
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <script type="text/javascript" src="js/mdb-ecommerce.min.js"></script>
    --><script type="text/javascript"></script>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script>
       function add(x) {
//                        alert(x);
//                        prompt(id);
                            var id =x;
//                             alert(id);
                        $.ajax({
                            url: "/MyProject/addToCart",
                            type: "get", //send it through get method
                            data: {
                                id1: id
                            },
                            success: function (data) {
                                alert("Success");
                            },
                            error: function (xhr) {
                                //Do Something to handle error
                            }
                        });
                    }
                    
    </script>
</body>
</html>
