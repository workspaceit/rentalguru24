<%--<div class="col-md-8 col-sm-8 col-xs-12 top-seach-bar">--%>

  <%--<div class="search-category col-md-4 col-md-offset-2">--%>
    <%--<select class="form-control" id="categorySelectedInSearch">--%>
      <%--<option class="category" value="">All</option>--%>
      <%--<d:forEach var="listValue" items="${category}" >--%>
        <%--<option categoryId="${listValue.id}" value="${listValue.id}" <d:if test="${selectedCategoryId == listValue.id}">selected</d:if> class="category developerCategoryAnchore" id="subCategoryOf_${listValue.id}">${listValue.name}</option>--%>
        <%--<d:forEach var="subcategory" items="${listValue.subcategory}">--%>
          <%--<option class="item" value="${subcategory.id}" <d:if test="${selectedCategoryId == subcategory.id}">selected</d:if> >&nbsp;&nbsp;&nbsp;&nbsp;${subcategory.name}</option>--%>
        <%--</d:forEach>--%>
      <%--</d:forEach>--%>
    <%--</select>--%>
  <%--</div>--%>
  <%--<div class="search-top col-md-6">--%>
    <%--<input placeholder="Search" class="search-per" name="searchTxtBox" id="searchTxtBox" type="text" onkeypress="doSearch(event)"--%>
           <%--<d:if test="${param.title != null}">value="${param.title}"</d:if>/>--%>
    <%--<span><i class="fa fa-search"></i></span>--%>
  <%--</div>--%>

<%--</div>--%>
<div class="col-md-2 top-seach-bar area-filter">
  <button class="btn btn-area" data-toggle="modal" data-target="#areaFilter"><i class="fa fa-map-marker"></i><span>Choose Area</span></button>
</div>
<!-- modal -->
<!-- Modal -->
<div class="modal fade" id="areaFilter" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
  <div class="modal-dialog modal-sm" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        <h4 class="modal-title" id="myModalLabel">Choose an Area</h4>
      </div>
      <div class="modal-body">
        <ul class="area-list">
          <d:forEach var="usState" items="${stateList}" >
            <li><a href="#">${usState.name}</a></li>
          </d:forEach>
        </ul>
      </div>
      <!-- <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Save changes</button>
      </div> -->
    </div>
  </div>
</div>
<div class="col-md-6 col-sm-8 col-xs-12 top-seach-bar pull-right ">
  <div class="input-group">
    <div class="input-group-btn search-panel">
      <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
        <span id="search_concept"></span> <span class="caret"></span>
      </button>
      <ul class="dropdown-menu" role="menu">
        <d:forEach var="listValue" items="${category}" >
          <li><a href="#${listValue.name}" categoryId="${listValue.id}" value="${listValue.id}" <d:if test="${selectedCategoryId == listValue.id}">selected</d:if> id="subCategoryOf_${listValue.id}">${listValue.name}</a></li>
          <d:forEach var="subcategory" items="${listValue.subcategory}">
            <li>
              <a href="#${subcategory.name}" categoryId="${subcategory.id}" class="item" value="${subcategory.id}" <d:if test="${selectedCategoryId == subcategory.id}">selected</d:if> >${subcategory.name}</a>
             <li>
          </d:forEach>
        </d:forEach>
      </ul>
    </div>
    <input type="hidden" name="search_param" value="all" id="search_param">
    <input type="text" class="form-control" name="x" placeholder="Search " id="searchTxtBox" type="text" onkeypress="doSearch(event)" <d:if test="${param.title != null}">value="${param.title}"</d:if> >

    <span class="input-group-btn">
        <button class="btn btn-default" type="button" onclick="doSearchByClick()"><i class="fa fa-search"></i></button>
    </span>
    <input type="hidden" id="categorySelectedInSearch" value="" />
  </div>
</div>