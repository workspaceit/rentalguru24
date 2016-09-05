<div class="container-fluid top_nav">
  <div class="row">
    <div class="container">
      <div class="row">
        <div class="col-md-6 col-sm-6 col-xs-5">
          <ul class="top_nav_ul">
            <li class="dropdown">
              <a href="#" class="dropdown-toggle top_nav_a" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">English <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
              </ul>
            </li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle top_nav_a" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Help <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="#">Action</a></li>
              </ul>
            </li>
          </ul>
        </div>

        <div class="col-md-6 col-sm-6 col-xs-7 ">
          <ul class="top_nav_ul right top_nav_ul">
            <d:if test="${IsLogIn != true}">
              <li class="dropdown">
                <a href="${BaseUrl}/signin" class="dropdown-toggle top_nav_a"
                   aria-expanded="false">
                  <i class="fa fa-user"></i>Login
                </a>
              </li>
              <li class="dropdown">
                <a href="${BaseUrl}/signup" class="dropdown-toggle top_nav_a"
                   aria-expanded="false">
                  <i class="fa fa-lock"></i>Register
                </a>
              </li>
            </d:if>
            <d:if test="${IsLogIn != false}">
              <li class="dropdown">
                <a href="${BaseUrl}/user/dashboard" class="dropdown-toggle top_nav_a"
                   aria-expanded="false">
                  <i class="fa fa-user"></i>${appCredential.getUserInf().getFirstName()}
                </a>
              </li>
              <li class="dropdown">
                <a href="javascript:void(0);" onclick="signout()" class="dropdown-toggle top_nav_a"
                   aria-expanded="false">
                  <i class="fa fa-sign-out "></i>Signout
                </a>
              </li>
            </d:if>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div>

