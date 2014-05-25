<#assign baseUrl><@spring.url ""/></#assign>
<#macro list>
    <#include "../js/suggestions.ftl"/>
    <div ng-controller="SuggestionsListController">
        <div class="container">
            <div class="suggestion-list-item" ng-repeat="suggestion in suggestions">
                <h2>
                    <a href="${baseUrl}/service/view/{{suggestion.id}}">
                        <img src="../../img/account.png"/>
                    </a>
                    <a class="suggestion-title" href="${baseUrl}/service/view/{{suggestion.id}}" >
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
</#macro>