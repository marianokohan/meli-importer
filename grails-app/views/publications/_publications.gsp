<div>${currentTime}</div>
<g:if test="${session.meliUser == null }">
<div>
	<a href="${authorizationCodeUrl}">
		Login
	</a>
	<g:if test="${session.testUser != null }">
		- Test user: ${session.testUser.nickname} (${session.testUser.password})
	</g:if>
</div>
</g:if>
<g:else>
<div>
	User: ${session.meliUser.nickname}
	<g:if test="${session.testUser == null }">
		- <g:link action="testUser">Create Test User</g:link>
	</g:if>
	<g:if test="${session.testUser != null }">
		- Test user: ${session.testUser.nickname} (${session.testUser.password})
	</g:if>
</div>
<div>
	<h2>Publications</h2>
	<g:each in="${publications}">
	<!-- TODO publication(s) tag -->
		<div>
			<ul>
				<li><a href="${it.permalink}">${it.title}</a></li>
				<!-- TODO: validacion diferente -->
				<g:if test="${it.thumbnail != ""}">
					<img src="${it.thumbnail}"/>
				</g:if>
				<ul>
					<g:if test="${it.subtitle != null}">
						<li><i>${it.subtitle}</i></li>
					</g:if>
					<g:if test="${it.price > 0}">
						<li><b>$ </b>${it.price}</li>
					</g:if>
					<!-- TODO: i18n con pluralidad -->
					<li>${it.available_quantity} unidades disponibles</li>
				</ul>
			</ul>
		</div>
	</g:each>
</div>
</g:else>