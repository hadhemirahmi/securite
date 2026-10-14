<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Protection : page accessible uniquement si connecte
    String user = (String) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Accueil</title>
  <style>
  *{box-sizing:border-box}
  body{font-family:Arial,sans-serif;background:#f0f2f5;display:flex;align-items:center;justify-content:center;min-height:100vh;margin:0}
  .card{background:#fff;padding:32px;border-radius:10px;box-shadow:0 4px 16px rgba(0,0,0,.1);width:340px}
  h2{margin-top:0;text-align:center;color:#222}
  label{display:block;margin:14px 0 4px;font-size:14px;color:#444}
  input{width:100%;padding:10px;border:1px solid #ccc;border-radius:6px;font-size:14px}
  button{width:100%;margin-top:20px;padding:11px;border:0;border-radius:6px;background:#2563eb;color:#fff;font-size:15px;cursor:pointer}
  button:hover{background:#1d4ed8}
  .error{background:#fee2e2;color:#b91c1c;padding:10px;border-radius:6px;margin-bottom:10px;font-size:14px}
  .success{background:#dcfce7;color:#166534;padding:10px;border-radius:6px;margin-bottom:10px;font-size:14px}
  .link{text-align:center;margin-top:16px;font-size:14px}
  a{color:#2563eb;text-decoration:none}
</style>
</head>
<body>
  <div class="card" style="text-align:center">
    <h2>Bienvenue, <%= user %> !</h2>
    <p>Vous etes connecte avec succes.</p>
    <a href="<%= request.getContextPath() %>/logout"><button type="button">Deconnexion</button></a>
  </div>
</body>
</html>
