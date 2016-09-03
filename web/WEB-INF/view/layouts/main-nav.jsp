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
          <a data-category-id="" id="dropdownCategorySelect" class="dropdown-toggle catagory_drop_a" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-bars"></i>Select a category
            <span class="caret"></span>
          </a>
          <ul class="dropdown-menu">
            <d:forEach var="listValue" items="${category}">
              <li><a onclick="selectedCategory(${listValue.id})" id="selected${listValue.id}" data-category-name="${listValue.name}">${listValue.name}</a></li>
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
        <d:forEach var="listValueMenue" items="${category}">
          <li><a id="categoryAnchor_${listValueMenue.id}" onclick="fetchProductByCategoryAndScrollDown(${listValueMenue.id},this)" href="#newProductPartialRender" class="scrollToSection" >${listValueMenue.name}</a></li>
        </d:forEach>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
