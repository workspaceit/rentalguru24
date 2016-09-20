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
                    <h3>My Products <button  onclick="location.href='${BaseUrl}/product/upload'" class="btn-filter user_dboard_btn">Add New Product</button></h3>
                    <%--<div class="row clearfix no-margin">--%>
                        <%--<div class="col-md-4 col-sm-4 col-xs-12">--%>
                            <%--<div class="form-group date-con">--%>
                                <%--<label>From</label>--%>
                                <%--<input type="text"  class="form-control datepicker" id="dpd1" placeholder="" >--%>
                                <%--<p class="help-block error-form">Please fill up the field</p>--%>
                            <%--</div>--%>
                        <%--</div>--%>

                        <%--<div class="col-md-4 col-sm-4 col-xs-12">--%>
                            <%--<div class="form-group date-con">--%>
                                <%--<label>To</label>--%>
                                <%--<input type="text"  class="form-control datepicker" id="dpd2" placeholder="" >--%>
                                <%--<p class="help-block error-form">Please fill up the field</p>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                        <%--<button class="btn-filter user_dboard_btn">Date Filters</button>--%>
                    <%--</div>--%>
                    <div class="row">
                        <div class="col-md-12 col-sm-12 col-xs-12">
                            <table id="example1" class="table table-bordered table-striped user_dashboard_table">
                                <thead>
                                    <tr>
                                        <th>Product</th>
                                        <th>Edit / Delete</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <d:forEach var="product" items="${myRentalProduct}">
                                    <tr>
                                        <td>
                                            <div class="table-img">
                                                <img class="img-responsive" src="<c:url value="${BaseUrl}/images/${product.getProfileImage().getOriginal().getPath()}" />" />
                                            </div>
                                            <div class="table-desc">
                                                <h5>${product.getName()}</h5>
                                                <p>${product.getDescription()}</p>
                                                <p><span><fmt:formatDate pattern="MMM d,yyyy" value="${product.getAvailableFrom()}"/> </span> to <span><fmt:formatDate pattern="MMM d,yyyy" value="${product.getAvailableTill()}"/></span></p>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="actions">
                                                <button class="btn btn-edit" onclick="editProduct(${product.id}, ${appCredential.id})">Edit</button>
                                                <button class="btn btn-delete">Delete</button>
                                            </div>
                                        </td>
                                    </tr>
                                </d:forEach>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th>Product</th>
                                        <th>Edit / Delete</th>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Dashboard-->
    <jsp:directive.include file="../layouts/top-footer.jsp" />
    <jsp:directive.include file="../layouts/footer.jsp" />
    <!-- Javascript framework and plugins end here -->
    <script>
        function editProduct(productId, ownerId){
            location.href = BASEURL+"/user/dashboard/edit-product/"+productId+"/"+ownerId;
        }
    </script>
    </body>
</html>


