package pt.ecl;
public class Html {
// Mostra texto do utilizador sem o interpretar como HTML.
public static String escapar(Object valor) {
if (valor == null) return "";
return valor.toString().replace("&", "&amp;")
.replace("<", "&lt;").replace(">", "&gt;")
.replace("\"", "&quot;").replace("'", "&#39;");
}
}