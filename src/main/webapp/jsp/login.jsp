<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Giriş - Kütüphane Yönetim Sistemi</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css" rel="stylesheet">
<link href="https://fonts.googleapis.com/css2?family=Inter:wght@300;400;500;600;700&family=Playfair+Display:wght@400;500;600&display=swap" rel="stylesheet">
<link rel="stylesheet" href="assets/css/common.css">
<link rel="stylesheet" href="assets/css/auth.css">
</head>
<body class="auth-page">
<div class="login-container">
    <div class="login-card">
        <div class="login-header">
            <div class="login-icon">
                <i class="bi bi-book-half"></i>
            </div>
            <h3 class="login-title">Hoş Geldiniz!</h3>
            <p class="login-subtitle">Lütfen Hesabınıza Giriş Yapın</p>
        </div>
        
        <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
		<c:out value="${errorMessage}"/>
		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
		</c:if>
		
        <form action="AuthenticationController" method="post">
        <input type="hidden" name="action" value="checkLogin">
            <div class="form-floating">
                <input type="text" class="form-control" id="username" name="username" placeholder="Username">
                <label for="username"><i class="bi bi-person-fill me-2"></i>E-Posta Adresi</label>
                <div class="text-danger small" id="usernameError"></div>
            </div>

            <div class="form-floating">
                <input type="password" class="form-control" id="password" name="password" placeholder="Password">
                <label for="password"><i class="bi bi-lock-fill me-2"></i>Şifre</label>
                <div class="text-danger small" id="passwordError"></div>
            </div>

            <button type="submit" class="btn btn-login">
                <i class="bi bi-box-arrow-in-right me-2"></i>Giriş Yap
            </button>

        </form>
    </div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
    // Add subtle animation to form elements
    document.addEventListener('DOMContentLoaded', function() {
        const formControls = document.querySelectorAll('.form-control');

        formControls.forEach(control => {
            control.addEventListener('focus', function() {
                this.parentElement.style.transform = 'translateY(-2px)';
            });

            control.addEventListener('blur', function() {
                this.parentElement.style.transform = 'translateY(0)';
            });
        });
    });
</script>
<script>
    function setLoginValidation() {

        const form = document.querySelector('.login-container form');

        if (!form) {
            return;
        }

        form.addEventListener('submit', function (event) {

            const username = document.getElementById('username').value.trim();
            const password = document.getElementById('password').value;

            const usernameError = document.getElementById('usernameError');
            const passwordError = document.getElementById('passwordError');

            let hasError = false;

            // Önceki hata mesajlarını temizle
            usernameError.textContent = '';
            passwordError.textContent = '';

            if (!username) {
                usernameError.textContent = 'Lütfen E-Posta Adresinizi Giriniz.';
                hasError = true;
            }

            if (!password) {
                passwordError.textContent = 'Lütfen Şifrenizi Giriniz.';
                hasError = true;
            }

            if (hasError) {
                event.preventDefault();
            }
        });
    }

    if (document.readyState === 'loading') {
        document.addEventListener('DOMContentLoaded', setLoginValidation);
    } else {
        setLoginValidation();
    }
</script>
</body>
</html>
