<#macro suggestionDetails>
    <#include "../js/suggestion-details.ftl"/>
    <div class="row">
        <div ng-controller="SuggestionDetailsController" class="span11 offset3 panel">
            <div class="panel-body">
                <#---${providerContact.email}=-->
                <#---{{edit}}=-->
                <#--asdf-->
                <h3>${suggestion.title()}</h3>
                <div>${suggestion.fullDescription()}</div>
                <div ng-repeat="category in suggestion.category">
                    <div>{{category}}</div>
                </div>
            </div>
        </div>
    </div>
</#macro>