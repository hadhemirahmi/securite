<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
  <meta charset="UTF-8">
  <title>Inscription</title>
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
  <div class="card">
    <h2>Creer un compte</h2>

    <% if (request.getAttribute("error") != null) { %>
      <div class="error"><%= request.getAttribute("error") %></div>
    <% } %>

    <form action="<%= request.getContextPath() %>/register" method="post">
      <label for="login">Login</label>
      <input type="text" id="login" name="login" required>

      <label for="password">Mot de passe</label>
      <input type="password" id="password" name="password" required>

      <label for="confirm">Confirmer le mot de passe</label>
      <input type="password" id="confirm" name="confirm" required>

      <button type="submit">S'inscrire</button>
    </form>

    <div class="link">Deja inscrit ? <a href="<%= request.getContextPath() %>/login">Se connecter</a></div>
  </div>
</body>
</html>
