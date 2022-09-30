<html lang="en">
<#include "base.ftl">

<#macro title>Users</#macro>

<#macro content>
    Hello,
    <br>
    <#list users as u>
        ${u.firstname} ${u.lastname}
        <br>
    </#list>!
</#macro>

</html>