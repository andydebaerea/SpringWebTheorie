<%@taglib prefix='c' uri='http://java.sun.com/jsp/jstl/core'%>
<c:url var='nederlandsURL' value=''>
	<c:param name='locale' value='nl_BE' />
</c:url>
<c:url var='engelsURL' value=''>
	<c:param name='locale' value='en_US' />
</c:url>
<ul class='zonderbolletjes'>
	<li><a href='${nederlandsURL}'>Nederlands</a></li>
	<li><a href='${engelsURL}'>English</a></li>
</ul>