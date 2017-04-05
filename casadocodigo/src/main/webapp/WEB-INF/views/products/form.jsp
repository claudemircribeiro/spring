<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<script>
			function submeteForm() {
				}

            function crunchifyAjax() {
            	/* $.getJSON( "list1", function( data ) {
            		  var items = [];
            		  $.each( data, function( key, val ) {
            		    items.push( "<li id='" + key.description + "'>" + val.description + "</li>" );
            		  });
            		 
            		  $( "<ul/>", {
            		    "class": "my-new-list",
            		    html: items.join( "" )
            		  }).appendTo( "body" );
            		}); */
            	$("#carregando").css("display", "inline");
            		$.ajax({
            			  url: "helloReport1",
            			  context: document.body
            			}).done(function() {
            				document.getElementById("form1").action="/casadocodigo/products/helloReportAtual";
            				 $("#carregando").css("display", "none");
            			    document.getElementById("form1").submit();
            			 // alert("feiot");
            			});
         
            }
           // $("#carregando").css("display", "none");
        </script>
<meta charset="UTF-8">
<title>Cadastro de produtos</title>
<spring:url value="/resources/js/produto/produto.js" var="produtoJS"></spring:url>

</head>
<body>
<form id="form1">
</form>

	<form:form action="${spring:mvcUrl('PC#save').build()}" method="post"
		commandName="product" id="formProduto">

		<script src="http://code.jquery.com/jquery-latest.js">   
        </script>
		<div id="carregando" style="display: none; color: green;">Carregando...
	
		<img alt="" src="<%=request.getContextPath() %>/resources/ajax-loading.gif" width="20%" height="20%">
		</div>
		<br>
		<div id="destino"></div>



		<input type="button" id="submit" value="Ajax Submit"
			onclick="crunchifyAjax()" />
		<br />
		<div id="result"></div>
		<spring:hasBindErrors name="product">
			<ul>
				<c:forEach var="error" items="${errors.allErrors}">


					<li><spring:message message="${error}" /></li>
				</c:forEach>
			</ul>
		</spring:hasBindErrors>

		<div>
			<label for="title">Titulo</label>
			<form:input path="title" id="title" />
		</div>
		<div>
			<label for="capa">Capa</label>
			<form:input path="capa" />
		</div>
		<div>
			<label for="description">Descrição</label>
			<form:textarea rows="10" cols="20" path="description"
				id="description" />

		</div>
		<div>
			<label for="pages">Número de paginas</label>
			<form:input type="text" path="pages" id="pages" />
		</div>
		<c:forEach items="${types}" var="bookType" varStatus="status">
			<div>
				<label for="price_${bookType}">${bookType}</label>
				<form:input path="prices[${status.index}].preco" />
				<input type="hidden" name="prices[${status.index}].bookType"
					value="${bookType}" />
			</div>
		</c:forEach>
		<div>
			<input type="button" value="Teste" onclick="crunchifyAjax()"> 
		</div>
		<div>
			<input type="submit" value="Salvar" > 
		</div>
		<script type="text/javascript" src="${produtoJS}"></script>
	</form:form>
</body>
</html>