<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="pt.ecl.Html" %>
<% String base = request.getContextPath(); %>
<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="UTF-8">
<title>Pedido de avaria registado</title>
<link rel="stylesheet" href="<%= base %>/estilo.css">
</head>
<body>
<a href="<%= base %>/">Página inicial</a>
<h1>Confirmação do pedido</h1>

<p>Sala: <strong><%= Html.escapar(request.getAttribute("sala")) %></strong></p>
<p>Tipo de avaria: <strong><%= Html.escapar(request.getAttribute("tipo")) %></strong></p>
<p class="descricao">Descrição: <%= Html.escapar(request.getAttribute("descricao")) %></p>

<%-- Substituir pela mensagem exata do enunciado, se for diferente. --%>
<p class="sucesso">Pedido de avaria registado com sucesso.</p>
</body>
</html>
