<#assign baseUrl><@spring.url ""/></#assign>
<#macro list>
    <#include "../js/presentations.ftl"/>
    <div ng-controller="PresentationListController">
        <div class="container">
            <div class="suggestion-list-item" ng-repeat="suggestion in presentations">
                <h2>
                    <a href="${baseUrl}/service/view/{{suggestion.id}}">
                        <img src="../../img/account.png"/>
                    </a>
                    <a class="suggestion-title" href="${baseUrl}/suggestion/view/{{suggestion.id}}" >
                        <span>{{suggestion.title}}</span>
                    </a>
                </h2>
                <a href="${baseUrl}/service/view/{{suggestion.id}}" class="short-description">
                    <p>{{suggestion.shortDescription}}</p>
                </a>
                <p class="suggestion-date">Опубликовано: {{formatDate(suggestion.published)}}</p>
            </div>
        </div>
    </div>
    <#--<script type="text/javascript" src="../../js/presentations.js"></script>-->
</#macro>