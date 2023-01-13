<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ page pageEncoding="UTF-8" %>
<jsp:include page="/jsp/frontend/head.jsp"></jsp:include>
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
<div class="container">
    <br>
    <span style="color: red">${message}</span>
    <h1>Cập nhật ${title}</h1>
    <br>
    <form id="brandForm" action="/backend/${path}/save" method="post" enctype="multipart/form-data" modelAttribute="model">
        <input type="text" hidden value="${model.id}" name="id" class="form-control" id="exampleInputPassword1">
        <div class="row">
            <div class="col-6">
                <div class="mb-3">
                    <label for="exampleInputEmail1" class="form-label">Tên ${title}</label>
                    <input value="${model.name}" type="text" name="name" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" required>
                </div>
            </div>
            <div class="col-6">
                <div class="mb-3">
                    <label for="exampleInputPassword1" class="form-label">Mô tả</label>
                    <input type="text" value="${model.description}" name="description" class="form-control" id="exampleInputPassword1">
                </div>
            </div>
            <div class="row col-12" style="padding-bottom: 20px">
                <div class="col-4">
                    <label for="choose-file" class="form-label">Ảnh</label>
                    <input type="file" class="form-control" accept="image/*" name="fileImage" id="choose-file" style = "width: 100%" />
                </div>
                <div class="col-6">
<%--                    <f:input type="text" Style="display: none" class="form-control" path="mainImage" id="validationCustom16"/>--%>
                    <div id="img-preview"><img src="/2208/${model.mainImage}" /></div>
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
        <button style="width: 10%" type="submit" class="btn btn-primary btn-block mb-4">Cập nhật</input>

    </form>
</div>
<jsp:include page="/jsp/frontend/footer.jsp"></jsp:include>
