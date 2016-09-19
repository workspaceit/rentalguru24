<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 8/25/16
  Time: 3:25 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="col-md-3 user-dash">
  <h3>User Dashboard</h3>
  <ul class="cstm-user-menu">
    <%--<li><a href="${BaseUrl}/user/dashboard">Financial Transaction</a></li>--%>
    <%--<li><a href="#">Rent History</a></li>--%>
    <li><a href="${BaseUrl}/user/dashboard/rentrequest">Rent Request</a></li>
    <li><a href="${BaseUrl}/user/dashboard/rent-history">Rent History</a></li>
    <li><a href="${BaseUrl}/user/dashboard/my-products">My Products</a></li>
    <%--<li><a href="${BaseUrl}/user/dashboard/my-rented-products">My Rented Products</a></li>--%>
    <%--<li><a href="${BaseUrl}/user/dashboard/my-products-on-rent">My Product On Rent</a></li>--%>
    <li><a href="${BaseUrl}/user/dashboard/my-booking">My Bookings</a></li>
    <li><a href="${BaseUrl}/user/dashboard/my-approved-bookings">My Approved Booking</a> </li>
    <li><a href="${BaseUrl}/user/dashboard/my-dispproved-bookings">My Disapproved Booking</a> </li>
    <li><a href="${BaseUrl}/user/dashboard/my-approved-rentrequest">My Approved Rent Request</a> </li>
    <li><a href="${BaseUrl}/user/dashboard/my-disapproved-rentrequest">My Disapproved Rent Request</a> </li>
    <li><a href="${BaseUrl}/user/dashboard/my-profile-edit">Profile Edit</a></li>
  </ul>
</div>
