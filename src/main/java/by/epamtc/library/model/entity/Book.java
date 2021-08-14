package by.epamtc.library.model.entity;

import java.io.Serializable;

public class Book implements Serializable {
    private long id;
    private String title;
    private String authorPseudo;
    private String isbn;
    private String availableQuantity;
    private String genre;
    private String shortDescription;
    private String pdf;
    private String img;
    private String authorImg;

    public Book(){}

    public Book(long id){this.id = id;}

    public Book(long id, String title, String authorPseudo, String isbn, String availableQuantity, String genre,
                String shortDescription, String pdf, String img, String authorImg) {
        this(title, authorPseudo, isbn, availableQuantity, genre, shortDescription, pdf, img, authorImg);
        this.id = id;
    }

    public Book(String title, String authorPseudo, String isbn, String availableQuantity, String genre,
                String shortDescription, String pdf, String img, String authorImg) {
        this.title = title;
        this.authorPseudo = authorPseudo;
        this.isbn = isbn;
        this.availableQuantity = availableQuantity;
        this.genre = genre;
        this.shortDescription = shortDescription;
        this.pdf = pdf;
        this.img = img;
        this.authorImg = authorImg;
    }

    public Book(long bookId, String bookTitle, String bookImg, String bookPdf) {
        this(bookTitle, bookImg, bookPdf);
        this.id = bookId;
    }

    public Book(String bookTitle, String bookImg, String bookPdf) {
        this.title = bookTitle;
        this.img = bookImg;
        this.pdf = bookPdf;
    }

    public Book(long bookId, String bookTitle, String bookImg, String bookPdf, String bookAvailableQuantity) {
        this(bookId, bookTitle, bookImg, bookPdf);
        this.availableQuantity = bookAvailableQuantity;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorPseudo() {
        return authorPseudo;
    }

    public void setAuthorPseudo(String authorPseudo) {
        this.authorPseudo = authorPseudo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(String availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAuthorImg() {
        return authorImg;
    }

    public void setAuthorImg(String authorImg) {
        this.authorImg = authorImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (!title.equals(book.title)) return false;
        if (!authorPseudo.equals(book.authorPseudo)) return false;
        if (!isbn.equals(book.isbn)) return false;
        if (!availableQuantity.equals(book.availableQuantity)) return false;
        if (!genre.equals(book.genre)) return false;
        return shortDescription.equals(book.shortDescription);
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + title.hashCode();
        result = 31 * result + authorPseudo.hashCode();
        result = 31 * result + isbn.hashCode();
        result = 31 * result + availableQuantity.hashCode();
        result = 31 * result + genre.hashCode();
        result = 31 * result + shortDescription.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(Book.class.getSimpleName() + "{");
        sb.append("id=").append(id);
        sb.append(", title='").append(title).append('\'');
        sb.append(", authorPseudo='").append(authorPseudo).append('\'');
        sb.append(", isbn='").append(isbn).append('\'');
        sb.append(", availableQuantity='").append(availableQuantity).append('\'');
        sb.append(", genre='").append(genre).append('\'');
        sb.append(", shortDescription='").append(shortDescription).append('\'');
        sb.append(", pdf='").append(pdf).append('\'');
        sb.append(", img='").append(img).append('\'');
        sb.append(", authorImg='").append(authorImg).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
