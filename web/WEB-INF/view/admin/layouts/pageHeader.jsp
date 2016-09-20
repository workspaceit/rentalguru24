<%--
  Created by IntelliJ IDEA.
  User: omar
  Date: 9/19/16
  Time: 4:41 PM
  To change this template use File | Settings | File Templates.
--%>
<section class="content-header">
  <h1>
    ${pageHeader}
    <small>${pageSubHeader}</small>
  </h1>
  <ol class="breadcrumb">
    <li>
      <d:if test="${subMenu == null}">
        <a href="${BaseUrl}/${pageUrl}">
          <i class="fa fa-dashboard"></i>
            ${mainMenu}
        </a>
      </d:if>
      <d:if test="${subMenu != null}">
        <a href="javascript:void(0);">
          <i class="fa fa-dashboard"></i>
            ${mainMenu}
        </a>
      </d:if>
    </li>
    <d:if test="${subMenu != null}">
      <li>
        <a href="${BaseUrl}/${pageUrl}">
          ${subMenu}
        </a>
      </li>
    </d:if>
  </ol>
</section>

