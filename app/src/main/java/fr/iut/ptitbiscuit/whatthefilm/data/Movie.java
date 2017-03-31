package fr.iut.ptitbiscuit.whatthefilm.data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * Classe contenant les données relatives à un film.<br/>
 * Les données sont récupérées sur le site <a href="https://www.themoviedb.org">themoviedb.org</a>
 */
public class Movie implements Parcelable {

    /**
     * Le titre du {@link Movie}
     */
    private String title;

    /**
     * La description/le résumé de ce {@link Movie}
     */
    private String desc;

    /**
     * La date de sortie de ce {@link Movie}
     */
    private String date;

    /**
     * Le path de l'affiche de ce {@link Movie}.<br/>
     * Ce path est à utiliser dans une requête à <a href="https://www.themoviedb.org">themoviedb.org</a>
     * afin de récupérer l'image
     */
    private String imagePath;

    /**
     * La liste des catégories de ce {@link Movie}
     */
    private String[] categories;

    /**
     * La note moyenne donnée par les utilisateurs à ce {@link Movie}
     */
    private float rating;

    /**
     * Instancie un nouveau {@link Movie} vide, ne contenant aucune donnée.
     * Les données doivent être ajoutées avec les setters adéquats :
     * <ul>
     *     <li>{@link #setTitle(String)}</li>
     *     <li>{@link #setDesc(String)}</li>
     *     <li>{@link #setDate(String)}</li>
     *     <li>{@link #setImagePath(String)}</li>
     *     <li>{@link #setCategories(String[])}</li>
     *     <li>{@link #setRating(float)}</li>
     * </ul>
     */
    public Movie() {}

    /**
     * Instancie un nouveau {@link Movie} avec les données passées en paramètre
     * @param title le titre du nouveau {@link Movie}
     * @param desc la description/le résumé du nouveau {@link Movie}
     * @param date la date de sortie du nouveau {@link Movie}
     */
    public Movie(String title, String desc, String date) {
        this.title = title;
        this.desc = desc;
        this.date = date;
    }

	protected Movie(Parcel in) {
		this.title = in.readString();
		this.desc = in.readString();
		this.date = in.readString();
		this.imagePath = in.readString();
		this.categories = in.createStringArray();
		this.rating = in.readFloat();
	}

	public static final Creator<Movie> CREATOR = new Creator<Movie>() {
		@Override
		public Movie createFromParcel(Parcel in) {
			return new Movie(in);
		}

		@Override
		public Movie[] newArray(int size) {
			return new Movie[size];
		}
	};

	/**
     * Retourne le titre de ce {@link Movie}
     * @return le titre de ce {@link Movie}
     */
    public String getTitle() {
        return title;
    }

    /**
     * Modifie le titre de ce {@link Movie} pour celui passé en paramètre
     * @param title le nouveau titre de ce {@link Movie}
     * @return ce {@link Movie}, pour pouvoir enchainer plusieurs setters
     * <ul>
     *     <li>{@link #setDesc(String)}</li>
     *     <li>{@link #setDate(String)}</li>
     *     <li>{@link #setImagePath(String)}</li>
     *     <li>{@link #setCategories(String[])}</li>
     *     <li>{@link #setRating(float)}</li>
     * </ul>
     */
    public Movie setTitle(String title) {
	    this.title = title;
	    return this;
    }

    /**
     * Retourne la description/le résumé de ce {@link Movie}
     * @return la description/le résumé de ce {@link Movie}
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Modifie la description/le résumé de ce {@link Movie} pour celle passée en paramètre
     * @param desc la nouvelle description de ce {@link Movie}
     * @return ce {@link Movie}, pour pouvoir enchainer plusieurs setters
     * <ul>
     *     <li>{@link #setTitle(String)}</li>
     *     <li>{@link #setDate(String)}</li>
     *     <li>{@link #setImagePath(String)}</li>
     *     <li>{@link #setCategories(String[])}</li>
     *     <li>{@link #setRating(float)}</li>
     * </ul>
     */
    public Movie setDesc(String desc) {
        this.desc = desc;
	    return this;
    }

    /**
     * Retourne la date de sortie de ce {@link Movie}
     * @return la date de sortie de ce {@link Movie}
     */
    public String getDate() {
        return date;
    }

