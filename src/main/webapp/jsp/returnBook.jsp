<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="includes/header.jsp"%>
<div class="container-fluid">
	<div class="row">
		<%@ include file="includes/sidebar.jsp"%>
		<main class="col-md-9 ms-sm-auto col-lg-10">
			<div class="page-header">
				<div>
					<h1 class="h2">
						<i class="bi bi-arrow-left-circle me-2"></i>Kitap İade Al
					</h1>
					<p class="text-muted mb-0">Ödünç alınan kitapları takip
						edebilir ve güvenli bir şekilde iade işlemlerini yapabilirsiniz.</p>
				</div>
			</div>

			<c:if test="${not empty successMessage}">
				<div class="alert alert-success alert-dismissible fade show"
					role="alert">
					<c:out value="${successMessage}" />
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
			</c:if>

			<c:if test="${not empty errorMessage}">
				<div class="alert alert-danger alert-dismissible fade show"
					role="alert">
					<c:out value="${errorMessage}" />
					<button type="button" class="btn-close" data-bs-dismiss="alert"
						aria-label="Close"></button>
				</div>
			</c:if>

			<div class="table-container">
				<div class="table-responsive">
					<c:choose>
						<c:when test="${not empty issuedList }">
							<table class="table table-hover align-middle">
								<thead>
									<tr>
										<th>#</th>
										<th>Kitap</th>
										<th>Üye</th>
										<th>Veriliş Tarihi</th>
										<th>Son Teslim Tarihi</th>
										<th>Durum</th>
										<th>İşlem</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="issued" items="${issuedList }"
										varStatus="status">
										<tr>
											<td><span class="badge bg-primary">${status.index +  1}</span></td>
											<td><strong>${issued.book.title }</strong>
												<div class="text-muted small">
													ISBN:
													<code>${issued.book.isbn }</code>
												</div></td>
											<td>
												<div>${issued.user.firstName } ${issued.user.lastName }</div>
												<div class="text-muted small">${issued.user.email }</div>
											</td>
											<td>${issued.issueDate }</td>
											<td>${issued.dueDate }</td>
											<td><c:choose>
													<c:when test="${issued.returnStatus == 'BUGÜN'}">
														<span class="badge bg-warning text-dark">BUGÜN</span>
													</c:when>

													<c:when test="${issued.returnStatus == 'GEÇMİŞ'}">
														<span class="badge bg-danger">GEÇMİŞ</span>
													</c:when>

													<c:otherwise>
														<span class="badge bg-success">SÜRE VAR</span>
													</c:otherwise>
												</c:choose></td>
											<td><a href="BookController?action=showReturnBookDetail&issueID=${issued.issueID }"
												class="btn btn-sm btn-outline-primary"> <i
													class="bi bi-arrow-return-left me-1"></i>Kitap Al
											</a></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</c:when>
						<c:otherwise>Herhangi bir kitap bulunamadı!</c:otherwise>
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
