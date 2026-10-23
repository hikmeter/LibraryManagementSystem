<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="includes/header.jsp" %>
<div class="container-fluid">
<div class="row">
<%@ include file="includes/sidebar.jsp" %>
    <main class="col-md-9 ms-sm-auto col-lg-10">
        <div class="page-header">
            <h1 class="h2"><i class="bi bi-book-fill me-2"></i>Yeni Kitap Ekle</h1>
        </div>
		
		<c:if test="${not empty errorMessage}">
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
		<c:out value="${errorMessage}"/>
		<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
		</div>
		</c:if>
	

        <div class="form-container">
            <form action="BookController" method="post">
            <input type="hidden" name="action" value="addBook">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-floating">
                            <input type="text" class="form-control" id="title" name="title" placeholder="Kitap Adı">
                            <label for="bookTitle"><i class="bi bi-book me-2"></i>Kitap Adı</label>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-floating">
                            <input type="text" class="form-control" id="author" name="author" placeholder="Yazar">
                            <label for="author"><i class="bi bi-person me-2"></i>Yazar</label>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-floating">
                            <input type="text" class="form-control" id="isbn" name="isbn" placeholder="ISBN Numarası">
                            <label for="isbn"><i class="bi bi-hash me-2"></i>ISBN Numarası</label>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-floating">
                            <select class="form-select" id="category" name="category">
                                <option selected disabled value="">Kategori Seçin</option>
                                <option value="Kurgu">Kurgu</option>
                                <option value="Sıradışı">Sıradışı</option>
                                <option value="Bilim">Bilim</option>
                                <option value="Teknoloji">Teknoloji</option>
                                <option value="Tarih">Tarih</option>
                                <option value="Biyografi">Biyografi</option>
                                <option value="Gizem">Gizem</option>
                                <option value="Romantik">Romantik</option>
                            </select>
                            <label for="category"><i class="bi bi-collection me-2"></i>Kategori</label>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-floating">
                            <input type="text" class="form-control" id="publisher" name="publisher" placeholder="Yayınevi">
                            <label for="publisher"><i class="bi bi-building me-2"></i>Yayınevi</label>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-floating">
                            <input type="number" class="form-control" id="availableCopies" name="availableCopies" placeholder="Mevcut Kitap Sayısı">
                            <label for="pages"><i class="bi bi-file-text me-2"></i>Mevcut Kitap Sayısı</label>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-floating">
                            <input type="number" class="form-control" id="totalCopies" name="totalCopies" placeholder="Toplam Adet Sayısı" min="1" value="1">
                            <label for="copies"><i class="bi bi-stack me-2"></i>Toplam Adet Sayısı</label>
                        </div>
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn-modern">
                        <i class="bi bi-save"></i>Kitabı Kaydet
                    </button>
                </div>
            </form>
        </div>
    </main>
</div>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
