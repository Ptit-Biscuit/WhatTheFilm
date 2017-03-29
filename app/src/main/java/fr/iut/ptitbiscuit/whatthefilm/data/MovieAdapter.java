package fr.iut.ptitbiscuit.whatthefilm.data;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import fr.iut.ptitbiscuit.whatthefilm.R;

import java.util.Arrays;
import java.util.List;

/**
 * Sous classe de {@link ArrayAdapter} permettant d'afficher des {@link Movie}
 * dans une {@link ListView} avec la {@link View} créée spécialement pour
 */
public class MovieAdapter extends ArrayAdapter<Movie> {
	/**
	 * L'activité contenant la {@link ListView} et servant de {@link Context}
	 */
    private Activity context;

	/**
	 * Référence vers les différentes {@link View} utilisées afin d'éviter d'appeler {@link View#findViewById(int)}
	 */
    private static class ViewHolder {
        public TextView nameHolder;
	    public TextView categoryHolder;
	    public ImageView imageHolder;
	    public RatingBar ratingHolder;
    }

	/**
	 * Instancie un nouveau {@link MovieAdapter}
	 * @param context l'{@link Activity} servant de {@link Context}
	 * @param items la {@link List} de {@link Movie} à traiter
	 */
	public MovieAdapter(Activity context, List<Movie> items) {
        super(context, R.layout.list_view, items);
        this.context = context;
    }

	/**
	 * Retourne une {@link View} affichant les informations du {@link Movie} pointé par le paramètre <code>position</code>
	 * @param position l'indice du {@link Movie} à afficher
	 * @param convertView une ancienne instance de {@link View} recyclée ou null si aucune instance n'est disponible
	 * @param parent le parent de la {@link View} à instancier
	 * @return la {@link View} nouvellement instanciée ou recyclée et affichant les données du {@link Movie} désiré
	 */
	public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null) {
            convertView = context.getLayoutInflater().inflate(R.layout.list_view, null);
            viewHolder = new ViewHolder();

	        viewHolder.nameHolder = (TextView) convertView.findViewById(R.id.filmName);
	        viewHolder.categoryHolder = (TextView) convertView.findViewById(R.id.filmCategory);
	        viewHolder.imageHolder = (ImageView) convertView.findViewById(R.id.filmImage);
	        viewHolder.ratingHolder = (RatingBar) convertView.findViewById(R.id.filmRating);

	        convertView.setTag(viewHolder);
        }
        else
        	viewHolder = (ViewHolder)convertView.getTag();

	    viewHolder.nameHolder.setText(getItem(position).getTitle());
	    viewHolder.categoryHolder.setText(Arrays.toString(getItem(position).getCategories()).replace("[", "").replace("]", ""));
		//TODO récupérer l'image et l'afficher
//	    viewHolder.imageHolder.setImageURI();
	    viewHolder.ratingHolder.setRating(getItem(position).getRating());

        return convertView;
    }
}
