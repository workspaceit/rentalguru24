<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 8/23/16
  Time: 3:31 PM
  To change this template use File | Settings | File Templates.
--%>
<!-- Left side column. contains the logo and sidebar -->
<aside class="main-sidebar">
  <!-- sidebar: style can be found in sidebar.less -->
  <section class="sidebar">
    <!-- Sidebar user panel -->
    <div class="user-panel">
      <div class="pull-left image">
        <img src="<c:url value="/admin-resources/dist/img/defaultProfileImages.png"/>" class="img-circle" alt="User Image">
      </div>
      <div class="pull-left info">
        <p>${adminUser.getUserInf().getFirstName()}</p>
      </div>
    </div>
    <!-- search form -->
    <form action="#" method="get" class="sidebar-form">
      <div class="input-group">
        <input type="text" name="q" class="form-control" placeholder="Search...">
              <span class="input-group-btn">
                <button type="submit" name="search" id="search-btn" class="btn btn-flat"><i class="fa fa-search"></i></button>
              </span>
      </div>
    </form>
    <!-- /.search form -->
    <!-- sidebar menu: : style can be found in sidebar.less -->
    <ul class="sidebar-menu">
      <li class="header">MAIN NAVIGATION</li>
      <li class="treeview">
        <a href="${BaseUrl}/admin/user/dashboard">
          <i class="fa fa-dashboard"></i> <span>Dashboard</span>
        </a>
      </li>
      <li class="treeview">
        <a href="#">
          <i class="fa fa-dashboard"></i> <span>User</span> <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="treeview-menu">
          <li><a href="${BaseUrl}/admin/user/app-user"><i class="fa fa-circle-o"></i> App User</a></li>
          <li><a href="${BaseUrl}/admin/user/app-user/verified"><i class="fa fa-circle-o"></i>Verified Users</a></li>
          <li><a href="${BaseUrl}/admin/user/app-user/unverified"><i class="fa fa-circle-o"></i>Un-verified Users</a></li>
          <li><a href="${BaseUrl}/admin/user/get-all-admin"><i class="fa fa-circle-o"></i>Admin User</a></li>
          <li><a href="${BaseUrl}/admin/user/create-new-admin"><i class="fa fa-circle-o"></i>Create New Admin</a></li>
        </ul>
      </li>
      <li class="treeview">
        <a href="#">
          <i class="fa fa-dashboard"></i> <span>Product</span> <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="treeview-menu">
          <li><a href="${BaseUrl}/admin/product/get-all-rental-product"><i class="fa fa-circle-o"></i>Rental Products List</a></li>
          <li><a href="${BaseUrl}/admin/product/get-all-approve-product"><i class="fa fa-circle-o"></i>Approve Rental Products List</a></li>
          <li><a href="${BaseUrl}/admin/product/get-all-disapprove-product"><i class="fa fa-circle-o"></i>Disapprove Rental Products List</a></li>
          <li><a href="#"><i class="fa fa-circle-o"></i>Other Products</a></li>
        </ul>
      </li>

      <li class="treeview">
        <a href="${BaseUrl}/admin/user/get-utility">
          <i class="fa fa-dashboard"></i> <span>Utility</span> </i>
        </a>
      </li>

      <li class="treeview">
        <a href="#">
          <i class="fa fa-dashboard"></i> <span>Category</span> <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="treeview-menu">
          <li><a href="${BaseUrl}/admin/user/category-list"><i class="fa fa-circle-o"></i>Category and subcategory List</a></li>
          <li><a href="${BaseUrl}/admin/user/add-category"><i class="fa fa-circle-o"></i>Create New Category</a></li>
          <li><a href="${BaseUrl}/admin/user/add-sub-category"><i class="fa fa-circle-o"></i>Create New Subcategory</a></li>
        </ul>
      </li>
      <li class="treeview">
        <a href="#">
          <i class="fa fa-dashboard"></i> <span>CMS</span> <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="treeview-menu">
          <li><a href="${BaseUrl}/admin/cms/page"><i class="fa fa-circle-o"></i>Create New CMS Page </a></li>
          <li><a href="${BaseUrl}/admin/cms/get-all"><i class="fa fa-circle-o"></i>CMS Pages List</a></li>
        </ul>
      </li>
      <li class="treeview">
        <a href="#">
          <i class="fa fa-dashboard"></i> <span>Notification</span> <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="treeview-menu">
          <li><a href="${BaseUrl}/admin/user/notification/global"><i class="fa fa-circle-o"></i>Dispute</a></li>
        </ul>
      </li>
      <li class="treeview">
        <a href="#">
          <i class="fa fa-dashboard"></i> <span>Banner Image</span> <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="treeview-menu">
          <li><a href="${BaseUrl}/admin/user/add-banner-image"><i class="fa fa-circle-o"></i>Add Banner Image</a></li>
          <li><a href="${BaseUrl}/admin/user/banner-image-list"><i class="fa fa-circle-o"></i>Banner Image List</a></li>
        </ul>
      </li>

      <li class="treeview">
        <a href="#">
          <i class="fa fa-dashboard"></i> <span>Rent request</span> <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="treeview-menu">
          <li><a href="${BaseUrl}/admin/rent-request/all"><i class="fa fa-circle-o"></i>All</a></li>
          <li><a href="${BaseUrl}/admin/rent-request/pending"><i class="fa fa-circle-o"></i>Pending</a></li>
          <li><a href="${BaseUrl}/admin/rent-request/on-progress"><i class="fa fa-circle-o"></i>On progress</a></li>
          <li><a href="${BaseUrl}/admin/rent-request/complete"><i class="fa fa-circle-o"></i>Complete</a></li>
        </ul>
      </li>
      <li class="treeview">
        <a href="#">
          <i class="fa fa-dashboard"></i> <span>Rent payment</span> <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="treeview-menu">
          <li><a href="${BaseUrl}/admin/rent-payment/all"><i class="fa fa-circle-o"></i>All</a></li>
        </ul>
      </li>
    </ul>
  </section>
  <!-- /.sidebar -->
</aside>