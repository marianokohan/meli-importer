<!DOCTYPE html>
<html>
<head>
	<meta charset="US-ASCII">
	<title>Publish</title>
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
	<g:javascript library='jquery' />
	<r:layoutResources />
</head>
<body>
	<g:form name="publishForm" url="[action:'publish']">
		<fieldset>
			<div>
				<!-- TODO: i18n for labels ? -->
				<label>Titulo: </label>
				<span>${session.item.title}</span>
			</div>
			<!-- TODO: used field ?-->
			<!-- div>
				<label>Subtitulo: </label>
				<g:textField name="subtitle"/>
			</div-->
			<div>
			<g:each in="${session.item.pictures}">
				<img src="${it.source}" higth="250" width="250" />
			</g:each>			
			</div>
			<div>
				<label>Descripcion: </label>
				<div class="itemDescription">${session.item.description}</div>
			</div>
			<div>
				<label>Precio: $</label>
				<span>${session.item.price}</span>
			</div>
			<hr/>
			<div>
				<label>Cantidad: </label>
				<g:textField name="available_quantity" value="${session.item.available_quantity}"/>
			</div>
			<div>
				<label>Garantía: </label>
				<g:textField name="warranty" value="${session.item.warranty}"/>
			</div>
			<!-- TODO seleccion de los siguientes campos (si aplica)  -->
			<div>
				<label>Estado: </label>
				<span>Nuevo</span>
				<!-- label en base a valor - session.item.condition  -->
			</div>
			<div>
				<label>Modo de compra:</label>
				<span>Inmediata</span>
				<!-- label en base a valor - session.item.buying_mode  -->
			</div>
			<div>
				<label>Tipo de listado: </label>
				<g:select name="listing_type_id" value="${session.item.listing_type_id}"
					from="${meLiService.getListingTypes()}"
					optionValue="name" optionKey="id"/>
			</div>
			<div>
				<label>Categoría</label>
				<div class="categories">
		            <g:select id="category_1" name="category_id.1" from="${meLiService.getCategories()}" 
			            optionKey="id" optionValue="name"
	                    onchange="categoryChanged(this.value, 2);"/>
	                <br/>
	                <div id="container_Category_2"></div>
                </div>
                <script>
			         function categoryChanged(categoryId, level) {
				         //implemented directly instead of using remoteFunction to allow use dinamic id for the span to update
			             jQuery.ajax({type:'POST',data:'categoryId='+categoryId+'&level='+level, url:'/meliImporter/publish/categoryChanged',
				             success:function(data,textStatus){jQuery('#container_Category_'+level).html(data);},
				             error:function(XMLHttpRequest,textStatus,errorThrown){}});
			         }
			     </script>
			
			</div>
			
		</fieldset>
	</g:form>
	<r:layoutResources />
</body>
</html>