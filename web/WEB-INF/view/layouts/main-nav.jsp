<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 8/24/16
  Time: 10:58 AM
  To change this template use File | Settings | File Templates.
--%>
<nav class="main_nav navbar navbar-default">
  <div class="container">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>

    </div>
    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse no-padding " id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="dropdown catagory_drop">
          <a href="#" class="dropdown-toggle catagory_drop_a" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><i class="fa fa-bars"></i>Select a category <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <d:forEach var="listValue" items="${category}">
              <%--<li><a href="#">Action</a></li>--%>
              <li><a href="#">${listValue.name}</a></li>
            </d:forEach>
          </ul>
        </li>
        <form class="navbar-form navbar-left no-padding main_search_form" role="search">

          <input class="form-control" placeholder="Search" name="srch-term" id="srch-term" type="text">
          <div class="input-group-btn search_button">
            <button class="btn btn-default search_btn" type="submit" style="padding:6px;"><i class="fa fa-search"></i></button>
          </div>
        </form>
      </ul>
      <ul class="nav navbar-nav navbar-right main_navigation">
        <li><a href="#">HOME</a></li>
        <li><a href="#">HOME APPLIANCE</a></li>
        <li><a href="#">FURNITURE</a></li>
        <li><a href="#">GAMING & PARTY</a></li>
        <li><a href="#">COOL GADGETS</a></li>
        <li><a href="#">BLOG</a></li>

      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
