/**
 * 
 */

function gerarRelatorio() {
	document.getElementById("form1").action="/casadocodigo/produtos/helloReportAtual";
    document.getElementById("form1").submit();
    alert();
    $("#carregando").css("display", "none");

	
}