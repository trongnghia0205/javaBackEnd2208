<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>




<style>
    .error {
        color: red;
    }
    input:focus,textarea:focus{
        border-color: initial;
    }

    input.error, textarea.error, .has-error{
        border:1px solid red;
    }
</style>
<jsp:include page="/jsp/frontend/head.jsp"></jsp:include>
<div class="container">
    <br>
    <span style="color: red">${message}</span>
    <h1>Tạo mới ${title}</h1>
    <br>
    <form  id="brandForm" action="/backend/${path}/save" method="post"enctype="multipart/form-data" >
        <div class="row">
            <div class="col-6">
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Tên ${title}</label>
                    <input type="text" name="name" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Mô tả</label>
                    <input type="text" name="description" class="form-control" id="exampleInputPassword1">
                </div>
            </div>
            <div class="row col-12" style="padding-bottom: 20px">
                <div class="col-4">
                    <label for="choose-file" class="form-label">Ảnh</label>
                    <input type="file" class="form-control" accept="image/*" name="fileImage" id="choose-file" style = "width: 100%"/>
                </div>
                <div class="col-6">
                    <div id="img-preview"></div>
                </div>
                <script>
                    const chooseFile = document.getElementById("choose-file");
                    const imgPreview = document.getElementById("img-preview");
                    chooseFile.addEventListener("change", function () {
                        getImgData();
                    });
                    function getImgData() {
                        const files = chooseFile.files[0];
                        if (files) {
                            const fileReader = new FileReader();
                            fileReader.readAsDataURL(files);
                            fileReader.addEventListener("load", function () {
                                imgPreview.style.display = "block";
                                imgPreview.innerHTML = '<img width="150px" src="' + this.result + '" />';
                            });
                        }
                    }
                </script>
            </div>
        </div>
        <!-- Submit button -->
        <button style="width: 10%" type="submit" class="btn btn-primary btn-block mb-4">Tạo mới</input>

    </form>
</div>
<jsp:include page="/jsp/frontend/footer.jsp"></jsp:include>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.min.js"></script>

<script>
    $( document ).ready(function() {
        $("#brandForm").validate({
            rules: {
                name: {
                    required: true
                },
                description : {
                    required : true
                },
                fileImage : {
                   required: true
                }
            },
            highlight: function(element) {
                $(element).parent().addClass('has-error');
            },
            unhighlight: function(element) {
                $(element).parent().removeClass('has-error');
            },
            errorElement: 'span',
            errorClass: 'validation-error-message help-block form-helper bold',
            errorPlacement: function(error, element) {
                if(element.parent('.input-group').length) {
                    error.insertAfter(element.parent());
                } else {
                    error.insertAfter(element);
                }
            },
            messages: {
                name: {required: "Vui lòng nhập tên thương hiệu"},
                description: {required: "Vui lòng nhập"},
                fileImage: {required: "Phải có ảnh"},

            }
        });
    });

</script>