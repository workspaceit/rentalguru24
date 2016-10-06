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
                                        <button class="btn btn-delete" onclick="showModal(${product.id})" >Delete</button>
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
<%----------------------------------------------------------%>
<!-- Modal -->
<div id="confirmationModal" class="modal fade" role="dialog">
    <div class="modal-dialog">
        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header"></div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-6 text-center">
                        Are you sure you want to delete this product
                    </div>
                    <div class="col-md-6 text-center">
                        <button type="button" class="btn btn-default" onclick="deleteProduct()">Yes</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                        <input id="deleteProductId" value="" hidden>
                    </div>
                </div>
            </div>
            <div class="alert alert-danger" hidden>
                Can't delete a product on rent
            </div>
            <div class="modal-footer"></div>
        </div>
    </div>
</div>
<%----------------------------------------------------------%>
<jsp:directive.include file="../layouts/top-footer.jsp" />
<jsp:directive.include file="../layouts/footer.jsp" />
<!-- Javascript framework and plugins end here -->
<script>
    function editProduct(productId, ownerId){
        location.href = BASEURL+"/product/edit/"+productId;
    }
    function deleteProduct(){
        var productId = $('#deleteProductId').val();
        $.ajax({
            type: "POST",
            url: BASEURL+"/api/auth/product/delete-Product/"+productId,
            success: function(data){
                if(data.responseStat.status==true){
                    location.reload();
                }else{
                    $(".alert-danger").show().fadeIn(500).delay(2000).fadeOut(500,function(){
                        location.reload();
                    });
                }
            },
        });
    }
</script>
<script>
    function showModal(productId){
        $("#deleteProductId").val(productId);
        $('#confirmationModal').modal('show');
    }
</script>
</body>
</html>


