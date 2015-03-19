<html>
	<head>
		<title>MeLi Product Importer</title>
	</head>
	<body>
		<div>${currentTime}</div>
		<g:if test="${session.meliUser == null }">
		<div>
			<a href="${authorizationCodeUrl}">
				Login
			</a>
		</div>
		</g:if>
		<g:else>
		<div>
			User: ${session.meliUser.nickname}
		</div>
		</g:else>
	</body>
</html>