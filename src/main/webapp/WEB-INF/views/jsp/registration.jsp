<%@include file="includes/top-login.jsp" %>
    <div class="row box">
        <div class="col-lg-4"></div>
        <div class="col-lg-4">
        <c:if test = "${messageCode == 1}">
            <div class="alert alert-danger">
               <spring:message code='registration.message.error'/>
            </div>
        </c:if>
        <c:if test = "${messageCode == 2}">
             <div class="alert alert-success">
                 <spring:message code='registration.message.success'/>
             </div>
        </c:if>
          <div class="panel panel-warning box">
                <div class="panel-heading">
                    <h4><label><spring:message code='registration.title'/></label></h4>
                </div>
                <div class="panel-body">
                    <form:form method="post" modelAttribute="userForm" action="/main/signup" role="form">
                        <div class="form-group">
                            <label for="login"><spring:message code='registration.username'/></label>
                            <form:input id="login" name = "login" type="text" path="login" class="form-control"/>
                        </div>
                        <div class="form-group">
                            <label for="email"><spring:message code='registration.email'/></label>
                            <form:input id="email" name = "email" type="text" path="email" class="form-control"/>
                        </div>
                        </br>
                        <div class="form-group">
                            <label for="password"><spring:message code='registration.password'/></label>
                            <form:input id="password" name="password" type="password" path="password" class="form-control"/>
                        </div>
                        </br>
                        <spring:message code='registration.create.an.account.button' var="createButton"/>
                        <form:input class="submit btn btn-success" path="" type="submit"
                                     								value="${createButton}" id="register"></form:input>
                    </form:form>
                </div>
				<div class="panel-footer">
					<spring:message code='login.new.user'/> <a href="/main/"><spring:message code='registration.return'/></a>
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
<%@include file="includes/bottom-login.jsp" %>