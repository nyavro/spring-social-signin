<#assign baseUrl><@spring.url ""/></#assign>
<#macro list>
    <#include "../js/presentations.ftl"/>
    <div ng-controller="PresentationListController">
        <div class="container">
            <div class="presentation-list-item" ng-repeat="presentation in presentations">
                <h2>
                    <a href="${baseUrl}/presentation/view/{{presentation.id}}">
                        <img src="../../img/account.png"/>
                    </a>
                    <a class="presentation-title" href="${baseUrl}/presentation/view/{{presentation.id}}" >
                        <span>{{presentation.title}}</span>
                    </a>
                </h2>
                <a href="${baseUrl}/presentation/view/{{presentation.id}}" class="short-description">
                    <p>{{presentation.shortDescription}}</p>
                </a>
                <p class="presentation-date">Опубликовано: {{formatDate(presentation.published)}}</p>
            </div>
        </div>
    </div>
    <#--<script type="text/javascript" src="../../js/presentations.js"></script>-->
</#macro>