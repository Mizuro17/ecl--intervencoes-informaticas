<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="pt.ecl.Html" %>
<%
String base = request.getContextPath();
// Na primeira visita (GET) os atributos não existem: Html.escapar(null) devolve "".
Object tipo = request.getAttribute("tipo");
%>
<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="UTF-8">
<title>Pedido de avaria</title>
<link rel="stylesheet" href="<%= base %>/estilo.css">
</head>
<body>
<a href="<%= base %>/">Página inicial</a>
<h1>Pedido de avaria</h1>

<form method="post" action="<%= base %>/avaria">

<label>Sala
<input name="sala" required placeholder="Exemplo: B12"
value="<%= Html.escapar(request.getAttribute("sala")) %>">
</label>
<p class="erro"><%= Html.escapar(request.getAttribute("erroSala")) %></p>

<label>Tipo de avaria
<select name="tipo" required>
<option value="">-- Escolha --</option>
<% for (String t : java.util.List.of("Hardware", "Software", "Rede")) { %>
<option value="<%= t %>" <%= t.equals(tipo) ? "selected" : "" %>><%= t %></option>
<% } %>
</select>
</label>
<p class="erro"><%= Html.escapar(request.getAttribute("erroTipo")) %></p>

<label>Descrição
<textarea name="descricao" required minlength="10"
><%= Html.escapar(request.getAttribute("descricao")) %></textarea>
</label>
<p class="erro"><%= Html.escapar(request.getAttribute("erroDescricao")) %></p>

<button type="submit">Enviar pedido</button>
</form>
</body>
</html>
