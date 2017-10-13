<%@include file="../includes/top.jsp" %>
        <div class="col-lg-1"></div>
        <div class="col-lg-10">
        <c:if test = "${messageCode == 1}">
                                <div class="alert alert-danger">
                                   <spring:message code='change.password.message.error'/>
                                </div>
                            </c:if>
         <c:if test = "${messageCode == 2}">
                                  <div class="alert alert-success">
                                      <spring:message code='change.password.success'/>
                                  </div>
                             </c:if>

                          <form:form method="post" modelAttribute="passwordsForm" action="${url}" role="form">
                                                  <div class="form-group">
                                                      <label for="password"><spring:message code='change.password'/></label>
                                                      <form:input id="password" name="password" type="password" path="password" class="form-control"/>
                                                     <div class="errors">
                                                         <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                                                         <form:errors path="password" element="div" />
                                                      </div>
                                                  </div>
                                                  <div class="form-group">
                                                       <label for="password2"><spring:message code='change.confirm.password'/></label>
                                                       <form:input id="password2" name="password2" type="password" path="password2" class="form-control"/>
                                                       <div class="errors">
                                                            <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                                                            <form:errors path="password2" element="div" />
                                                       </div>
                                                   </div>
                                                  </br>
                                                  <div class="g-recaptcha" data-sitekey="${recaptchaSiteKey}"></div>
                                                  <spring:message code='change.password.button' var="changePasswordButton"/>
                                                  <form:input class="submit btn btn-success" path="" type="submit"
                                                               								value="${changePasswordButton}" id="register"></form:input>
                                              </form:form>

                    </div>
                    <div class="col-lg-1"></div>
                        </div>
                        </br>
                        </br>
                        </br>
                        </br>
                        </br>
                        </br>
                        </br>
                        </br>
                        </br>
                        </br>
                        </br>
                        </br>
                        </br>
                        </br>
                        </br>
                        </br>
                        </br>
                        </br>
                        </br>
                    <%@include file="../includes/bottom.jsp" %>