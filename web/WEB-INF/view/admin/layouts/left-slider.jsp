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
        <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
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
        </ul>

        <ul class="treeview-menu">
          <li><a href="${BaseUrl}/admin/user/get-all-admin"><i class="fa fa-circle-o"></i>Admin User</a></li>
        </ul>

        <ul class="treeview-menu">
          <li><a href="${BaseUrl}/admin/user/create-new-admin"><i class="fa fa-circle-o"></i>Create New Admin</a></li>
        </ul>
      </li>
      <li class="treeview">
        <a href="#">
          <i class="fa fa-dashboard"></i> <span>Product</span> <i class="fa fa-angle-left pull-right"></i>
        </a>
        <ul class="treeview-menu">
          <li><a href="${BaseUrl}/admin/product/get-all-rental-product"><i class="fa fa-circle-o"></i>All Rental Products</a></li>
        </ul>
        <ul class="treeview-menu">
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
        </ul>
        <ul class="treeview-menu">
          <li><a href="${BaseUrl}/admin/user/add-category"><i class="fa fa-circle-o"></i>Add Category</a></li>
        </ul>

        <ul class="treeview-menu">
          <li><a href="${BaseUrl}/admin/user/add-sub-category"><i class="fa fa-circle-o"></i>Add Subcategory</a></li>
        </ul>
      </li>
    </ul>
  </section>
  <!-- /.sidebar -->
</aside>