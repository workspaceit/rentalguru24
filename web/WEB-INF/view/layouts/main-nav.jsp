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
          <a data-category-id="" id="dropdownCategorySelect"  class="dropdown-toggle catagory_drop_a" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-bars"></i>Select a category <span class="caret"></span>
          </a>
          <ul class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu">
            <li>
              <a tabindex="-1" onclick="selectedCategory(0)" id="selected0" data-category-name="All Category">All Category</a>
            </li>
            <d:forEach var="listValue" items="${category}">
              <d:set value="" var="dropdownSubCls" ></d:set>
              <d:if test="${listValue.subcategory.size()>0}">
                <d:set value="dropdown-submenu" var="dropdownSubCls" ></d:set>
              </d:if>

              <li class="${dropdownSubCls}" >
                <a tabindex="-1" onclick="selectedCategory(${listValue.id})" id="selected${listValue.id}" data-category-name="${listValue.name}">${listValue.name}</a>
                <d:if test="${listValue.subcategory.size()>0}">
                  <ul class="dropdown-menu">
                    <d:forEach var="subcategory" items="${listValue.subcategory}">
                      <li><a  onclick="selectedCategory(${subcategory.id})" id="selected${subcategory.id}" data-category-name="${subcategory.name}">${subcategory.name}</a></li>
                    </d:forEach>
                  </ul>
                </d:if>

              </li>
            </d:forEach>

          </ul>
        </li>
      </ul>

      <ul id="categoryPageLinkUl" class="nav navbar-nav navbar-right main_navigation">
        <d:forEach var="listValueMenue" items="${category}">
          <li><a id="categoryAnchor_${listValueMenue.id}" categoryId="${listValueMenue.id}" href="#newProductPartialRender" class="scrollToSection" >${listValueMenue.name}</a></li>
        </d:forEach>
      </ul>
    </div><!-- /.navbar-collapse -->
  </div><!-- /.container-fluid -->
</nav>
