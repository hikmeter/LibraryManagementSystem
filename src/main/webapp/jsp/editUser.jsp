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
					<i class="bi bi-person-plus me-2"></i>Kullanıcı Güncelle
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

			<div class="alert alert-info d-flex align-items-start gap-2"
				role="alert">
				<i class="bi bi-info-circle-fill fs-5"></i>
				<div>
					<strong>Şifre Güncelleme Hakkında</strong>
					<p class="mb-0 mt-1">Güvenliğiniz için şifre değişikliği
						işlemi için yazılım destek ekibiyle iletişime geçmeniz
						gerekmektedir.</p>
				</div>
			</div>
			<div class="form-container">
				<form id="userForm" action="UserController" method="post">
					<input type="hidden" name="action" value="updateUser">
					<input type="hidden" name="userId" value="${user.userId }">
					<div class="row">
						<div class="col-md-6">
							<div class="form-floating">
								<input type="text" class="form-control" id="firstName"
									name="firstName" placeholder="Ad" value="${user.firstName }">
								<label for="firstName"><i class="bi bi-person me-2"></i>Ad</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-floating">
								<input type="text" class="form-control" id="lastName"
									name="lastName" placeholder="Soyad" value="${user.lastName }">
								<label for="lastName"><i class="bi bi-person me-2"></i>Soyad</label>
							</div>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6">
							<div class="form-floating">
								<input type="email" class="form-control" id="email" name="email"
									placeholder="E-Posta Adresi" value="${user.email }"> <label
									for="email"><i class="bi bi-envelope me-2"></i>E-Posta
									Adresi</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-floating">
								<input type="tel" class="form-control" id="phone" name="phone"
									placeholder="Telefon Numarası" value="${user.phoneNo }">
								<label for="phone"><i class="bi bi-telephone me-2"></i>Telefon
									Numarası</label>
							</div>
						</div>
					</div>

					<div class="form-floating">
						<textarea class="form-control" id="address" name="address"
							placeholder="Adres">${user.address }</textarea>
						<label for="address"><i class="bi bi-geo-alt me-2"></i>Adres</label>
					</div>


					<div class="form-actions">
						<button type="submit" class="btn-modern">
							<i class="bi bi-person-plus"></i>Kullanıcı Güncelle
						</button>
					</div>
				</form>
			</div>
		</main>
	</div>
</div>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
