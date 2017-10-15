<%@include file="../includes/top-login.jsp" %>
            <div class="row box">
                <div class="col-lg-4"></div>
                <div class="col-lg-4">
                    <center><img src="/resources/images/user.png"></center>

                    <c:if test = "${messageCode == 1}">
                                                      <div class="alert alert-success">
                                                          <spring:message code='login.account.activate'/>
                                                      </div>
                                                 </c:if>
                    <c:if test = "${messageCode == 2}">
                                                      <div class="alert alert-success">
                                                          <spring:message code='login.password.changed'/>
                                                      </div>
                                                 </c:if>

                    <div class="panel panel-warning box">
                      <div class="panel-heading">
                        <h4><label><spring:message code='login.title'/></label></h4>
                      </div>
                      <div class="panel-body">
                        <form action="/security/logIn" method="post">
                          <label for="login"><spring:message code='login.username'/></label>
                          <div class="input-group">
                             <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                             <input id="login" name = "login" type="text" class="form-control">
                          </div>
                          </br>
                          <label for="password"><spring:message code='login.password'/></label>
                          <div class="input-group">
                              <span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
                              <input id="password" name="password" type="password" class="form-control">
                          </div>
                          </br>
                          <input type="hidden" name="${_csrf.parameterName}"
                          									value="${_csrf.token}" />
                          <button type="submit" class="btn btn-success"><spring:message code='login.button.send'/></button>
                          <span style="float:right"><a href="/password/emailForm"><spring:message code='login.forgot.password'/></a></span>
                         </form>
                      </div>
                      <div class="panel-footer">
                        <spring:message code='login.new.user'/> <a href="/main/signup"><spring:message code='login.create.account'/></a>
                      </div>
                    </div>
                </div>
                <div class="col-lg-4"></div>
            </div>
            </br>
            </br>
            </br>
            </br>
            </br>
<%@include file="../includes/bottom-login.jsp" %>