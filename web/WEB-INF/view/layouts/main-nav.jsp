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
    <div class="collapse navbar-collapse no-padding " id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav nav-cat">
        <li class="dropdown catagory_drop">
          <a data-category-id="" id="dropdownCategorySelect"  class="dropdown-toggle catagory_drop_a" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">
            <i class="fa fa-bars"></i>${preSelectedCategoryName}<span class="caret"></span>
          </a>
          <div class="drop-div">
            <div class="mega-list">
              <ul  id="categoryPageLinkUl" >
                <d:forEach var="listValue" items="${category}">
                  <%--href="#newProductPartialRender"--%>
                  <li class="${dropdownSubCls}" >
                    <a

                       href="javascript:void(0)"
                       categoryId="${listValue.id}"
                       onclick="selectedCategory(${listValue.id})"
                       id="categoryAnchor_${listValue.id}"
                       data-category-name="${listValue.name}" class="scrollToSection developerCategoryAnchore">${listValue.name}</a>
                  </li>
                </d:forEach>
              </ul>
            </div>
            <d:forEach var="listValue" items="${category}">
                        <div class="sub-list" id="subCategoryOf_${listValue.id}" style="background:url(${BaseUrl}/category-images/${listValue.picture.original.path})no-repeat;">
                          <h3>${listValue.name}</h3>
                          <ul>
                          <d:forEach var="subcategory" items="${listValue.subcategory}">
                            <li>
                              <%--href="#newProductPartialRender"--%>
                              <a
                                    href="javascript:void(0)"
                                    categoryId="${subcategory.id}"
                                    onclick="selectedCategory(${subcategory.id})"
                                    id="categoryAnchor_${subcategory.id}"
                                    data-category-name="${subcategory.name}" class="scrollToSection developerCategoryAnchore">${subcategory.name}</a>

                            </li>
                          </d:forEach>

                          </ul>
                        </div>
            </d:forEach>
          </div>
        </li>
      </ul>
      <%--id="dark-select"--%>
      <%--id="ailment_id"--%>
      <select id="categoryMobileSelectBox" class="chzn-select mob-select visible-xs"
              name="ailment_id"
              style="width: 350px">

        <option class="category" value="" disabled selected>Choose Category</option>
        <d:forEach var="listValue" items="${category}" >
          <option categoryId="${listValue.id}" value="${listValue.id}" class="category developerCategoryAnchore" id="subCategoryOf_${listValue.id}">${listValue.name}</option>
          <d:forEach var="subcategory" items="${listValue.subcategory}">
            <option class="item" value="${subcategory.id}">${subcategory.name}</option>
          </d:forEach>
        </d:forEach>

      </select>
      <ul class="nav navbar-nav navbar-right main_navigation">
        <%--<d:forEach var="cmsPage" items="${cmsPages}" >
          <li><a href="${BaseUrl}/static/${cmsPage.pageKey}">${cmsPage.pageName}</a></li>
        </d:forEach>--%>
          <li><a href="${BaseUrl}/home/category/8">Home & Garden</a></li>
          <li><a href="${BaseUrl}/home/category/9">Cars and Vehicles, </a></li>
          <li><a href="${BaseUrl}/home/category/12">Electronics</a></li>
          <li><a href="${BaseUrl}/home/category/100">Property</a></li>
      </ul>
    </div>
  </div><!-- /.container-fluid -->
</nav>
<script type="text/javascript">

</script>
