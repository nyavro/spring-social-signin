<#assign baseUrl><@spring.url ""/></#assign>
<#import "login.ftl" as login/>
<#macro mainMenu>
    <@login.loginForm/>
    <script type="text/javascript">
        function MenuController($scope, $http) {
            $scope.onLogin = function() {
                $('#loginModal').modal('show');
            }
            <#if error??>
                $scope.onLogin();
            </#if>
        }
    </script>
    <div class='navbar navbar-fixed-top' ng-controller="MenuController">
        <div class='navbar-inner'>
            <div class="container">
                <div class='nav-collapse'>
                    <ul class="nav pull-left">
                        <li><a href="${baseUrl}/" title="Главная">Главная</a></li>
                        <#if principal?? && principal.isAuthorized()>
                            <li><a href="${baseUrl}/suggestion/" title="Личный кабинет">Личный кабинет [${principal.getUsername()}]</a></li>
                        <#else>
                            <li><a href="${baseUrl}/user/register" title="Регистрация">Регистрация</a></li>
                        </#if>
                        <li><a href="" title="Найти исполнителя">Найти исполнителя</a></li>
                        <li><a href="" title="О проекте">О проекте</a></li>
                    </ul>
                </div>
                <span class="rightinfo pull-right">
                    <div class='nav-collapse'>
                        <#if principal?? && principal.isAuthorized()>
                            <form name="logoutForm" class="form-inline" style="margin:0; display: inline;" action='<@spring.url"/logout"/>' method="POST">
                                <a href="" onclick="document.forms['logoutForm'].submit();">Выйти</a>
                            </form>
                        <#else>
                            <a title="Вход" ng-click="onLogin()">Войти</a>
                        </#if>
                    </div>
                </span>
            </div>
        </div>
    </div>
    <br/>
    <br/>
</#macro>