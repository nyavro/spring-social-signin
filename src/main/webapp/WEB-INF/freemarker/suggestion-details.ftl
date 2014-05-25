<#import "/spring.ftl" as spring/>
<#assign baseUrl><@spring.url ""/></#assign>
<#import "commons.ftl" as commons/>
<#import "components/menu.ftl" as menu/>
<#import "components/suggestion-details.ftl" as details/>

<!doctype html>
<html ng-app="app">
<@commons.head/>
<body>
        <@menu.mainMenu/>
        <@details.suggestionDetails/>
</body>
</html>