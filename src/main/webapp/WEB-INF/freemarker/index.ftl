<#import "/spring.ftl" as spring/>
<#assign baseUrl><@spring.url ""/></#assign>
<#import "commons.ftl" as commons/>
<#import "components/menu.ftl" as menu/>
<#import "components/category.ftl" as category/>
<#import "components/suggestions.ftl" as suggestions/>
<#import "js/search.ftl" as search/>

<!doctype html>
<html ng-app="app">
    <@commons.head/>
    <body>
        <@search.service/>
        <@menu.mainMenu/>
        <@category.categoryPanel/>
        <@suggestions.list/>
    </body>
</html>