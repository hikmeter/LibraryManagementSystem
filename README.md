# 📚 Kütüphane Yönetim Sistemi

Java, JSP, Servlet ve MySQL teknolojileri kullanılarak geliştirilen web tabanlı bir **Kütüphane Yönetim Sistemi** projesidir. Uygulama; kitapların yönetilmesini, kullanıcı işlemlerinin gerçekleştirilmesini ve kitap ödünç alma ve iade süreçlerinin takip edilmesini sağlar.

## ✨ Özellikler

- **Kullanıcı Girişi:** Uygulamaya erişmek için kimlik doğrulama işlemleri.
- **Kitap Yönetimi:** Kitap ekleme, listeleme, güncelleme ve yönetme işlemleri.
- **Kitap Ödünç Verme:** Kitapların kullanıcılara atanması ve ödünç işlemlerinin kaydedilmesi.
- **Kitap İade İşlemleri:** İade edilen kitapların durumlarının güncellenmesi.
- **Ödünç Kayıtları:** Ödünç alma tarihi, son teslim tarihi ve işlem durumlarının takibi.
- **Veritabanı Entegrasyonu:** MySQL veritabanı üzerinden verilerin saklanması ve yönetilmesi.
- **Dinamik Arayüz:** JSP ve JSTL kullanılarak veritabanındaki bilgilerin dinamik olarak görüntülenmesi.

## 🛠️ Kullanılan Teknolojiler

| Teknoloji | Kullanım Amacı |
|---|---|
| Java | Backend geliştirme |
| Java Servlet | HTTP isteklerinin yönetilmesi |
| JSP | Dinamik web sayfalarının oluşturulması |
| JSTL | JSP üzerinde dinamik içerik yönetimi |
| JDBC | Veritabanı bağlantısı ve işlemleri |
| MySQL | İlişkisel veritabanı yönetimi |
| Apache Tomcat 9 | Web uygulamasının çalıştırılması |
| HTML, CSS, JavaScript | Kullanıcı arayüzünün geliştirilmesi |
| Eclipse IDE | Geliştirme ortamı |

## 📂 Proje Yapısı

```text
LibraryManagementSystem/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/lms/
│       │       ├── controller/
│       │       ├── dao/
│       │       ├── service/
│       │       └── ...
│       └── webapp/
│           ├── assets/
│           │   ├── css/
│           │   └── js/
│           └── jsp/
├── .classpath
├── .project
└── README.md
```

*Not: Bu yapı örnek olarak verilmiştir. Klasör isimleri ve dizin yapısı projenin mevcut hâline göre farklılık gösterebilir.*

## ⚙️ Gereksinimler

Projeyi çalıştırmak için aşağıdaki yazılımlara ihtiyaç vardır:

- Java Development Kit (JDK)
- Eclipse IDE
- Apache Tomcat 9
- MySQL Server
- MySQL Workbench (isteğe bağlı)

## 🚀 Kurulum

### 1. Projeyi bilgisayarınıza indirin

Terminal veya komut satırında aşağıdaki komutları çalıştırın:

```bash
git clone https://github.com/hikmeter/LibraryManagementSystem.git
cd LibraryManagementSystem
```

### 2. Veritabanını oluşturun

MySQL Workbench'i açarak aşağıdaki SQL komutlarını çalıştırın:

```sql
CREATE DATABASE library_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE library_db;
```

Uygulamanın kullandığı tabloları, sütunları ve ilişkileri proje içerisindeki SQL sorgularına uygun şekilde oluşturun.

Temel tablolar şunlardır:

- `users`: Kullanıcı bilgilerini tutar.
- `books`: Kitap bilgilerini, ISBN numaralarını, yayınevi ve kopya sayılarını tutar.
- `book_issued`: Kitap ödünç verme ve iade işlemlerini, tarihleri ve işlem durumlarını tutar.

### 3. Veritabanı bağlantısını yapılandırın

Projedeki veritabanı bağlantısını yöneten sınıfı (örneğin `DbUtil.java`) açın ve bağlantı bilgilerini kendi ortamınıza göre düzenleyin.

Örnek bağlantı yapılandırması:

```java
String url = "jdbc:mysql://localhost:3306/library_db?useUnicode=true&characterEncoding=UTF-8";
String username = "your_mysql_username";
String password = "your_mysql_password";
```

`username` ve `password` alanlarını kendi MySQL kullanıcı bilgilerinizle değiştirin. Ayrıca MySQL JDBC sürücüsünün projeye eklenmiş olduğundan emin olun.

### 4. Apache Tomcat yapılandırmasını yapın

1. Projeyi Eclipse IDE içerisine içe aktarın.
2. Apache Tomcat 9 sunucusunu Eclipse'e tanımlayın.
3. Projeyi Tomcat sunucusuna ekleyin.
4. Sunucuyu başlatın.

### 5. Uygulamayı çalıştırın

Tarayıcınızdan aşağıdaki adresi açın:

```text
http://localhost:8080/libraryManagementSystem/
```

Uygulamanın adresi, Tomcat üzerinde tanımlanan bağlam adına (context path) göre değişebilir.

## 🗄️ Veritabanı Yapısı

Uygulama, kütüphane işlemlerini yönetmek için ilişkisel bir veritabanı kullanır.

| Tablo | Açıklama |
|---|---|
| `users` | Kullanıcı kayıtları |
| `books` | Kitap bilgileri ve envanter kayıtları |
| `book_issued` | Kitap ödünç alma ve iade kayıtları |

`book_issued` tablosu, kullanıcı ve kitap kayıtlarını ilgili kimlik alanları üzerinden ilişkilendirerek ödünç işlemlerinin takip edilmesini sağlar.

## 🎯 Projenin Amacı

Bu proje ile aşağıdaki konularda deneyim kazanılması amaçlanmıştır:

- Java ile web uygulaması geliştirme.
- Servlet ve JSP teknolojilerinin kullanımı.
- Controller, Service ve DAO katmanlarıyla kod organizasyonu.
- JDBC ve SQL kullanarak veritabanı işlemlerinin gerçekleştirilmesi.
- CRUD (Ekleme, Listeleme, Güncelleme ve Silme) işlemleri.
- Oturum yönetimi ve kullanıcı doğrulama.
- JSP ve JSTL ile dinamik web sayfalarının oluşturulması.
- Kitap ödünç verme ve iade süreçlerinin yönetilmesi.

## 👨‍💻 Geliştirici

**Hikmet Öter**

- GitHub: [@hikmeter](https://github.com/hikmeter)
- LinkedIn: [hikmetoter](https://linkedin.com/in/hikmetoter/)

---

*Bu proje, Java web geliştirme, backend programlama ve veritabanı entegrasyonu konularında pratik yapmak amacıyla geliştirilmiştir.*
