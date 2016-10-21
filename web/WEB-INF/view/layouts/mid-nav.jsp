<div class="container-fluid mid_nav">
  <div class="row">
    <div class="container">
      <div class="row">
        <div class="col-md-6 col-sm-6 col-xs-6">
          <ul class="mid_nav_logo_ul no-margin">
            <li class="logo_li">
              <a href="${BaseUrl}/home"><img src="<c:url value="/resources/img/logo.gif" />" ></a>
            </li>
          </ul>
        </div>
        <%--Search Box--%>
        <div class="col-md-6 col-sm-6 col-xs-12">
          <div class="search-top">
            <input placeholder="Search" class="search-per" name="searchTxtBox" id="searchTxtBox" type="text" onkeypress="doSearch(event)" />
            <span><i class="fa fa-search"></i></span>
            <select class="drop-cat-main">
              <option>All</option>
              <d:forEach var="listValue" items="${category}" >
                <option categoryId="${listValue.id}" value="${listValue.id}" class="category developerCategoryAnchore" id="subCategoryOf_${listValue.id}">${listValue.name}</option>
                <d:forEach var="subcategory" items="${listValue.subcategory}">
                  <option class="item" value="${subcategory.id}">&nbsp;&nbsp;&nbsp;&nbsp;${subcategory.name}</option>
                </d:forEach>
              </d:forEach>
            </select>

            <%--<option class="category" value="" disabled selected>Choose Category</option>--%>
            <%--<d:forEach var="listValue" items="${category}" >--%>
              <%--<option categoryId="${listValue.id}" value="${listValue.id}" class="category developerCategoryAnchore" id="subCategoryOf_${listValue.id}">${listValue.name}</option>--%>
              <%--<d:forEach var="subcategory" items="${listValue.subcategory}">--%>
                <%--<option class="item" value="${subcategory.id}">${subcategory.name}</option>--%>
              <%--</d:forEach>--%>
            <%--</d:forEach>--%>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
