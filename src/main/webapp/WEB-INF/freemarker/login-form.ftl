<!DOCTYPE html>
<html>
<head>
    <title></title>
</head>
<body>
<div class="page-header">
    <h1>Title</h1>
</div>
<#--<sec:authorize access="isAnonymous()">-->
    <div class="panel panel-default">
        <div class="panel-body">
            <#--<c:if test="${param.error eq 'bad_credentials'}">-->
                <#--<div class="alert alert-danger alert-dismissable">-->
                    <#--<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>-->
                    <#--<spring:message code="text.login.page.login.failed.error"/>-->
                <#--</div>-->
            <#--</c:if>      -->
            <form action="/login" method="POST" role="form">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="row">
                    <div id="form-group-email" class="form-group col-lg-4">
                        <label class="control-label" for="user-email">email:</label>
                        <input id="user-email" name="username" type="text" class="form-control"/>
                    </div>
                </div>

                <div class="row">
                    <div id="form-group-password" class="form-group col-lg-4">
                        <label class="control-label" for="user-password">password:</label>
                        <input id="user-password" name="password" type="password" class="form-control"/>
                    </div>
                </div>
                <div class="row">
                    <div class="form-group col-lg-4">
                        <button type="submit" class="btn btn-default">login</button>
                    </div>
                </div>
            </form>
            <div class="row">
                <div class="form-group col-lg-4">
                    <a href="/user/register">register</a>
                </div>
            </div>
        </div>
    </div>
    <div class="panel panel-default">
        <div class="panel-body">
            <h2>title</h2>
            <div class="row social-button-row">
                <div class="col-lg-4">
                    <a href="/auth/facebook"><button class="btn btn-facebook"><i class="icon-facebook"></i> | facebook login</button></a>
                </div>
            </div>
            <div class="row social-button-row">
                <div class="col-lg-4">
                    <a href="<c:url value="/auth/twitter"/>"><button class="btn btn-twitter"><i class="icon-twitter"></i> | twitter login</button></a>
                </div>
            </div>
        </div>
    </div>

</body>
</html>