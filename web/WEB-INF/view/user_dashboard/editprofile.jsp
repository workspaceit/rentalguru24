<!DOCTYPE html>
<html lang="en">
<jsp:directive.include file="../layouts/header.jsp" />
    <body class="ux">
        <!--top Nav Bar-->
        <jsp:directive.include file="../layouts/top-nav.jsp" />
        <!--mid navbar-->
        <jsp:directive.include file="../layouts/mid-nav.jsp" />
        <!--main navbar-->
        <jsp:directive.include file="../layouts/main-nav.jsp" />
        <!--end main Nav-->
        <!--Dashboard-->
        <div class="container user-dash-container">
            <div class="row">
                <jsp:directive.include file="../layouts/userDashboardLeftMemu.jsp" />
                <div class="col-md-9 side-container">
                    <h3>Edit profile</h3>
                    <div class="col-md-3 profile-pic">
                        <div class="profile-pic-review">
                            <img id="profileImage" src="${BaseUrl}/images/${appCredential.getUserInf().getProfilePicture().getOriginal().getPath()}" style="height: 178px; width: 200px;">
                        </div>
                        <div class="form-group" style="margin-bottom:0px;">
                            <label for="fallback">Upload photo</label>
                            <div id="fallback" class="fallback pos-relative" >
                                Choose a profile picture
                                <span class="inner-load" hidden id="editProfilePicUploadLoader"></span>
                            </div>
                            <p class="help-block error-form" id="errorMsg_profileImageToken"></p>
                        </div>
                    </div>
                    <form class="form-signup clearfix form-edit col-md-9" onsubmit="return postEditProfile();">
                        <div class="col-md-12">
                            <div class="form-group">
                                <label for="firstName">First name</label>
                                <input type="text" class="form-control" placeholder="ex.John" id="firstName" name="firstName" value="${appCredential.getUserInf().getFirstName()}">
                                <p class="help-block error-form" id="errorMsg_firstName"></p>
                            </div>
                            <div class="form-group">
                                <label for="lastName">Last name</label>
                                <input type="text" class="form-control" placeholder="ex.Wick" id="lastName" name="lastName" value="${appCredential.getUserInf().getLastName()}">
                                <p class="help-block error-form" id="errorMsg_lastName"></p>
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" class="form-control" placeholder="ex.email@email.com" id="email" name="email" value="${appCredential.getEmail()}">
                                <p class="help-block error-form" id="errorMsg_email"></p>
                            </div>
                            <div class="form-group">
                                <label for="oldPassword">Old Password</label>
                                <input type="password" class="form-control" placeholder="ex.password" id="oldPassword" name="oldPassword">
                                <p class="help-block error-form" id="errorMsg_oldPassword"></p>
                            </div>
                            <div class="form-group">
                                <label for="newPassword">New Password</label>
                                <input type="password" class="form-control" placeholder="ex.password" id="newPassword" name="newPassword">
                                <p class="help-block error-form" id="errorMsg_newPassword"></p>
                            </div>
                        </div>
                        <div class="col-md-12 text-right">
                            <button class="btn-cstm-sign  pos-relative" style="margin-right:0px;" id="editProfileButton" >
                                Edit Profile
                                <span class="inner-load" hidden id="editProfileLoder"></span>
                            </button>
                            <br>
                            <div class="alert alert-success text-center" role="alert" hidden >Profile Edit Successfully done</div>
                        </div>
                        <input value="" id="profileImageToken" name="profileImageToken" type="hidden" >
                    </form>
                </div>
            </div>
        </div>
        <%------------------------------------------------------------------%>
        <div class="table table-striped" class="files" id="previews">

            <div id="template" class="file-row">
                <!-- This is used as the file preview template -->
                <div>
                    <span class="preview"><img data-dz-thumbnail style="height: 178px; width: 200px;"/></span>
                </div>
                <div>
                    <strong class="error text-danger" data-dz-errormessage></strong>
                </div>
            </div>
        </div>
        <%------------------------------------------------------------------------------------%>
        <!--Dashboard-->
        <jsp:directive.include file="../layouts/top-footer.jsp" />
        <jsp:directive.include file="../layouts/footer.jsp" />
        <!-- Javascript framework and plugins end here -->
        <script>
            Dropzone.autoDiscover = false;
            var previewNode = document.querySelector("#template");
            previewNode.id = "";
            var previewTemplate = previewNode.parentNode.innerHTML;
            previewNode.parentNode.removeChild(previewNode);
            $(function() {
                var profilePictureFile = $("div#fallback").dropzone(
                        {
                            url: BASEURL+"/fileupload/upload/auth/user/profile-image",
                            paramName: "profileImage",
                            maxFilesize: 1,
                            previewTemplate: previewTemplate,
                            thumbnailWidth: 200,
                            thumbnailHeight: 175,
                            maxFiles: 1,
                            acceptedFiles: "image/jpeg,image/png,image/jpg",
                            maxfilesexceeded: function(file) {
                                this.removeAllFiles();
                                this.addFile(file);
                            },
                            uploadprogress:function(file, progress){
                                $('#editProfileButton').attr("disabled","disabled");
                                $('#editProfileLoder').show();
                                $('#editProfilePicUploadLoader').show();
                            },
                            success:function(file, response){
                                if(response.responseStat.status == true) {
                                    $('#profileImage').remove();
                                    $('#editProfilePicUploadLoader').hide();
                                    $('#editProfileButton').removeAttr("disabled","disabled");
                                    $('#editProfileLoder').hide();
                                    $('#profileImageToken').val(response.responseData);
                                } else{
                                    BindErrorsWithHtml('errorMsg_', response.requestErrors);
                                }
                            },
                            previewsContainer: ".profile-pic-review",
                            error:function(file, errorMessage, xhr){
                                $('#editProfilePicUploadLoader').hide();
                                $('#editProfileButton').removeAttr("disabled","disabled");
                                $('#editProfileLoder').hide();
                            }
                        }
                );
            });
        </script>
        <script>
            function postEditProfile(){
                $('#editProfileLoder').show();
                var firstName = $('#firstName').val();
                var lastName = $('#lastName').val();
                var email = $('#email').val();
                var oldPassword = $('#oldPassword').val();
                var newPassword = $('#newPassword').val();
                var profileImageToken = $('#profileImageToken').val();
//                console.log(firstName, lastName, email, oldPassword, newPassword, profileImageToken);
                $.ajax({
                    url: BASEURL+"/api/auth/profile/edit",
                    type: "POST",
                    data: {
                        firstName: firstName,
                        lastName: lastName,
                        email: email,
                        oldPassword: oldPassword,
                        newPassword: newPassword,
                        profileImageToken: profileImageToken,
                    },
                    success: function(data){
                        console.log(data);
                        if(data.responseStat.status == false){
                            BindErrorsWithHtml("errorMsg_", data.responseStat.requestErrors);
                        }else{
                            $('.alert-success').show().delay(5000).fadeOut(500,function(){
                            });
                        }
                        $('#editProfileLoder').hide();
                    },
                    error: function(data){
                        console.log('fail');
                        $('#editProfileLoder').hide();
                    }
                });
                return false;
            }
        </script>

    </body>
</html>


