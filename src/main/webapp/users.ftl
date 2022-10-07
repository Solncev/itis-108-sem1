<html lang="en">
<#include "base.ftl">

<#macro title>Users</#macro>

<#macro content>
    Hello,
    <br>
    <#list users as u>
        ${u.firstname} ${u.lastname} ${u.login}
        <br>
    </#list>!
</#macro>

</html>