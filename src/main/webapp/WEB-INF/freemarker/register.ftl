<#import "/spring.ftl" as spring/>
<#import "components/menu.ftl" as menu/>
<#import "commons.ftl" as commons/>

<#assign baseUrl><@spring.url ""/></#assign>
<#if edit?? && edit>
    <#assign actionUrl><@spring.url "/user/edit/${user.id!}"/></#assign>
<#else>
    <#assign actionUrl><@spring.url "/user/create"/></#assign>
</#if>

<!doctype html>
<html ng-app id="register">
    <@commons.head/>
    <body>
        <@menu.mainMenu/>
        <#include "js/register.ftl"/>
        <div ng-controller="RegistrationController">
            <form class="well form-horizontal" name='registrationForm' action='${actionUrl}' method='post'>
                <div>
                    <div class="control-group">
                        <label class="control-label" for="inputLogin">Логин</label>
                        <div class="controls">
                            <input type="text" id="inputLogin" ng-model="user.login" name="login">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputPassword">Пароль</label>
                        <div class="controls">
                            <input type="password" id="inputPassword" name="password" ng-model="user.password">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputEmail">Email</label>
                        <div class="controls">
                            <input type="text" id="inputEmail" ng-model="user.email" name="email">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputEmailConfirm">Подтверждение Email</label>
                        <div class="controls">
                            <input type="text" id="inputEmailConfirm" placeholder="Подтверждение email">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="inputEmail">Кто вы</label>
                        <div class="controls">
                            <label class="radio">
                                <input type="radio" id="optionsRadios1" ng-value="true" ng-model="user.private" name="private">
                                    Частное лицо
                            </label>
                            <label class="radio">
                                <input type="radio" id="optionsRadios2" ng-value="false" ng-model="user.private" name="private">
                                    Компания
                            </label>
                        </div>
                    </div>
                    <div class="control-group" ng-show="!user.private">
                        <label class="control-label" for="company">Наименование организации</label>
                        <div class="controls">
                            <input type="text" id="company" name="company">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="lastname">Фамилия {{user.private && " " || "представителя"}}</label>
                        <div class="controls">
                            <input type="text" id="lastname" ng-model="user.last" name="last">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="name">Имя {{user.private && " " || "представителя"}}</label>
                        <div class="controls">
                            <input type="text" id="name" ng-model="user.first" name="first">
                        </div>
                    </div>
                    <div class="control-group">
                        <label class="control-label" for="city">Город</label>
                        <div class="controls">
                            <input type="text" name="city" ng-model="user.city" ng-show="false"/>
                            <select id="city" ng-model="user.city" ng-options="v.name as v.name for v in cities"/>
                        </div>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <input type="hidden" name="saveAndRegister" value="false"/>
                    </div>
                </div>
                <div class="control-group">
                    <div class="controls">
                        <button class='btn min-width btn-success btn-primary' type='submit'>Зарегистрироваться</button>
                    </div>
                </div>
            </form>
            <script type="text/javascript" src="../../js/angular/1.0.6/angular.min.js"></script>
            <script type="text/javascript" src="../../js/jquery/1.9.1/jquery.min.js"></script>
            <script type="text/javascript" src="../../js/bootstrap/bootstrap.min.js"></script>
        </div>
    </body>
</html>