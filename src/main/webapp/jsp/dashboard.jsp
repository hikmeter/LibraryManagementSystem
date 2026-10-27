<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="includes/header.jsp"%>
<div class="container-fluid">
	<div class="row">
		<%@ include file="includes/sidebar.jsp"%>
		<main class="col-md-9 ms-sm-auto col-lg-10 px-md-4">
			<div class="page-header">
				<h1 class="h2">
					<i class="bi bi-speedometer2 me-2"></i>Dashboard
				</h1>
			</div>

			<!-- Statistics Cards -->
			<div class="row mb-4">
				<div class="col-md-3">
					<div class="card text-white stat-card primary mb-3">
						<div class="card-body">
							<i class="bi bi-book-fill stat-icon"></i>
							<h5 class="card-title">Toplam Kitap</h5>
							<p class="card-text">${dashboardStats.totalBooks }</p>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="card text-white stat-card warning mb-3">
						<div class="card-body">
							<i class="bi bi-arrow-right-circle-fill stat-icon"></i>
							<h5 class="card-title">Ödünç Alınan Kitap</h5>
							<p class="card-text">${dashboardStats.booksAssigned }</p>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="card text-white stat-card success mb-3">
						<div class="card-body">
							<i class="bi bi-arrow-left-circle-fill stat-icon"></i>
							<h5 class="card-title">Geri Verilen Kitap</h5>
							<p class="card-text">${dashboardStats.booksReturned }</p>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<div class="card text-white stat-card info mb-3">
						<div class="card-body">
							<i class="bi bi-people-fill stat-icon"></i>
							<h5 class="card-title">Kullanıcı</h5>
							<p class="card-text">${dashboardStats.totalUsers }</p>
						</div>
					</div>
				</div>
			</div>

			<!-- Recent Books Table -->
			<h4 class="section-title">Son Ödünç Verilen Kitaplar</h4>
			<div class="table-container">
				<div class="table-responsive">
					<c:choose>
						<c:when test="${not empty issuedList }">
							<table class="table table-hover align-middle">
								<thead>
									<tr>
										<th>#</th>
										<th>Ad</th>
										<th>Yazar</th>
										<th>Kategori</th>
										<th>ISBN</th>
										<th>Teslim Tarihi</th>
									</tr>
								</thead>
								<tbody>
								<c:forEach var="issued" items="${issuedList }" varStatus="status">
									<tr>
										<td><span class="badge bg-primary">${status.index + 1 }</span></td>
										<td><strong>${issued.book.title }</strong></td>
										<td>${issued.book.author }</td>
										<td><span class="badge bg-info">${issued.book.category }</span></td>
										<td><code>${issued.book.isbn }</code></td>
										<td><span class="badge bg-warning">${issued.dueDate }</span></td>
									</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
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
