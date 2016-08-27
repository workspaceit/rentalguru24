<!DOCTYPE html>
<html lang="en">
<jsp:directive.include file="../layouts/header.jsp" />
    <body class="ux">
        <!--top Nav Bar-->
        <jsp:directive.include file="../layouts/mid-nav.jsp" />

         <div class="parallax-window bg1" data-enllax-ratio="0.7">
            <div class="container title-block">
                <h1>Approve Request</h1>
            </div>
         </div>

        <div class="container rqst_main_content_wrap">
            <div class="user_info_wrap">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <h3 class="aproval_container_title">Order Information</h3>
                    </div>
                    <div class="col-md-7 col-sm-7 col-xs-12">
                        <div class="row">
                            <div class="col-md-3 col-xs-3 col-xs-12">
                                <div class="product_img_container">
                                    <img src="img/20.jpg" class=" img-responsive user_img">
                                </div>
                            </div>
                            <div class="col-md-9 col-xs-9 col-xs-12">
                                <h3>Arnab Dehar</h3>

                                <h5 class="social_link_header">Social Links:</h5>
                                <ul class="social_link_client_rqst">
                                    <li><a href=""><i class="fa fa-facebook-square facebook"></i></a></li>
                                    <li><a href=""><i class="fa fa-linkedin-square linkedin"></i></a></li>
                                    <li><a href=""><i class="fa fa-twitter-square twitter"></i></a></li>
                                </ul>
                            
                            </div>
                        </div>
                        
                        
                        <div class="row">
                            <div class="col-md-12 col-sm-12 col-xs-12">
                                <h5 class="rent_client_other_info_head">Other Informations</h5>
                                <div class="all_info">
                                    <div class="info_title">
                                        <p class="no-margin"><strong>Bio-graphy</strong></p>
                                    </div>
                                    <div class="detail_info">
                                        <p class="justify no-margin">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer at magna elementum, consectetur purus a, euismod odio. Duis mattis libero metus, id eleifend nisl gravida non. Pellentesque felis nibh, fringilla a nibh et, pulvinar blandit nibh.</p>
                                    </div>

                                </div>

                                <div class="all_info">
                                    <div class="info_title">
                                        <p class="no-margin"><strong>Contact Number</strong></p>
                                    </div>
                                    <div class="detail_info">
                                        <p class="justify no-margin">01664-526-5</p>
                                    </div>

                                </div>

                                <div class="all_info">
                                    <div class="info_title">
                                        <p class="no-margin"><strong>Email</strong></p>
                                    </div>
                                    <div class="detail_info">
                                        <p class="justify no-margin">dehar@reneguru.com</p>
                                    </div>

                                </div>
                                <div class="all_info">
                                    <div class="info_title">
                                        <p class="no-margin"><strong>Address</strong></p>
                                    </div>
                                    <div class="detail_info">
                                        <p class="justify no-margin">2218 Newton Street,Dassel,Minnesota,55326</p>
                                    </div>

                                </div>
                                <div class="all_info">
                                    <div class="info_title">
                                        <p class="no-margin"><strong>Credit Card Type</strong></p>
                                    </div>
                                    <div class="detail_info">
                                        <p class="justify no-margin">Visa</p>
                                    </div>

                                </div>
                                <div class="all_info">
                                    <div class="info_title">
                                        <p class="no-margin"><strong>Credit Card Number</strong></p>
                                    </div>
                                    <div class="detail_info">
                                        <p class="justify no-margin">103-105-5592</p>
                                    </div>

                                </div>
                                
                            </div>
                            
                        </div>
                    </div>
                    <div class="col-md-5 col-sm-5 col-xs-12">
                        <h5 class="color invoice_head">Transection Details</h5>
                        <div class="table-responsive">
                            <table class="table table-striped">
                                <thead>
                                    <tr>
                                        <th>Order Info</th>
                                        <th></th>
                                        <th>Order Details</th>  
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td>Start Date</td>
                                        <td></td>
                                        <td>${rentRequest.getStartDate()}</td>
                                    </tr>
                                    <tr>
                                        <td>End Date</td>
                                        <td></td>
                                        <td>${rentRequest.getEndDate()}</td>
                                    </tr>
                                    <tr>
                                        <td>Number of Days</td>
                                        <td></td>
                                        <td>15 days</td>
                                    </tr>
                                    <tr>
                                        <td>Rent/Day</td>
                                        <td></td>
                                        <td>$4</td>                                        
                                    </tr>
                                    <tr>
                                        <td>Total Rent</td>
                                        <td></td>
                                        <td>$1200</td>                                        
                                    </tr>
                                    
                                    <tr>
                                        <td>Additional Fees</td>
                                        <td> </td>                                        
                                        <td> </td>                                        
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>Development Fee</td>                                        
                                        <td>$400</td>                                        
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td>Tax Fee</td>                                        
                                        <td>$100</td>                                        
                                    </tr>
                                </tbody>
                                <tfoot>
                                    <tr>
                                        <th><strong>Total</strong></th>
                                        <th></th>
                                        <th><strong>$1700</strong></th>  
                                    </tr>
                                </tfoot>
                            </table>
                        </div>
                        <h5>Remarks: </h5>
                        <p class="justify no-margin p_des">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer at magna elementum, consectetur purus a, euismod odio. </p>
                        <ul class="confirmation_link_ul">
                           
                            <li><BUTTON class="cancel_btn approval_btn">Cancel</BUTTON></li>
                            <li><BUTTON class="approve_btn approval_btn">Approve</BUTTON></li>
                        </ul>
                    </div>
                </div>
            </div>
            <h3 class="aproval_container_title">Product Information</h3>
            <div class="product_info_wrap">
                <div class="row">
                    <div class="col-md-6 col-sm-6 col-xs-12 ">
                        <div class="product_img_container">
                            <img src="img/15.jpg" class=" img-responsive">
                        </div>
                    </div>
                    <div class="col-md-6 col-sm-6 col-xs-12">
                        <div class="product_info_container">
                            
                            <div class="req_information">
                                <h3 class="color p_name "> Twin-in-one Bed </h3>
                                <h5 class=" p_des_head">Description</h5>
                                <p class="justify no-margin p_des">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Integer at magna elementum, consectetur purus a, euismod odio. Duis mattis libero metus, id eleifend nisl gravida non. Pellentesque felis nibh, fringilla a nibh et, pulvinar blandit nibh.</p>
                                <ul class="order_time_interval">
                                    <li>From</li>
                                    <li><strong><span class="time_date">13-06-2016</span></strong></li>
                                    <li>To</li>
                                    <li><strong><span class="time_date">25-06-2016</span></strong></li>
                                </ul>
                            </div>
                        
                                                       
                        </div>
                        <div class="other_image_rqst_product">
                            <h5 class=" p_des_head no-padding">Other Images</h5>                            
                            <div class=" rqst_product_slider">
                                <div class="owl-carousel">
                                    <div class="item"><img src="img/1.jpg"></div>
                                    <div class="item"><img src="img/1.jpg"></div>
                                    <div class="item"><img src="img/1.jpg"></div>
                                    <div class="item"><img src="img/1.jpg"></div>
                                    <div class="item"><img src="img/1.jpg"></div>
                                    <div class="item"><img src="img/1.jpg"></div>
                                    <div class="item"><img src="img/1.jpg"></div>
                                    <div class="item"><img src="img/1.jpg"></div>
                                    <div class="item"><img src="img/1.jpg"></div>
                                    <div class="item"><img src="img/1.jpg"></div>
                                    <div class="item"><img src="img/1.jpg"></div>
                                </div>
                            </div> 
                        </div>
                    </div>
                </div>
            </div>
            
            <div class="other_order_wrap">
                <div class="row">
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <h3 class="aproval_container_title">Previous Orders of this client</h3>
                    </div>
                    <div class="col-md-12 col-sm-12 col-xs-12">
                        <div id="carousel-example-generic" class="carousel slide carousel-cstm" data-ride="carousel" data-interval='false'>
                        <!-- Indicators -->
                            <ol class="carousel-indicators">
                                <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                                <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                            </ol>
                            
                            <!-- Wrapper for slides -->
                            <div class="carousel-inner" role="listbox">
                                <div class="item active">
                                    <div class="row clearfix">
                                        <div class="col-md-3 single-item">
                                            <div class="panel panel-default">
                                                <div class="panel-body">
                                                    <div class="img-single">
                                                        <img src="img/6.jpg" />                           
                                                    </div>
                                                    <div class="block-desc">
                                                        <label>Aenean Ru Bristique</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-3 single-item">
                                            <div class="panel panel-default">
                                                <div class="panel-body">
                                                    <div class="img-single">
                                                        <img src="img/6.jpg" />                           
                                                    </div>
                                                    <div class="block-desc">
                                                        <label>Aenean Ru Bristique</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-3 single-item">
                                            <div class="panel panel-default">
                                                <div class="panel-body">
                                                    <div class="img-single">
                                                        <img src="img/6.jpg" />                           
                                                    </div>
                                                    <div class="block-desc">
                                                        <label>Aenean Ru Bristique</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-md-3 single-item">
                                            <div class="panel panel-default">
                                                <div class="panel-body">
                                                    <div class="img-single">
                                                        <img src="img/6.jpg" />                           
                                                    </div>
                                                    <div class="block-desc">
                                                        <label>Aenean Ru Bristique</label>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>                                    
                                    </div>
                                </div>                            
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        


        <div class="footer">
            <div class="container">
                <div class="row">
                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <p class="footer_head">INFORMATION</p>
                        <ul class="footer_ul">
                            <li>About US</li>
                            <li>Privacy</li>
                            <li>Conditions</li>
                            <li>Online Support</li>
                        </ul>
                    </div>
                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <p class="footer_head">MY ACCOUNT</p>
                        <ul class="footer_ul">
                            <li>Login</li>
                            <li>My Cart</li>
                            <li>Wishlist</li>
                            <li>Checkout</li>
                        </ul>
                    </div>
                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <p class="footer_head">INFORMATION</p>
                        <ul class="footer_ul">
                            <li>Specials</li>
                            <li>New Products</li>
                            <li>Best Sellers</li>
                            <li>Our Stored</li>
                        </ul>
                    </div>
                    <div class="col-md-3 col-sm-6 col-xs-12">
                        <p class="footer_head">ORDERS</p>
                        <ul class="footer_ul">
                            <li>Payment Option</li>
                            <li>Shipping Delivery</li>
                            <li>Returns</li>
                            <li>Shipping</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <jsp:directive.include file="../layouts/footer.jsp" />
    </body>
</html>


