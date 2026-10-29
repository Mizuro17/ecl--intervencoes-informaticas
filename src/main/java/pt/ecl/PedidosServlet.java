package pt.ecl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

// O web.xml associa este Servlet ao endereço /pedidos.

public class PedidosServlet extends HttpServlet {
    private final Repositorio repositorio = new Repositorio();
    private final List<String> estados = List.of(
    "Por analisar", "Em intervenção", "A aguardar", "Resolvido");

    private String campo(HttpServletRequest req, String nome) {
        String valor = req.getParameter(nome);
        return valor == null ? "" : valor.trim();
    }
@Override
    protected void doGet(HttpServletRequest req,
    HttpServletResponse resp)
    throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    req.setAttribute("pedidos", repositorio.listar());
    req.getRequestDispatcher("/WEB-INF/pedidos.jsp")
    .forward(req, resp);
    }

@Override
    protected void doPost(HttpServletRequest req,
    HttpServletResponse resp)
    throws ServletException, IOException {
    req.setCharacterEncoding("UTF-8");
    String acao = campo(req, "acao");
    String erro = "";
    if (acao.equals("criar")) {
    String nome = campo(req, "nome");
    String sala = campo(req, "sala");
    String equipamento = campo(req, "equipamento");
    String descricao = campo(req, "descricao");
    if (nome.isBlank() || sala.isBlank()
                       || equipamento.isBlank()
                       || descricao.length() < 20) {
    erro = "Preencha os campos. Descrição: mínimo 20 caracteres.";
    } else {
        repositorio.adicionar(nome, sala,
        equipamento, descricao);
    }
    } else if (acao.equals("atualizar")) {
        String estado = campo(req, "estado");
        String observacao = campo(req, "observacao");
    if (!estados.contains(estado)) {
    erro = "Estado inválido.";
    } else if (estado.equals("Resolvido")
    && observacao.length() < 10) {
        erro = "Indique a solução com pelo menos 10 caracteres.";
    } else {
        try {
                int id = Integer.parseInt(campo(req, "id"));
                if (!repositorio.atualizar(id, estado, observacao)) {
                erro = "Pedido inexistente.";
            }
        } catch (NumberFormatException e) {
            erro = "Número do pedido inválido.";
        }
    }
    } else {
        erro = "Operação inválida.";
    }
        if (!erro.isEmpty()) {
        req.setAttribute("erro", erro);
        doGet(req, resp);
        return;
    }
        // A sessão transporta a mensagem até ao pedido seguinte.
        req.getSession().setAttribute("mensagem",
        "Operação realizada com sucesso.");
        resp.sendRedirect(req.getContextPath() + "/pedidos");
    }
}