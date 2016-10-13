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
    <%--<div class="collapse navbar-collapse no-padding " id="bs-example-navbar-collapse-1">--%>
      <%--<ul class="nav navbar-nav">--%>
        <%--<li class="dropdown catagory_drop">--%>
          <%--<a data-category-id="" id="dropdownCategorySelect"  class="dropdown-toggle catagory_drop_a" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">--%>
            <%--<i class="fa fa-bars"></i>${preSelectedCategoryName}<span class="caret"></span>--%>
          <%--</a>--%>
          <%--<ul  id="categoryPageLinkUl" class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu">--%>
            <%--&lt;%&ndash;<li>&ndash;%&gt;--%>
              <%--&lt;%&ndash;<a tabindex="-1" onclick="selectedCategory(0)" id="selected0" data-category-name="All Category">All Category</a>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</li>&ndash;%&gt;--%>
            <%--<d:forEach var="listValue" items="${category}">--%>
              <%--<d:set value="" var="dropdownSubCls" ></d:set>--%>
              <%--<d:if test="${listValue.subcategory.size()>0}">--%>
                <%--<d:set value="dropdown-submenu" var="dropdownSubCls" ></d:set>--%>
              <%--</d:if>--%>

              <%--<li class="${dropdownSubCls}" >--%>
                <%--<a tabindex="-1"--%>
                   <%--href="#newProductPartialRender"--%>
                   <%--categoryId="${listValue.id}"--%>
                   <%--onclick="selectedCategory(${listValue.id})"--%>
                   <%--id="categoryAnchor_${listValue.id}"--%>
                   <%--data-category-name="${listValue.name}" class="scrollToSection developerCategoryAnchore">${listValue.name}</a>--%>


                <%--<d:if test="${listValue.subcategory.size()>0}">--%>
                  <%--<ul class="dropdown-menu">--%>
                    <%--<d:forEach var="subcategory" items="${listValue.subcategory}">--%>
                      <%--<li><a--%>
                      <%--href="#newProductPartialRender"--%>
                      <%--categoryId="${subcategory.id}"--%>
                      <%--onclick="selectedCategory(${subcategory.id})"--%>
                      <%--id="categoryAnchor_${subcategory.id}"--%>
                      <%--data-category-name="${subcategory.name}" class="scrollToSection developerCategoryAnchore">${subcategory.name}</a>--%>

                      <%--</li>--%>
                    <%--</d:forEach>--%>
                  <%--</ul>--%>
                <%--</d:if>--%>

              <%--</li>--%>
            <%--</d:forEach>--%>

          <%--</ul>--%>
        <%--</li>--%>
      <%--</ul>--%>

      <%--<ul class="nav navbar-nav navbar-right main_navigation">--%>
        <%--<d:forEach var="cmsPage" items="${cmsPages}" >--%>
          <%--<li><a href="${BaseUrl}/static/${cmsPage.pageKey}">${cmsPage.pageName}</a></li>--%>
        <%--</d:forEach>--%>
        <%--&lt;%&ndash;<d:forEach var="listValueMenue" items="${category}">&ndash;%&gt;--%>
          <%--&lt;%&ndash;<li><a  >${listValueMenue.name}</a></li>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</d:forEach>&ndash;%&gt;--%>
      <%--</ul>--%>
    <%--</div><!-- /.navbar-collapse -->--%>
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

                  <li class="${dropdownSubCls}" >
                    <a
                       href="#newProductPartialRender"
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
                            <d:forEach var="subcategory" items="${listValue.subcategory}">
                              <li><a
                                      href="#newProductPartialRender"
                                      categoryId="${subcategory.id}"
                                      onclick="selectedCategory(${subcategory.id})"
                                      id="categoryAnchor_${subcategory.id}"
                                      data-category-name="${subcategory.name}" class="scrollToSection developerCategoryAnchore">${subcategory.name}</a>

                              </li>
                            </d:forEach>
                          </d:forEach>
                          </ul>
                        </div>
            </d:forEach>
          </div>
        </li>
      </ul>
      <ul class="nav navbar-nav navbar-right main_navigation">
        <d:forEach var="cmsPage" items="${cmsPages}" >
          <li><a href="${BaseUrl}/static/${cmsPage.pageKey}">${cmsPage.pageName}</a></li>
        </d:forEach>
      </ul>
    </div>
  </div><!-- /.container-fluid -->
</nav>
<script type="text/javascript">

</script>
