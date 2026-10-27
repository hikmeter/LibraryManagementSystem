<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Hata - Kütüphane Yönetim Sistemi</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="assets/css/error.css">
</head>
<body>
<div class="error-page">
    <div class="error-card">
        <div class="error-code">404</div>
        <h1 class="error-title">Sayfa Bulunamadı</h1>
        <p class="error-message">Aradığınız sayfayı bulamıyoruz. Sayfa taşınmış veya silinmiş olabilir. Devam etmek için aşağıdaki butona tıklayarak giriş sayfasına yönlenebilirsiniz.</p>
        <div class="action-buttons d-flex flex-column flex-sm-row justify-content-center gap-3">
            <a href="AuthenticationController?action=showLogin" class="btn btn-primary">Giriş Sayfasına Dön</a>
        </div>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
