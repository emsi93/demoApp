<%@include file="../includes/top.jsp" %>
<div class="row error-top">
    <div class="col-lg-1"></div>
    <div class="col-lg-10">
        <c:if test="${messageCode == 1}">
            <div class="alert alert-danger">
                <spring:message code='change.password.message.error'/>
            </div>
        </c:if>
        <c:if test="${messageCode == 2}">
            <div class="alert alert-success">
                <spring:message code='change.password.message.success'/>
            </div>
        </c:if>
        <form:form method="post" modelAttribute="emailForm" action="/password/emailForm" role="form">
            <div class="form-group">
                <label for="email"><spring:message code='change.password.email'/></label>
                <form:input id="email" name="email" type="email" path="email" class="form-control"/>
                <div class="errors">
                    <span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span>
                    <form:errors path="email" element="div"/>
                </div>
            </div>
            <spring:message code='change.password.button' var="changePasswordButton"/>
            <form:input class="submit btn btn-success" path="" type="submit"
                        value="${changePasswordButton}" id="changePassword"></form:input>
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