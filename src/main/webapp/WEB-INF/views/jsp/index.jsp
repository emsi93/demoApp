<%@include file="includes/top.jsp" %>
            <div class="col-lg-1">
                <span><label><spring:message code='languages'/></label>
                <a href="javascript:changeLanguage('en');"><img src="/resources/images/en.png"/></a>
                <a href="javascript:changeLanguage('pl');"><img src="/resources/images/pl.png"/></a></span>
            </div>
             <div class="col-lg-3"></div>
            <div class="col-lg-4 box">
                <center><img src="/resources/images/user.png"></center>
                <div class="panel panel-warning box">
                  <div class="panel-heading">
                    <h4><label><spring:message code='login.title'/></label></h4>
                  </div>
                  <div class="panel-body">
                    <form role="form">
                      <label for="exampleInputEmail2"><spring:message code='login.username.email'/></label>
                      <div class="input-group">
                         <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                         <input type="email" class="form-control" id="exampleInputEmail2">
                      </div>
                      </br>
                      <label for="exampleInputPassword2"><spring:message code='login.password'/></label>
                      <div class="input-group">
                          <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                          <input type="password" class="form-control" id="exampleInputPassword2">
                      </div>
                      </br>
                      <button type="submit" class="btn btn-success"><spring:message code='login.button.send'/></button>
                      <span style="float:right"><a href=""><spring:message code='login.forgot.password'/></a></span>
                  </div>
                  <div class="panel-footer">
                    <spring:message code='login.new.user'/> <a href=""><spring:message code='login.create.account'/></a>
                  </div>
                </div>
            </div>
            <div class="col-lg-4"></div>
<%@include file="includes/bottom.jsp" %>