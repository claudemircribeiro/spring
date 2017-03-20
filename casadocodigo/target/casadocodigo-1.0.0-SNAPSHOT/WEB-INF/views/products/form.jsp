<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<meta charset="UTF-8">
<title>Cadastro de produtos</title>
<spring:url value="/resources/js/produto/produto.js" var="seuJs"></spring:url>

</head>
<body>
	
	<form:form action="${spring:mvcUrl('PC#save').build()}" method="post" commandName="product">
		<spring:hasBindErrors name="product">
			<ul>
				<c:forEach var="error" items="${errors.allErrors}">
				
					
					<li><spring:message message="${error}"  /></li>
				</c:forEach>
			</ul>
		</spring:hasBindErrors>
		<div>
		Boa noite ${userName}
		</div>
		<div>
			<label for="title">coisa</label> 
				<input name="coisa"  id="coisa" />
		</div>
			<div>
			<label for="title">Titulo</label> 
				<input name="title"  id="title" />
		</div>
		<div>
			<label for="capa">Capa</label> 
				<form:input path="capa" />
		</div>
		<div>
			<label for="description">Descrição</label>
			<form:textarea rows="10" cols="20" path="description" id="description" />

		</div>
		<div>
			<label for="pages">Número de paginas</label> <form:input type="text"
				path="pages" id="pages" />
		</div>
		<c:forEach items="${types}" var="bookType" varStatus="status">
			<div>
				<label for="price_${bookType}">${bookType}</label> 
				<form:input
					 path="prices[${status.index}].preco"/> 
					<input type="hidden"
					name="prices[${status.index}].bookType" value="${bookType}" />
			</div>
		</c:forEach>
		<div>
			<input type="submit" value="Enviar"> <a href="helloReport1"
				target="_blank" onclick="gerarRelatorio();">Clique aqui para
				gerar o relatório!</a>
		</div>
		<script type="text/javascript" src="${seuJs}"></script>
	</form:form>
</body>
</html>