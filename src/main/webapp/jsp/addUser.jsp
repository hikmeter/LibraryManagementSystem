<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="includes/header.jsp"%>
<link rel="stylesheet" href="assets/css/user.css">
<div class="container-fluid">
	<div class="row">
		<%@ include file="includes/sidebar.jsp"%>
		<main class="col-md-9 ms-sm-auto col-lg-10">
			<div class="page-header">
				<h1 class="h2">
					<i class="bi bi-person-plus me-2"></i>Yeni Kullanıcı Ekle
				</h1>
			</div>

			<c:if test="${not empty errorMessage}">
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					<c:out value="${errorMessage}" />
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
			</c:if>

			<div class="form-container">
				<form id="userForm" action="UserController" method="post">
					<input type="hidden" name="action" value="addUser">
					<div class="row">
						<div class="col-md-6">
							<div class="form-floating">
								<input type="text" class="form-control" id="firstName"
									name="firstName" placeholder="Ad"> <label
									for="firstName"><i class="bi bi-person me-2"></i>Ad</label>
							<div class="text-danger small" id="firstNameError"></div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-floating">
								<input type="text" class="form-control" id="lastName"
									name="lastName" placeholder="Soyad"> <label
									for="lastName"><i class="bi bi-person me-2"></i>Soyad</label>
								<div class="text-danger small" id="lastNameError"></div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6">
							<div class="form-floating">
								<input type="email" class="form-control" id="email" name="email"
									placeholder="E-Posta Adresi"> <label for="email"><i
									class="bi bi-envelope me-2"></i>E-Posta Adresi</label>
								<div class="text-danger small" id="emailError"></div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-floating">
								<input type="password" class="form-control" id="password"
									name="password" placeholder="Şifre"> <label
									for="password"><i class="bi bi-lock-fill me-2"></i>Şifre</label>
									<div class="text-danger small" id="passwordError"></div>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6">
							<div class="form-floating">
								<textarea class="form-control" id="address" name="address"
									placeholder="Adres"></textarea>
								<label for="address"><i class="bi bi-geo-alt me-2"></i>Adres</label>
								<div class="text-danger small" id="addressError"></div>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-floating">
								<input type="tel" class="form-control" id="phone" name="phone"
									placeholder="Telefon Numarası"> <label for="phone"><i
									class="bi bi-telephone me-2"></i>Telefon Numarası</label>
									<div class="text-danger small" id="phoneError"></div>
							</div>
						</div>
					</div>

					<div class="form-actions">
						<button type="submit" class="btn-modern">
							<i class="bi bi-person-plus"></i>Kullanıcı Oluştur
						</button>
					</div>
				</form>
			</div>
		</main>
	</div>
</div>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
function setUserValidation() {

    const form = document.getElementById('userForm');

    if (!form) {
        return;
    }

    form.addEventListener('submit', function (event) {

        const firstName = document.getElementById('firstName').value.trim();
        const lastName = document.getElementById('lastName').value.trim();
        const email = document.getElementById('email').value.trim();
        const password = document.getElementById('password').value;
        const address = document.getElementById('address').value.trim();
        const phone = document.getElementById('phone').value.trim();

        const firstNameError = document.getElementById('firstNameError');
        const lastNameError = document.getElementById('lastNameError');
        const emailError = document.getElementById('emailError');
        const passwordError = document.getElementById('passwordError');
        const addressError = document.getElementById('addressError');
        const phoneError = document.getElementById('phoneError');

        let hasError = false;

        // Önceki hata mesajlarını temizle
        firstNameError.textContent = '';
        lastNameError.textContent = '';
        emailError.textContent = '';
        passwordError.textContent = '';
        addressError.textContent = '';
        phoneError.textContent = '';

        // Ad kontrolü
        if (!firstName) {
            firstNameError.textContent = 'Lütfen Adınızı Giriniz.';
            hasError = true;
        }

        // Soyad kontrolü
        if (!lastName) {
            lastNameError.textContent = 'Lütfen Soyadınızı Giriniz.';
            hasError = true;
        }

        // E-posta kontrolü
        if (!email) {
            emailError.textContent = 'Lütfen E-Posta Adresinizi Giriniz.';
            hasError = true;
        } else if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
            emailError.textContent = 'Geçerli Bir E-Posta Adresi Giriniz.';
            hasError = true;
        }

        // Şifre kontrolü
        if (!password.trim()) {
            passwordError.textContent = 'Lütfen Şifrenizi Giriniz.';
            hasError = true;
        } else if (password.length < 8) {
            passwordError.textContent = 'Şifre En Az 8 Karakter Olmalıdır.';
            hasError = true;
        }

        // Adres kontrolü
        if (!address) {
            addressError.textContent = 'Lütfen Adresinizi Giriniz.';
            hasError = true;
        }

        // Telefon kontrolü
        if (!phone) {
            phoneError.textContent = 'Lütfen Telefon Numaranızı Giriniz.';
            hasError = true;
        } else {
            const digits = phone.replace(/\D/g, '');

            if (digits.length < 10 || digits.length > 15) {
                phoneError.textContent =
                    'Telefon Numarası 10 İle 15 Rakam Arasında Olmalıdır.';
                hasError = true;
            }
        }

        // Hata varsa formu gönderme
        if (hasError) {
            event.preventDefault();
        }

    });
}

if (document.readyState === 'loading') {
    document.addEventListener('DOMContentLoaded', setUserValidation);
} else {
    setUserValidation();
}
</script>
</body>
</html>
