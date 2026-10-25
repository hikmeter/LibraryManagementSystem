<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="includes/header.jsp"%>
<div class="container-fluid">
	<div class="row">
		<%@ include file="includes/sidebar.jsp"%>
		<main class="col-md-9 ms-sm-auto col-lg-10">
			<div class="page-header">
				<h1 class="h2">
					<i class="bi bi-arrow-right-circle me-2"></i>Kitap Ver
				</h1>
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

			<div class="form-container">
				<form action="BookController" method="post">
					<input type="hidden" name="action" value="assignBook">
					<div class="row">
						<div class="col-md-6 mb-3">
							<label for="bookId" class="form-label">
							</label> <select class="form-select" id="bookId" name="bookId" required>
								<option value=""></option>
								<c:forEach var="book" items="${bookList}">
									<option value="${book.bookId}">
										<c:out value="${book.title}" /> -
										<c:out value="${book.author}" />
									</option>
								</c:forEach>
							</select>
						</div>
						<div class="col-md-6 mb-3">
							<label for="userId" class="form-label">
							</label> <select class="form-select" id="userId" name="userId" required>
								<option value=""></option>
								<c:forEach var="user" items="${userList}">
									<option value="${user.userId}">
										<c:out value="${user.firstName}" />
										<c:out value="${user.lastName}" /> -
										<c:out value="${user.email}" />
									</option>
								</c:forEach>
							</select>
						</div>
					</div>

					<div class="row">
						<div class="col-md-6">
							<div class="form-floating">
								<input type="date" class="form-control" id="dueDate"
									name="dueDate"> <label for="dueDate"><i
									class="bi bi-calendar-event me-2"></i>Teslim Tarihi</label>
							</div>
						</div>
						<div class="col-md-6">
							<div class="form-floating">
								<textarea class="form-control" id="assignmentNotes"
									name="assignmentNotes" placeholder="Notlar"></textarea>
								<label for="notes"><i class="bi bi-card-text me-2"></i>Notlar
									(Varsa)</label>
							</div>
						</div>
					</div>

					<div class="form-actions">
						<button type="submit" class="btn-modern">
							<i class="bi bi-check-circle"></i>Kitap Ver
						</button>
					</div>
				</form>
			</div>
		</main>
	</div>
</div>
<script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/select2@4.1.0-rc.0/dist/js/select2.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
<script>
	$(document).ready(function() {
		$('#bookId').select2({
			placeholder : 'Kitap adı veya yazar ara...',
			allowClear : true,
			width : '100%'
		});

		$('#userId').select2({
			placeholder : 'Kullanıcı veya e-posta adresi ara...',
			allowClear : true,
			width : '100%'
		});

	});
</script>
</body>
</html>