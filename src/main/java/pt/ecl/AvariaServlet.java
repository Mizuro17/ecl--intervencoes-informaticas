package pt.ecl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

// O web.xml associa este Servlet ao endereço /avaria.

public class AvariaServlet extends HttpServlet {
    // Opções aceites para o tipo de avaria (iguais aos value das <option>).
    private final List<String> tipos = List.of("Hardware", "Software", "Rede");

    // Um parâmetro pode chegar como null: só se aplica trim() depois de o verificar.
    private String campo(HttpServletRequest req, String nome) {
        String valor = req.getParameter(nome);
        return valor == null ? "" : valor.trim();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/avaria-formulario.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        // 1. Ler os parâmetros pelo mesmo nome usado no atributo name.
        String sala = campo(req, "sala");
        String tipo = campo(req, "tipo");
        String descricao = campo(req, "descricao");

        // 2. Guardar os valores para a JSP os poder mostrar.
        req.setAttribute("sala", sala);
        req.setAttribute("tipo", tipo);
        req.setAttribute("descricao", descricao);

        // 3. Validar cada campo no servidor.
        boolean valido = true;
        if (sala.isEmpty()) {
            req.setAttribute("erroSala", "Indique a sala.");
            valido = false;
        }
        if (!tipos.contains(tipo)) {
            req.setAttribute("erroTipo", "Escolha Hardware, Software ou Rede.");
            valido = false;
        }
        if (descricao.length() < 10) {
            req.setAttribute("erroDescricao",
                    "A descrição tem de ter pelo menos 10 caracteres.");
            valido = false;
        }

        // 4. Encaminhar para a página adequada.
        String pagina = valido ? "/WEB-INF/avaria-confirmacao.jsp"
                               : "/WEB-INF/avaria-formulario.jsp";
        req.getRequestDispatcher(pagina).forward(req, resp);
    }
}
