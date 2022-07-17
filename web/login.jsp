<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
    <jsp:include page="setUpPage.jsp"/>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <link rel="stylesheet"  href="${pageContext.request.contextPath}/css/style.css" >
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.14.0/css/all.min.css">
    </head>
    <body>
       
        <h2 style="color: red">${mess2}</h2>
        <div class="container" id="container">
            <div class="form-container sign-up-container">
                <form action="signup" method="get">
                    <h1>Create Account</h1>
                    <p class="text-danger">${mess2}</p>
                    <input name="rUser" type="text" placeholder="Username" />
                    <input name="rPass" id="password" type="password" onkeyup='check();' placeholder="Password" />
                    <input name="rRePass" id="confirm_password"  onkeyup='check();' type="password" placeholder="Confirm Password" />

                    <span id='message'></span>
                    <button>Sign Up</button>
                </form>
            </div>
            <div class="form-container sign-in-container">
                <form action="login" method="post">
                    <h1>Sign in</h1>
                    <p class="text-danger">${mess}</p>
                    <input value="${u}" type="text" name="uname" placeholder="Username" />
                    <input value="${p}" type="password" name="psw" placeholder="Password" />
                    <div class="form-check">
                        <input name="remember" type="checkbox" class="form-check-input" id="exampleCheck1">
                        <label class="form-check-label" for="exampleCheck1">Remember Me</label>
                    </div>
                    <a href="#"></a>
                    <button>Sign In</button>
                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1>Welcome Back!</h1>
                        <p>To keep connected with us please login with your personal info</p>
                        <button class="ghost" id="signIn">Sign In</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1>Hello, Friend!</h1>
                        <p>Enter your personal details and start journey with us</p>
                        <button class="ghost" id="signUp">Sign Up</button>
                    </div>
                </div>
            </div>
        </div>
        <!--        <footer>
                    <p>
                        Created with <i class="fa fa-heart"></i> by
                        <a target="_blank" href="https://florin-pop.com">Florin Pop</a>
                        - Read how I created this and how you can join the challenge
                        <a target="_blank" href="https://www.florin-pop.com/blog/2019/03/double-slider-sign-in-up-form/">here</a>.
                    </p>
                </footer>-->
        <script src="js/script.js"></script>
    </body>


</html>
