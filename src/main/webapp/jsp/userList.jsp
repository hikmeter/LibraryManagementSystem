<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="includes/header.jsp"%>
<link rel="stylesheet" href="assets/css/user.css">
<div class="container-fluid">
	<div class="row">
		<%@ include file="includes/sidebar.jsp"%>
		<main class="col-md-9 ms-sm-auto col-lg-10">
			<div class="page-header header-with-btn">
				<h1 class="h2">
					<i class="bi bi-people-fill me-2"></i>Kullanıcı Yönetimi
				</h1>
				<a href="add_user.html" class="btn-modern"><i
					class="bi bi-person-plus-fill"></i>Yeni Kullanıcı Ekle</a>
			</div>

			<c:if test="${not empty successMessage}">
				<div class="alert alert-success alert-dismissible fade show"
					role="alert">
					<c:out value="${successMessage}" />
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
			</c:if>


			<div class="table-container">
				<div class="table-responsive">
					<c:choose>
						<c:when test="${not empty userList }">
							<table class="table table-hover align-middle">
								<thead>
									<tr>
										<th>#</th>
										<th>Kullanıcı</th>
										<th>E-Posta Adresi</th>
										<th>Rolü</th>
										<th>Kayıt Tarihi</th>
										<th>İşlemler</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="user" items="${userList}" varStatus="status">
										<tr>
											<td><span class="badge bg-primary">${status.index + 1 }</span></td>
											<td>
												<div class="user-info">
													<strong>${user.firstName} ${user.lastName }</strong>
												</div>
											</td>
											<td>${user.email }</td>
											<td><span class="badge bg-danger">${user.role }</span></td>
											<td>${user.createdAt }</td>
											<td><a href="edit_user.html" class="action-btn edit"
												title="Kullanıcıyı Düzenle"><i
													class="bi bi-pencil-square"></i></a> <a
												href="UserController?action=deleteUser&userId=${user.userId}"
												class="action-btn delete" title="Kullanıcıyı Sil"> <i
													class="bi bi-trash-fill"></i>
											</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>Herhangi bir kullanıcı bulunamadı!</c:otherwise>
					</c:choose>
				</div>
			</div>
		</main>
	</div>
</div>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