    /**
     * Modifie la date de sortie de ce {@link Movie} pour celle passée en paramètre
     * @param date la nouvelle date de sortie de ce {@link Movie}
     * @return ce {@link Movie}, pour pouvoir enchainer plusieurs setters
     * <ul>
     *     <li>{@link #setTitle(String)}</li>
     *     <li>{@link #setDesc(String)}</li>
     *     <li>{@link #setImagePath(String)}</li>
     *     <li>{@link #setCategories(String[])}</li>
     *     <li>{@link #setRating(float)}</li>
     * </ul>
     */
    public Movie setDate(String date) {
        this.date = date;
	    return this;
    }

    /**
     * Retourne le path de l'affiche de ce {@link Movie}
     * @return le path de l'affiche de ce {@link Movie}
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * Modifie le path de l'affiche de ce {@link Movie} pour celui passé en paramètre
     * @param imagePath le nouveau path de l'affiche de ce {@link Movie}
     * @return ce {@link Movie}, pour pouvoir enchainer plusieurs setters
     * <ul>
     *     <li>{@link #setTitle(String)}</li>
     *     <li>{@link #setDesc(String)}</li>
     *     <li>{@link #setDate(String)}</li>
     *     <li>{@link #setCategories(String[])}</li>
     *     <li>{@link #setRating(float)}</li>
     * </ul>
     */
    public Movie setImagePath(String imagePath) {
        this.imagePath = imagePath;
	    return this;
    }

    /**
     * Retourne la liste des catégories auxquelles appartient ce {@link Movie}
     * @return la liste des catégories auxquelles appartient ce {@link Movie}
     */
    public String[] getCategories() {
        return this.categories;
    }

    /**
     * Modifie la liste des catégories auxquelles appartient ce {@link Movie} pour celle passée en paramètre
     * @param categories la nouvelle liste de catégories de ce {@link Movie}
     * @return ce {@link Movie}, pour pouvoir enchainer plusieurs setters
     * <ul>
     *     <li>{@link #setTitle(String)}</li>
     *     <li>{@link #setDesc(String)}</li>
     *     <li>{@link #setDate(String)}</li>
     *     <li>{@link #setImagePath(String)}</li>
     *     <li>{@link #setRating(float)}</li>
     * </ul>
     */
    public Movie setCategories(String[] categories) {
        this.categories = categories;
	    return this;
    }

    /**
     * Retourne la note moyenne attribuée à ce {@link Movie}
     * @return la note moyenne que les utilisateurs ont attribué à ce {@link Movie}
     */
    public float getRating() {
        return this.rating;
    }

    /**
     * Modifie la note moyenne attribuée à ce {@link Movie} pour celle passée en paramètre
     * @param rating la nouvelle note moyenne attribuée à ce {@link Movie}
     * @return ce {@link Movie}, pour pouvoir enchainer plusieurs setters
     * <ul>
     *     <li>{@link #setTitle(String)}</li>
     *     <li>{@link #setDesc(String)}</li>
     *     <li>{@link #setDate(String)}</li>
     *     <li>{@link #setImagePath(String)}</li>
     *     <li>{@link #setCategories(String[])}</li>
     * </ul>
     */
    public Movie setRating(float rating) {
        this.rating = rating;
	    return this;
    }

    /**
     * Retourne une représentation textuelle de ce {@link Movie}.<br/>
     * Cette représentation est de la forme :<br/>
     * <i>
     * Movie{<br/>
     *     title=&lt;titre&gt;<br/>
     *     desc=&lt;description&gt;<br/>
     *     date=&lt;date de sortie&gt;<br/>
     *     imagePath=&lt;path de l'affiche&gt;<br/>
     *     categories=&lt;catégories&gt;<br/>
     *     rating=&lt;note moyenne des utilisateurs&gt;<br/>
     * }</i>
     * @return une représentation textuelle de ce {@link Movie}
     */
    @Override
    public String toString() {
        return "Movie{\n" +
                "title='" + title + "\',\n" +
                "desc='" + desc + "\',\n" +
                "date='" + date + "\',\n" +
                "imagePath='" + imagePath + "\',\n" +
                "categories='" + Arrays.toString(categories) + "\',\n" +
                "rating='" + rating + "\',\n" +
                '}';
    }

    // Méthodes pour interface Parcelable

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.desc);
        dest.writeString(this.date);
        dest.writeString(this.imagePath);
        dest.writeStringArray(this.categories);
	    dest.writeFloat(this.rating);
    }
}
