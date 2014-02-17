<#macro loginForm>
<div id="loginModal" class="modal hide fade" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-header">
        <#--<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>-->
        <#--<h3 id="templateModalLabel">Email template</h3>-->
        <h3>Вход</h3>
    </div>
    <div class="modal-body padding20px form">
        <form name="loginForm" action="j_spring_security_check" method="POST" id="form" class="kissmetrics-login">
            <input type="text"  name="j_username" value="" placeholder="Логин" autofocus/><br>
            <input type="password" name="j_password" value="" placeholder="Пароль"/>
            <div class="cf">
                <div class="left">
                    <input type="submit" class="btn btn-success btn-large" value="Войти">
                </div>
            </div>
        </form>
        <div class="row social-button-row">
            <div class="col-lg-4">
                <a href="/auth/facebook"><button class="btn btn-facebook"><i class="icon-facebook"></i> facebook login</button></a>
            </div>
        </div>

        <div class="row social-button-row">
            <div class="col-lg-4">
                <a href="/auth/vkontakte"><button class="btn btn-facebook"><i class="icon-vk"></i> vk login</button></a>
            </div>
        </div>
    </div>
    <#if error?? && Session.SPRING_SECURITY_LAST_EXCEPTION?? && Session.SPRING_SECURITY_LAST_EXCEPTION.message?has_content>
        <div style="padding-left: 15px">
            Ошибка: ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}<br/>
            Неверный логин или пароль
        </div>
    </#if>
</div>
</#macro>

