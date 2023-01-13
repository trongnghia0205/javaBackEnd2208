<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!-- Font Awesome -->
<link
        href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css"
        rel="stylesheet"
/>
<!-- Google Fonts -->
<link
        href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap"
        rel="stylesheet"
/>
<!-- MDB -->
<link
        href="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.1/mdb.min.css"
        rel="stylesheet"
/>
<!-- MDB -->
<script
        type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/mdb-ui-kit/6.0.1/mdb.min.js"
></script>
<style>
    .divider:after,
    .divider:before {
        content: "";
        flex: 1;
        height: 1px;
        background: #eee;
    }
    .h-custom {
        height: calc(100% - 73px);
    }
    @media (max-width: 450px) {
        .h-custom {
            height: 100%;
        }
    }
</style>

<section class="vh-100">
    <div class="container-fluid h-custom">
        <div class="row d-flex justify-content-center align-items-center h-100">
            <div class="col-md-9 col-lg-6 col-xl-5">
                <img src="/frontend/img/carousel-1.jpg" style="width: 100%; "
                     class="img-fluid" alt="Sample image">
            </div>
            <div class="col-md-8 col-lg-6 col-xl-4 offset-xl-1">
                <form action="/doLogin" method="post">
                    <div class="d-flex flex-row align-items-center justify-content-center justify-content-lg-start">
                        <p class="lead fw-normal mb-0 me-3" style="color: red">${message}</p>
                        <p class="lead fw-normal mb-0 me-3"><spring:message code="login.title.head"></spring:message></p>
                        <button type="button" class="btn btn-primary btn-floating mx-1" style="background-color: black!important;">
                            <i class="fab fa-facebook-f" style="color: #FFD333"></i>
                        </button>

                        <button type="button" class="btn btn-primary btn-floating mx-1" style="background-color: black!important;">
                            <i class="fab fa-twitter" style="color: #FFD333"></i>
                        </button>

                        <button type="button" class="btn btn-primary btn-floating mx-1" style="background-color: black!important;">
                            <i class="fab fa-linkedin-in" style="color: #FFD333"></i>
                        </button>
                    </div>

                    <div class="divider d-flex align-items-center my-4">
                        <p class="text-center fw-bold mx-3 mb-0"><spring:message code="login.title.or"></spring:message></p>
                    </div>

                    <!-- Email input -->
                    <div class="form-outline mb-4">
                        <input type="email" id="form3Example3" name="email" class="form-control form-control-lg"
                               placeholder="Enter a valid email address" />
                        <label class="form-label" for="form3Example3">Email </label>
                    </div>

                    <!-- Password input -->
                    <div class="form-outline mb-3">
                        <input type="password" id="form3Example4" name="password" class="form-control form-control-lg"
                               placeholder="Enter password" />
                        <label class="form-label" for="form3Example4"><spring:message code="login.title.password"></spring:message></label>
                    </div>

                    <div class="d-flex justify-content-between align-items-center">
                        <!-- Checkbox -->
                        <div class="form-check mb-0">
                            <input class="form-check-input me-2" type="checkbox" value="" id="form2Example3" />
                            <label class="form-check-label" for="form2Example3">
                                Remember me
                            </label>
                        </div>
                        <a href="#!" class="text-body"><spring:message code="login.title.forgot.password"></spring:message>?</a>
                    </div>

                    <div class="text-center text-lg-start mt-4 pt-2">
                        <button type="submit" class="btn btn-primary btn-lg"
                                style="padding-left: 2.5rem; padding-right: 2.5rem; color:#FFD333!important; background-color: black!important;" ><spring:message code="login.title.login"></spring:message></button>
                        <p class="small fw-bold mt-2 pt-1 mb-0">bạn chưa có tài khoản? <a href="/register"
                                                                                          class="link-danger"><spring:message code="login.title.register"></spring:message></a></p>
                    </div>

                </form>
            </div>
        </div>
    </div>
    <div class="d-flex flex-column flex-md-row text-center text-md-start justify-content-between py-4 px-4 px-xl-5 bg-primary" style ="background-color: black!important;">
        <!-- Copyright -->
        <div class="text-white mb-3 mb-md-0" style="color:#FFD333!important;">
            Copyright © 2022. All rights reserved.
        </div>
        <!-- Copyright -->

        <!-- Right -->
        <div>
            <a href="#!" class="text-white me-4" >
                <i class="fab fa-facebook-f" style="color:#FFD333!important;"></i>
            </a>
            <a href="#!" class="text-white me-4">
                <i class="fab fa-twitter" style="color:#FFD333!important;"></i>
            </a>
            <a href="#!" class="text-white me-4">
                <i class="fab fa-google" style="color:#FFD333!important;"></i>
            </a>
            <a href="#!" class="text-white">
                <i class="fab fa-linkedin-in" style="color:#FFD333!important;"></i>
            </a>
        </div>
        <!-- Right -->
    </div>
</section>