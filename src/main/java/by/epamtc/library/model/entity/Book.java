package by.epamtc.library.model.entity;

import java.io.Serializable;

/**
 * Entity class that represents a book.
 *
 * @author Artur Mironchik
 */
public class Book implements Serializable {
    private long id;
    private String title;
    private String authorPseudo;
    private String isbn;
    private short availableQuantity;
    private String genre;
    private String shortDescription;
    private String pdf;
    private String img;
    private String authorImg;

    /**
     * Instantiates a new Book.
     */
    public Book(){}

    /**
     * Instantiates a new Book.
     *
     * @param id the id
     */
    public Book(long id){this.id = id;}

    /**
     * Instantiates a new Book.
     *
     * @param title             the title
     * @param authorPseudo      the author pseudo
     * @param isbn              the isbn
     * @param availableQuantity the available quantity
     * @param genre             the genre
     * @param shortDescription  the short description
     * @param pdf               the pdf
     * @param img               the img
     * @param authorImg         the author img
     */
    public Book(String title, String authorPseudo, String isbn, short availableQuantity, String genre,
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

    /**
     * Instantiates a new Book.
     *
     * @param id                the id
     * @param title             the title
     * @param authorPseudo      the author pseudo
     * @param isbn              the isbn
     * @param availableQuantity the available quantity
     * @param genre             the genre
     * @param img               the img
     * @param authorImg         the author img
     */
    public Book(long id, String title, String authorPseudo, String isbn, short availableQuantity, String genre,
                String img, String authorImg) {
        this(id,title,img);
        this.authorPseudo = authorPseudo;
        this.isbn = isbn;
        this.availableQuantity = availableQuantity;
        this.genre = genre;
        this.authorImg = authorImg;
    }


    /**
     * Instantiates a new Book.
     *
     * @param bookId    the book id
     * @param bookTitle the book title
     * @param bookImg   the book img
     * @param bookPdf   the book pdf
     */
    public Book(long bookId, String bookTitle, String bookImg, String bookPdf) {
        this(bookTitle, bookImg, bookPdf);
        this.id = bookId;
    }

    /**
     * Instantiates a new Book.
     *
     * @param bookTitle the book title
     * @param bookImg   the book img
     * @param bookPdf   the book pdf
     */
    public Book(String bookTitle, String bookImg, String bookPdf) {
        this.title = bookTitle;
        this.img = bookImg;
        this.pdf = bookPdf;
    }

    /**
     * Instantiates a new Book.
     *
     * @param bookId                the book id
     * @param bookTitle             the book title
     * @param bookImg               the book img
     * @param bookPdf               the book pdf
     * @param bookAvailableQuantity the book available quantity
     */
    public Book(long bookId, String bookTitle, String bookImg, String bookPdf, short bookAvailableQuantity) {
        this(bookId, bookTitle, bookImg, bookPdf);
        this.availableQuantity = bookAvailableQuantity;
    }

    /**
     * Instantiates a new Book.
     *
     * @param bookId    the book id
     * @param bookTitle the book title
     * @param bookImg   the book img
     */
    public Book(long bookId, String bookTitle, String bookImg) {
        this(bookId);
        this.title = bookTitle;
        this.img = bookImg;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets author pseudo.
     *
     * @return the author pseudo
     */
    public String getAuthorPseudo() {
        return authorPseudo;
    }

    /**
     * Sets author pseudo.
     *
     * @param authorPseudo the author pseudo
     */
    public void setAuthorPseudo(String authorPseudo) {
        this.authorPseudo = authorPseudo;
    }

    /**
     * Gets isbn.
     *
     * @return the isbn
     */
    public String getIsbn() {
        return isbn;
    }

    /**
     * Sets isbn.
     *
     * @param isbn the isbn
     */
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    /**
     * Gets available quantity.
     *
     * @return the available quantity
     */
    public short getAvailableQuantity() {
        return availableQuantity;
    }

    /**
     * Sets available quantity.
     *
     * @param availableQuantity the available quantity
     */
    public void setAvailableQuantity(short availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    /**
     * Gets genre.
     *
     * @return the genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     * Sets genre.
     *
     * @param genre the genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     * Gets short description.
     *
     * @return the short description
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Sets short description.
     *
     * @param shortDescription the short description
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Gets pdf.
     *
     * @return the pdf
     */
    public String getPdf() {
        return pdf;
    }

    /**
     * Sets pdf.
     *
     * @param pdf the pdf
     */
    public void setPdf(String pdf) {
        this.pdf = pdf;
    }

    /**
     * Gets img.
     *
     * @return the img
     */
    public String getImg() {
        return img;
    }

    /**
     * Sets img.
     *
     * @param img the img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * Gets author img.
     *
     * @return the author img
     */
    public String getAuthorImg() {
        return authorImg;
    }

    /**
     * Sets author img.
     *
     * @param authorImg the author img
     */
    public void setAuthorImg(String authorImg) {
        this.authorImg = authorImg;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (availableQuantity != book.availableQuantity) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (authorPseudo != null ? !authorPseudo.equals(book.authorPseudo) : book.authorPseudo != null) return false;
        if (isbn != null ? !isbn.equals(book.isbn) : book.isbn != null) return false;
        if (genre != null ? !genre.equals(book.genre) : book.genre != null) return false;
        return shortDescription != null ? shortDescription.equals(book.shortDescription) : book.shortDescription == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (authorPseudo != null ? authorPseudo.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        result = 31 * result + (int) availableQuantity;
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", authorPseudo='" + authorPseudo + '\'' +
                ", isbn='" + isbn + '\'' +
                ", availableQuantity=" + availableQuantity +
                ", genre='" + genre + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", pdf='" + pdf + '\'' +
                ", img='" + img + '\'' +
                ", authorImg='" + authorImg + '\'' +
                '}';
    }
}
