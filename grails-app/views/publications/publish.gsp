<!DOCTYPE html>
<html>
<head>
<meta charset="US-ASCII">
<title>Publish</title>
<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
</head>
<body>
	<g:form url="[action:'publishToMeLi']">
		<g:hiddenField name="productId" value="${product.prestaShopId}"/>
		<fieldset>
			<div>
				<!-- TODO: i18n for labels ? -->
				<label>Titulo: </label>
				<span>${product.name}</span>
			</div>
			<!-- TODO: used field ?-->
			<!-- div>
				<label>Subtitulo: </label>
				<g:textField name="subtitle"/>
			</div-->
			<div>
				<label>Descripcion: </label>
				<div class="itemDescription">${product.description}</div>
			</div>
			<div>
				<!-- TODO imagenes  -->
				<label>Imagen: </label>
				<g:textField name="pictures[0].source"/>
			</div>
			<div>
				<label>Precio: $</label>
				<span>${product.price}</span>
			</div>
			<hr/>
			<!-- TODO valor init de stock  -->
			<div>
				<label>Cantidad: </label>
				<g:textField name="available_quantity" value="1"/><!-- for the fixed category -->
			</div>
			<div>
				<label>Garantía: </label>
				<g:textField name="warranty"/>
			</div>
			<!-- TODO seleccion de los siguientes campos (si aplica)  -->
			<div>
				<label>Moneda</label>
				<g:textField name="currency_id" value="ARS"/>
			</div>
			<div>
				<label>Estado: </label>
				<g:textField name="condition" value="new"/>
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
				<!-- TODO: campos de valores fijos => selección ? -->
				<label>Categoría</label>
				<g:textField name="category_id" value="MLA86029"/>
			</div>
			
			<g:submitButton name="publish" value="Publish" />
		</fieldset>
	</g:form>
</body>
</html>