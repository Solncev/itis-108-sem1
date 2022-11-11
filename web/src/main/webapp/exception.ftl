<html lang="en">
<#include "base.ftl">

<#macro title>Exception details</#macro>

<#macro content>
    <h1>Exception details: </h1><br>
    <strong>Status code: </strong>${statusCode}<br>
    <strong>URI: </strong>${uri}<br>
    <#if message??><strong>Message: </strong>${message}<br></#if>
</#macro>

</html>