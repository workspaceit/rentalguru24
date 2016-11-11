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
    <d:forEach var="breadcrumbList" items="${breadcrumb}">
      <li>
        <a href="${breadcrumbList.getValue()}">
            ${breadcrumbList.getKey()}
        </a>
      </li>
    </d:forEach>
  </ol>
</section>

