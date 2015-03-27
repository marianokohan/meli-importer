<div>
	<h2>Products</h2>
	<g:each in="${products}">
	<!-- TODO product(s) tag -->
		<div>
			<h4>${it.name}</h4>
			<div>${it.description}</div>
			<ul>
				<li><b>$ </b>${it.price}</li>
				<!-- TODO: i18n con pluralidad -->
				<g:if test="${it.stock > 0}">
					<li>${it.stock} unidades disponibles</li>
				</g:if>
				<!-- TODO: como pasar objeto product como parametro (¿es posible?) ? -->
				<g:if test="${session.meliUser != null }">
					<g:link action="publish" params="[productId: it.prestaShopId]">Publish</g:link>
				</g:if>					
			</ul>
		</div>
	</g:each>
</div>