<#macro presentationDetails>
    <#include "../js/presentation-details.ftl"/>
    <div class="row">
        <div ng-controller="PresentationDetailsController" class="span11 offset3 panel">
            <div class="panel-body">
                -${providerContact.email}=
                -{{edit}}=
            </div>
        </div>
    </div>
</#macro>