<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page import="java.util.List,pt.ecl.Pedido,pt.ecl.Html" %>
<%
List<Pedido> pedidos =
(List<Pedido>) request.getAttribute("pedidos");
String base = request.getContextPath();
Object mensagem = session.getAttribute("mensagem");
session.removeAttribute("mensagem");
%>
<!DOCTYPE html>
<html lang="pt">
<head>
<meta charset="UTF-8">
<title>Pedidos de intervenção</title>
<link rel="stylesheet" href="<%= base %>/estilo.css">
</head>
<body>
<a href="<%= base %>/">Página inicial</a>
<h1>Pedidos de intervenção informática</h1>
<p class="erro"><%= Html.escapar(request.getAttribute("erro")) %></p>
<p class="sucesso"><%= Html.escapar(mensagem) %></p>
<h2>Comunicar um problema</h2>
<form method="post" action="<%= base %>/pedidos">
<input type="hidden" name="acao" value="criar">
<label>Nome do requerente
<input name="nome" required
value="<%= Html.escapar(request.getParameter("nome")) %>">
</label>
<label>Sala
<input name="sala" required placeholder="Exemplo: Pólux"
value="<%= Html.escapar(request.getParameter("sala")) %>">
</label>
<label>Equipamento ou serviço
<input name="equipamento" required placeholder="Exemplo: PC-07"
value="<%= Html.escapar(request.getParameter("equipamento")) %>">
</label>
<label>Descrição do problema
<textarea name="descricao" required minlength="20"
><%= Html.escapar(request.getParameter("descricao")) %></textarea>
</label>
<button type="submit">Enviar pedido</button>
</form>
<h2>Acompanhamento</h2>
<p>Total de pedidos: <%= pedidos.size() %></p>
<% if (pedidos.isEmpty()) { %>
<p>Ainda não existem pedidos.</p>
<% } %>
<% for (Pedido p : pedidos) { %>
<article>
<h3>Pedido <%= p.id %> — <%= Html.escapar(p.sala) %></h3>
<p>Registo: <%= Html.escapar(p.data) %></p>
<p>Requerente: <%= Html.escapar(p.nome) %></p>
<p>Equipamento: <%= Html.escapar(p.equipamento) %></p>
<p class="descricao"><%= Html.escapar(p.descricao) %></p>
<p>Estado: <strong><%= Html.escapar(p.estado) %></strong></p>
<form method="post" action="<%= base %>/pedidos">
<input type="hidden" name="acao" value="atualizar">
<input type="hidden" name="id" value="<%= p.id %>">
<label>Estado
<select name="estado">
<% for (String e : List.of("Por analisar",
"Em intervenção", "A aguardar", "Resolvido")) { %>
<option <%= e.equals(p.estado) ? "selected" : "" %>
><%= Html.escapar(e) %></option>
<% } %>
</select>
</label>
<label>Observação técnica ou solução
<textarea name="observacao"
><%= Html.escapar(p.observacao) %></textarea>
</label>
<button type="submit">Guardar acompanhamento</button>
</form>
</article>
<% } %>
</body>
</html>