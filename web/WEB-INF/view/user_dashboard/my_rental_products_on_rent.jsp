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
                    <h3>My Products On Rent</h3>
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
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                <d:forEach var="product" items="${myProductOnRent}">
                                    <tr>
                                        <td>
                                            <div class="table-img">
                                                <img class="img-responsive" src="<c:url value="${BaseUrl}/images/${product.getProfileImage().getOriginal().getPath()}" />" />
                                            </div>
                                            <div class="table-desc">
                                                <h5>${product.getName()}</h5>
                                                <p>${product.getDescription()}</p>
                                                <p><span><fmt:formatDate pattern="MMM d,yyyy" value="${product.getAvailableFrom()}"/> </span> to <span><fmt:formatDate pattern="MMM d,yyyy" value="${product.getAvailableTill()}"/></span></p>
                                                <h5>Rented By</h5>
                                                <d:set var="locaIsProductRenturned" value="${false}" ></d:set>
                                                <d:forEach var="rentInf" items="${product.getRentInf()}">
                                                    <d:set var="productRenturned" value="${tyre}" ></d:set>
                                                    <p>${rentInf.getRentee().getUserInf().getFirstName()} ${rentInf.getRentee().getUserInf().getLastName()}</p>
                                                    <p><span><fmt:formatDate pattern="MMM d,yyyy" value="${rentInf.getStartDate()}"/> </span> to <span><fmt:formatDate pattern="MMM d,yyyy" value="${rentInf.getEndsDate()}"/></span></p>
                                                    <d:if test="${rentInf.rentalProductReturned != null}">
                                                        <d:if test="${rentInf.rentalProductReturned != null}">
                                                            <d:choose>
                                                                <d:when test="${rentInf.rentalProductReturned.confirm==true}">
                                                                    <div class="alert alert-success">
                                                                        <strong>Confirmed on
                                                                            <d:set var="rentalProductReturnedHistories" value="${rentInf.rentalProductReturned.rentalProductReturnedHistories}" />
                                                                            <d:if test="${rentalProductReturnedHistories != null && rentalProductReturnedHistories.size()>0}">
                                                                                <span><fmt:formatDate pattern="MMM d,yyyy" value="${rentalProductReturnedHistories[rentalProductReturnedHistories.size()-1].createdDate}"/> </span>
                                                                            </d:if>
                                                                        </strong>
                                                                    </div>
                                                                </d:when>
                                                                <d:when test="${rentInf.rentalProductReturned.dispute==true}">
                                                                    <div class="alert alert-success">
                                                                        <strong>Dispute on
                                                                            <d:set var="rentalProductReturnedHistories" value="${rentInf.rentalProductReturned.rentalProductReturnedHistories}" />
                                                                            <d:if test="${rentalProductReturnedHistories != null && rentalProductReturnedHistories.size()>0}">
                                                                                <span><fmt:formatDate pattern="MMM d,yyyy" value="${rentalProductReturnedHistories[rentalProductReturnedHistories.size()-1].createdDate}"/> </span>
                                                                            </d:if>
                                                                        </strong>
                                                                    </div>
                                                                </d:when>
                                                                <d:otherwise>
                                                                    <button class="btn btn-warning confirm${rentInf.rentalProductReturned.id}" onclick="productReceiveConfirm(${rentInf.rentalProductReturned.id})" >Confirm</button>
                                                                    <button class="btn btn-accept dispute${rentInf.rentalProductReturned.id}" onclick="productReceiveDispute(${rentInf.rentalProductReturned.id})" >Dispute</button>
                                                                </d:otherwise>
                                                            </d:choose>
                                                        </d:if>
                                                    </d:if>
                                                    <div class="alert alert-success" id="successConfirm${rentInf.rentalProductReturned.id}" hidden>
                                                        Product return request has been confirm.
                                                    </div>
                                                    <div class="alert alert-success" id="successDispute${rentInf.rentalProductReturned.id}" hidden>
                                                        Product return request has been Dispute.
                                                    </div>
                                                    <div class="alert alert-success" id="errorConfirmDispute${rentInf.rentalProductReturned.id}" hidden>
                                                    </div>
                                                </d:forEach>
                                            </div>
                                        </td>
                                        <d:if test="${!locaIsProductRenturned}" >
                                            <d:forEach var="rentInf" items="${product.getRentInf()}">
                                                <td>
                                                    <div class="actions">
                                                        <button class="btn btn-edit" onclick="requestToReturnProduct(${rentInf.id})" id="buttonRequestReturn">Request Return</button>
                                                    </div>
                                                    <div class="alert alert-success" id="successRequestReturn${rentInf.id}" hidden>
                                                        Product return request has been sent.
                                                    </div>
                                                    <div class="alert alert-danger" id="errorRequestReturn${rentInf.id}" hidden></div>
                                                </td>
                                            </d:forEach>
                                        </d:if>
                                    </tr>
                                </d:forEach>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th>Product</th>
                                        <th>Action</th>
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Dashboard-->
        <jsp:directive.include file="../layouts/footer.jsp" />
        <!-- Javascript framework and plugins end here -->
        <script>

        </script>
    </body>
</html>


