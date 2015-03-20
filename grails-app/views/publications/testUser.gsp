
<html>
	<head>
		<title>Test User</title>
	</head>
	<body>
		<h1>Test user data</h1>
		<table>
			<tr>
				<th>id</th>
				<td>${session.testUser.id}</td>
			</tr>
			<tr>
				<th>nickname</th>
				<td>${session.testUser.nickname}</td>
			</tr>
			<tr>
				<th>password</th>
				<td>${session.testUser.password}</td>
			</tr>
		</table>
		<a href="${authorizationCodeUrl}">
			Login
		</a>
	</body>
</html>