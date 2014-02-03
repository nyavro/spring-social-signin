<#macro categoryPanel>
    <#include "../js/category.ftl"/>
    <div class="row">
        <div ng-controller="CategoryController" class="span11 offset3 panel">
            <div class="panel-heading">
                <h3>Поиск по категории</h3>
            </div>
            <div class="panel-body">
                <ul id="category">
                    <li ng-repeat="category in categories">
                        <a ng-click="addCategoryFilter(category.id)">{{category.title}}</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</#macro>