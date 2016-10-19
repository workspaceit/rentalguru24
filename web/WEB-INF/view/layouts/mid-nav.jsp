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
        <div class="col-md-6 col-sm-6 col-xs-6">
          <div class="search-top">
            <input placeholder="Search" name="searchTxtBox" id="searchTxtBox" type="text" onkeypress="doSearch(event)" />
            <span><i class="fa fa-search"></i></span>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
