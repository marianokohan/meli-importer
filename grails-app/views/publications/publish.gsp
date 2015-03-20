<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>Publish</title>
</head>
<body>
	<g:form url="[action:'publishToMeLi']">
		<div>
			<!-- TODO: i18n for labels ? -->
			<label>Titulo: </label>
			<g:textField name="title"/>
		</div>
		<!-- TODO: used field -->
		<!-- div>
			<label>Subtitulo: </label>
			<g:textField name="subtitle"/>
		</div-->
		<div>
			<label>Descripcion: </label>
			<g:textField name="description"/>
		</div>
		<div>
			<label>Cantidad: </label>
			<g:textField name="available_quantity" value="1"/><!-- for the fixed category -->
		</div>
		<div>
			<label>Precio: $</label>
			<g:textField name="price"/>
		</div>
		<div>
			<!-- TODO: +1 imagen -->
			<label>Imagen: </label>
			<g:textField name="pictures[0].source"/>
		</div>
		<div>
			<label>Garantía: </label>
			<g:textField name="warranty" value="${myValue}"/>
		</div>
		
		<div>
			<!-- TODO: campos de valores fijos => selección ? -->
			<label>Categoría</label>
			<g:textField name="category_id" value="MLA86029"/>
		</div>
		<div>
			<label>Moneda</label>
			<g:textField name="currency_id" value="ARS"/>
		</div>
		<div>
			<label>Modo de compra:</label>
			<g:textField name="buying_mode" value="buy_it_now"/>
		</div>
		<div>
			<label>Tipo de listado: </label>
			<g:textField name="listing_type_id" value="free"/>
		</div>
		<div>
			<label>Estado: </label>
			<g:textField name="condition" value="new"/>
		</div>
		
		<!-- TODO (sig. paso): identificar mapeos de productos existentes => mostrar solo valor + completado de los otros -->
		<g:submitButton name="publish" value="Publish" />
	</g:form>
</body>
</html>