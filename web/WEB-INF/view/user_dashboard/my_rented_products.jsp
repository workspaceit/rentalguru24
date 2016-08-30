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
                    <h3>My Rented Product</h3>
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
                                <d:forEach var="product" items="${myRentedProduct}">
                                    <tr>
                                        <td>
                                            <div class="table-img">
                                                <img class="img-responsive" src="<c:url value="${BaseUrl}/images/${product.getProfileImage().getOriginal().getPath()}" />" />
                                            </div>
                                            <div class="table-desc">
                                                <h5>${product.getName()}</h5>
                                                <p>${product.getDescription()}</p>
                                                <p><span><fmt:formatDate pattern="MMM d,yyyy" value="${product.getAvailableFrom()}"/> </span> to <span><fmt:formatDate pattern="MMM d,yyyy" value="${product.getAvailableTill()}"/></span></p>
                                                <h5>Product Owner</h5>
                                                <d:forEach var="rentInf" items="${product.getRentInf()}">
                                                    <d:if test="${rentInf.getRentee().getId() ==appCredential.getId()}">
                                                        <p>${product.getOwner().getUserInf().getFirstName()} ${product.getOwner().getUserInf().getLastName()}</p>
                                                        <p><span><fmt:formatDate pattern="MMM d,yyyy" value="${rentInf.getStartDate()}"/> </span> to <span><fmt:formatDate pattern="MMM d,yyyy" value="${rentInf.getEndsDate()}"/></span></p>
                                                    </d:if>
                                                </d:forEach>
                                            </div>
                                        </td>
                                        <td>
                                            <div class="actions">
                                                <button class="btn btn-edit">Return Product</button>
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
        <jsp:directive.include file="../layouts/footer.jsp" />
        <!-- Javascript framework and plugins end here -->
        <script>
            (function ($) {
                $('.spinner .btn:first-of-type').on('click', function () {
                    $('.spinner input').val(parseInt($('.spinner input').val(), 10) + 1);
                });
                $('.spinner .btn:last-of-type').on('click', function () {
                    $('.spinner input').val(parseInt($('.spinner input').val(), 10) - 1);
                });
            })(jQuery);
        </script>
        <script>
            $('#h-slider').slider({
                range: true,
                values: [17, 67]
            });
        </script>
        <script>

            (function ($) {

                //Plugin activation
                $(window).enllax();

                //            $('#some-id').enllax();

                //            $('selector').enllax({
                //                type: 'background', // 'foreground'
                //                ratio: 0.5,
                //                direction: 'vertical' // 'horizontal'
                //            });

            })(jQuery);
        </script>

        <script type="text/javascript">
//            $(document).ready(function () {
//                $("#successModal").modal('show');
//            });
        </script>
        <script>
            $(document).ready(function () {
                $('#example1').DataTable();
            });
        </script>
        <script>
            var nowTemp = new Date();
            var now = new Date(nowTemp.getFullYear(), nowTemp.getMonth(), nowTemp.getDate(), 0, 0, 0, 0);

            var checkin = $('#dpd1').datepicker({
                onRender: function (date) {
                    return date.valueOf() < now.valueOf() ? 'disabled' : '';
                }
            }).on('changeDate', function (ev) {
                if (ev.date.valueOf() > checkout.date.valueOf()) {
                    var newDate = new Date(ev.date)
                    newDate.setDate(newDate.getDate() + 1);
                    checkout.setValue(newDate);
                }
                checkin.hide();
                $('#dpd2')[0].focus();
            }).data('datepicker');
            var checkout = $('#dpd2').datepicker({
                onRender: function (date) {
                    return date.valueOf() <= checkin.date.valueOf() ? 'disabled' : '';
                }
            }).on('changeDate', function (ev) {
                checkout.hide();
            }).data('datepicker');
        </script>

    </body>
</html>


